package edu.asu.msse.pkondudu.hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;

    LocationListener locationListener;

    TextView latitudeTextView;
    TextView longitudeTextView;
    TextView accuracyTextView;
    TextView altitudeTextView;
    TextView addressTextView;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            startListening();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitudeTextView = (TextView) findViewById(R.id.latitudeTextView);
        longitudeTextView = (TextView) findViewById(R.id.longitudeTextView);
        accuracyTextView = (TextView) findViewById(R.id.accuracyTextView);
        altitudeTextView = (TextView) findViewById(R.id.altitudeTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                updateLocationInfo(location);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {

            startListening();

        } else {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {

                    updateLocationInfo(location);
                }
            }
        }
    }

    public void updateLocationInfo(Location location) {

        Log.i("LOCATIONINFO", location.toString());

        latitudeTextView.setText("Latitude: " + location.getLatitude());
        longitudeTextView.setText("Longitude" + location.getLongitude());
        accuracyTextView.setText("Accuracy: " + location.getAccuracy());
        altitudeTextView.setText("Altitude: " + location.getAltitude());

        Geocoder geocoder = new Geocoder(this.getApplicationContext(), Locale.getDefault());

        try {
            String address = "Could not find address";

            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addresses != null && addresses.size() > 0) {
                Log.i("PLACEINFO", addresses.get(0).toString());
            }

            address = "Address: \n";

            if (addresses.get(0).getSubThoroughfare() != null) {

                address += addresses.get(0).getSubThoroughfare() + " ";

            }

            if (addresses.get(0).getThoroughfare() != null) {

                address += addresses.get(0).getThoroughfare() + "\n";

            }

            if (addresses.get(0).getLocality() != null) {

                address += addresses.get(0).getLocality() + "\n";

            }

            if (addresses.get(0).getPostalCode() != null) {

                address += addresses.get(0).getPostalCode() + "\n";

            }

            if (addresses.get(0).getCountryName() != null) {

                address += addresses.get(0).getCountryName() + "\n";

            }

            addressTextView.setText(address);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startListening() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        }
    }

}
