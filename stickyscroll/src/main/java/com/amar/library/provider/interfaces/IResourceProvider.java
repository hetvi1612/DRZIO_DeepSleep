package com.amar.library.provider.interfaces;


import androidx.annotation.StyleableRes;

public interface IResourceProvider {
    int getResourceId(@StyleableRes final int styleResId);
    void recycle();
}
