package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DistanceCalculator {
    public interface DistanceCallback {
        void onDistanceCalculated(String distance);
    }

    public static void calculateDistance(Context context, String origin, String destination, String apiKey, final DistanceCallback callback) {
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + origin + "&destination=" + destination + "&key=" + apiKey;
        Log.d("DistanceCalculator", "Request URL: " + url);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("DistanceCalculator", "Response: " + response.toString());
                try {
                    JSONArray routes = response.getJSONArray("routes");
                    if (routes.length() > 0) {
                        JSONObject route = routes.getJSONObject(0);
                        JSONArray legs = route.getJSONArray("legs");
                        if (legs.length() > 0) {
                            JSONObject leg = legs.getJSONObject(0);
                            JSONObject distance = leg.getJSONObject("distance");
                            String distanceText = distance.getString("text");
                            callback.onDistanceCalculated(distanceText);
                        } else {
                            Log.e("DistanceCalculator", "No legs found in the response");
                            callback.onDistanceCalculated(null);
                        }
                    } else {
                        Log.e("DistanceCalculator", "No routes found in the response");
                        callback.onDistanceCalculated(null);
                    }
                } catch (JSONException e) {
                    Log.e("DistanceCalculator", "Failed to parse directions response", e);
                    callback.onDistanceCalculated(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("DistanceCalculator", "Request failed", error);
                callback.onDistanceCalculated(null);
            }
        });
        queue.add(request);
    }
}
