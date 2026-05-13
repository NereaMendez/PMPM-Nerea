package com.example.proyectofloristerianerea.navigation

object Routes {
    const val LOGIN = "login"
    const val MAIN = "main"
    const val HOME = "home"
    const val PRODUCTS = "products"
    const val CART = "cart"
    const val PRODUCT_DETAIL = "product_detail/{productId}"

    fun productDetail(productId: Long): String = "product_detail/$productId"
}