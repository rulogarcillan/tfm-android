package tuppersoft.com.burguerfinder.features.main

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import tuppersoft.com.burguerfinder.core.extension.log
import tuppersoft.com.burguerfinder.core.platform.GlobalActivity


class MainActivity : GlobalActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(tuppersoft.com.burguerfinder.R.layout.activity_main)
        initPlaces()
    }

    private fun initPlaces() {
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            val placesClient = Places.createClient(this)
            val token = AutocompleteSessionToken.newInstance()
            val request = FindAutocompletePredictionsRequest.builder()
                .setSessionToken(token)
                .setQuery("hamburguesas")
                .build()

            placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener { response ->
                    ("number of results in search places response " + response.autocompletePredictions.size).log()
                    val sb = StringBuilder()
                    for (prediction in response.autocompletePredictions) {
                        sb.append(prediction.getPrimaryText(null).toString())
                        sb.append("\n")
                    }
                    sb.toString().log()
                }.addOnFailureListener { exception -> exception.printStackTrace() }

        } else {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initPlaces()
                } else {
                    finish()
                }
                return
            }
        }
    }

}
