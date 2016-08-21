package com.denkmayr.andreas.ct800_client;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.denkmayr.andreas.ct800_client.Entity.Farmer;
import com.denkmayr.andreas.ct800_client.Interfaces.OnChooseFarmerDialogFinished;
import com.denkmayr.andreas.ct800_client.Interfaces.OnFragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FarmerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FarmerFragment extends Fragment implements OnChooseFarmerDialogFinished {

    private OnFragmentInteractionListener mListener;

    private Farmer farmer;

    private EditText etName;
    private EditText etEmail;
    private EditText etResidence;
    private EditText etZip;
    private EditText etStreet;
    private EditText etStreetNumber;
    private Button btSave;
    private Button btCancel;
    private ToggleButton toggleEditMode;
    private Button btChangeFarmer;

    public FarmerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *.
     * @return A new instance of fragment FarmerFragment.
     */
    static FarmerFragment newInstance(Farmer farmer) {
        FarmerFragment fragment = new FarmerFragment();
        Bundle args = new Bundle();
        args.putParcelable("farmer", farmer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_farmer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = (EditText) getActivity().findViewById(R.id.etFartmerName);
        etEmail = (EditText) getActivity().findViewById(R.id.etFarmerEmail);
        etResidence = (EditText) getActivity().findViewById(R.id.etFarmerResidence);
        etZip = (EditText) getActivity().findViewById(R.id.etFarmerZip);
        etStreet = (EditText) getActivity().findViewById(R.id.etFarmerStreet);
        etStreetNumber = (EditText) getActivity().findViewById(R.id.etFarmerStreetNumber);
        btSave = (Button) getActivity().findViewById(R.id.btFarmerSave);
        btCancel = (Button) getActivity().findViewById(R.id.btFarmerCancel);
        toggleEditMode = (ToggleButton) getActivity().findViewById(R.id.toggleFarmerEditMode);
        btChangeFarmer = (Button) getActivity().findViewById(R.id.btFarmerPopup);

        toggleEditMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshFields();
                setEditMode(toggleEditMode.isChecked());
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAction();
                setEditMode(false);
                toggleEditMode.setChecked(false);
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshFields(); //Cancel by refreshing all fields
                setEditMode(false);
                toggleEditMode.setChecked(false);
            }
        });

        btChangeFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        //Get farmer and set fields with relevant information
        farmer = getArguments().getParcelable("farmer");

        if (farmer != null) {
            etName.setText(farmer.getName());
            etEmail.setText(farmer.getEmail());
            etResidence.setText(farmer.getResidence());
            etZip.setText(farmer.getZip());
            etStreet.setText(farmer.getStreet());
            etStreetNumber.setText(farmer.getStreetNumber());
        }

        setEditMode(false);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void showPopup() {
        FarmerListFragment farmerListFragment = FarmerListFragment.newInstance();
        farmerListFragment.show(getActivity().getSupportFragmentManager(), "choose_farmer");
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

    private void setEditMode(Boolean editMode)
    {
        //Black as TextColor
        etName.setTextColor(Color.BLACK);
        etEmail.setTextColor(Color.BLACK);
        etResidence.setTextColor(Color.BLACK);
        etZip.setTextColor(Color.BLACK);
        etStreet.setTextColor(Color.BLACK);
        etStreetNumber.setTextColor(Color.BLACK);

        //Enable or disable all Fields
        etName.setEnabled(editMode);
        etEmail.setEnabled(editMode);
        etResidence.setEnabled(editMode);
        etZip.setEnabled(editMode);
        etStreet.setEnabled(editMode);
        etStreetNumber.setEnabled(editMode);
        if(editMode) //Show save and Edit-Buttons in editMode otherwise disable them
        {
            btSave.setVisibility(View.VISIBLE);
            btCancel.setVisibility(View.VISIBLE);
        } else {
            btSave.setVisibility(View.INVISIBLE);
            btCancel.setVisibility(View.INVISIBLE);
        }
    }

    private void saveAction() {
        farmer.setName(etName.getText().toString());
        farmer.setEmail(etEmail.getText().toString());
        farmer.setResidence(etResidence.getText().toString());
        farmer.setZip(etZip.getText().toString());
        farmer.setStreet(etStreet.getText().toString());
        farmer.setStreetNumber(etStreetNumber.getText().toString());

        //TODO update in DB
    }

    private void refreshFields() {
        etName.setText(farmer.getName());
        etEmail.setText(farmer.getEmail());
        etResidence.setText(farmer.getResidence());
        etZip.setText(farmer.getZip());
        etStreet.setText(farmer.getStreet());
        etStreetNumber.setText(farmer.getStreetNumber());
    }

    @Override
    public void onFinishChooseFarmer(Farmer selectedFarmer) {
        farmer = selectedFarmer;
        refreshFields();
    }
}
