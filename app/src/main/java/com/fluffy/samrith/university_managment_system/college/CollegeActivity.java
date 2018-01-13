package com.fluffy.samrith.university_managment_system.college;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fluffy.samrith.university_managment_system.R;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import recyclerview.RowAdapter;
import recyclerview.RowItem;
import recyclerview.RowListener;

public class CollegeActivity extends AppCompatActivity {

    private SearchView searchView;
    private ArrayList<RowItem> RowItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.setTitle("College");
        recyclerView = (RecyclerView) findViewById(R.id.professorList);
        prepareRowItemData(Database.COLLEGE);

        // vertical RecyclerView
        // keep RowItem_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);




    }

    private void prepareRowItemData(String url) {
        ArrayList<RowItem> row = new ArrayList<>();

        JsonArrayRequest js = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                int count=0;
                try{
                    JSONObject j = response.getJSONObject(count);
                    while(count<response.length()) {
                        row.add(new RowItem(j.getInt("id"),j.getString("name"),j.getString("flag")));
                        count++;
                    }
                    mAdapter = new RowAdapter(getApplicationContext(),row);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnClick(new RowListener() {
                        @Override
                        public void onRowClick(RowItem row) {

                        }
                    });



                }catch (Exception e){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
