package com.example.mahas.movein;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,TabFH.OnFragmentInteractionListener,TabFur.OnFragmentInteractionListener,TabGro.OnFragmentInteractionListener,TabHome.OnFragmentInteractionListener,TabStat.OnFragmentInteractionListener,TabMS.OnFragmentInteractionListener,TabWifi.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    int clickcount=0;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ActionBarDrawerToggle atog;
    private DrawerLayout d1;
    SharedPreferences sharedpreferences ;
    private TextView t3;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this, "likeddddd",Toast.LENGTH_SHORT).show();
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(R.layout.activity_main);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setEnterTransition(new Fade());
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        d1=(DrawerLayout)findViewById(R.id.main_content);
        atog=new ActionBarDrawerToggle(this,d1,R.string.open,R.string.close);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        d1.addDrawerListener(atog);
        sharedpreferences= getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedpreferences.edit();
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        final AppBarLayout appBarLayout=(AppBarLayout)findViewById(R.id.appbar);
        final Window window= getWindow();
        final NavigationView navigationView= (NavigationView)findViewById(R.id.navdraw);
        t3=(TextView)findViewById(R.id.textView3);
        t3.setText("Hi "+sharedpreferences.getString("name",null));
        navigationView.setNavigationItemSelectedListener(this);
        window.setStatusBarColor(getColor(R.color.FandH));
        //navImg=(ImageView)findViewById(R.id.imageView9);
        //Picasso.get().load("http://192.168.0.112/img/a1.jpg").fit().into(navImg);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    /*case 0:tabLayout.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                        toolbar.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                        appBarLayout.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                        window.setStatusBarColor(getColor(R.color.colorPrimaryDark));break;*/
                    case 0:tabLayout.setBackgroundColor(getColor(R.color.FandH));
                        toolbar.setBackgroundColor(getColor(R.color.FandH));
                        appBarLayout.setBackgroundColor(getColor(R.color.FandH));
                        window.setStatusBarColor(getColor(R.color.FandH));break;
                    case 1:tabLayout.setBackgroundColor(getColor(R.color.WP));
                        toolbar.setBackgroundColor(getColor(R.color.WP));
                        appBarLayout.setBackgroundColor(getColor(R.color.WP));
                        window.setStatusBarColor(getColor(R.color.WP));break;
                    case 2:tabLayout.setBackgroundColor(getColor(R.color.Stat));
                        toolbar.setBackgroundColor(getColor(R.color.Stat));
                        appBarLayout.setBackgroundColor(getColor(R.color.Stat));
                        window.setStatusBarColor(getColor(R.color.Stat));break;
                    case 3:tabLayout.setBackgroundColor(getColor(R.color.Grocer));
                        toolbar.setBackgroundColor(getColor(R.color.Grocer));
                        appBarLayout.setBackgroundColor(getColor(R.color.Grocer));
                        window.setStatusBarColor(getColor(R.color.Grocer));break;
                    case 4:tabLayout.setBackgroundColor(getColor(R.color.MS));
                        toolbar.setBackgroundColor(getColor(R.color.MS));
                        appBarLayout.setBackgroundColor(getColor(R.color.MS));
                        window.setStatusBarColor(getColor(R.color.MS));break;
                    case 5:tabLayout.setBackgroundColor(getColor(R.color.wifi));
                        toolbar.setBackgroundColor(getColor(R.color.wifi));
                        appBarLayout.setBackgroundColor(getColor(R.color.wifi));
                        window.setStatusBarColor(getColor(R.color.wifi));break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (atog.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        clickcount++;
        if(clickcount==1)
        {
            Toast.makeText(getApplicationContext(),"Press Back Again to Exit",Toast.LENGTH_SHORT).show();
        }
        else {
            super.onBackPressed();
        }
    }

    public void logout()
    {
        sharedpreferences= getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedpreferences.edit();
        editor.clear();
        editor.apply();
        finish();
        startActivity(new Intent(MainActivity.this,LogandSign.class));
        return;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_liked:
                startActivity(new Intent(MainActivity.this,FavouritesActivity.class));
                break;
            case R.id.logout:
                logout();
                break;

        }
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        atog.syncState();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private View rootView;
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView =null;
            switch (getArguments().getInt(ARG_SECTION_NUMBER))
            {
                case 1:rootView=inflater.inflate(R.layout.fragment_tab_home, container, false);
                    break;
                case 2:rootView=inflater.inflate(R.layout.fragment_tab_fh, container, false);
                    break;
                case 3:rootView=inflater.inflate(R.layout.fragment_tab_fur, container, false);
                    break;
                case 4:rootView=inflater.inflate(R.layout.fragment_tab_gro, container, false);
                    break;
                case 5:rootView=inflater.inflate(R.layout.fragment_tab_m, container, false);
                    break;
                case 6:rootView=inflater.inflate(R.layout.fragment_tab_stat, container, false);
                    break;
            }
            Log.w("akshay123",":" + getArguments().getInt(ARG_SECTION_NUMBER));

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fr= null;
            switch (position)
            {
                /*case 0:fr= new TabHome();
                    break;*/
                case 0:fr= new TabFH();
                    break;
                case 1:fr= new TabFur();
                    break;
                case 2:fr= new TabGro();
                    break;
                case 3:fr= new TabMS();
                    break;
                case 4:fr= new TabStat();
                    break;
                case 5:fr=new TabWifi();
                break;
            }
            Log.w("akshay",":" + position);
            return fr;
            //return PlaceholderFragment.newInstance(position+1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }
    }
}
