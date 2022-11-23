package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddVehicleFragment extends Fragment {

    public AddVehicleFragment() {
        // Required empty public constructor
    }

    EditText etYear, etMake, etModel, etPrice, etDescription;
    Button btnAddCar;
    FirebaseDatabase database;
    DatabaseReference carsDBRef;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_vehicle,container, false);

        etYear = v.findViewById(R.id.year);
        etMake = v.findViewById(R.id.make);
        etModel = v.findViewById(R.id.model);
        etPrice = v.findViewById(R.id.price);
        etDescription = v.findViewById(R.id.description);
        btnAddCar = v.findViewById(R.id.btnAddCar);

        database = FirebaseDatabase.getInstance();
        carsDBRef = FirebaseDatabase.getInstance().getReference().child("Cars");

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertCarData();
            }
        });

        return v;

    }

    private void insertCarData() {


        int year = Integer.parseInt(etYear.getText().toString());
        String make = etMake.getText().toString();
        String model = etModel.getText().toString();
        int price = Integer.parseInt(etPrice.getText().toString());
        String description = etDescription.getText().toString();


        Cars car = new Cars(year,make,model,price,description);

        carsDBRef.push().setValue(car);
        Toast.makeText(getActivity(),"car added to the inventory",Toast.LENGTH_SHORT).show();
    }


}