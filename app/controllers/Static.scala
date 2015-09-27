package controllers

import java.io.File
import play.api._
import play.api.mvc._

object StaticFile extends Controller {

  def html(path: String, file: String) = Action {
    var f = new File(path + "/" + file)
    if (f.exists())
      Ok(scala.io.Source.fromFile(f.getCanonicalPath()).mkString).as("css/text")
    else
      NotFound(path + "/" + file)
  }

}