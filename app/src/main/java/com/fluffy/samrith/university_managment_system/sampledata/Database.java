package com.fluffy.samrith.university_managment_system.sampledata;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fluffy.samrith.university_managment_system.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import recyclerview.RowAdapter;
import recyclerview.RowItem;

/**
 * Created by samrith on 13/1/18.
 */

public class Database {
    public static final String HOST = "http://10.33.1.52/ums/";
    public static final String COLLEGE= HOST+"college.php";
    public static final String DEPARTMENT = HOST+ "department.php";
    public static final String INSTRUCTOR = HOST+ "instructor.php";
    public static final String COURSE = HOST+ "course.php";
    public static final String STUDENT = HOST+ "student.php";
    public static final String SESSION = HOST+ "session.php";


}
