package org.markensic.learn.jetpack

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import org.markensic.learn.jetpack.databinding.ActivityAddressLinkBinding
import org.markensic.learn.jetpack.models.capitals
import org.markensic.learn.jetpack.models.provinces
import org.markensic.learn.jetpack.models.emails
import org.markensic.learn.jetpack.viewmodels.AddressLinkViewModel
import org.markensic.learn.jetpack.viewmodels.adapter.CityAdapter
import org.markensic.learn.jetpack.viewmodels.adapter.EmailAdapter
import org.markensic.learn.jetpack.viewmodels.adapter.ProvincesAdapter

class AddresslinkActivity : BaseActicity(), LifecycleObserver {

  private lateinit var binding: ActivityAddressLinkBinding
  private val viewmodel: AddressLinkViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_address_link)
    binding.vm = viewmodel

    val provincesAdapter = ProvincesAdapter(this, android.R.layout.simple_spinner_item, provinces)
    binding.provSp.adapter = provincesAdapter
    binding.provSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        (binding.citySp.adapter as CityAdapter).apply {
          setCityListInProvince(provinces[position].value)
          binding.citySp.setSelection(0)
          getItem(0)?.let {
            (binding.emailSp.adapter as EmailAdapter).apply {
              setEmailListInCity(it)
              binding.emailSp.setSelection(0)
            }
          }
        }
      }

      override fun onNothingSelected(parent: AdapterView<*>?) {
      }
    }

    val cityAdapter = CityAdapter(this, android.R.layout.simple_spinner_item, capitals)
    binding.citySp.adapter = cityAdapter
    binding.citySp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        (binding.citySp.adapter as CityAdapter).getItem(position)?.let {
          (binding.emailSp.adapter as EmailAdapter).setEmailListInCity(it)
        }
      }

      override fun onNothingSelected(parent: AdapterView<*>?) {
      }
    }

    val emailAdapter = EmailAdapter(this, android.R.layout.simple_spinner_item, emails)
    binding.emailSp.adapter = emailAdapter
  }
}

