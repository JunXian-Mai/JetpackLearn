package org.markensic.learn.jetpack

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.model.LatLng
import com.markensic.core.global.log.CoreLog
import org.markensic.learn.jetpack.databinding.ActivityBaidumapBinding
import org.markensic.learn.jetpack.viewmodels.BaiduMapViewModel

class BaiduMapActivity : BaseActicity() {
  lateinit var binding: ActivityBaidumapBinding
  lateinit var locationClient: LocationClient
  val viewModel: BaiduMapViewModel by viewModels()

  @Volatile
  private var toCurrentLocation = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_baidumap)
    binding.vm = viewModel

    binding.mapView.map.isMyLocationEnabled = true

    val locationClientOp = LocationClientOption().apply {
      openGps = true
      coorType = "bd09ll"
      scanSpan = 1000
    }

    locationClient = LocationClient(this, locationClientOp).apply {
      registerLocationListener(object : BDAbstractLocationListener() {
        override fun onReceiveLocation(p0: BDLocation?) {
          p0?.let {
            CoreLog.d(
              """
              Loaction -->
                latitude: ${it.latitude}
                longitude: ${it.longitude}
            """.trimIndent()
            )

            val locData = MyLocationData.Builder()
              .accuracy(it.radius)
              .direction(it.direction)
              .latitude(it.latitude)
              .longitude(it.longitude)
              .build()

            binding.mapView.map.apply {
              setMyLocationData(locData)

              if (!toCurrentLocation) {
                animateMapStatus(
                  MapStatusUpdateFactory.newLatLng(
                    LatLng(it.latitude, it.longitude)
                  )
                )
                animateMapStatus(
                  MapStatusUpdateFactory.zoomTo(18f)
                )
                toCurrentLocation = true
              }
            }
          }
        }
      })
    }

    locationClient.start()
  }

  override fun onResume() {
    binding.mapView.onResume()
    super.onResume()
  }

  override fun onPause() {
    binding.mapView.onPause()
    super.onPause()
  }

  override fun onDestroy() {
    locationClient.stop()
    binding.mapView.map.isMyLocationEnabled = false
    binding.mapView.onDestroy()
    super.onDestroy()
  }
}
