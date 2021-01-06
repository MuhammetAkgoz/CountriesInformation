package com.example.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlincountries.R
import com.example.kotlincountries.adapter.CountryAdapter
import com.example.kotlincountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {


    private lateinit var viewModel : FeedViewModel
    private val countryAdapter=CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragmentü

    return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel :: class.java)
        viewModel.refreshData()

        countryList.layoutManager = LinearLayoutManager(this.context)
        countryList.adapter = countryAdapter

        swipeRefreshLayout.setOnRefreshListener {
            countryList.visibility = View.GONE
            error.visibility = View.GONE
            coumtryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }

        ObserverLiveData()
    }


    fun ObserverLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
                it?.let {
                    countryList.visibility= View.VISIBLE
                    countryAdapter.updateCountryList(it)
                }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    error.visibility= View.VISIBLE

                }else{
                    error.visibility= View.GONE
                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it){
                    coumtryLoading.visibility= View.VISIBLE
                    countryList.visibility= View.GONE
                    error.visibility= View.GONE
                }else{
                    coumtryLoading.visibility = View.GONE
                }
            }
        })
    }

}