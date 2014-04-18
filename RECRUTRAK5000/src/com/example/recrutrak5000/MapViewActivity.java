package com.example.recrutrak5000;

import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MapViewActivity extends Activity {
	

	static final LatLng hmComer = new LatLng(33.2155727, -87.5442692);
	static final LatLng SEC = new LatLng(33.2142844, -87.541722);
	static final LatLng SERC = new LatLng(33.214321, -87.543813);
	static final LatLng houser = new LatLng(33.214383, -87.544532);
	static final LatLng hardaway = new LatLng(33.213234, -87.544741);
	
	

	private GoogleMap map;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map_view_activity);
	    Map<String,LatLng> locs = new HashMap<String, LatLng>();
	    String[] parts = getIntent().getStringExtra("locName").split(" ");
		String loc = parts[1];
		locs.put("HMComer", hmComer);
		locs.put("SEC", SEC);
		locs.put("SERC", SERC);
		locs.put("Houser", houser);
		locs.put("Hardaway", hardaway);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	        .getMap();
	    
	    if (map!=null){
	      //Marker comer = map.addMarker(new MarkerOptions().position(hmComer)
	    	Marker marker = map.addMarker(new MarkerOptions().position(locs.get(loc))
	          .title("Science and Engineering Complex")
	          .snippet("Meeting in SEC 3447 at 3:20"));
	      
	      //map.moveCamera(CameraUpdateFactory.newLatLngZoom(hmComer, 17));
	      map.moveCamera(CameraUpdateFactory.newLatLngZoom(locs.get(loc), 17));
	    }
	  }   
}
