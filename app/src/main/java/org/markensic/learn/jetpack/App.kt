package org.markensic.learn.jetpack

import android.content.Context
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.markensic.core.global.App
import com.markensic.core.global.stack.ActivityStack
import org.markensic.mvvm.base.BaseMvvmApplication

class App : BaseMvvmApplication() {
    override val activityStack = ActivityStack()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        App.initApplication(this)
    }

    override fun onCreate() {
        super.onCreate()
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL)
    }

}