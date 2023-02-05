package com.uta.appeventosuta.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appeventosuta.R;
import com.uta.appeventosuta.model.Person;

public class ProfileFragment extends Fragment {

    TextView txtName, txtBirtDate, txtEmail, txtPhone, txtRole;

    Person user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public ProfileFragment(Person user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("PERFIL");
        initComponents(view);
        loadUserData();
    }

    private void initComponents(View view) {
        txtName = view.findViewById(R.id.txtNameP);
        txtBirtDate = view.findViewById(R.id.txtBirthDateP);
        txtEmail = view.findViewById(R.id.txtEmailP);
        txtPhone = view.findViewById(R.id.txtPhoneP);
        txtRole = view.findViewById(R.id.txtRoleP);
    }

    private void loadUserData() {
        txtName.setText(String.format("%s %s", user.getName(), user.getLastName()));
        txtBirtDate.setText(user.getBirthDate());
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtRole.setText(user.getRole());
    }

}