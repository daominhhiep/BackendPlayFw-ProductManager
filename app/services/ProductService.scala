package services

import models.Product
import scalikejdbc.{AutoSession, DB, DBSession}

class ProductService {

  def getProducts()(implicit session: DBSession = AutoSession): List[Product] = {
    Product.findAll()
  }

  def getProductById(id: Int): Option[Product] = DB readOnly { implicit session =>
    Product.findById(id)
  }

  def addProduct(product: Product)(implicit session: DBSession = AutoSession): Long = {
    Product.createWithAttributes(
      'name -> product.name,
      'price -> product.price,
      'status -> product.status
    )
  }

  def updateProduct(id: Int, product: Product)(implicit session: DBSession = AutoSession):Int = {
    Product.updateById(id).withAttributes(
      'name -> product.name,
      'price -> product.price,
      'status -> product.status
    )
  }

  def deleteProduct(id: Int)(implicit session: DBSession = AutoSession): Int = {
    Product.deleteById(id)
  }

}
