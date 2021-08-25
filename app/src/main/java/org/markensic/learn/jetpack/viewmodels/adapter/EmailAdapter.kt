package org.markensic.learn.jetpack.viewmodels.adapter

import android.content.Context
import android.widget.ArrayAdapter
import org.markensic.learn.jetpack.models.CityCenter

class EmailAdapter(context: Context, resource: Int, val objects: Array<out CityCenter>) :
  ArrayAdapter<String>(context, resource) {

  fun setEmailListInCity(city: String) {
    clear()
    addAll(objects.filter {
      it.belong == city
    }.map {
      it.value
    })
  }
}
