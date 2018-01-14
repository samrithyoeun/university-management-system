package com.fluffy.samrith.university_managment_system;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fluffy.samrith.university_managment_system.model.Department;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import recyclerview.RowItem;

public class DetailActivity extends AppCompatActivity {
    String function;
    private ArrayList<RowItem> RowItemList = new ArrayList<>();
    TextView tx;
    String text ="";
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.setTitle("Detail");
        logo = (ImageView)findViewById(R.id.image);


        tx = (TextView)findViewById(R.id.text) ;
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        String type="";
        switch (b.getString("type")){
            case "course":
                type="course";
                break;

            case "department":
                type = "department";
                break;


        }
        Uri uri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/drawable/"+type);
        try{
            logo.setImageURI(uri);

        }catch (Exception e){
            e.printStackTrace();
        }





                String mainkey = b.getString("text")
                        .replace(" ","\u00A0");
                tx.setText(mainkey);


    }
}
