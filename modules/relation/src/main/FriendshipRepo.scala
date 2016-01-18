package lila.relation

import play.api.libs.json._

import lila.common.PimpedJson._
import lila.db.api._
import lila.db.Implicits._
import tube.friendshipTube

private[relation] object FriendshipRepo {

  def friendship(id: ID): Fu[Option[Relation]] =
    $primitive.one($select byId id, "r")(_.asOpt[Boolean])

  def friendship(u1: ID, u2: ID): Fu[Option[Relation]] = friendship(makeId(u1, u2))

  def friends(userId: ID):Fu[Set[ID]] = friends(userId, Friend)

  private def friends(userId: ID, relation: Relation): Fu[Set[ID]] =
    $projection(Json.obj("u2" -> userId), Seq("u1", "r")) { obj =>
      obj str "u1" map { _ -> ~(obj boolean "r") }
    } map (_.filter(_._2 == relation).map(_._1).toSet)

  def doFriend(u1: ID, u2: ID): Funit = save(u1, u2, Friend)
  def unfriend(u1: ID, u2: ID): Funit = remove(u1, u2)

  private def save(u1: ID, u2: ID, relation: Relation): Funit = $save(
    makeId(u1, u2),
    Json.obj("u1" -> u1, "u2" -> u2, "r" -> relation)
  )

  def remove(u1: ID, u2: ID): Funit = $remove byId makeId(u1, u2)

  def drop(userId: ID, relation: Relation, nb: Int) =
    $primitive(
      Json.obj("u1" -> userId, "r" -> relation),
      "_id",
      _ sort $sort.naturalAsc,
      max = nb.some,
      hint = reactivemongo.bson.BSONDocument("u1" -> 1)
    )(_.asOpt[String]) flatMap { ids =>
      $remove(Json.obj("_id" -> $in(ids)))
    }

  private def makeId(u1: String, u2: String) = u1 + "/" + u2
}
