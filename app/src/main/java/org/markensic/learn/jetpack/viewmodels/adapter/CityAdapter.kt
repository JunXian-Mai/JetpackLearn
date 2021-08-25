package org.markensic.learn.jetpack.viewmodels.adapter

import android.content.Context
import android.widget.ArrayAdapter
import org.markensic.learn.jetpack.models.CityCenter

class CityAdapter(context: Context, resource: Int, val objects: Array<out CityCenter>) :
  ArrayAdapter<String>(context, resource) {

  fun setCityListInProvince(province: String): List<String> {
    clear()
    val renewalList = objects.filter {
      it.belong == province
    }.map {
      it.value
    }
    addAll(renewalList)
    return renewalList
  }
}
