package com.cricbuzz.resturantsearch.adapters

import Menu
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cricbuzz.resturantsearch.R
import com.cricbuzz.resturantsearch.models.Restaurant
import java.util.*

class ResturantAdapter(var resturants: List<Restaurant>, var menus: List<Menu>, val context: Context) :
    RecyclerView.Adapter<ResturantAdapter.ResturantViewHolder>(), Filterable{

    var filterdList = ArrayList<Restaurant>()
    init {
        filterdList = resturants as ArrayList<Restaurant>
    }

    class ResturantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: Restaurant) {
            itemView.findViewById<TextView>(R.id.tvResturantName).text = restaurant.name
            itemView.findViewById<TextView>(R.id.tvCuisineType).text = restaurant.cuisine_type
            itemView.findViewById<TextView>(R.id.tvAddress).text = restaurant.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResturantViewHolder {
        return ResturantViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_resturants, parent, false))
    }

    override fun onBindViewHolder(holder: ResturantViewHolder, position: Int) {
        holder.bind(filterdList.get(position));
    }

    override fun getItemCount(): Int {
        return filterdList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterdList = resturants as ArrayList<Restaurant>
                } else {
                    val resultList = ArrayList<Restaurant>()
                    for (resturant in resturants) {

                        for (menu in menus) {
                            if (menu.restaurantId.equals(resturant.id)) {
                                val categories= menu.categories
                                for (category in categories) {
                                    if (category.name.contains(charSearch, true)){
                                        resultList.add(resturant)
                                        break
                                    }
                                }
                            }
                        }

                        if (resturant.name.contains(charSearch, true) || resturant.cuisine_type.contains(charSearch, true)) {
                            resultList.add(resturant)
                        }
                    }
                    filterdList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterdList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterdList = results?.values as ArrayList<Restaurant>
                notifyDataSetChanged()
            }

        }
    }
}