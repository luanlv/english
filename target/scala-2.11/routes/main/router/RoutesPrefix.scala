
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/ramdisk/mainapp/conf/routes
// @DATE:Thu May 26 05:20:56 ICT 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
