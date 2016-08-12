package com.denkmayr.andreas.ct800_client;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.denkmayr.andreas.ct800_client.Database.CowRepository;
import com.denkmayr.andreas.ct800_client.Entity.Cow;
import com.denkmayr.andreas.ct800_client.Entity.Inspection;

import java.util.List;

public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CowListFragment.OnFragmentInteractionListener {

    CowListFragment cowListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cowListFragment = CowListFragment.newInstance();


        System.out.println("DEBUG START"); //DEBUG START TODO
        CowRepository cr = CowRepository.getInstance(this);
        cr.deleteAllCows();

        for(int i = 0; i < 10; i++)
        {
            Cow cow = new Cow();
            cow.setName("CowName"+i);
            cow.setEartag("EarTag"+i);
            cr.insertCow(cow);
        }

        List<Cow> cows = cr.getAllCows();
        System.out.println("There were " + cows.size() + " Cows inserted!");
        for (Cow cow : cows) {
            System.out.println(cow.getEartag() + " | " + cow.getName());
        }

        Inspection inspection = new Inspection();
        Toast.makeText(this, inspection.getDateString(), Toast.LENGTH_SHORT).show();
        inspection.setDateString("6-6-2016");
        Toast.makeText(this, inspection.getDateString(), Toast.LENGTH_SHORT).show();


        System.out.println("DEBUG END"); //DEBUG START TODO
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
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
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

        if (id == R.id.nav_info) {

        } else if (id == R.id.nav_cows) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFraPlace, cowListFragment).commit();
        } else if (id == R.id.nav_inspections) {

        } else if (id == R.id.nav_import) {

        } else if (id == R.id.nav_export) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
