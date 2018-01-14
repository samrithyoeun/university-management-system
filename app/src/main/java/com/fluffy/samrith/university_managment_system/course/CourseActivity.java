package com.fluffy.samrith.university_managment_system.course;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.fluffy.samrith.university_managment_system.DetailActivity;
import com.fluffy.samrith.university_managment_system.R;
import com.fluffy.samrith.university_managment_system.college.CollegeActivity;
import com.fluffy.samrith.university_managment_system.college.CollegeEditActivity;
import com.fluffy.samrith.university_managment_system.model.College;
import com.fluffy.samrith.university_managment_system.model.Course;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import recyclerview.RowAdapter;
import recyclerview.RowItem;
import recyclerview.RowListener;

public class CourseActivity extends AppCompatActivity {
    private ArrayList<RowItem> RowItemList = new ArrayList<>();
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;
    String function;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        function = b.getString("func");
        switch (function) {
            case "view":
                this.setTitle("List of Course");
                break;
            case "delete": case  "edit":
                this.setTitle("Select Course ");

        }
        recyclerView = (RecyclerView) findViewById(R.id.professorList);
        mAdapter = new RowAdapter(this, RowItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        String url = Database.COURSE+"?opt=getall";
        Log.d("volley",url+"");
        JsonArrayRequest js = new JsonArrayRequest(Request.Method.GET,url , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("volley",url);
                int count = 0;
                try {
                    while (count < response.length()) {
                        JSONObject j = response.getJSONObject(count);
                        RowItemList.add(new Course(j.getString("CoName"),j.getInt("CCode")));
                        Log.d("volley", RowItemList.get(count).toString());
                        count++;

                    }

                    mAdapter.notifyDataSetChanged();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(js);


        mAdapter.setOnClick(new RowListener() {
            @Override
            public void onRowClick(RowItem row) {
                String url;
                switch (function) {
                    case "view":
                        url = Database.COURSE+"?opt=getdetail&mainkey="+row.getName();
                        gotoDetail(url);
                        break;

                    case "delete":
                        url=  Database.COURSE+"?opt=del&mainkey="+((Course)row).getCode();
                        Log.d("volley",url);
                        del(url);
                        break;

                    case "edit":

                        url = Database.COURSE+"?opt=getdetail&mainkey="+row.getName();
                        gotoUpdate(url);
                        break;

                }
            }
        });

    }






    public void gotoDetail(String url ){


        Log.d("activities",this.getClass().getSimpleName());

        JsonArrayRequest js = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("volley",url);

                try{
                    String text ="";

                    text += "\nSubject : "+  response.getJSONObject(0).getString("CDesc");
                    text += "\nCode : "+  response.getJSONObject(0).getString("CCode");
                    text += "\nCredit : "+  response.getJSONObject(0).getString("Credits");
                    text += "\nLevel : "+  response.getJSONObject(0).getString("Level");
                    text += "\nDescription : "+  response.getJSONObject(0).getString("CoName");

                    text +="\n";
                    Log.d("volley",text);

                    Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                    i.putExtra("text",text);
                    i.putExtra("type","course");
                    startActivity(i);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("volley",url);
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(js);

    }
    public void del( String url ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention!");
        builder.setMessage("Do you want to delete this entry?");

        // add the buttons
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("activities",this.getClass().getSimpleName());

                StringRequest js = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("true")){
                            Toast.makeText(getApplicationContext(), "Delete this entry", Toast.LENGTH_SHORT).show();
                            onBackPressed();

                        }
                        else{
                            Log.d("volley",response);
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("volley",url);
                    }
                });

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(js);

            }
        });
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void gotoUpdate(String url ){
        Log.d("activities",this.getClass().getSimpleName());

        JsonArrayRequest js = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("volley",url);

                try{
                    String text ="";
                    Intent i = new Intent(getApplicationContext(),CourseEditActivity.class);
                    i.putExtra("func","edit");
                    i.putExtra("code", response.getJSONObject(0).getString("CCode"));
                    i.putExtra("desc",response.getJSONObject(0).getString("CDesc"));
                    i.putExtra("name",response.getJSONObject(0).getString("CoName"));
                    i.putExtra("credit",response.getJSONObject(0).getString("Credits"));
                    i.putExtra("dcodes",response.getJSONObject(0).getString("DEPT_DCode"));
                    i.putExtra("level",response.getJSONObject(0).getString("Level"));



                    startActivity(i);
                    finish();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("volley",url);
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(js);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

}
