package com.buggysofts.bottomsheetmenu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

class AttributeResolver {
    private final Context context;
    private final TypedValue typedValue;
    private final Resources.Theme theme;

    public AttributeResolver(@NonNull Context context) {
        this.context = context;
        this.typedValue = new TypedValue();
        this.theme = context.getTheme();
    }

    @Nullable
    @ColorInt
    public Integer getColor(int attrId) {
        boolean found = theme.resolveAttribute(attrId, typedValue, true);
        return found ? typedValue.data : null;
    }

    @Nullable
    public String getColorInHex(int attrId) {
        boolean found = theme.resolveAttribute(attrId, typedValue, true);
        return found ? String.format("#%06X", (0xFFFFFF & typedValue.data)) : null;
    }

    @Nullable
    public Drawable getDrawable(int attrId) {
        boolean found = theme.resolveAttribute(attrId, typedValue, true);
        if (found) {
            Drawable returningDrawable = null;
            try {
                returningDrawable = ContextCompat.getDrawable(
                    context,
                    typedValue.resourceId
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returningDrawable;
        } else {
            return null;
        }
    }
}
