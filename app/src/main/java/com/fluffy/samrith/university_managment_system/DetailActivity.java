package com.fluffy.samrith.university_managment_system;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fluffy.samrith.university_managment_system.model.Department;
import com.fluffy.samrith.university_managment_system.model.Session;
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
    Bundle b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.setTitle("Detail");
        logo = (ImageView)findViewById(R.id.image);


        tx = (TextView)findViewById(R.id.text) ;
        b = new Bundle();
        b = getIntent().getExtras();


        String type="";
        switch (b.getString("type")){
            case "course":
                type="course";
                break;

            case "department":
                type = "department";
                break;

            case "professor":
                type = "professor";
                break;

            case "faculty":
                type = "faculty";
                break;

            case "student":
                type = "student";
                tx.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        setContentView(R.layout.activity_schedule);
                        b = new Bundle();
                        b = getIntent().getExtras();

                        String id = b.getString("studId");
//                        Toast.makeText(DetailActivity.this, id, Toast.LENGTH_SHORT).show();
                        scoreDialog(id);
                        return true;
                    }
                });
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


    private void scoreDialog(String studentID){

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetailActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.text_input_dialog, null);
        final EditText se = (EditText) mView.findViewById(R.id.sn);
        final EditText gr = (EditText) mView.findViewById(R.id.gr);



                mBuilder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       String sec=se.getText().toString();
                       String mark=gr.getText().toString();

                        String url = Database.MARK+"?mark="+mark+"&se="+sec+"&mainkey="+studentID+"&opt=adds";
                        setScore(url);
                        onBackPressed();
                        Toast.makeText(DetailActivity.this, "Add entry ", Toast.LENGTH_SHORT).show();


                    }
                });

                mBuilder.setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sec=se.getText().toString();
                        String mark=gr.getText().toString();

                        String url = Database.MARK+"?mark="+mark+"&se="+sec+"&mainkey="+studentID+"&opt=upd";
                        setScore(url);
                        onBackPressed();
                        Toast.makeText(DetailActivity.this, "Update entry ", Toast.LENGTH_SHORT).show();

                    }
                });

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

    }

    private void setScore(String url){
        Log.d("volley",url+"");
        JsonArrayRequest js = new JsonArrayRequest(Request.Method.GET,url , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("volley",url);
                int count = 0;
                try {

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

    }


}
