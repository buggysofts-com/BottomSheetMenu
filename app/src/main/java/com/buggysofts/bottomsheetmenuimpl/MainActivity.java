package com.buggysofts.bottomsheetmenuimpl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.buggysofts.bottomsheetmenu.BottomSheetMenu;

/**
 * Test or sample activity.
 **/

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
                    showBuilderBasedMenu();
                }

                private void showFullConstructorBasedMenu() {
                    try {
                        new BottomSheetMenu(
                            MainActivity.this,
                            R.menu.data_package_restore_options_menu,
                            new BottomSheetMenu.ViewSelector() {
                                @Nullable
                                @org.jetbrains.annotations.Nullable
                                @Override
                                public View getInitialView() {
                                    return null;
                                }

                                @Nullable
                                @org.jetbrains.annotations.Nullable
                                @Override
                                public View selectViewForItem(MenuItem item) {
                                    return null;
                                }
                            },
                            null,
                            "Installed app context menu",
                            null, null,
                            BottomSheetMenu.getSystemDefaultDivider(MainActivity.this),
                            null, null, null, null,
                            new BottomSheetMenu.MenuItemClickListener() {
                                @Override
                                public void onClick(MenuItem item) {

                                }
                            }
                        ).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                private void showBuilderBasedMenu() {
                    try {
                        new BottomSheetMenu(
                            MainActivity.this,
                            R.menu.data_package_restore_options_menu,
                            new BottomSheetMenu.MenuItemClickListener() {
                                @Override
                                public void onClick(MenuItem item) {
                                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        )
                            .headerViewSelector(new BottomSheetMenu.ViewSelector() {
                                @Nullable
                                @Override
                                public View getInitialView() {
                                    View inflate = getLayoutInflater().inflate(R.layout.sample_header, null);
                                    ImageView imageView = inflate.findViewById(R.id.imageView);
                                    TextView textView = inflate.findViewById(R.id.textView);
                                    imageView.setOnClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(MainActivity.this, "Hi from head!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    );
                                    textView.setOnClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(MainActivity.this, "Hi from body!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    );
                                    return inflate;
                                }

                                @Nullable
                                @Override
                                public View selectViewForItem(MenuItem item) {
                                    return getLayoutInflater().inflate(R.layout.sample_header, null);
                                }
                            }).footerViewSelector(new BottomSheetMenu.ViewSelector() {
                                @Nullable
                                @Override
                                public View getInitialView() {
                                    return getLayoutInflater().inflate(R.layout.sample_footer, null);
                                }

                                @Nullable
                                @Override
                                public View selectViewForItem(MenuItem item) {
                                    return getLayoutInflater().inflate(R.layout.sample_footer, null);
                                }
                            })
                            .iconTint(Color.parseColor("#666666"))
                            .expandIconTint(Color.parseColor("#666666"))
                            .dividerDrawable(BottomSheetMenu.getSystemDefaultDivider(MainActivity.this))
                            .show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
    }
}