package controllers

import javax.inject._
import play.api.libs.functional.syntax._
import dtos.{UserDTO}
import play.api._
import play.api.mvc._
import play.api.libs.json._


@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  /*
   * Json Play
   */

  implicit val userWrites = new Writes[UserDTO]{
    override def writes(user: UserDTO): JsValue = Json.obj(
      "name" -> user.name,
      "age"  -> user.age
    )
  }

  implicit val userReads: Reads[UserDTO] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "age").read[Int]
  )(UserDTO.apply _)

  def parseJson() = Action{

    println("Json Data: " + (getJson))
    Ok(getJson)
  }

  def parseObject = Action{
    val json = getJson
    val user: JsResult[UserDTO] = json.validate[UserDTO]

    user match {
      case s: JsSuccess[UserDTO] => Ok(s.get.name)
      case e: JsError => Ok("Error")
    }
  }

  def getJson: JsValue = {
    val user = new UserDTO()
    Json.toJson(user)
  }

  def parseFromRequest = Action{request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    val user = jsonBody.get.validate[UserDTO]

    user.fold(
      errors => {
        BadRequest(Json.obj("Status" -> "KO"))
      },
      userDTO => {

        Ok(Json.obj("status" -> "Ok", "message" -> ("Username: " + userDTO.name)))
      }
    )
  }
}
