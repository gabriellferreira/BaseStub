package br.com.gosafe.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import br.com.gabriellferreira.stub.BuildConfig
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appName = BuildConfig.APPLICATION_ID
        if (isGooglePlayServicesAvailable()) {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)))
            } catch (e: Exception) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appName)))
            }
        }
    }

    fun isGooglePlayServicesAvailable(): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(this)
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                val dialog = googleApiAvailability.getErrorDialog(this, status, 0)
                dialog.setOnDismissListener { finish() }
                dialog.show()
            } else {
                finish()
            }
            return false
        }
        return true
    }
}