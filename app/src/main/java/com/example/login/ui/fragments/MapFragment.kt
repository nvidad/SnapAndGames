package com.example.login.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.login.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.MarkerOptions



class MapFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync { map->
            map.setOnMapClickListener { lat->

                //when clicked, initialize marker options
                val markerOptions = MarkerOptions()

                markerOptions.position(lat)

                markerOptions.title(lat.latitude.toString() + lat.longitude.toString())

                //remove marker
                map.clear()

                //animate to zoom marker
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(lat, 10F))

                //ad marker on map
                map.addMarker(markerOptions)
            }
        }

        return root
    }



}