package com.example.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincountries.R
import com.example.kotlincountries.databinding.ItemCountryBinding
import com.example.kotlincountries.model.Country
import com.example.kotlincountries.util.downloadFromURL
import com.example.kotlincountries.util.placeholderProgressBar
import com.example.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.fragment_feed.view.*
import kotlinx.android.synthetic.main.item_country.view.*
import java.util.ArrayList

class CountryAdapter(val countryList:ArrayList<Country>):
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {


    class CountryViewHolder(var view: ItemCountryBinding):RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        //val view =  inflater.inflate(R.layout.item_country,parent,false)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

            holder.view.country = countryList[position]
            holder.view.listener = this


            /*holder.view.text_country.text=countryList[position].countryName
            holder.view.text_region.text=countryList[position].countryRegion

            holder.view.setOnClickListener {
                val action = FeedFragmentDirections.actionFeedFragment2ToCountryFragment2(countryList[position].uuid)
                Navigation.findNavController(it).navigate(action)
            }

            holder.view.imageview.downloadFromURL(countryList[position].imageURL,
                placeholderProgressBar(holder.view.context))*/
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList:List<Country>){
        //mevcur listeyi siler
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClickListener(v: View) {
        val uuid= v.uuid.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragment2ToCountryFragment2(uuid)
        Navigation.findNavController(v).navigate(action)
    }


}