package com.ee.shopingcart

import com.ee.shopingcart.model.Product

import scala.collection.mutable.Map

class Cart(taxRate: Double = 0.00) {

  private val products = Map[Product, Int]()

  def size: Int = {
    products.foldLeft(0) {
      case (accSize, (_, quantity)) => accSize + quantity
    }
  }

  def add(product: Product, quantity: Int = 1): Unit = {
    products.get(product) match {
      case Some(currentQuantity) => products.put(product, currentQuantity + quantity)
      case None => products.put(product, quantity)
    }
  }

  def isEmpty: Boolean = products.isEmpty

  def totalTax: Double = roundPrice(totalPriceWithoutTax * taxRate / 100)

  def totalPrice: Double = totalPriceWithoutTax + totalTax

  private def totalPriceWithoutTax: Double = {
    val totalPrice = products.foldLeft(0.0) {
      case (accTotal, (product, quantity)) => accTotal + (product.price * quantity)
    }
    roundPrice(totalPrice)
  }

  private def roundPrice(totalPrice: Double): Double = {
    BigDecimal(totalPrice).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }
}
