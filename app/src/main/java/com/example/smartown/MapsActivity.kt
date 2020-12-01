package com.example.smartown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.smartown.api.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_criar_nota.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val newWordActivityRequestCode = 1
    private lateinit var problemas: List<Problem>

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
        val call = request.getPontos()

        call.enqueue(object : Callback<List<Problem>>{
            override fun onResponse(call: Call<List<Problem>>, response: Response<List<Problem>>){
                if(response.isSuccessful){ //working
                    mMap.clear()
                    for(entry in response.body()!!){
                        val loc = LatLng(entry.lat.toDouble(), entry.lng.toDouble())

                        mMap.addMarker(MarkerOptions().position(loc).title("${entry.id}, ${entry.descr}"))

                    }
                }
            }

            override fun onFailure(call: Call<List<Problem>>, t: Throwable){
                Toast.makeText(this@MapsActivity , "${t.message}", Toast.LENGTH_SHORT).show()
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