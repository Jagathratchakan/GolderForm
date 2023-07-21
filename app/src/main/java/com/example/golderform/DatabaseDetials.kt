package com.example.golderform

import android.widget.EditText

data class AskaDetials(
    var Date : String ?= null,
    var Vechile_Number: String? = null,
    var Number_of_Bags: String ? = null,
    var Company_Name : String ? = null
){

}

data class NattuSakkarai(
    var Date : String ?= null,
    var Vechile_Number: String? = null,
    var Number_of_Bags: String ? = null,
    var Company_Name : String ? = null
){

}

data class MattaiDetials(
    var Date : String ?= null,
    var Number_of_trips: String ? = null,
    var Company_Name : String ? = null
){

}

data class OtherDetials(
    var Date : String ?= null,
    var Company_Name : String ? = null,
    var No_of_Oil_Tin : String ?= null,
    var No_of_Soad_Bags : String ?= null,
    var No_of_Kuranai_Bags : String ?= null,
    var No_of_Sunambu_Bags : String ?= null,
    var Mattai_Powder_Kg : String ?= null,
    var Hydros : String ?= null,
    var No_of_Workers : String ?= null,
    var Gender_of_Workers : String ?= null
){

}

data class Sugarcane(
    var Date : String ?= null,
    var Company_Name : String ? = null,
    var S_No : String ?= null,
    var Vechile_Number: String? = null,
    var Total_Ton : String ?= null,
    var Vettal : String ?= null,
    var Name_Of_Person : String ?= null,
){

}
data class User(
    var Name : String ?= null,
    var Email : String ?= null,
    var Password : String ?= null,
    var PIN : String ?= null,
    var Role : String ?= null
){

}

data class StockDetials(
    var JB : Number ?= null,
    var JC : Number ?= null,
    var MNS : Number ?= null,
    var NJB : Number ?= null,
    var NJC : Number ?= null,
    var NC20 : Number ?= null,
    var NC30 : Number ?= null,
    var ORGANIC : Number ?= null
){

}



data class DeliveryDetials(
    var Date: String ?= null,
    var person_delivery: String? = null,
    var Item: String ? = null,
    var Company_Name: String ? = null
){

}

data class AllaiRunning(
    var Date: String ?= null,
    var numofItem : String ?= null,
    var Item : String ? = null,
    var Company_Name : String ? = null
)
{

}