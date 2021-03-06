package dana.cuaca;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

/*
import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;*/

public class MainActivity extends AppCompatActivity {

    /*public interface CuacaService {
        @GET("/cuaca?id={id}")
        Call<List<Cuaca>> listCuacas(@Path("id") String id);
    }*/

    private Spinner province, city;
    private Button btnSubmit;
    Realm realm = null;
    int city_val;
//    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (Spinner)findViewById(R.id.city);
        province = (Spinner)findViewById(R.id.province);
        //Load Setting from DB
        realm = Realm.getDefaultInstance();
        RealmResults<SettingDB> result = realm.where(SettingDB.class).equalTo("id", 1).findAll();
//
        final SettingDB results1 = realm.where(SettingDB.class).equalTo("id", 1).findFirst();
        if (result.size() !=0){


            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.province_arrays, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            for(int i = 0; i < adapter.getCount(); i++) {
                if(adapter.getItem(i).equals(results1.getPropinsi())) {
                    province.setSelection(i);
                    city_val = city_select(results1.getPropinsi());
                    ganti(city_val,results1);
                    break;
                }
            }
//

        }
        //end

        btnSubmit=(Button)findViewById(R.id.okbutton);

        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                //((TextView) parentView.getChildAt(0)).setTextColor(Color.BLACK);
                String kota = province.getSelectedItem().toString();
                Log.d("pilih", kota);
                city_val = city_select(kota);
                ganti(city_val,results1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                Log.d("Not selected province", "not selected");
            }

        });


                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        progress=new ProgressDialog(MainActivity.this);
//                        progress.setMessage("Loading");
//                        progress.show();
                        //Set Setting ID to 1
                        String prop = province.getSelectedItem().toString();
                        Log.d("Prop", "kampret" + prop);
                        String kot = city.getSelectedItem().toString();
                        Log.d("Kot", "kampret" + kot);
                        Intent intent = new Intent(MainActivity.this, CuacaActivity.class);
                        realm = Realm.getDefaultInstance();
                        SettingDB setting = realm.where(SettingDB.class).equalTo("id", 1).findFirst();
                        realm.beginTransaction();
//                        Log.d("coba",setting.getKota());
                        setting.setKota(kot);
                        setting.setPropinsi(prop);
                        realm.commitTransaction();
                        intent.putExtra("propinsi", prop);
                        intent.putExtra("kota", kot);
                        finish();
                        startActivity(intent);
                    }
                });

            }

            void ganti(int arr,SettingDB results1) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arr, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                city.setAdapter(adapter);
                for(int i = 0; i < adapter.getCount(); i++) {
                    if(adapter.getItem(i).equals(results1.getKota())) {
                        Log.d("pilihan kota",adapter.getItem(i).toString());
                        city.setSelection(i);
                        break;
                    }
                }

            }



            Integer city_select(String kota){
                switch (kota) {
                    case "Aceh":
                        //Log.d("pilih","aceh");
                        city_val = R.array.aceh_arrays;
                        break;
                    case "Sumatera Utara":
                        //Log.d("pilih","sumut");
                        city_val = R.array.sumut_arrays;
                        break;
                    case "Sumatera Barat":
                        city_val = R.array.sumut_arrays;
                        break;
                    case "Jambi":
                        city_val = R.array.jambi_arrays;
                        break;
                    case "Bengkulu":
                        city_val = R.array.bengkulu_arrays;
                        break;
                    case "Riau":
                        city_val = R.array.riau_arrays;
                        break;
                    case "Riau Kepulauan":
                        city_val = R.array.riau_kep_arrays;
                        break;
                    case "Sumatera Selatan":
                        city_val = R.array.sumsel_arrays;
                        break;
                    case "Bangka Belitung":
                        city_val = R.array.bangka_arrays;
                        break;
                    case "Lampung":
                        city_val = R.array.lampung_arrays;
                        break;
                    case "Banten":
                        city_val = R.array.banten_arrays;
                        break;
                    case "Jabodetabek":
                        city_val = R.array.jabodetabek_arrays;
                        break;
                    case "Jawa Barat":
                        city_val = R.array.jabar;
                        break;
                    case "Jawa Tengah":
                        city_val = R.array.jateng;
                        break;
                    case "DI Yogyakarta":
                        city_val = R.array.jogja;
                        break;
                    case "Jawa Timur":
                        city_val = R.array.jatim;
                        break;
                    case "Bali":
                        city_val = R.array.bali;
                        break;
                    case "Nusa Tenggara Barat":
                        city_val = R.array.ntb;
                        break;
                    case "Nusa Tenggara Timur":
                        city_val = R.array.ntt;
                        break;
                    case "Kalimantan Barat":
                        city_val = R.array.kalbar;
                        break;
                    case "Kalimantan Tengah":
                        city_val = R.array.kalteng;
                        break;
                    case "Kalimantan Selatan":
                        city_val = R.array.kalsel;
                        break;
                    case "Kalimantan Timur":
                        city_val = R.array.kaltim;
                        break;
                    case "Gorontalo":
                        city_val = R.array.gorontalo;
                        break;
                    case "Sulawesi Utara":
                        city_val = R.array.sulut;
                        break;
                    case "Sulawesi Tengah":
                        city_val = R.array.sulteng;
                        break;
                    case "Sulawesi Tenggara":
                        city_val = R.array.sultenggara;
                        break;
                    case "Sulawesi Selatan":
                        city_val = R.array.sulsel;
                        break;
                    case "Sulawesi Barat":
                        city_val = R.array.sulbar;
                        break;
                    case "Maluku":
                        city_val = R.array.maluku;
                        break;
                    case "Maluku Utara":
                        city_val = R.array.malukuutara;
                        break;
                    case "Papua Barat":
                        city_val = R.array.papuabarat;
                        break;
                    case "Papua":
                        city_val = R.array.papua;
                        break;
                }
                return city_val;
            }
        }
