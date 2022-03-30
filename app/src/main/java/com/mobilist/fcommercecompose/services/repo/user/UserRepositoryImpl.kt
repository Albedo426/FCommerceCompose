package com.mobilist.fcommercecompose.services.repo.user

import com.mobilist.fcommercecompose.data.entity.informative.Comment
import com.mobilist.fcommercecompose.data.entity.informative.Like
import com.mobilist.fcommercecompose.data.entity.informative.Score
import com.mobilist.fcommercecompose.data.entity.product.*
import com.mobilist.fcommercecompose.data.entity.user.Address
import com.mobilist.fcommercecompose.data.entity.user.AddressType
import com.mobilist.fcommercecompose.data.entity.user.Role
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.data.entity.user.User
import com.mobilist.fcommercecompose.data.model.LoginModel
import com.mobilist.fcommercecompose.data.model.UserAddressModel
import com.mobilist.fcommercecompose.services.room.user_api.UserDao
import java.lang.Exception
import javax.inject.Inject

//usecase gibi ama farklı use case kullanılıcak apinin altında olmayacak // kalıtım alıcak interfaceyi
class UserRepositoryImpl  @Inject constructor(private val userDao : UserDao): UserRepository {

    override suspend fun insert(user: User): Resource<Long> {
        return try {
            Resource.Success(userDao.insert(user))
        }catch (e: Exception){
            Resource.Error("Kullanıcı eklenirken bir hata meydana geldi")
        }
    }
    override suspend fun insertAddress(address: Address): Resource<Long> {
        return try {
            Resource.Success(userDao.insertAddress(address))
        }catch (e: Exception){
            Resource.Error("insertAddress")
        }
    }

    override suspend fun deleteAddressById(Id: Int): Resource<Boolean> {
        return try {
            userDao.deleteAddressById(Id)
            Resource.Success(true)
        }catch (e: Exception){
            Resource.Error("deleteAddressById")
        }
    }
    override suspend fun getAddress(Id: Int):  Resource<List<UserAddressModel>> {
        //karar mercisi nereye gidicek nerden alıcak veriyi gibi
        return try {
            val resource=userDao.getUserAddress(Id)
            if(resource.isEmpty()){
                Resource.Error("Address Bulunamadı")
            }else {
                Resource.Success(resource)
            }
        }catch (e: Exception){
            println(e)
            Resource.Error(e.message!!)
        }
    }


    // değişicek çok karmaşık oldu
    override suspend fun updateUserNamePhoneById(Id:Int,name:String,phone:String) :Resource<Boolean>{
        val nameSplit=name.split(" ")
        if(nameSplit.size>=2){
            var namer=""
            for(i in nameSplit.indices){
                if(i==nameSplit.size-1){
                    break
                }
                namer+= "${nameSplit[i]} "
            }
            userDao.updateUserNamePhoneById(Id,namer,nameSplit[nameSplit.size-1],phone)
        }
        else{
            userDao.updateUserNamePhoneById(Id,nameSplit[0],"",phone)
        }
        return Resource.Success(true)
    }
    override suspend fun updateUserPasswordById(Id:Int,password:String) :Resource<Boolean>{
        userDao.updateUserPasswordById(Id,password)
        return Resource.Success(true)
    }
    override suspend fun updateUserMailById(Id:Int,mail:String) :Resource<Boolean>{
        userDao.updateUserMailById(Id,mail)
        return Resource.Success(true)
    }


    override suspend fun getUserById(Id: Int): Resource<User> {
        //karar mercisi nereye gidicek nerden alıcak veriyi gibi
        return try {
            val resource=userDao.getUserById(Id)
            if(resource.isEmpty()){
                Resource.Error("Kullanıcı Bulunamadı")
            }else {
                Resource.Success(resource[0])
            }
        }catch (e: Exception){
            println(e)
            Resource.Error(e.message!!)
        }
    }
    override suspend fun insertAll(vararg users: User):List<Long> {
        return userDao.insertAll(*users)
    }
    override suspend fun loginUser(user: LoginModel): Resource<User> {
        //karar mercisi nereye gidicek nerden alıcak veriyi gibi
        return try {
            val resource=userDao.loginUser(user.userEmail,user.userPassword)
            if(resource.isEmpty()){
                Resource.Error("Kullanıcı Bulunamadı")
            }else {
                Resource.Success(resource[0])
            }
        }catch (e: Exception){
            println(e)
            Resource.Error(e.message!!)
        }
    }
    override suspend fun getAllUser():List<User> {
        return userDao.allUser()
    }

    override suspend fun init() {
        userDao.insertRole(Role("Kullanici"))
        userDao.insert(
            User(
                "test Kullanıcı",
                "pass",
                "test@test.com",
                "testad",
                "testsoyad",
                "5644442424",
                "5545980194",
                1
            )
        )
        userDao.insert(
            User(
                "test Kullanıcı2",
                "pass2",
                "test2@test.com",
                "testad2",
                "testsoyad2",
                "5644442424",
                "5545980194",
                1
            )
        )
        userDao.insertCategory(Category("Bilgisayar", 0,0))// ama sil sonradan 0ı main başlık demek
        userDao.insertCategory(Category("Leoptop", 1,0))// ama sil sonradan 0ı main başlık demek
        userDao.insertCategory(Category("Monster", 0,2))// ama sil sonradan 0ı main başlık demek
        userDao.insertBrand(Brand("Monster"))
        userDao.insertProduct(
            Product(
                1,
                1,
                3,
                "Monster Tulpar T7 V21.8",
                "Monster Tulpar T7 V21.8 Intel Core I7 11800H 16GB 500GB SSD RTX3070 Freedos 17.3",
                "İşlemci Tipi:Intel Core i7|SSD Kapasitesi:500 GB|İşletim Sistemi:Free Dos|Ram (Sistem Belleği):16 GB|Garanti Tipi:Resmi Distribütör Garantili|Ekran Boyutu:17,3 inç|Çözünürlük:2560 x 1440|Kullanım Amacı:OyunCihaz Ağırlığı2 - 4 kg|Şarjlı Kullanım Süresi:3 Saat ve Altı|Ekran Kartı Tipi:Harici|Ekran Kartı Hafızası:8 GB|İşlemci Modeli:11800HHDMIVarKapasite500 GB|Dokunmatik Ekran:Yok|Panel Tipi:VA|Çözünürlük Standartı:Full HD (FHD)|Ekran Kartı:Nvidia GeForce RTX 3070|Optik Sürücü Tipi:Yok|İşlemci Nesli:11.Nesil|Bağlantılar:USB|Ekran Kartı Bellek Tipi:GDDR6|Temel İşlemci Hızı: (GHz)4 +|Klavye:Numerik Tuşlu Aydınlatmalı|Ekran Özelliği",
                "https://i4.hurimg.com/i/hurriyet/75/866x494/61024fe57af5071fe498df66.jpg",
                "10.10.2022",
                2)
        )
        userDao.insertProduct(
            Product(
                1,
                1,
                3,
                "Monster Abra A5 V16.7.1",
                "Monster Abra A5 V16.7.1 Intel Core I5 11400h 8gb 250gb Ssd Gtx1650 Freedos 15.6",
                "İşlemci Tipi:Intel Core i5|SSD Kapasitesi:250 GB|İşletim Sistemi:Free Dos|Ekran Kartı:Nvidia GeForce GTX1650|Ram (Sistem Belleği):8 GB|Garanti Tipi:Resmi Distribütör Garantili|Çözünürlük:1920 x 1080|Kullanım Amacı:Oyun|Cihaz Ağırlığı:2 - 4 kg|Şarjlı Kullanım Süresi:3 Saat ve Altı|Ekran Yenileme Hızı:144 Hz|Ekran Kartı Tipi:Harici|Ekran Kartı Hafızası:4 GB|İşlemci Modeli:11400H|Maksimum İşlemci Hızı (GHz):4.5|Kapasite:250 GB|Hard Disk Kapasitesi:Yok|Dokunmatik Ekran:Yok|Panel Tipi|IPS:Çözünürlük Standartı:Full HD (FHD)|Ram (Sistem Belleği) Tipi:DDR4|Ekran Boyutu:15,6 inç|Optik Sürücü Tipi:Yok|İşlemci Nesli:11.Nesil|Bağlantılar:USB|Ekran Kartı Bellek Tipi:GDDR6|Temel İşlemci Hızı (GHz):2.7|Klavye:RGB Türkçe|İşlemci Çekirdek Sayısı:6",
                "https://i.ytimg.com/vi/gbTkyj1uP9w/maxresdefault.jpg",
                "10.10.2022",
                2)
        )
        userDao.insertProduct(
            Product(
                1,
                1,
                3,
                "Monster Abra A5 V18.1.3",
                "Monster Abra A5 V18.1.3 Intel Core I7 11800h 16gb 500gb Ssd Rtx3050 Freedos 15.6",
                "Ürün Özellikleriİşlemci TipiIntel Core i7SSD Kapasitesi500 GBİşletim SistemiFree DosRam (Sistem Belleği)16 GBGaranti TipiResmi Distribütör GarantiliEkran Boyutu15,6 inçÇözünürlük1920 x 1080Kullanım AmacıOyunCihaz Ağırlığı2 - 4 kgŞarjlı Kullanım Süresi3 Saat ve AltıEkran Yenileme Hızı144 HzEkran Kartı TipiHariciEkran Kartı Hafızası4 GBİşlemci Modeli11800HMaksimum İşlemci Hızı (GHz)4.6KapasiteYokHard Disk KapasitesiYokDokunmatik EkranYokPanel TipiIPSÇözünürlük StandartıFull HD (FHD)Ram (Sistem Belleği) TipiDDR4Ekran KartıNvidia GeForce RTX3050Optik Sürücü TipiYokİşlemci Nesli11.NesilBağlantılarBluetoothEkran Kartı Bellek TipiGDDR6KlavyeQ Türkçeİşlemci Çekirdek Sayısı8",
                "https://www.ikincielbilgisayaralanyerler.com/wp-content/uploads/2018/10/monster_laptop_notebook_alan_yerler.jpg",
                "10.10.2022",
                2)
        )
        userDao.insertProduct(
            Product(
                1,
                1,
                3,
                "Monster Abra A5 V17.2",
                "Monster Abra A5 V17.2 Intel Core I5 11400H 8GB 500GB SSD RTX3050Ti Freedos 15.6",
                "İşlemci TipiIntel Core i7SSD Kapasitesi500 GBİşletim SistemiFree DosRam (Sistem Belleği)16 GBGaranti TipiResmi Distribütör GarantiliEkran Boyutu17,3 inçÇözünürlük2560 x 1440Kullanım AmacıOyunCihaz Ağırlığı2 - 4 kgŞarjlı Kullanım Süresi3 Saat ve AltıEkran Kartı TipiHariciEkran Kartı Hafızası8 GBİşlemci Modeli11800HHDMIVarKapasite500 GBDokunmatik EkranYokPanel TipiVAÇözünürlük StandartıFull HD (FHD)Ekran KartıNvidia GeForce RTX 3070Optik Sürücü TipiYokİşlemci Nesli11.NesilBağlantılarUSBEkran Kartı Bellek TipiGDDR6Temel İşlemci Hızı (GHz)4 +KlavyeNumerik Tuşlu AydınlatmalıEkran Özelliği",
                "https://i4.hurimg.com/i/hurriyet/75/866x494/61024fe57af5071fe498df66.jpg",
                "10.10.2012",
                2)
        )
        userDao.insertProduct(
            Product(
                1,
                1,
                3,
                "Monster Abra A7 V13.2",
                "Monster Abra A7 V13.2 Intel Core I5 11400h 8gb 500gb Ssd Rtx3050ti Freedos 17.3",
                "İşlemci TipiIntel Core i5SSD Kapasitesi500 GBİşletim SistemiFree DosRam (Sistem Belleği)8 GBGaranti TipiResmi Distribütör GarantiliEkran Boyutu17,3 inçÇözünürlük1920 x 1080Kullanım AmacıOyunCihaz Ağırlığı2 - 4 kgŞarjlı Kullanım Süresi3 Saat ve AltıEkran Kartı TipiHariciEkran Kartı Hafızası4 GBİşlemci Modeli11400HHDMIVarDokunmatik EkranYokPanel TipiIPSÇözünürlük StandartıFull HD (FHD)Ekran KartıNVIDIA GeForce RTX 3050TiOptik Sürücü TipiYokİşlemci Nesli11.NesilBağlantılarBluetoothEkran Kartı Bellek TipiGDDR6Temel İşlemci Hızı (GHz)4 +KlavyeNumerik Tuşlu AydınlatmalıEkran ÖzelliğiFull HDGörüntü KalitesiFull HDKapasite500 GB",
                "https://mediatrend.mediamarkt.com.tr/wp-content/uploads/2018/02/corsair-one-100707813-orig.jpg",
                "10.10.2021",
                2)
        )
        userDao.insertProduct(
            Product(
                1,
                1,
                3,
                "Monster Abra A7 V13.2.3",
                "Monster Abra A7 V13.2.3 Intel Core I5 11400h 16gb 500gb Ssd Rtx3050ti Freedos 17.3",
                "İşlemci TipiIntel Core i5SSD Kapasitesi500 GBİşletim SistemiFree DosRam (Sistem Belleği)16 GBGaranti TipiResmi Distribütör GarantiliEkran Boyutu17,3 inçÇözünürlük1920 x 1080Kullanım AmacıOyunCihaz Ağırlığı2 - 4 kgŞarjlı Kullanım Süresi3 Saat ve AltıEkran Yenileme Hızı144 HzEkran Kartı TipiHariciEkran Kartı Hafızası4 GBİşlemci Modeli11400HMaksimum İşlemci Hızı (GHz)4.5KapasiteYokHard Disk KapasitesiYokDokunmatik EkranYokPanel TipiIPSÇözünürlük StandartıFull HD (FHD)Ram (Sistem Belleği) TipiDDR4Ekran KartıNvidia GeForce RTX3050TiOptik Sürücü TipiYokİşlemci Nesli11.NesilBağlantılarBluetoothEkran Kartı Bellek TipiGDDR6KlavyeQ Türkçeİşlemci Çekirdek Sayısı6",
                "https://i4.hurimg.com/i/hurriyet/75/866x494/61024fe57af5071fe498df66.jpg",
                "10.10.2022",
                2)
        )
        userDao.insertProduct(
            Product(
                1,
                1,
                3,
                "Monster Tulpar T5 V20.3",
                "Monster Tulpar T5 V20.3 Intel Core I7 11800H 16GB 500GB SSD RTX3060 Freedos 15.6",
                "Monster Tulpar T5 V20.3 Monster Tulpar T5 V20.3 Intel Core I7 11800H 16GB 500GB SSD RTX3060 Freedos 15.6 FHDBu üründen en fazla 2 adet sipariş verilebilir. 2 adetin üzerindeki siparişleri Trendyol iptal etme hakkını saklı tutar.Kampanya fiyatından satılmak üzere 5 adetten fazla stok sunulmuştur.İncelemiş olduğunuz ürünün satış fiyatını satıcı belirlemektedir.Listelenen fiyat 12 Şubat 2022 tarihine kadar geçerlidir.",
                "https://i4.hurimg.com/i/hurriyet/75/866x494/61024fe57af5071fe498df66.jpg",
                "10.10.2022",
                2)
        )
        userDao.insertProductPrice(ProductPrice(5,0,"","",4523.32))
        userDao.insertProductPrice(ProductPrice(2,0,"","",6685.12))
        userDao.insertProductPrice(ProductPrice(3,0,"","",5896.56))
        userDao.insertProductPrice(ProductPrice(4,80,"02.09.2022","02.10.2023",14060.75))
        userDao.insertProductPrice(ProductPrice(1,50,"02.09.2022","02.10.2023",16789.42))
        userDao.insertProductPrice(ProductPrice(6,39,"02.09.2022","02.10.2023",19754.89))
        userDao.insertProductPrice(ProductPrice(7,40,"02.09.2022","02.10.2023",11405.75))
        userDao.insertProductImage(ProductImage("https://cdn.dsmcdn.com/mnresize/1200/1800/ty146/product/media/images/20210712/13/109731588/191207677/1/1_org_zoom.jpg",1))
        userDao.insertProductImage(ProductImage("https://www.ikincielbilgisayaralanyerler.com/wp-content/uploads/2018/10/monster_laptop_notebook_alan_yerler.jpg",1))
        userDao.insertProductImage(ProductImage("data:image/jpeg;base64,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",2))
        userDao.insertProductImage(ProductImage("data:image/jpeg;base64,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",2))
        userDao.insertProductImage(ProductImage("https://img-monsternotebook.mncdn.com/UPLOAD/urun-gorselleri-yeni/Aksesuarlar/CANTA/Kilif/thumb/3_medium.png",1))
        userDao.insertProductImage(ProductImage("https://www.maximum.com.tr/content-management/PublishingImages/campaigns/2020-kampanyalar/subat/Monster-Bilgisayar-9-Taksit-Banner-10869_280x280.jpg",2))
        userDao.insertProductImage(ProductImage("https://www.ikincielbilgisayaralanyerler.com/wp-content/uploads/2018/10/monster_laptop_notebook_alan_yerler.jpg",1))
        userDao.insertProductImage(ProductImage("https://www.ikincielbilgisayaralanyerler.com/wp-content/uploads/2018/10/monster_laptop_notebook_alan_yerler.jpg",1))
        userDao.insertLike(Like(1,2))
        userDao.insertLike(Like(2,1))
        userDao.insertLike(Like(2,2))
        userDao.insertLike(Like(1,1))

        userDao.insertComment(Comment("bence","Anne çantası olarak kullanıyorum 2 küçük çocuğum var kaliteli büyük hersey sığıyor","24 haziran 2022",1,1))
        userDao.insertComment(Comment("güzel","Erkek arkadaşıma hediye olarak aldım çok beğendi. Kalitesi ve duruşu çok güzel büyüklüğü de oldukça yeterl","12 mart 2022",2,1))
        userDao.insertComment(Comment("denenir","Boyutu spor için ideal fiyatına göre fena sayılmaz ama çok ince yırtılmaz umarım.","21 şubat 2019",1,2))
        userDao.insertComment(Comment("kötü be abi","Boyutu spor için ideal fiyatına göre fena sayılmaz ama çok ince yırtılmaz umarım.","15 ocak 2018",1,2))


        userDao.insertScore(Score(1,1,4))
        userDao.insertScore(Score(2,1,3))
        userDao.insertScore(Score(2,2,3))
        userDao.insertScore(Score(1,2,5))

        userDao.insertAddressType(AddressType(  "ev"))
        userDao.insertAddress(Address("Ev","Adress1","istanbul",1,"5545980194",1,36000,1))
        userDao.insertAddress(Address("Ev2","Adress2","istanbul",1,"5545980194",1,36000,1))
        userDao.insertAddress(Address("Ev2","Adress2","istanbul",1,"5545980194",1,36000,1))




    }
}