package com.example.recrutrak5000;

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
	private GoogleMap map;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map_view_activity);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	        .getMap();
	    
	    if (map!=null){
	      Marker comer = map.addMarker(new MarkerOptions().position(hmComer)
	          .title("HM Comer")
	          .snippet("Meeting room in nnn room at tt:tt"));
	      
	      map.moveCamera(CameraUpdateFactory.newLatLngZoom(hmComer, 17));
	    }
	  }   
}
