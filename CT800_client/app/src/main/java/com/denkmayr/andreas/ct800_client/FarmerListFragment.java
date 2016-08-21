package com.denkmayr.andreas.ct800_client;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.denkmayr.andreas.ct800_client.Adapter.FarmerAdapter;
import com.denkmayr.andreas.ct800_client.Database.FarmerRepository;
import com.denkmayr.andreas.ct800_client.Entity.Farmer;
import com.denkmayr.andreas.ct800_client.Interfaces.OnFragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FarmerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FarmerListFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;

    private FarmerRepository fr;

    private ListView lvFarmers;

    public FarmerListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment FarmerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    static FarmerListFragment newInstance() {
        FarmerListFragment fragment = new FarmerListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fr = FarmerRepository.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_farmer_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle("Choose a Farmer!");
        lvFarmers = (ListView) view.findViewById(R.id.lvFarmers);
        lvFarmers.setAdapter(new FarmerAdapter(getActivity(), fr.getAllFarmers()));
        lvFarmers.setOnItemClickListener(this);
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
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FarmerFragment farmerFragment = (FarmerFragment) fm.findFragmentById(R.id.mainFraPlace);

        //Set this farmer as the current Farmer
        farmerFragment.onFinishChooseFarmer((Farmer) adapterView.getItemAtPosition(i));
        this.dismiss();
    }
}
