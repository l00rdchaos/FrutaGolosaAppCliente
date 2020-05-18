package com.frutagolosa.fgapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContacNuFragment extends Fragment {
  ImageView a;

  public ContacNuFragment() {
    // Required empty public constructor
  }




  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view= inflater.inflate(R.layout.fragment_contac_nu,container,false);
    ImageView a=(ImageView) view.findViewById(R.id.Map1);
    ImageView b=(ImageView) view.findViewById(R.id.Map2);

    a.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String map = "http://maps.google.co.in/maps?q= -0.184794, -78.507358";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        getActivity().startActivity(intent);
      }
    });

    b.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String map = "http://maps.google.co.in/maps?q=-2.190755, -79.889706";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        getActivity().startActivity(intent);
      }
    });
    // Inflate the layout for this fragment
    return view;



  }

}
