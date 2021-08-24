package org.markensic.learn.jetpack

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.EasyPermissions

open class BaseActicity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this::class.simpleName + "_Life", "onCreate")

        Log.d(this::class.simpleName, "requestPermissions!")
        EasyPermissions.requestPermissions(
            this, "requestPermission", 200,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onStart() {
        super.onStart()
        Log.d(this::class.simpleName + "_Life", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(this::class.simpleName + "_Life", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(this::class.simpleName + "_Life", "onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d(this::class.simpleName + "_Life", "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(this::class.simpleName + "_Life", "onDestroy")
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val result = super.dispatchTouchEvent(ev)
        Log.e(this::class.simpleName, "Acticity.dispatchTouchEvent")
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }
}