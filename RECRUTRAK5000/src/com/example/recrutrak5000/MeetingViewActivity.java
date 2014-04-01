package com.example.recrutrak5000;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MeetingViewActivity extends Activity {
	
	static final LatLng Comer = new LatLng(33.2155727, -87.5442692);
	//static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_meeting_view);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	        .getMap();
	    
	    if (map!=null){
	      Marker comer = map.addMarker(new MarkerOptions().position(Comer)
	          .title("Comer"));
	      /*Marker kiel = map.addMarker(new MarkerOptions()
	          .position(KIEL)
	          .title("Kiel")
	          .snippet("Kiel is cool")
	          .icon(BitmapDescriptorFactory
	              .fromResource(R.drawable.ic_launcher)));*/
	    }
	  }   
}
