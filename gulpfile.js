var gulp        = require('gulp');
var sass        = require('gulp-sass');
var browserify = require('gulp-browserify');
var rename = require('gulp-rename');

var gulpif = require('gulp-if');

var browserSync = require('browser-sync');
var sourcemaps = require('gulp-sourcemaps');
var minifyCss = require('gulp-minify-css');

// Registering a 'less' task that just compile our LESS files to CSS



gulp.task('bundle', function() {
  gulp.src('js/app.jsx')
      .pipe(browserify({
        transform: ['mithrilify']
      }))
      .pipe(rename('bundle.js'))
      .pipe(gulp.dest('public/javascripts/'))
});


gulp.task('sass', function() {
  gulp.src('./resources/sass/main.scss')
      .pipe(sourcemaps.init())
      .pipe(sass({
        errLogToConsole: true
      }))
      .pipe(sourcemaps.write())
      .pipe(minifyCss({compatibility: 'ie8'}))
      .pipe(gulp.dest('./public/stylesheets'));
});

gulp.task('watch', ['sass'], function () {
  gulp.watch('./resources/sass/{,*/}*.{scss,sass}', ['sass'])
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

gulp.task('watchjsx', ['bundle'], function () {
  gulp.watch('js/*.jsx', ['bundle'])
});
// Creating the default gulp task
gulp.task('default', [  'watchjsx', 'serve']);
