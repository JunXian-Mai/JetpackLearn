package org.markensic.learn.jetpack.viewmodels.adapter

import android.content.Context
import android.widget.ArrayAdapter
import org.markensic.learn.jetpack.models.CityCenter

class ProvincesAdapter(context: Context, resource: Int, val objects: Array<out CityCenter>) :
    ArrayAdapter<String>(context, resource) {
    init {
        addAll(objects.map {
            it.value
        })
    }
}