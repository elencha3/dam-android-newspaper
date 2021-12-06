package com.curso.ejercicio04;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InicioFragment extends Fragment {

    EditText url1et, url2et, name1et, name2et;
    String url1, url2, name1, name2;
    Button button1, button2;
    View vista;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        button1 = view.findViewById(R.id.add1);
        button2 = view.findViewById(R.id.add2);
        url1et = view.findViewById(R.id.periodicoET);
        url2et = view.findViewById(R.id.periodicoET2);
        name1et = view.findViewById(R.id.nameET);
        name2et = view.findViewById(R.id.nameET2);
        vista = view.findViewById(R.id.inicio_layout);
        MainActivity mainActivity = (MainActivity) getActivity();
        Pattern pattern = Pattern.compile("http");


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url1 = url1et.getText().toString();
                name1 = name1et.getText().toString();

                Matcher urlMatch = pattern.matcher(url1);
                boolean urlOk = urlMatch.lookingAt();


                if (!url1.isEmpty() && !name1.isEmpty()) {
                    if (urlOk) {
                        mainActivity.receiveUrl1(url1);
                        mainActivity.receiveName1(name1);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(R.string.periodico_anadido);
                        builder.setMessage(R.string.que_desea_hacer);
                        builder.setPositiveButton(R.string.ir_periodico,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mainActivity.cambiarPantalla(1);
                                    }
                                });
                        builder.setNegativeButton(R.string.anadir_otro,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    } else {
                        Snackbar.make(getView(), (R.string.introduce_valid_url),
                                Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(vista, (R.string.introduce_name_url), Snackbar.LENGTH_LONG).show();
                }
            }


        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url2 = url2et.getText().toString();
                name2 = name2et.getText().toString();

                Matcher urlMatch = pattern.matcher(url2);
                boolean urlOk = urlMatch.lookingAt();

                if (!url2.isEmpty() && !name2.isEmpty()) {
                    if (urlOk) {
                        mainActivity.receiveUrl2(url2);
                        mainActivity.receiveName2(name2);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(R.string.periodico_anadido);
                        builder.setMessage(R.string.que_desea_hacer);
                        builder.setPositiveButton(R.string.ir_periodico,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mainActivity.cambiarPantalla(2);
                                    }
                                });
                        builder.setNegativeButton(R.string.anadir_otro,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    } else {
                        Snackbar.make(vista, getString(R.string.introduce_valid_url),
                                Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(vista, getString(R.string.introduce_name_url),
                            Snackbar.LENGTH_LONG).show();
                }
            }
        });

        return view;

    }


}

