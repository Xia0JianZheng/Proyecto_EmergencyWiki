package com.example.proyecto_emergencywiki;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.exoplayer2.SimpleExoPlayer;


public class TeamUpFragment extends Fragment {

    NavController navController;
    AHBottomNavigation bottomNavigation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_up, container, false);
        bottomNavigation = rootView.findViewById(R.id.bottom_navigation);
        initBottomNavigation();



        return rootView;    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        ImageView imageView1 = view.findViewById(R.id.kachi1);
        ImageView imageView2 = view.findViewById(R.id.kachi2);

        TextView draw10 = view.findViewById(R.id.draw10);



        draw10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }



        });


        OrientationEventListener orientationEventListener = new OrientationEventListener(this.getContext()) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (orientation < 0) {
                    return; // invalid orientation
                }

                if (orientation > 45 && orientation < 135) {

                    // landscape orientation
                    imageView1.setVisibility(View.GONE);
                    imageView2.setVisibility(View.VISIBLE);
                } else if (orientation > 225 && orientation < 315) {
                    // reverse landscape orientation
                    imageView1.setVisibility(View.GONE);
                    imageView2.setVisibility(View.VISIBLE);
                } else {
                    // portrait orientation
                    imageView1.findViewById(R.id.kachi1).setVisibility(View.VISIBLE);
                    imageView2.findViewById(R.id.kachi2).setVisibility(View.GONE);
                }
            }
        };
        orientationEventListener.enable();
    }

    private void initBottomNavigation() {
        AHBottomNavigationItem homeItem = new AHBottomNavigationItem("Home", R.drawable.home_logo);
        homeItem.setDrawable(R.drawable.home_logo);
        AHBottomNavigationItem charactersItem = new AHBottomNavigationItem("Characters", R.drawable.character_logo);
        AHBottomNavigationItem weaponsItem = new AHBottomNavigationItem("Weapons", R.drawable.weapon_logo);
        AHBottomNavigationItem artifactsItem = new AHBottomNavigationItem("Artifacts", R.drawable.artifact_logo);
        AHBottomNavigationItem teamUpItem = new AHBottomNavigationItem("TeamUp", R.drawable.team_up_logo);

        bottomNavigation.addItem(homeItem);
        bottomNavigation.addItem(charactersItem);
        bottomNavigation.addItem(weaponsItem);
        bottomNavigation.addItem(artifactsItem);
        bottomNavigation.addItem(teamUpItem);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#EEE8D4"));


        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottomNavigation.setCurrentItem(4);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        navController.navigate(R.id.homeFragment);
                        break;
                    case 1:
                        navController.navigate(R.id.charactersFragment);
                        break;
                    case 2:
                        navController.navigate(R.id.weaponsFragment);
                        break;
                    case 3:
                        navController.navigate(R.id.artifactsFragment);
                        break;
                    case 4:
                        navController.navigate(R.id.teamUpFragment);
                        break;
                }
                return true;
            }
        });
    }
}