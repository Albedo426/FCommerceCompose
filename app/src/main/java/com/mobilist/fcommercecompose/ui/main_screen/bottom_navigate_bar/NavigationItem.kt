package com.mobilist.fcommercecompose.ui.main_screen.bottom_navigate_bar

import com.mobilist.fcommercecompose.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("Anasayfa", R.drawable.ic_baseline_home_24, "Anasayfa")
    object Favorite : NavigationItem("Favoriler", R.drawable.ic_baseline_favorite_24, "Favoriler")
    object Shopping : NavigationItem("Sepetim", R.drawable.ic_baseline_shopping_cart_24, "Sepetim")
    object Category : NavigationItem("Kategoriler", R.drawable.ic_baseline_category_24, "Kategoriler")
    object Account : NavigationItem("Hesabım", R.drawable.ic_baseline_account_circle_24, "Hesabım")
}
