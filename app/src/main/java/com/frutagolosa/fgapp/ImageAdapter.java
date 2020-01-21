package com.frutagolosa.fgapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;

public class ImageAdapter extends PagerAdapter {
  private Context mcontex;
  private String[] mImageIds= new String[]{"https://frutagolosa.com/FrutaGolosaApp/BannersApp/banner1.jpg","https://frutagolosa.com/FrutaGolosaApp/BannersApp/banner2.jpg","https://frutagolosa.com/FrutaGolosaApp/BannersApp/banner3.jpg","https://frutagolosa.com/FrutaGolosaApp/BannersApp/banner4.jpg","https://frutagolosa.com/FrutaGolosaApp/BannersApp/banner5.jpg","https://frutagolosa.com/FrutaGolosaApp/BannersApp/banner6.jpg","https://frutagolosa.com/FrutaGolosaApp/BannersApp/banner7.jpg","https://frutagolosa.com/FrutaGolosaApp/BannersApp/banner8.jpg"};
  ImageAdapter(Context context){
    mcontex= context;


  }
  @Override
  public int getCount() {


    return mImageIds.length;
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view==object;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    ImageView imageView= new ImageView(mcontex);

    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    Glide.with(imageView).asBitmap().load(mImageIds[position]).transition(BitmapTransitionOptions.withCrossFade(100)).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imageView);


    container.addView(imageView,0);
    return imageView;

  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((ImageView) object);
  }
}
