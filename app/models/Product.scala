package models

import play.api.libs.json.{Json, OFormat}
import scalikejdbc.WrappedResultSet
import skinny.orm.{Alias, SkinnyCRUDMapper}

case class Product(id: Int, name: String, price: Int, status: Boolean)

object Product extends SkinnyCRUDMapper[Product]{
  implicit val format: OFormat[Product] = Json.format[Product]

  override def defaultAlias: Alias[Product] = createAlias("p")

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Product]): Product = new Product(
    id = rs.get(n.id),
    name = rs.get(n.name),
    price = rs.get(n.price),
    status = rs.get(n.status)
  )
}