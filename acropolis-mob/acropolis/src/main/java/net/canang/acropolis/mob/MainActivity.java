package net.canang.acropolis.mob;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements LocationListener {

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerList;
    private static List<Issue> issues = new ArrayList<Issue>();
    private static List<Marker> markers = new ArrayList<Marker>();
    private List<String> titles;
    private MapFragment mapFragment;
    private GoogleMap map;
    private LocationManager locationManager;
    private String provider;
    private Location location;
    private static final String ENDPOINT_URL = "http://161.139.20.154/issues/findunresolved";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles = new ArrayList<String>();

        // =========================================================================================
        // LOCATION STUFF
        // =========================================================================================

        // get location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        location = locationManager.getLastKnownLocation(provider);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_view);

        map = mapFragment.getMap();
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12));
        map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);

        issues.clear();
        titles.clear();
        JSONParser parser = new JSONParser();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("lat", Double.toString(location.getLatitude())));
        params.add(new BasicNameValuePair("lng", Double.toString(location.getLongitude())));
        JSONArray result = parser.makeHttpRequest(ENDPOINT_URL, "GET", params);
        try {
            if (null != result) {
                for (int i = 0; i < result.length(); i++) {
                    JSONObject o = result.getJSONObject(i);
                    Issue issue = new Issue(
                            o.getLong("id"),
                            o.getDouble("latitude"),
                            o.getDouble("longitude"),
                            o.getString("title"),
                            o.getString("description"));
                    issues.add(issue);
                }

                for (Issue issue : issues) {
                    Marker marker = map.addMarker(new MarkerOptions()
                            .position(new LatLng(issue.getLat(), issue.getLng()))
                            .title(issue.getTitle())
                            .snippet(issue.getDescription()));
                    markers.add(marker);
                    titles.add(issue.getTitle());
                }

                // =========================================================================================
                // LIST VIEW
                // =========================================================================================
                drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, titles));
                drawerList.setOnItemClickListener(new DrawerItemClickListener());
                drawerToggle = new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        R.drawable.ic_drawer,
                        R.string.drawer_open,
                        R.string.drawer_close
                );
                drawerLayout.setDrawerListener(drawerToggle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
//            selectItem(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Log.d(TAG, "position = " + position);
        Bundle args = new Bundle();
        if (null != drawerList && drawerList.getCount() > 0) {
//            drawerList.setItemChecked(position, true);
            drawerLayout.closeDrawer(drawerList);

            Issue issue = issues.get(position);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(issue.getLat(), issue.getLng()), 12));
            map.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
            markers.get(position).showInfoWindow();
        }
    }

    public void onLocationChanged(Location location) {
        Toast.makeText(this, "lat:" + location.getLatitude() + " lon:" + location.getLongitude(), Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
