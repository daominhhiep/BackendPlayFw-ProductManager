package controllers

import models.Product
import play.api.libs.json.JsValue

import javax.inject.Inject
import play.api.libs.json.Json._
import play.api.mvc.{Action, AnyContent, InjectedController}
import services.ProductService

class ProductStoreController @Inject()(productService: ProductService) extends InjectedController {
  def getProducts: Action[AnyContent] = Action {
    val products = productService.getProducts()
    Ok(toJson(products))
  }

  def getProduct(id: Int): Action[AnyContent] = Action {
    val product = productService.getProductById(id)
    Ok(toJson(product))
  }

  def addProduct(): Action[JsValue] = Action(parse.json) { implicit request =>
    val newProduct = request.body.as[Product]
    val record = productService.addProduct(newProduct)
    if (record > 0) Ok(toJson(newProduct)) else InternalServerError(toJson("error"))
  }

  def updateProduct(id: Int): Action[JsValue] = Action(parse.json) { implicit request =>
    val updateProduct = request.body.as[Product]
    val record = productService.updateProduct(id, updateProduct)
    if (record > 0) Ok(toJson("success")) else InternalServerError(toJson("error"))
  }

  def deleteProduct(id: Int): Action[AnyContent] = Action { implicit request =>
    val record = productService.deleteProduct(id)
    if (record > 0) Ok(toJson("success")) else InternalServerError(toJson("error"))
  }
}

