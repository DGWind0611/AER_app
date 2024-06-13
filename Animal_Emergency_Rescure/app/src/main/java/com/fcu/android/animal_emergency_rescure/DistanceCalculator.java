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
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&key=" + apiKey;
        Log.d("DistanceCalculator", "Request URL: " + url);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("DistanceCalculator", "Response: " + response.toString());
                try {
                    JSONArray rows = response.getJSONArray("rows");
                    JSONObject elements = rows.getJSONObject(0);
                    JSONArray elementArray = elements.getJSONArray("elements");
                    JSONObject element = elementArray.getJSONObject(0);

                    if ("OK".equals(element.getString("status"))) {
                        JSONObject distance = element.getJSONObject("distance");
                        String distanceText = distance.getString("text");
                        callback.onDistanceCalculated(distanceText);
                    } else {
                        Log.e("DistanceCalculator", "Distance calculation failed: " + element.getString("status"));
                        callback.onDistanceCalculated(null);
                    }
                } catch (JSONException e) {
                    Log.e("DistanceCalculator", "Failed to parse distance matrix response", e);
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
