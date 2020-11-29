package com.example.smartown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.smartown.api.EndPoints
import com.example.smartown.api.ResultProb
import com.example.smartown.api.ServiceBuilder
import com.example.smartown.api.User
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_criar_nota.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val newWordActivityRequestCode = 1
    private lateinit var problemas: List<ResultProb>

    //botao menu(cima)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_mapa, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.inserir_prob->{
                val intent = Intent(this@MapsActivity, Problemas::class.java)
                startActivityForResult(intent, newWordActivityRequestCode)
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.postPontos()
        var position: LatLng

        call.enqueue(object: Callback<ResultProb> {
            override fun onResponse(call: Call<ResultProb>, response: Response<ResultProb>){
                if(response.isSuccessful){
                    problemas = response.body()!!
                        position = LatLng()


                }
            }

            override fun onFailure(call: Call<ResultProb>, t: Throwable) {

            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onBackPressed(){

    }
}