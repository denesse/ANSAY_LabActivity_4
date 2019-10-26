package com.ansay.ansay_labactivity_4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] compNames, compCountries, compCEOs, compIndustries,compDescriptions;
    int[] logos = {R.drawable.bmw, R.drawable.ford, R.drawable.honda, R.drawable.hyundai, R.drawable.kia,
            R.drawable.mitsubishi, R.drawable.nissan, R.drawable.peugeot, R.drawable.porsche, R.drawable.renault,
            R.drawable.subaru, R.drawable.suzuki, R.drawable.toyota, R.drawable.volkswagen};

    ArrayList<TopCompanies> companies = new ArrayList<>();

    ListView listCompanies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TOP GLOBAL COMPANIES");

        compNames = getResources().getStringArray(R.array.companies);
        compCountries = getResources().getStringArray(R.array.country);
        compCEOs = getResources().getStringArray(R.array.ceo);
        compIndustries = getResources().getStringArray(R.array.industry);
        compDescriptions = getResources().getStringArray(R.array.info);

        for (int i=0; i < compNames.length; i++){
            companies.add(i, new TopCompanies(logos[i], compNames[i], compCountries[i],compCEOs[i],compIndustries[i],compDescriptions[i]));
        }

        CompanyAdapter adapter = new CompanyAdapter(this, R.layout.company,companies);

        listCompanies = findViewById(R.id.lvCompanies);
        listCompanies.setAdapter(adapter);
        listCompanies.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long id) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(logos[i]);
        dialog.setTitle(compNames[i]);
        /*dialog.setMessage(compNames[i] + " is located in " + compCountries[i] + "." +
                " It is owned by " + compCEOs[i] + " and is under the industry of " + compIndustries[i] + "."
                + " ");*/
        dialog.setMessage(compDescriptions[i]);
        dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, compNames[i] + " is located in " + compCountries[i] + ".",Toast.LENGTH_LONG).show();
            }
        });
        dialog.create().show();
    }
}
