package com.cricbuzz.resturantsearch.viewmodels

import MenusModel
import android.content.Context
import androidx.lifecycle.ViewModel
import com.cricbuzz.resturantsearch.models.ResturantModel
import com.google.gson.Gson
import java.io.IOException

class MainFragmentViewModel : ViewModel() {

    private val gson = Gson()
    fun getResturantModel(context: Context) : ResturantModel {
        val response = getJsonDataFromAsset(context, "resturant_response.json")
        val resturant = gson.fromJson<ResturantModel>(response, ResturantModel::class.java)
        return resturant
    }

    fun getMenusModel(context: Context) : MenusModel {
        val response = getJsonDataFromAsset(context, "menu_items_response.json")
        val menus = gson.fromJson<MenusModel>(response, MenusModel::class.java)
        return menus
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}