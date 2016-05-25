@REM english launcher script
@REM
@REM Environment:
@REM JAVA_HOME - location of a JDK home dir (optional if java on path)
@REM CFG_OPTS  - JVM options (optional)
@REM Configuration:
@REM ENGLISH_config.txt found in the ENGLISH_HOME.
@setlocal enabledelayedexpansion

@echo off

if "%ENGLISH_HOME%"=="" set "ENGLISH_HOME=%~dp0\\.."

set "APP_LIB_DIR=%ENGLISH_HOME%\lib\"

rem Detect if we were double clicked, although theoretically A user could
rem manually run cmd /c
for %%x in (!cmdcmdline!) do if %%~x==/c set DOUBLECLICKED=1

rem FIRST we load the config file of extra options.
set "CFG_FILE=%ENGLISH_HOME%\ENGLISH_config.txt"
set CFG_OPTS=
if exist %CFG_FILE% (
  FOR /F "tokens=* eol=# usebackq delims=" %%i IN ("%CFG_FILE%") DO (
    set DO_NOT_REUSE_ME=%%i
    rem ZOMG (Part #2) WE use !! here to delay the expansion of
    rem CFG_OPTS, otherwise it remains "" for this loop.
    set CFG_OPTS=!CFG_OPTS! !DO_NOT_REUSE_ME!
  )
)

rem We use the value of the JAVACMD environment variable if defined
set _JAVACMD=%JAVACMD%

if "%_JAVACMD%"=="" (
  if not "%JAVA_HOME%"=="" (
    if exist "%JAVA_HOME%\bin\java.exe" set "_JAVACMD=%JAVA_HOME%\bin\java.exe"
  )
)

if "%_JAVACMD%"=="" set _JAVACMD=java

rem Detect if this java is ok to use.
for /F %%j in ('"%_JAVACMD%" -version  2^>^&1') do (
  if %%~j==java set JAVAINSTALLED=1
  if %%~j==openjdk set JAVAINSTALLED=1
)

rem BAT has no logical or, so we do it OLD SCHOOL! Oppan Redmond Style
set JAVAOK=true
if not defined JAVAINSTALLED set JAVAOK=false

if "%JAVAOK%"=="false" (
  echo.
  echo A Java JDK is not installed or can't be found.
  if not "%JAVA_HOME%"=="" (
    echo JAVA_HOME = "%JAVA_HOME%"
  )
  echo.
  echo Please go to
  echo   http://www.oracle.com/technetwork/java/javase/downloads/index.html
  echo and download a valid Java JDK and install before running english.
  echo.
  echo If you think this message is in error, please check
  echo your environment variables to see if "java.exe" and "javac.exe" are
  echo available via JAVA_HOME or PATH.
  echo.
  if defined DOUBLECLICKED pause
  exit /B 1
)


rem We use the value of the JAVA_OPTS environment variable if defined, rather than the config.
set _JAVA_OPTS=%JAVA_OPTS%
if "!_JAVA_OPTS!"=="" set _JAVA_OPTS=!CFG_OPTS!

rem We keep in _JAVA_PARAMS all -J-prefixed and -D-prefixed arguments
rem "-J" is stripped, "-D" is left as is, and everything is appended to JAVA_OPTS
set _JAVA_PARAMS=
set _APP_ARGS=

:param_loop
call set _PARAM1=%%1
set "_TEST_PARAM=%~1"

if ["!_PARAM1!"]==[""] goto param_afterloop


rem ignore arguments that do not start with '-'
if "%_TEST_PARAM:~0,1%"=="-" goto param_java_check
set _APP_ARGS=!_APP_ARGS! !_PARAM1!
shift
goto param_loop

:param_java_check
if "!_TEST_PARAM:~0,2!"=="-J" (
  rem strip -J prefix
  set _JAVA_PARAMS=!_JAVA_PARAMS! !_TEST_PARAM:~2!
  shift
  goto param_loop
)

if "!_TEST_PARAM:~0,2!"=="-D" (
  rem test if this was double-quoted property "-Dprop=42"
  for /F "delims== tokens=1,*" %%G in ("!_TEST_PARAM!") DO (
    if not ["%%H"] == [""] (
      set _JAVA_PARAMS=!_JAVA_PARAMS! !_PARAM1!
    ) else if [%2] neq [] (
      rem it was a normal property: -Dprop=42 or -Drop="42"
      call set _PARAM1=%%1=%%2
      set _JAVA_PARAMS=!_JAVA_PARAMS! !_PARAM1!
      shift
    )
  )
) else (
  if "!_TEST_PARAM!"=="-main" (
    call set CUSTOM_MAIN_CLASS=%%2
    shift
  ) else (
    set _APP_ARGS=!_APP_ARGS! !_PARAM1!
  )
)
shift
goto param_loop
:param_afterloop

set _JAVA_OPTS=!_JAVA_OPTS! !_JAVA_PARAMS!
:run
 
set "APP_CLASSPATH=%APP_LIB_DIR%\english.english-0.1-SNAPSHOT.jar;%APP_LIB_DIR%\org.lichess.api-2.0.jar;%APP_LIB_DIR%\org.lichess.common-2.0.jar;%APP_LIB_DIR%\org.lichess.db-2.0.jar;%APP_LIB_DIR%\org.lichess.hub-2.0.jar;%APP_LIB_DIR%\org.lichess.memo-2.0.jar;%APP_LIB_DIR%\org.lichess.user-2.0.jar;%APP_LIB_DIR%\org.lichess.image-2.0.jar;%APP_LIB_DIR%\org.lichess.security-2.0.jar;%APP_LIB_DIR%\org.lichess.i18n-2.0.jar;%APP_LIB_DIR%\org.lichess.notification-2.0.jar;%APP_LIB_DIR%\org.lichess.pref-2.0.jar;%APP_LIB_DIR%\org.lichess.game-2.0.jar;%APP_LIB_DIR%\org.lichess.socket-2.0.jar;%APP_LIB_DIR%\org.lichess.monitor-2.0.jar;%APP_LIB_DIR%\org.lichess.site-2.0.jar;%APP_LIB_DIR%\org.lichess.usermessage-2.0.jar;%APP_LIB_DIR%\org.lichess.chatroom-2.0.jar;%APP_LIB_DIR%\org.lichess.relation-2.0.jar;%APP_LIB_DIR%\org.lichess.activity-2.0.jar;%APP_LIB_DIR%\org.lichess.question-2.0.jar;%APP_LIB_DIR%\com.github.ornicar.scalalib_2.11-5.4.jar;%APP_LIB_DIR%\com.typesafe.play.play-ws_2.11-2.4.6.jar;%APP_LIB_DIR%\io.spray.spray-caching_2.11-1.3.3.jar;%APP_LIB_DIR%\com.typesafe.play.play-netty-server_2.11-2.4.6.jar;%APP_LIB_DIR%\com.roundeights.hasher_2.11-1.2.0.jar;%APP_LIB_DIR%\org.eclipse.jgit.org.eclipse.jgit-3.2.0.201312181205-r.jar;%APP_LIB_DIR%\org.reactivemongo.play2-reactivemongo_2.11-0.11.9.jar;%APP_LIB_DIR%\com.sanoma.cda.maxmind-geoip2-scala_2.11-1.2.3-THIB.jar;%APP_LIB_DIR%\com.sksamuel.scrimage.scrimage-io_2.11-2.1.0.M2.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-codec-1.8.jar;%APP_LIB_DIR%\com.ybrikman.ping.big-pipe_2.11-0.0.12.jar;%APP_LIB_DIR%\io.spray.spray-util_2.11-1.3.3.jar;%APP_LIB_DIR%\org.specs2.specs2-core_2.11-3.0.1.jar;%APP_LIB_DIR%\oauth.signpost.signpost-commonshttp4-1.2.1.2.jar;%APP_LIB_DIR%\com.ning.async-http-client-1.9.21.jar;%APP_LIB_DIR%\com.googlecode.concurrentlinkedhashmap.concurrentlinkedhashmap-lru-1.4.2.jar;%APP_LIB_DIR%\com.typesafe.play.play-server_2.11-2.4.6.jar;%APP_LIB_DIR%\com.typesafe.netty.netty-http-pipelining-1.1.4.jar;%APP_LIB_DIR%\com.googlecode.javaewah.JavaEWAH-0.5.6.jar;%APP_LIB_DIR%\com.jcraft.jsch-0.1.46.jar;%APP_LIB_DIR%\org.reactivemongo.reactivemongo_2.11-0.11.9.jar;%APP_LIB_DIR%\org.apache.logging.log4j.log4j-to-slf4j-2.0.2.jar;%APP_LIB_DIR%\org.reactivemongo.reactivemongo-play-json_2.11-0.11.9-1.jar;%APP_LIB_DIR%\com.maxmind.geoip2.geoip2-0.8.0.jar;%APP_LIB_DIR%\com.sksamuel.scrimage.scrimage-core_2.11-2.1.0.M2.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-thumbsdb-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-tga-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-tiff-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-sgi-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-psd-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-pnm-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-pdf-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-pict-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-pcx-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-iff-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-icns-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-bmp-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-batik-3.1.1.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-transcoder-1.8.jar;%APP_LIB_DIR%\com.typesafe.play.play_2.11-2.4.6.jar;%APP_LIB_DIR%\com.chuusai.shapeless_2.11-2.0.0.jar;%APP_LIB_DIR%\org.specs2.specs2-matcher_2.11-3.0.1.jar;%APP_LIB_DIR%\oauth.signpost.signpost-core-1.2.1.2.jar;%APP_LIB_DIR%\io.netty.netty-3.10.4.Final.jar;%APP_LIB_DIR%\org.apache.logging.log4j.log4j-core-2.0.2.jar;%APP_LIB_DIR%\org.apache.logging.log4j.log4j-api-2.0.2.jar;%APP_LIB_DIR%\org.reactivemongo.reactivemongo-bson-macros_2.11-0.11.9.jar;%APP_LIB_DIR%\com.google.http-client.google-http-client-1.18.0-rc.jar;%APP_LIB_DIR%\com.maxmind.db.maxmind-db-0.3.3.jar;%APP_LIB_DIR%\ar.com.hjg.pngj-2.1.0.jar;%APP_LIB_DIR%\commons-io.commons-io-2.4.jar;%APP_LIB_DIR%\com.drewnoakes.metadata-extractor-2.8.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-jpeg-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-core-3.1.1.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-svggen-1.8.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-bridge-1.8.jar;%APP_LIB_DIR%\com.google.code.findbugs.jsr305-3.0.1.jar;%APP_LIB_DIR%\org.apache.httpcomponents.httpclient-4.0.1.jar;%APP_LIB_DIR%\com.google.inject.extensions.guice-assistedinject-4.0.jar;%APP_LIB_DIR%\javax.transaction.jta-1.1.jar;%APP_LIB_DIR%\xerces.xercesImpl-2.11.0.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-slf4j_2.11-2.4.3.jar;%APP_LIB_DIR%\ch.qos.logback.logback-classic-1.1.3.jar;%APP_LIB_DIR%\com.typesafe.play.twirl-api_2.11-1.1.1.jar;%APP_LIB_DIR%\com.typesafe.play.play-netty-utils-2.4.6.jar;%APP_LIB_DIR%\com.typesafe.play.play-json_2.11-2.4.6.jar;%APP_LIB_DIR%\com.typesafe.play.build-link-2.4.6.jar;%APP_LIB_DIR%\org.specs2.specs2-common_2.11-3.0.1.jar;%APP_LIB_DIR%\org.reactivemongo.reactivemongo-bson_2.11-0.11.9.jar;%APP_LIB_DIR%\org.scala-lang.scala-compiler-2.11.7.jar;%APP_LIB_DIR%\com.adobe.xmp.xmpcore-5.1.2.jar;%APP_LIB_DIR%\com.twelvemonkeys.imageio.imageio-metadata-3.1.1.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-script-1.8.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-gvt-1.8.jar;%APP_LIB_DIR%\joda-time.joda-time-2.9.2.jar;%APP_LIB_DIR%\org.apache.httpcomponents.httpcore-4.0.1.jar;%APP_LIB_DIR%\commons-logging.commons-logging-1.1.1.jar;%APP_LIB_DIR%\com.google.inject.guice-4.0.jar;%APP_LIB_DIR%\commons-codec.commons-codec-1.10.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-actor_2.11-2.4.3.jar;%APP_LIB_DIR%\ch.qos.logback.logback-core-1.1.3.jar;%APP_LIB_DIR%\org.apache.commons.commons-lang3-3.4.jar;%APP_LIB_DIR%\org.slf4j.jcl-over-slf4j-1.7.12.jar;%APP_LIB_DIR%\org.slf4j.jul-to-slf4j-1.7.12.jar;%APP_LIB_DIR%\com.fasterxml.jackson.datatype.jackson-datatype-jsr310-2.5.4.jar;%APP_LIB_DIR%\com.fasterxml.jackson.datatype.jackson-datatype-jdk8-2.5.4.jar;%APP_LIB_DIR%\org.joda.joda-convert-1.7.jar;%APP_LIB_DIR%\com.typesafe.play.play-datacommons_2.11-2.4.6.jar;%APP_LIB_DIR%\com.typesafe.play.play-functional_2.11-2.4.6.jar;%APP_LIB_DIR%\com.typesafe.play.play-iteratees_2.11-2.4.6.jar;%APP_LIB_DIR%\org.javassist.javassist-3.19.0-GA.jar;%APP_LIB_DIR%\com.typesafe.play.play-exceptions-2.4.6.jar;%APP_LIB_DIR%\org.scalaz.stream.scalaz-stream_2.11-0.6a.jar;%APP_LIB_DIR%\com.twelvemonkeys.common.common-image-3.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.common.common-lang-3.1.1.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-anim-1.8.jar;%APP_LIB_DIR%\org.scala-lang.modules.scala-java8-compat_2.11-0.7.0.jar;%APP_LIB_DIR%\com.google.guava.guava-19.0.jar;%APP_LIB_DIR%\aopalliance.aopalliance-1.0.jar;%APP_LIB_DIR%\javax.inject.javax.inject-1.jar;%APP_LIB_DIR%\org.slf4j.slf4j-api-1.7.16.jar;%APP_LIB_DIR%\com.fasterxml.jackson.core.jackson-databind-2.5.4.jar;%APP_LIB_DIR%\com.typesafe.config-1.3.0.jar;%APP_LIB_DIR%\org.scala-stm.scala-stm_2.11-0.7.jar;%APP_LIB_DIR%\org.typelevel.scodec-bits_2.11-1.0.4.jar;%APP_LIB_DIR%\org.scalaz.scalaz-concurrent_2.11-7.1.1.jar;%APP_LIB_DIR%\com.twelvemonkeys.common.common-io-3.1.1.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-svg-dom-1.8.jar;%APP_LIB_DIR%\org.scala-lang.scala-reflect-2.11.7.jar;%APP_LIB_DIR%\com.fasterxml.jackson.core.jackson-annotations-2.5.4.jar;%APP_LIB_DIR%\com.fasterxml.jackson.core.jackson-core-2.5.4.jar;%APP_LIB_DIR%\org.scalaz.scalaz-effect_2.11-7.1.1.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-parser-1.8.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-dom-1.8.jar;%APP_LIB_DIR%\org.scalaz.scalaz-core_2.11-7.1.7.jar;%APP_LIB_DIR%\xalan.xalan-2.7.0.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-xml-1.8.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-css-1.8.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-awt-util-1.8.jar;%APP_LIB_DIR%\org.scala-lang.modules.scala-xml_2.11-1.0.5.jar;%APP_LIB_DIR%\org.scala-lang.modules.scala-parser-combinators_2.11-1.0.4.jar;%APP_LIB_DIR%\xml-apis.xml-apis-ext-1.3.04.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-ext-1.8.jar;%APP_LIB_DIR%\org.apache.xmlgraphics.batik-util-1.8.jar;%APP_LIB_DIR%\org.scala-lang.scala-library-2.11.8.jar;%APP_LIB_DIR%\xml-apis.xml-apis-1.4.01.jar;%APP_LIB_DIR%\english.english-0.1-SNAPSHOT-assets.jar"
set "APP_MAIN_CLASS=play.core.server.ProdServerStart"

if defined CUSTOM_MAIN_CLASS (
    set MAIN_CLASS=!CUSTOM_MAIN_CLASS!
) else (
    set MAIN_CLASS=!APP_MAIN_CLASS!
)

rem Call the application and pass all arguments unchanged.
"%_JAVACMD%" !_JAVA_OPTS! !ENGLISH_OPTS! -cp "%APP_CLASSPATH%" %MAIN_CLASS% !_APP_ARGS!

@endlocal


:end

exit /B %ERRORLEVEL%
