package com.onurdemir.gradle3.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.onurdemir.gradle3.R
import com.onurdemir.gradle3.util.downloadFromUrl
import com.onurdemir.gradle3.util.placeholderProgressBar
import com.onurdemir.gradle3.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.countryCapital
import kotlinx.android.synthetic.main.fragment_country.countryCurrency
import kotlinx.android.synthetic.main.fragment_country.countryImage
import kotlinx.android.synthetic.main.fragment_country.countryLanguage
import kotlinx.android.synthetic.main.fragment_country.countryName
import kotlinx.android.synthetic.main.fragment_country.countryRegion


class CountryFragment : Fragment() {

    private lateinit var viewModel : CountryViewModel

    private var countryUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)



        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->

            country?.let {
                countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countryCurrency.text = country.countryCurrency
                countryLanguage.text = country.countryLanguage
                countryRegion.text = country.countryRegion
                context?.let {
                    countryImage.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))
                }
            }

        })
    }

}