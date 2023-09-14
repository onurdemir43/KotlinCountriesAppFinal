package com.onurdemir.gradle3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.onurdemir.gradle3.R
import com.onurdemir.gradle3.model.Country
import com.onurdemir.gradle3.util.downloadFromUrl
import com.onurdemir.gradle3.util.placeholderProgressBar
import com.onurdemir.gradle3.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.imageView
import kotlinx.android.synthetic.main.item_country.view.name
import kotlinx.android.synthetic.main.item_country.view.region

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.name.text = countryList[position].countryName
        holder.view.region.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageView.downloadFromUrl(countryList[position].imageUrl,
            placeholderProgressBar(holder.view.context)
        )

    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}