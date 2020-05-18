package com.frutagolosa.fgapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.frutagolosa.fgapp.adapter.RecyclerAdapterArreglos;
import com.frutagolosa.fgapp.api.ApiClient;
import com.frutagolosa.fgapp.api.ApiInterFaceArreglo;
import com.frutagolosa.fgapp.model.Arreglos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConChocolateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConChocolateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class ConChocolateFragment extends Fragment  {
    public static final String IdArreglo="bc" ;
    public static final String Precio="precio" ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static final String ska="prueba" ;
    public static final String ska2="1" ;
    int cc1Nums=10;


    private OnFragmentInteractionListener mListener;
//referencias

    RecyclerView recyclerView;

    private RecyclerAdapterArreglos adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ApiInterFaceArreglo apiInterface;
    private List<Arreglos> arreglos;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    public ConChocolateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConChocolateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConChocolateFragment newInstance(String param1, String param2) {
        ConChocolateFragment fragment = new ConChocolateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);




        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista=inflater.inflate(R.layout.fragment_con_chocolate, container, false);
        recyclerView= (RecyclerView) vista.findViewById(R.id.recyclerId);
        final GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);

        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        apiInterface = ApiClient.getApiClient().create(ApiInterFaceArreglo.class);
String tip="Fruta Golosa";

        Call<List<Arreglos>> call = apiInterface.getArreglos("https://frutagolosa.com/FrutaGolosaApp/ArreglosEnApp.php?t="+tip+"&k=121523");
        call.enqueue(new Callback<List<Arreglos>>() {
            @Override
            public void onResponse(Call<List<Arreglos>> call, Response<List<Arreglos>> response) {
                if(response.body()!=null) {
                    arreglos = response.body();
                    adapter = new RecyclerAdapterArreglos(arreglos);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    adapter.notifyDataSetChanged();
                    recyclerView.setNestedScrollingEnabled(false);


                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String nombreArreglo = arreglos.get(recyclerView.getChildAdapterPosition(view)).getNombre();
                            String valorArreglo = arreglos.get(recyclerView.getChildAdapterPosition(view)).getValor();

                            // String tipoArreglo=arreglos.get(recyclerView.getChildAdapterPosition(view)).getNombre_arreglo();

                            Intent c = new Intent(getContext(), CompArreglo.class);
                            Intent re = new Intent(getContext(), CompArreglo.class);
                            re.putExtra(IdArreglo, nombreArreglo);
                            re.putExtra(Precio, valorArreglo);
                            startActivity(re);
                        }
                    });

                }     }

            @Override
            public void onFailure(Call<List<Arreglos>> call, Throwable t) {

            }


        });









        SharedPreferences preferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String nombreus=preferences.getString("nombreus","Registrese");
        String mailus=preferences.getString("mailus","No");
        String telefonous=preferences.getString("telefonous","No");






        if(nombreus.equals("Registrese")) {
            Intent ra = new Intent(getContext(), Login_ValidActivity.class);

            startActivity(ra);

        }













        return vista;
    }








    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        arreglos.clear();
        adapter.notifyDataSetChanged();
        Glide.get(getContext()).clearMemory();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

        void onFragmentInteraction(Uri uri);
    }
}
