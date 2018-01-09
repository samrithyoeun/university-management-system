package com.fluffy.samrith.university_managment_system;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fluffy.samrith.university_managment_system.admin.CollegeFragment;
import com.fluffy.samrith.university_managment_system.admin.CourseFragment;
import com.fluffy.samrith.university_managment_system.admin.DepartmentFragment;
import com.fluffy.samrith.university_managment_system.admin.HomeFragment;
import com.fluffy.samrith.university_managment_system.admin.LecturerFragment;
import com.fluffy.samrith.university_managment_system.admin.SessionFragment;
import com.fluffy.samrith.university_managment_system.admin.StudentFragment;

import recyclerview.CreateToast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment newFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.commit();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragmentCon, newFragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Welcome");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            // Create new fragment and transaction
            Fragment newFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.fragmentCon, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            this.setTitle("Welcome");
        } else if (id == R.id.College) {
            // Create new fragment and transaction
            Fragment newFragment = new CollegeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.fragmentCon, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            this.setTitle("College");


        } else if (id == R.id.Department) {
            // Create new fragment and transaction
            Fragment newFragment = new DepartmentFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.fragmentCon, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            this.setTitle("Department");

        } else if (id == R.id.Lecturer) {
            // Create new fragment and transaction
            Fragment newFragment = new LecturerFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.fragmentCon, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            this.setTitle("Instructor");


        } else if (id == R.id.Student) {
            // Create new fragment and transaction
            Fragment newFragment = new StudentFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.fragmentCon, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            this.setTitle("Student");


        } else if (id == R.id.Course) {
            // Create new fragment and transaction
            Fragment newFragment = new CourseFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.fragmentCon, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            this.setTitle("Course");


        } else if (id == R.id.Session) {
            // Create new fragment and transaction
            Fragment newFragment = new SessionFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.fragmentCon, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            this.setTitle("Session");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
