package gps.ice.sms.com.interviewtask;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mipstech i5 2 on 15-Feb-18.
 */

public class dummyapicall extends AppCompatActivity {
    RequestQueue requestQueue;
    String JSONURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Array.JSON";
    String data;
    String name1;
    ArrayList datas = new ArrayList<String>();
    TextView textView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        final DatabaseHelper dbManager = new DatabaseHelper(this);
        //dbManager.open();
        final SQLiteDatabase sql = dbManager.getWritableDatabase();

        requestQueue = Volley.newRequestQueue(dummyapicall.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(JSONURL, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    JSONArray jsonArray = jsonObject.getJSONArray("colorArray");
                    for (int i = 0; i <= jsonArray.length(); i++) {
                         JSONObject results = jsonArray.getJSONObject(i);
                        String s = results.getString("colorName");
                        ContentValues cv1 = new ContentValues();
                        cv1.put("shapename1", s);
                        sql.insert("Colors", null, cv1);


                        // textView.setText(s);

                    }
                    //
                    // RecyclerView.Adapter adapter = new DataAdapter(datas);
                    //  recyclerView.setAdapter(adapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SQLiteDatabase sql1 = dbManager.getReadableDatabase();
                //The basic purpose of a  cursor is to point to a single row of the result fetched by the query.
                Cursor cursor = sql1.query("Colors", null, null, null, null, null, null,
                        null);
                if (cursor != null && cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {
                        do {

                            name1 = cursor.getString(cursor
                                    .getColumnIndex(DatabaseHelper.SUBJECT1));

                            Toast.makeText(dummyapicall.this,
                                    name1, Toast.LENGTH_SHORT)
                                    .show();

                            datas.add(name1);
                        } while (cursor.moveToNext());
                    }
                    //datas.add(name1);
                }
                RecyclerView.Adapter adapter = new DataAdapter(datas);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );

        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(JSONURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject1 = null;
                try {
                    jsonObject1 = response.getJSONObject(1);
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("shapeArray");
                    for (int j = 0; j <= jsonArray1.length(); j++) {
                        JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                        data = jsonObject2.getString("shapeName");
                        ContentValues cv = new ContentValues();
                        cv.put("shapename", data);
                        sql.insert("APIResponse", null, cv);
                        Toast.makeText(dummyapicall.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();

                        Toast.makeText(dummyapicall.this, data, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", String.valueOf(error));
            }
        }
        );


        requestQueue.add(jsonArrayRequest);
        requestQueue.add(jsonArrayRequest1);

    }
}
