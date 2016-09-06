package com.example.dmitry.cashmonitor.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dmitry.cashmonitor.Fragment.AboutProgrammFragment;
import com.example.dmitry.cashmonitor.Fragment.CalendarFragment;
import com.example.dmitry.cashmonitor.Fragment.GraphFragment;
import com.example.dmitry.cashmonitor.Fragment.HistoryBoughtFragment;
import com.example.dmitry.cashmonitor.Fragment.NotesFragment;
import com.example.dmitry.cashmonitor.R;
import com.example.dmitry.cashmonitor.Fragment.SettingFragment;

public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setFragmentFromExtra(getIntent().getCharExtra("FragmentType", 'o'));
    }

    private void setFragmentFromExtra(char fragmentType) {
        //(findViewById(R.id.MainFragmentInUserActivity))
        switch (fragmentType){
            case 'n':
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.MainFragmentInUserActivity, new NotesFragment())
                        .commit();
                break;
            case 'h':
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.MainFragmentInUserActivity, new HistoryBoughtFragment())
                        .commit();
                break;
            case 'g':
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.MainFragmentInUserActivity, new GraphFragment())
                        .commit();
                break;
            case 's':
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.MainFragmentInUserActivity, new SettingFragment())
                        .commit();
                break;
            case 'c':
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.MainFragmentInUserActivity, new CalendarFragment())
                        .commit();
                break;
            case 'a':
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.MainFragmentInUserActivity, new AboutProgrammFragment())
                        .commit();
                break;
            case '0':
                Toast.makeText(this, "не изменяется!!!!!!", Toast.LENGTH_LONG).show();
                break;
        };
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
        getMenuInflater().inflate(R.menu.user, menu);
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

        if (id == R.id.nav_notes) {

            setFragmentFromExtra('n');
        } else if (id == R.id.nav_historyBought) {
            setFragmentFromExtra('h');
        } else if (id == R.id.nav_graph) {
            setFragmentFromExtra('g');
        } else if (id == R.id.nav_setting) {
            setFragmentFromExtra('s');
        } else if (id == R.id.nav_calendar) {
            setFragmentFromExtra('c');
        } else if (id == R.id.nav_aboutProgramm) {
            setFragmentFromExtra('a');
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
