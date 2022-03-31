package com.mobilist.fcommercecompose.services.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobilist.fcommercecompose.data.entity.informative.Chat
import com.mobilist.fcommercecompose.data.entity.informative.Comment
import com.mobilist.fcommercecompose.data.entity.informative.Like
import com.mobilist.fcommercecompose.data.entity.informative.Score
import com.mobilist.fcommercecompose.data.entity.informative.images_entity.ChatImage
import com.mobilist.fcommercecompose.data.entity.informative.images_entity.CommentImage
import com.mobilist.fcommercecompose.data.entity.product.Brand
import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.data.entity.product.Product
import com.mobilist.fcommercecompose.data.entity.product.ProductImage
import com.mobilist.fcommercecompose.data.entity.product.ProductPrice
import com.mobilist.fcommercecompose.data.entity.sales.Cargo
import com.mobilist.fcommercecompose.data.entity.sales.Cart
import com.mobilist.fcommercecompose.data.entity.shopping_list.Order
import com.mobilist.fcommercecompose.data.entity.shopping_list.OrderStatus
import com.mobilist.fcommercecompose.data.entity.user.*
import com.mobilist.fcommercecompose.services.room.category_api.CategoryDao
import com.mobilist.fcommercecompose.services.room.comment_api.CommentDao
import com.mobilist.fcommercecompose.services.room.favorite_api.LikeDao
import com.mobilist.fcommercecompose.services.room.order_api.OrderDao
import com.mobilist.fcommercecompose.services.room.product_api.ProductDao
import com.mobilist.fcommercecompose.services.room.user_api.UserDao

@Database(
    entities = [
        User::class, Address::class, AddressType::class, Role::class, Card::class,
        Cargo::class, Cart::class,
        Brand::class, Category::class, Product::class, ProductImage::class, ProductPrice::class,
        Chat::class, Comment::class, Like::class, Score::class, ChatImage::class, CommentImage::class, Order::class, OrderStatus::class
    ],
    version = 5
)
abstract class FCDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
    abstract fun categoryDao(): CategoryDao
    abstract fun commentDao(): CommentDao
    abstract fun likeDao(): LikeDao
}
