package org.markensic.learn.jetpack.viewmodels

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.markensic.learn.jetpack.BR
import java.security.KeyPair
import java.security.KeyPairGenerator
import javax.crypto.Cipher

class DataBindingViewModelLiveData: ViewModel(){
  val detail = MutableLiveData("Detail:")

  val btnText = "Add Input To Detail"

  val btnChangeColor = "Other Change Detail Text"

  fun addInputToDetail(d: String) {
    if (d.isNotBlank()) {
      detail.value?.let {
        val sb = StringBuilder(it).appendLine().append(d)
        detail.value = sb.toString()
      }
    }
  }

  fun changeTextColor(v: TextView, color: Int) {
    v.setTextColor(color)
  }

  fun directSetText(v: TextView, t: String) {
    if (t.isNotBlank()) {
      v.apply {
        val sb = StringBuilder(v.text).appendLine().append(t)
        text = sb.toString()
      }
    }
  }
}

class DataBindingViewModel: ViewModel(), Observable {
  private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()
  private val _detailSb = StringBuilder("Detail:")

  @get:Bindable
  var detail get() = _detailSb.toString()
  set(value) {
    if (value != _detailSb.toString()) {
      _detailSb.clear()
      _detailSb.append(value)
    }
  }

  val btnText = "Add Input To Detail"

  val btnChangeColor = "Other Change Detail Text"

  fun addInputToDetail(d: String) {
    if (d.isNotBlank()) {
      _detailSb.appendLine()
      _detailSb.append(d)
      callbacks.notifyChange(this, BR.detail)
    }
  }

  fun changeTextColor(v: TextView, color: Int) {
    v.setTextColor(color)
  }

  fun directSetText(v: TextView, t: String) {
    if (t.isNotBlank()) {
      v.text = "${v.text}\n$t"
    }
  }

  override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    callbacks.add(callback)
  }

  override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    callbacks.remove(callback)
  }
}

class DataBinding: BaseObservable(){
  private val _detailSb = StringBuilder("Detail:")

  val paddingTop = 0

  @get:Bindable
  val detail get() = _detailSb.toString()

  val btnChangeColor = "Change Detail Background Color"

  val btnText: String = "Add Input To Detail"

  fun addInputToDetail(d: String) {
    if (d.isNotBlank()) {
      _detailSb.appendLine()
      _detailSb.append(d)
      notifyPropertyChanged(BR.detail)
    }
  }
}


@BindingAdapter("android:paddingLeft")
fun setPaddingTop(view: View, oldPadding: Int, newPadding: Int) {
  if (oldPadding < newPadding) {
    view.setPadding(
      view.paddingLeft,
      newPadding,
      view.paddingRight,
      view.paddingBottom
    )
  }
}

