package com.buggysofts.bottomsheetmenuimpl;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.buggysofts.bottomsheetmenu.BottomSheetMenu;

public class MainActivity extends AppCompatActivity {
    private Button show_menu_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        show_menu_btn = findViewById(R.id.show_menu_btn);
        show_menu_btn.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        new BottomSheetMenu(
                            MainActivity.this,
                            R.menu.sample_menu,
                            new BottomSheetMenu.MenuItemClickListener() {
                                @Override
                                public void onClick(MenuItem item) {
                                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        ).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
    }
}