package com.buggysofts.bottomsheetmenu;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.PopupMenu;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

public class BottomSheetMenu {
    // non - modifiable components
    // host Context
    private final Context context;
    // menu to be shown
    private final Menu mainMenu;
    // main root view, menu list view
    private final View mainView;
    private final ListView menuList;
    private final View headerView;
    private final View footerView;
    private final TextView titleView;
    private final LinearLayout customHeaderHolder;
    private final LinearLayout customFooterHolder;
    // main bottom sheet dialog
    private final BottomSheetDialog dialog;
    // main menu item click listener
    private final MenuItemClickListener listener;

    // modifiable components
    // view selectors
    private ViewSelector headerViewSelector;
    private ViewSelector footerViewSelector;
    // menu properties
    private String menuTitle;
    private Drawable menuIconPlaceHolder;
    private Drawable menuExpandIcon;
    private Drawable dividerDrawable;
    private Drawable menuBackground;
    private Integer textColor;
    private Integer menuItemIconTint;
    private Integer expandIconTint;

    // internal control components
    // menu name tree
    private final List<String> menuTree;


    ///////////////
    // Constructors
    ///////////////

    /**
     * Minimal constructor. Default icons and colors will be used.
     * <br><br>
     * Defaults:
     * <br>- Text color: <b>Primary text color (<i>?android:attr/textColorPrimary</i>)</b>)
     * <br>- Menu icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Expand icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Menu background: <b>Theme default background (<i>?android:attr/colorBackground</i>)</b>
     * <br> You can also use the static getter methods to obtain the other default properties.
     */
    public BottomSheetMenu(@NonNull Context context,
                           @MenuRes int menuRes,
                           @NonNull MenuItemClickListener listener) throws Exception {
        this(
            context,
            menuRes,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            listener
        );
    }

    /**
     * Minimal constructor. Default icons and colors will be used.
     * <br><br>
     * Defaults:
     * <br>- Text color: <b>Primary text color (<i>?android:attr/textColorPrimary</i>)</b>)
     * <br>- Menu icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Expand icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Menu background: <b>Theme default background (<i>?android:attr/colorBackground</i>)</b>
     * <br> You can also use the static getter methods to obtain the other default properties.
     */
    public BottomSheetMenu(@NonNull Context context,
                           @NonNull Menu menu,
                           @NonNull MenuItemClickListener listener) throws Exception {
        this(
            context,
            menu,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            listener
        );
    }

    /**
     * Fully customized menu creation constructor. Use <b>null</b> drawables to use default icons, and <b>null</b> or <b>0</b> to apply default colors.
     * <br><br>
     * Defaults:
     * <br>- Pass null values to set the default icons.
     * <br>- Text color: <b>Primary text color (<i>?android:attr/textColorPrimary</i>)</b>)
     * <br>- Menu icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Expand icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Menu background: <b>Theme default background (<i>?android:attr/colorBackground</i>)</b>
     * <br> You can also use the static getter methods to obtain the other default properties.
     */
    public BottomSheetMenu(@NonNull Context context,
                           @MenuRes int menuRes,
                           @Nullable ViewSelector headerViewSelector,
                           @Nullable ViewSelector footerViewSelector,
                           @Nullable String menuTitle,
                           @Nullable Drawable menuIconPlaceHolder,
                           @Nullable Drawable menuExpandIcon,
                           @Nullable Drawable dividerDrawable,
                           @Nullable Drawable menuBackground,
                           @Nullable @ColorInt Integer textColor,
                           @Nullable @ColorInt Integer menuItemIconTint,
                           @Nullable @ColorInt Integer expandIconTint,
                           @NonNull MenuItemClickListener listener) throws Exception {
        this(
            context,
            getMenuFromMenuRes(context, menuRes),
            headerViewSelector,
            footerViewSelector,
            menuTitle,
            dividerDrawable,
            menuIconPlaceHolder,
            menuExpandIcon,
            menuBackground,
            textColor,
            menuItemIconTint,
            expandIconTint,
            listener
        );
    }

    /**
     * Fully customized menu creation constructor. Use <b>null</b> drawables to use default icons, and <b>null</b> or <b>0</b> to apply default colors.
     * <br><br>
     * Defaults:
     * <br>- Pass null values to set the default icons.
     * <br>- Text color: <b>Primary text color (<i>?android:attr/textColorPrimary</i>)</b>)
     * <br>- Menu icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Expand icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Menu background: <b>Theme default background (<i>?android:attr/colorBackground</i>)</b>
     * <br> You can also use the static getter methods to obtain the other default properties.
     */
    public BottomSheetMenu(@NonNull Context context,
                           @NonNull Menu menu,
                           @Nullable ViewSelector headerViewSelector,
                           @Nullable ViewSelector footerViewSelector,
                           @Nullable String menuTitle,
                           @Nullable Drawable menuIconPlaceHolder,
                           @Nullable Drawable menuExpandIcon,
                           @Nullable Drawable dividerDrawable,
                           @Nullable Drawable menuBackground,
                           @Nullable @ColorInt Integer textColor,
                           @Nullable @ColorInt Integer menuItemIconTint,
                           @Nullable @ColorInt Integer expandIconTint,
                           @NonNull MenuItemClickListener listener) throws Exception {
        this.context = context;
        this.mainMenu = menu;
        this.headerViewSelector = headerViewSelector;
        this.footerViewSelector = footerViewSelector;
        this.dividerDrawable = dividerDrawable;
        this.menuTitle = menuTitle;
        this.menuIconPlaceHolder = menuIconPlaceHolder;
        this.menuExpandIcon = menuExpandIcon;
        this.menuBackground = menuBackground;
        this.textColor = textColor;
        this.menuItemIconTint = menuItemIconTint;
        this.expandIconTint = expandIconTint;
        this.listener = listener;

        // init
        {
            // init data
            {
                menuTree = new ArrayList<>(0);
            }

            // init ui elements
            {
                // get an inflater handle
                LayoutInflater inflater = LayoutInflater.from(context);

                // initialize class field views
                mainView = inflater.inflate(
                    R.layout.bottom_sheet_menu_layout,
                    null
                );
                headerView = inflater.inflate(
                    R.layout.menu_header_view,
                    null
                );
                footerView = inflater.inflate(
                    R.layout.menu_footer_view,
                    null
                );
                menuList = mainView.findViewById(
                    R.id.menu_list
                );
                titleView = headerView.findViewById(
                    R.id.menu_title
                );
                customHeaderHolder = headerView.findViewById(
                    R.id.custom_header_holder
                );
                customFooterHolder = footerView.findViewById(
                    R.id.custom_footer_holder
                );

                dialog = new BottomSheetDialog(context);
                dialog.setContentView(mainView);
            }

            // init listeners
            {
                menuList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ListView mainList = (ListView) parent;
                            if (position >= 1 && position < mainList.getCount() - 1) {
                                // first dismiss
                                dialog.dismiss();

                                // get clicked item reference
                                MenuItem item = (MenuItem) parent.getItemAtPosition(position);

                                // perform click
                                listener.onClick(item);

                                // show submenu if available
                                if (item.hasSubMenu()) {
                                    show(item.getSubMenu(), item);
                                }
                            }
                        }
                    }
                );
            }
        }
    }


    /////////////////////////
    // initialization methods
    /////////////////////////

    private void applyProperties() {
        // add header and footer views in the list view
        menuList.setHeaderDividersEnabled(dividerDrawable != null);
        menuList.setFooterDividersEnabled(dividerDrawable != null);
        menuList.addHeaderView(
            headerView,
            null,
            dividerDrawable != null
        );
        menuList.addFooterView(
            footerView,
            null,
            dividerDrawable != null
        );

        // set initial properties to the views
        if (menuBackground != null) {
            mainView.setBackground(menuBackground);
        }
        if (dividerDrawable != null) {
            menuList.setDivider(dividerDrawable);
        }
        if (!TextUtils.isEmpty(menuTitle)) {
            menuTree.add(menuTitle);

            titleView.setText(Html.fromHtml(getFormattedMenuTitle(menuTree)));
            if (textColor != null) {
                titleView.setTextColor(textColor);
            }

            // make visible
            titleView.setVisibility(View.VISIBLE);
        } else {
            // add null to title list - this will be the only null in the list
            menuTree.add(null);

            // make invisible
            titleView.setVisibility(View.GONE);
        }

        // set initial views
        if (headerViewSelector != null && headerViewSelector.getInitialView() != null) {
            customHeaderHolder.addView(headerViewSelector.getInitialView());
        }
        if (footerViewSelector != null && footerViewSelector.getInitialView() != null) {
            customFooterHolder.addView(footerViewSelector.getInitialView());
        }
    }


    /////////////////
    // Public getters
    /////////////////

    @NonNull
    public Context getContext() {
        return context;
    }

    @NonNull
    public Menu getMainMenu() {
        return mainMenu;
    }

    @Nullable
    public ViewSelector getHeaderViewSelector() {
        return headerViewSelector;
    }

    @Nullable
    public ViewSelector getFooterViewSelector() {
        return footerViewSelector;
    }

    @Nullable
    public String getMenuTitle() {
        return menuTitle;
    }

    @Nullable
    public Drawable getMenuIconPlaceHolder() {
        return menuIconPlaceHolder;
    }

    @Nullable
    public Drawable getMenuExpandIcon() {
        return menuExpandIcon;
    }

    @Nullable
    public Drawable getDividerDrawable() {
        return dividerDrawable;
    }

    @Nullable
    @ColorInt
    public Integer getTextColor() {
        return textColor;
    }

    @Nullable
    @ColorInt
    public Integer getMenuItemIconTint() {
        return menuItemIconTint;
    }

    @Nullable
    @ColorInt
    public Integer getExpandIconTint() {
        return expandIconTint;
    }


    /////////////////
    // Public setters
    /////////////////

    /**
     * Set a header view selector based on parent menu item of current submenu, ana an initial header view. If not specified, no footer views will be applied.
     */
    public BottomSheetMenu headerViewSelector(@Nullable ViewSelector headerViewSelector) {
        this.headerViewSelector = headerViewSelector;
        return this;
    }

    /**
     * Set a footer view selector based on parent menu item of current submenu, ana an initial footer view. If not specified, no header views will be applied.
     */
    public BottomSheetMenu footerViewSelector(@Nullable ViewSelector footerViewSelector) {
        this.footerViewSelector = footerViewSelector;
        return this;
    }

    /**
     * Set title of this menu. If not specified, main menu icon will not be shown, but going to a submenu will make its path appear on the ui.
     */
    public BottomSheetMenu menuTitle(@Nullable String menuTitle) {
        this.menuTitle = menuTitle;
        return this;
    }

    /**
     * Set a placeholder drawable to show in case a menu item does not contain any icon. If not specified, default one will be used. To apply default manually, use the available static method.
     */
    public BottomSheetMenu menuIconPlaceHolder(@Nullable Drawable menuIconPlaceHolder) {
        this.menuIconPlaceHolder = menuIconPlaceHolder;
        return this;
    }

    /**
     * Set a drawable to show at the end of a menu item containing submenu. If a menu item does not contain submenu, it will not be visible. If not specified, default one will be used. To apply default manually, use the available static method.
     */
    public BottomSheetMenu menuExpandIcon(@Nullable Drawable menuExpandIcon) {
        this.menuExpandIcon = menuExpandIcon;
        return this;
    }

    /**
     * Set the divider drawable. This divider will divide the menu items, and will also be applied to the bottom of header view and at the top of footer view. If not specified, no dividers will be used. If you want to apply the system default divider, use the available static method.
     */
    public BottomSheetMenu dividerDrawable(@Nullable Drawable dividerDrawable) {
        this.dividerDrawable = dividerDrawable;
        return this;
    }

    /**
     * Set a background to the entire menu ui. If not specified, default one will be used. To apply default manually, use the available static method.
     */
    public BottomSheetMenu menuBackground(@Nullable Drawable menuBackground) {
        this.menuBackground = menuBackground;
        return this;
    }

    /**
     * Set a background color to the entire menu ui. If not specified, default one will be used. To apply default manually, use the available static method.
     */
    public BottomSheetMenu menuBackgroundColor(@ColorInt @Nullable Integer menuBackgroundColor) {
        this.menuBackground =
            menuBackgroundColor != null ?
                new ColorDrawable(menuBackgroundColor) :
                null;

        return this;
    }

    /**
     * Set text color of the text components. If not specified, default one will be used. To apply default manually, use the available static method.
     */
    public BottomSheetMenu textColor(@ColorInt @Nullable Integer textColor) {
        this.textColor = textColor;
        return this;
    }

    /**
     * Set menu item icon tint color. If not specified, default one will be used. To apply default manually, use the available static method.
     */
    public BottomSheetMenu iconTint(@ColorInt @Nullable Integer iconTint) {
        this.menuItemIconTint = iconTint;
        return this;
    }

    /**
     * Set expand icon tint color. If not specified, default one will be used. To apply default manually, use the available static method.
     */
    public BottomSheetMenu expandIconTint(@ColorInt @Nullable Integer expandTint) {
        this.expandIconTint = expandTint;
        return this;
    }


    /////////////
    // Open menu
    /////////////

    /**
     * Open the menu.
     */
    public void show() {
        // apply properties before first open
        applyProperties();

        // show
        show(mainMenu, null);
    }

    /**
     * Open a submenu.
     */
    private void show(@NonNull Menu menu, @Nullable MenuItem parent) {
        if (parent != null) {
            // add to mame tree
            if (parent.getTitle() != null) {
                menuTree.add(parent.getTitle().toString());
            }

            // reset header and footer views
            customHeaderHolder.removeAllViews();
            customFooterHolder.removeAllViews();

            // set new menu title
            // make visible first
            titleView.setVisibility(View.VISIBLE);
            // now set text
            titleView.setText(
                Html.fromHtml(
                    getFormattedMenuTitle(
                        menuTree
                    )
                )
            );

            // set new header view
            View headerView = headerViewSelector != null ? headerViewSelector.selectViewForItem(parent) : null;
            if (headerView != null) {
                customHeaderHolder.addView(headerView);
            }
            // set new footer view
            View footerView = footerViewSelector != null ? footerViewSelector.selectViewForItem(parent) : null;
            if (footerView != null) {
                customFooterHolder.addView(footerView);
            }
        } else {
            // initial menu
            // initial header and footer views were already populated in initializeViews()
        }

        List<MenuItem> menuItems = new ArrayList<>(0);
        for (int i = 0; i < menu.size(); ++i) {
            menuItems.add(menu.getItem(i));
        }

        menuList.setAdapter(
            new BottomSheetMenuAdapter(
                context,
                menuItems,
                menuIconPlaceHolder,
                menuExpandIcon,
                textColor,
                menuItemIconTint,
                expandIconTint
            )
        );

        dialog.show();
    }


    /////////////////////////
    // Static default getters
    /////////////////////////

    private static Menu getMenuFromMenuRes(Context Context, int menuRes) throws Exception {
        // We will use popup menu to give us a populated Menu instance.
        // As we can not create menus directly (MenuBuilder can only be called from its own library group), this is our easiest way.
        // We will pass a dummy view as anchor - since we are not going to use the popup menu, it does not matter.
        PopupMenu popupMenu = new PopupMenu(Context, new View(Context));
        popupMenu.inflate(menuRes);
        return popupMenu.getMenu();
    }

    private static String getFormattedMenuTitle(List<String> namesList) {
        StringBuilder builder = new StringBuilder(0);
        for (int i = 0; i < namesList.size(); ++i) {
            if (i == 0) {
                if (namesList.get(i) == null) {
                    builder.append("◉");
                } else {
                    builder.append(String.format("<b>%s</b>", namesList.get(i)));
                }
            } else {
                builder.append(String.format("%s", namesList.get(i)));
            }
            if (i < namesList.size() - 1) {
                builder.append("  »  ");
            }
        }

        String nameListStr = builder.toString();
        if (nameListStr.length() > 0) {
            nameListStr = String.format("%s", nameListStr);
        }

        return nameListStr;
    }

    /**
     * Get the default menu icon placeholder drawable.
     */
    public static Drawable getDefaultMenuIconPlaceHolder(Context context) {
        return AppCompatResources.getDrawable(context, R.drawable.ic_menu);
    }

    /**
     * Get the default expand icon drawable.
     */
    public static Drawable getDefaultExpandIcon(Context context) {
        return AppCompatResources.getDrawable(context, R.drawable.ic_arrow_right_stroke);
    }

    /**
     * Get the system provided default divider drawable. This may change depend on active theme.
     */
    public static Drawable getSystemDefaultDivider(Context context) {
        return new AttributeResolver(context).getDrawable(android.R.attr.listDivider);
    }

    /**
     * Get the system provided default window background. This may change depend on active theme.
     */
    public static Drawable getDefaultMenuBackground(Context context) {
        return new AttributeResolver(context).getDrawable(android.R.attr.colorBackground);
    }

    /**
     * Get the system provided text color. This may change depend on active theme.
     */
    @Nullable
    @ColorInt
    public static Integer getDefaultTextColor(Context context) {
        return new AttributeResolver(context).getColor(android.R.attr.textColorPrimary);
    }

    /**
     * Get the system provided default window background. This may change depend on active theme.
     */
    @Nullable
    @ColorInt
    public static Integer getDefaultMenuItemIconTint(Context context) {
        return new AttributeResolver(context).getColor(android.R.attr.colorAccent);
    }

    /**
     * Get the system provided default window background. This may change depend on active theme.
     */
    @Nullable
    @ColorInt
    public static Integer getDefaultExpandIconTint(Context context) {
        return new AttributeResolver(context).getColor(android.R.attr.colorAccent);
    }


    /////////////
    // Interfaces
    /////////////

    /**
     * Select a view to show.
     */
    public interface ViewSelector {
        /**
         * View to show in initial menu window. Return null if you don't want to show anything.
         */
        @Nullable
        public View getInitialView();

        /**
         * Select a view to show. Mainly used for BottomSheetMenu header or footer.
         *
         * @param item Parent menu item of currently visible menu.
         * @return If you want to have an always visible view, no matter what menu item you select, simply return it, or, if you do not want any view for any menu item, return null. For other use cases, derive a logic based on the passed menu item to return an appropriate view. Note that if you want to show the same view initially and in any submenu, return the same view from both <b><i>selectViewForItem()</i></b> & <b><i>getInitialView()</i></b>.
         */
        @Nullable
        public View selectViewForItem(MenuItem item);
    }

    public interface MenuItemClickListener {
        public void onClick(MenuItem item);
    }
}
