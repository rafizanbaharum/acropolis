package net.canang.acropolis.mob;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ct1 on 7/6/13.
 */
public class IssueAsyncTask extends AsyncTask<LatLng, Void, List<Issue>> {

    private static final String ENDPOINT_URL = "http://161.139.20.154/issues/findunresolved";

    @Override
    protected List<Issue> doInBackground(LatLng... latLngs) {
        List<Issue> issues = new ArrayList<Issue>();
        for (LatLng latLng : latLngs) {

            JSONParser parser = new JSONParser();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("lat", Double.toString(latLng.latitude)));
            params.add(new BasicNameValuePair("lng", Double.toString(latLng.longitude)));
            JSONArray result = parser.makeHttpRequest(ENDPOINT_URL, "GET", params);
            try {
                if (null != result) {
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject o = result.getJSONObject(i);
                        Issue issue = new Issue(
                                o.getLong("id"),
                                o.getString("key"),
                                o.getDouble("latitude"),
                                o.getDouble("longitude"),
                                o.getString("title"),
                                o.getString("description"),
                                o.getString("type"),
                                o.getString("status")
                        );
                        issues.add(issue);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return issues;
    }
}