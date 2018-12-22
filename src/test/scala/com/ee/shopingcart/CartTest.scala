package com.ee.shopingcart

import com.ee.shopingcart.model.Product
import org.scalatest.FreeSpec

class CartTest extends FreeSpec {

  private val doveSoap = Product("Dove Soap", 39.99)

  trait Fixture {
    val cart = new Cart()
  }

  "shopping cart" - {

    "should be empty when its initialized" in new Fixture {
      assert(cart.isEmpty)
      assert(cart.totalPrice == 0.00)
    }

    "should have one product when a product is added" in new Fixture {
      cart.add(doveSoap)
      assert(cart.size == 1)
      assert(!cart.isEmpty)
    }

    "should return total price of products when five products are added to cart" in new Fixture {
      cart.add(doveSoap, 5)
      assert(cart.size == 5)
      assert(!cart.isEmpty)
      assert(cart.totalPrice == 199.95)
    }

    "should have eight dove soaps when we add five dove soaps and then three dove soaps to cart" in new Fixture {
      cart.add(doveSoap, 5)
      assert(cart.size == 5)
      assert(!cart.isEmpty)
      cart.add(doveSoap, 3)
      assert(cart.size == 8)
      assert(cart.totalPrice == 319.92)
    }

    "should have total four products when we add two dove soaps and then two axe deo to cart" in new Fixture {
      private val axeDeo = Product("Axe Deo", 99.99)
      cart.add(doveSoap, 2)
      assert(cart.size == 2)
      assert(!cart.isEmpty)
      cart.add(axeDeo, 2)
      assert(cart.size == 4)
      assert(cart.totalPrice == 279.96)
    }

    "should return total tax amount as zero when cart is empty" in {
      val cart = new Cart(taxRate = 12.5)
      assert(cart.size == 0)
      assert(cart.isEmpty)
      assert(cart.totalTax == 0.00)
    }

    "should return total tax amount when two products are added to cart" in {
      val cart = new Cart(taxRate = 12.5)
      cart.add(doveSoap, 2)
      assert(cart.size == 2)
      assert(!cart.isEmpty)
      assert(cart.totalTax == 10.00)
      assert(cart.totalPrice == 89.98)
    }

    "should have total four products and calculate total tax and total price when we add two dove soaps and then two axe deo to cart" in {
      val cart = new Cart(taxRate = 12.5)
      val axeDeo = Product("Axe Deo", 99.99)
      cart.add(doveSoap, 2)
      assert(cart.size == 2)
      assert(!cart.isEmpty)
      cart.add(axeDeo, 2)
      assert(cart.size == 4)
      assert(cart.totalTax == 35)
      assert(cart.totalPrice == 314.96)
    }
  }
}
