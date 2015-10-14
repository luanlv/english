var gulp        = require('gulp');
var sass        = require('gulp-sass');
var browserify = require('gulp-browserify');
var rename = require('gulp-rename');

var gulpif = require('gulp-if');

var browserSync = require('browser-sync');
var sourcemaps = require('gulp-sourcemaps');
var minifyCss = require('gulp-minify-css');

// Registering a 'less' task that just compile our LESS files to CSS



gulp.task('app', function() {
  gulp.src('js/app/_main.msx')
      .pipe(browserify({
        transform: ['mithrilify']
      }))
      .pipe(rename('app2.js'))
      .pipe(gulp.dest('public/javascripts/'))
});


gulp.task('ws', function(){
  gulp.src('js/ws/_main.js')
      .pipe(browserify({
        transform: ['mithrilify']
      }))
      .pipe(rename('ws2.js'))
      .pipe(gulp.dest('public/javascripts/'))
});

//
gulp.task('serve', function () {
  browserSync({
    // By default, Play is listening on port 9000
    proxy: 'localhost:9000',
    // We will set BrowserSync on the port 9001
    port: 9001,
    // Reload all assets
    // Important: you need to specify the path on your source code
    // not the path on the url
    files: ['public/stylesheets/*.css',
      'public/javascripts/*.js',
      'app/views/{,*/}*.html',
      'app/views/{,*/}*.stream',
      'app/controllers/{,*/}*.scala',
      'conf/routes'],
    open: false
  });
});

gulp.task('watchjsx', ['app'], function () {
  gulp.watch('js/{,*/}*.msx', ['app']);
  gulp.watch('js/{,*/}*.js', ['app']);
});

// Creating the default gulp task
gulp.task('default', [  'app', 'watchjsx', 'serve']);
