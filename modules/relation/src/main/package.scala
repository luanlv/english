package lila

import lila.db.JsTube

package object relation extends PackageObject with WithPlay {

  type Relation = Boolean
  private[relation] val Follow: Relation = true
  private[relation] val Request: Relation = true
  private[relation] val Friend: Relation = true
  private[relation] val Block: Relation = false

  private[relation] type ID = String

  object tube {
    private[relation] implicit lazy val relationTube =
      JsTube.json inColl Env.current.relationColl

    private[relation] implicit lazy val makeFriendTube =
      JsTube.json inColl Env.current.makeFriendColl

    private[relation] implicit lazy val friendshipTube =
      JsTube.json inColl Env.current.friendshipColl
  }
}
