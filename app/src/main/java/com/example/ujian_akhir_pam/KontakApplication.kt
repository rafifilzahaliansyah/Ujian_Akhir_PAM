package com.example.ujian_akhir_pam

import android.app.Application
import com.example.ujian_akhir_pam.data.AppContainer
import com.example.ujian_akhir_pam.data.KontakContainer

class KontakApplication:Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = KontakContainer()
    }
}