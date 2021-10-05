package com.mpr.classinfinity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.mpr.classinfinity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    /**
                     * if we are in Activity then so i used getSupportFragmentManger()
                     * else if we were in fragment then use getFragmentManager()
                     */
                    case R.id.home:
                        FragmentTransaction homeTransaction = getSupportFragmentManager().beginTransaction();
                        homeTransaction.replace(R.id.home_fragment,new HomeFragment());
                        homeTransaction.commit();
                        break;

                    case R.id.setting_menu:
                        FragmentTransaction settingsTransaction = getSupportFragmentManager().beginTransaction();
                        settingsTransaction.replace(R.id.setting_fragment,new SettingsFragment());
                        settingsTransaction.commit();
                        break;

                    case R.id.chatBot:
                        FragmentTransaction chatBotTransaction = getSupportFragmentManager().beginTransaction();
                        chatBotTransaction.replace(R.id.chatBot_fragment,new ChatBotFragment());
                        chatBotTransaction.commit();
                        break;

                    case R.id.scanner:
                        FragmentTransaction scannerTransaction = getSupportFragmentManager().beginTransaction();
                        scannerTransaction.replace(R.id.scanner_fragment,new ScannerFragment());
                        scannerTransaction.commit();
                        break;

                    case R.id.books:
                        FragmentTransaction booksTransaction = getSupportFragmentManager().beginTransaction();
                        booksTransaction.replace(R.id.books_fragment,new BooksFragment());
                        booksTransaction.commit();
                        break;

                }
                return false;
            }
        });

    }
}