package dana.cuaca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
    int city_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (Spinner)findViewById(R.id.city);
        province = (Spinner)findViewById(R.id.province);
        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String kota=province.getSelectedItem().toString();
                Log.d("pilih",kota);
                switch(kota){
                    case "Aceh" :
                        Log.d("pilih","aceh");
                        city_val=R.array.aceh_arrays;
                        break;
                    case "Sumatera Utara" :
                        Log.d("pilih","sumut");
                        city_val=R.array.sumut_arrays;
                        break;
                    case "Sumatera Barat" :
                        break;
                    case "Jambi" :
                        break;
                    case "Bengkulu" :
                        break;
                    case "Riau" :
                        break;
                    case "Riau Kepulauan" :
                        break;
                    case "Sumatera Selatan" :
                        break;
                    case "Bangka Belitung" :
                        break;
                    case "Lampung" :
                        break;
                    case "Banten" :
                        break;
                    case "Jabodetabek" :
                        break;
                    case "Jawa Barat" :
                        break;
                    case "Jawa Tengah" :
                        break;
                    case "DI Yogyakarta" :
                        break;
                    case "Jawa Timur" :
                        break;
                    case "Bali" :
                        break;
                    case "Nusa Tenggara Barat" :
                        break;
                    case "Nusa Tenggara Timur" :
                        break;
                    case "Kalimantan Barat" :
                        break;
                    case "Kalimantan Tengah" :
                        break;
                    case "Kalimantan Selatan" :
                        break;
                    case "Kalimantan Timur" :
                        break;
                    case "Gorontalo" :
                        break;
                    case "Sulawesi Utara" :
                        break;
                    case "Sulawesi Tengah" :
                        break;
                    case "Sulawesi Tenggara" :
                        break;
                    case "Sulawesi Selatan" :
                        break;
                    case "Sulawesi Barat" :
                        break;
                    case "Maluku" :
                        break;
                    case "Maluku Utara" :
                        break;
                    case "Papua Barat" :
                        break;
                    case "Papua" :
                        break;
                }
                ganti(city_val);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.aceh_arrays, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        city.setAdapter(adapter);
    }

    void ganti(int arr){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,  arr, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter);
    }
//    void sumut(){
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sumut_arrays, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        city.setAdapter(adapter);
//    }
}
