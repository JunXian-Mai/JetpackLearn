package org.markensic.learn.jetpack

import android.content.Context
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.markensic.core.global.CoreApp
import org.markensic.mvvm.base.BaseMvvmApplication

class App : BaseMvvmApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        CoreApp.initCore(this)
    }

    override fun onCreate() {
        super.onCreate()
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL)
    }

}