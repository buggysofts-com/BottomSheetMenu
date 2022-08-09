package com.buggysofts.bottomsheetmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BottomSheetMenuAdapter extends ArrayAdapter<MenuItem> {
    @ColorInt
    private Integer textColor;
    @ColorInt
    private Integer iconTint;
    @ColorInt
    private Integer expandTint;

    private Drawable menuIconPlaceHolder;
    private Drawable menuExpandIcon;

    /**
     * Use the default icons and current theme colors.
     */
    public BottomSheetMenuAdapter(@NonNull Context context,
                                  @NonNull List<MenuItem> objects) {
        super(context, R.layout.menu_item_node, objects);
    }

    /**
     * Use the default icons and custom colors.
     * Use <b>null</b> to apply default colors.
     * <br>
     * <br>
     * Defaults:
     * <br>- Text color: <b>Primary text color (<i>?android:attr/textColorPrimary</i>)</b>)
     * <br>- Menu icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Expand icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     */
    public BottomSheetMenuAdapter(@NonNull Context context,
                                  @NonNull List<MenuItem> objects,
                                  @ColorInt Integer textColor,
                                  @ColorInt Integer iconTint,
                                  @ColorInt Integer expandTint) {
        super(context, R.layout.menu_item_node, objects);

        this.textColor = textColor;
        this.iconTint = iconTint;
        this.expandTint = expandTint;
    }

    /**
     * Use custom icons and custom colors.
     * Use <b>null</b> to use default icons colors.
     * <br>
     * <br>
     * Defaults:
     * <br>- Test with other constructors or pass null values to see the default icons.
     * <br>- Text color: <b>Primary text color (<i>?android:attr/textColorPrimary</i>)</b>)
     * <br>- Menu icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     * <br>- Expand icon tint: <b>Theme default accent color (<i>?android:attr/colorAccent</i>)</b>
     */
    public BottomSheetMenuAdapter(@NonNull Context context,
                                  @NonNull List<MenuItem> objects,
                                  @Nullable Drawable menuIconPlaceHolder,
                                  @Nullable Drawable menuExpandIcon,
                                  @ColorInt Integer textColor,
                                  @ColorInt Integer iconTint,
                                  @ColorInt Integer expandTint) {
        super(context, R.layout.menu_item_node, objects);

        this.menuIconPlaceHolder = menuIconPlaceHolder;
        this.menuExpandIcon = menuExpandIcon;

        this.textColor = textColor;
        this.iconTint = iconTint;
        this.expandTint = expandTint;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                R.layout.menu_item_node,
                null
            );
            holder = new ViewHolder(
                convertView.findViewById(R.id.item_txt),
                convertView.findViewById(R.id.item_icon),
                convertView.findViewById(R.id.item_expand)
            );
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }

        // set view properties
        MenuItem item = getItem(position);
        if (item != null) {
            // apply texts and icons
            holder.item_txt.setText(item.getTitle());
            if (item.getIcon() != null) {
                holder.item_icon.setImageDrawable(item.getIcon());
            } else {
                if (menuIconPlaceHolder != null) {
                    holder.item_icon.setImageDrawable(menuIconPlaceHolder);
                } else {
                    // will use default one
                }
            }
            if (item.hasSubMenu()) {
                // make the expand icon visible if a submenu is available
                holder.item_expand.setVisibility(View.VISIBLE);

                if (menuExpandIcon != null) {
                    holder.item_expand.setImageDrawable(menuExpandIcon);
                } else {
                    // will use default one
                }
            } else {
                // hide the expand icon if no submenu is available
                holder.item_expand.setVisibility(View.INVISIBLE);
            }

            // apply colors and tints
            if (textColor != null) {
                holder.item_txt.setTextColor(textColor);
            } else {
                // will use default one
            }
            if (iconTint != null) {
                holder.item_icon.setColorFilter(iconTint);
            } else {
                // will use default one
            }
            if (expandTint != null) {
                holder.item_expand.setColorFilter(expandTint);
            } else {
                // will use default one
            }
        } else {
            // something is wrong - control should not be in here
        }

        return convertView;
    }

    private static class ViewHolder {
        public TextView item_txt;
        public ImageView item_icon;
        public ImageView item_expand;

        public ViewHolder(TextView item_txt, ImageView item_icon, ImageView item_expand) {
            this.item_txt = item_txt;
            this.item_icon = item_icon;
            this.item_expand = item_expand;
        }
    }
}
