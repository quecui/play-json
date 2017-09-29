package controllers

import javax.inject.{Inject, Singleton}

import com.github.tototoshi.play2.json4s.native.Json4s
import dtos.UserDTO
import org.json4s._
import play.api.http.DefaultHttpFilters
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import play.filters.cors.CORSFilter

@Singleton
class Json4sController @Inject()(cc: ControllerComponents, json4s: Json4s) extends AbstractController(cc) {

  import json4s.implicits._
  implicit val formats = DefaultFormats

  def getUser = Action{ implicit request: Request[AnyContent] =>
    Ok(Extraction.decompose(new UserDTO()))
  }

  def postUser = Action(json4s.json){ implicit request: Request[JValue] =>
    Ok(request.body.extract[UserDTO].name)
  }
  //czxczxczxc
}