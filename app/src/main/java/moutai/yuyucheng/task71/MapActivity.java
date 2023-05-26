package moutai.yuyucheng.task71;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private DatabaseHelper databaseHelper;

    public LatLng getLatLngFromLocationString(String locationString) {
        String[] parts = locationString.split(",");
        if(parts.length == 2) {
            try {
                double lat = Double.parseDouble(parts[0]);
                double lng = Double.parseDouble(parts[1]);
                return new LatLng(lat, lng);
            } catch (NumberFormatException e) {
                // Handle the situation when the string cannot be parsed into double
                e.printStackTrace();
            }
        }
        return null; // Return null if conversion is unsuccessful
    }

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        databaseHelper = new DatabaseHelper(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Cursor cursor = databaseHelper.getAllData();

        if (cursor.moveToFirst()) {
            do {
                int locationIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_LOCATION);
                if (locationIndex != -1) {
                    String locationString = cursor.getString(locationIndex);
                    LatLng location = getLatLngFromLocationString(locationString);
                    if (location != null) {
                        mMap.addMarker(new MarkerOptions().position(location).title("Marker"));
                    }
                }
            } while (cursor.moveToNext());
        }
    }

}

