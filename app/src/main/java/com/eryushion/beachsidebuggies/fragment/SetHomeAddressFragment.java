package com.eryushion.beachsidebuggies.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.eryushion.beachsidebuggies.R;
import com.eryushion.beachsidebuggies.activity.MapsActivitys;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;


@SuppressLint("ValidFragment")
public class SetHomeAddressFragment extends Fragment {


    public SetHomeAddressFragment() {

    }

    private MapsActivitys activity() {
        return (MapsActivitys) getActivity();
    }

    @Nullable
    @Override
    @SuppressLint("InflateParams")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sethomeaddress, null);

        SupportPlaceAutocompleteFragment autocompleteFragment1 = (SupportPlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.place_fragment);
        autocompleteFragment1.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.d("RESULTPLACE", String.valueOf(place.getName()));
                Log.d("RESULTPLACE", String.valueOf(place.getId()));
                Log.d("RESULTPLACE", String.valueOf(place.getLatLng()));
                setShardPref(place);
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getActivity(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


    private void setShardPref(Place place)
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("HOMEADDRESS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("HOME_NAME", String.valueOf(place.getName()));
        editor.putString("HOME_PLACEID", place.getId());
        editor.putBoolean("ISSETHOME", true);
        editor.apply();
        getFragmentManager().popBackStack("back", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


}
