package appxcitinglabs.lovenotes.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import appxcitinglabs.lovenotes.R;
import appxcitinglabs.lovenotes.fragments.AboutFragment;
import appxcitinglabs.lovenotes.fragments.HomeFragment;
import appxcitinglabs.lovenotes.fragments.NavigationDrawerFragment;
import appxcitinglabs.lovenotes.fragments.NotesListFragment;


public class Home extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        HomeFragment.OnFragmentInteractionListener,
        NotesListFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    protected NavigationDrawerFragment mNavigationDrawerFragment;
    protected DrawerLayout mDrawerLayout;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                mDrawerLayout);



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        //Toast.makeText(Home.this, "on Navigation Item Selected", Toast.LENGTH_LONG).show();
        // update the main content by replacing fragments

        //Replace with different fragments here
        FragmentManager fragmentManager = getFragmentManager();
        if (position == 0)
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance(new String("ZZZ"), new String("YYY")))
                    .commit();
        }
        else if (position == 1)
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, NotesListFragment.newInstance(new String("ZZZ"), new String("YYY")))
                    .commit();
        }
        else if (position == 2)
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, AboutFragment.newInstance(new String("ABC"), new String("DEF")))
                    .commit();
        }


    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_home);
                break;
            case 2:
                mTitle = getString(R.string.title_newNotes);
                break;
            case 3:
                mTitle = getString(R.string.title_aboutapp);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    @Override
    public void onFragmentInteraction(String id) {

    }
}
