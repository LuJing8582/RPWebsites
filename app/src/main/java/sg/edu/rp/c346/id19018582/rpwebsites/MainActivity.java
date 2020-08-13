package sg.edu.rp.c346.id19018582.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView tvCat,tvSubCat;
    Spinner spnCat,spnSubCat;
    Button btnGo;
    ArrayList<String> alSubcat;
    ArrayAdapter<String> aaSubcat;

    String[][] sites = {
            {"https://www.rp.edu.sg", "httpd://www.rp.edu.sg/student-life"},
            {"https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
            "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCat = findViewById(R.id.textViewCat);
        tvSubCat = findViewById(R.id.textViewSubCat);
        spnCat = findViewById(R.id.spinnerCat);
        spnSubCat = findViewById(R.id.spinnerSubCat);
        btnGo = findViewById(R.id.buttonGo);

        alSubcat = new ArrayList<>();
        aaSubcat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alSubcat);
        spnSubCat.setAdapter(aaSubcat);

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        String[] strSubcat = getResources().getStringArray(R.array.rpList);
                        alSubcat.clear();
                        alSubcat.addAll(Arrays.asList(strSubcat));
                        break;
                    case 1:
                        strSubcat = getResources().getStringArray(R.array.soiList);
                        alSubcat.clear();
                        alSubcat.addAll(Arrays.asList(strSubcat));
                        break;
                }
                /*if (position == 0) {
                    String[] strSubcat = getResources().getStringArray(R.array.rpList);
                    alSubcat.addAll(Arrays.asList(strSubcat));
                }
                else if (position == 1){
                    String[] strSubcat = getResources().getStringArray(R.array.soiList);
                    alSubcat.addAll(Arrays.asList(strSubcat));
                }*/
                aaSubcat.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = sites[spnCat.getSelectedItemPosition()][spnSubCat.getSelectedItemPosition()];
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }

        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        int catPos = spnCat.getSelectedItemPosition();
        int subCatPos = spnSubCat.getSelectedItemPosition();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        SharedPreferences.Editor prefEdit = pref.edit();

        prefEdit.putInt("posC", catPos);
        prefEdit.putInt("posSC", subCatPos);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        int catPos = pref.getInt("posC", 0);
        int subCatPos = pref.getInt("posSC", 0);

        spnCat.setSelection(catPos);

        alSubcat.clear();
        //obtain string array of sub menu
        if(catPos == 0){
            String[] strSubCat = getResources().getStringArray(R.array.rpList);
            alSubcat.addAll(Arrays.asList(strSubCat));
        } else if (catPos == 1) {
            String[] strSubCat = getResources().getStringArray(R.array.soiList);
            alSubcat.addAll(Arrays.asList(strSubCat));
        }
        spnSubCat.setSelection(subCatPos);

        aaSubcat.notifyDataSetChanged();
    }
}
