package dana.cuaca;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.GsonConverterFactory;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Dana on 1/25/16.
 */
public class CuacaActivity  extends AppCompatActivity {

    public interface CuacaService {
        @FormUrlEncoded
        @POST("getcuaca.php")
        Call<Cuaca> getCuaca(@Field("propinsi") String prop, @Field("kota") String kot);
    }

    ImageView imageView;
    TextView cuacaView;
    TextView tanggalView;
    TextView namaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuaca);
        //Receive post data
        Bundle bundle= getIntent().getExtras();
        String propinsi=bundle.getString("propinsi");
        String kota=bundle.getString("kota");
        Log.d("Log Cuaca", propinsi);
        Log.d("Log Kota",kota);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://kovloq.com/cuaca/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CuacaService service = retrofit.create(CuacaService.class);
        Call<Cuaca> cuaca=service.getCuaca(propinsi,kota);
        cuaca.enqueue(new Callback<Cuaca>() {
            @Override
            public void onResponse(Response<Cuaca> response, Retrofit retrofit) {
                String name_res=response.body().getName();
                String tanggal_res=response.body().getTanggal();
                String cuaca_res=response.body().getCuaca();
                String image_res=response.body().getImage();
                cuacaView=(TextView) findViewById(R.id.cuaca);
                tanggalView=(TextView) findViewById(R.id.tanggal);
                namaView=(TextView) findViewById(R.id.nama);
                imageView=(ImageView) findViewById(R.id.image_cuaca);
                cuacaView.setText(cuaca_res);
                tanggalView.setText(tanggal_res);
                namaView.setText(name_res);
                int resId = getResources().getIdentifier(image_res, "drawable", getPackageName());
                imageView.setImageResource(resId);
//                switch(image_res){
//                    case "cerah_berawan.png" :
//                        imageView.setImageResource(R.drawable.cerah_berawan);
//                        break;
//                    case "hujan_lebat.png" :
//                        imageView.setImageResource(R.drawable.hujan_lebat);
//                        break;
//                    case "hujan_ringan.png" :
//                        imageView.setImageResource(R.drawable.hujan_ringan);
//                        break;
//                    case "hujan_sedang.png" :
//                        imageView.setImageResource(R.drawable.hujan_sedang);
//                        break;
//                }
                Log.d("Name", name_res);
                Log.d("Tanggal", tanggal_res);
                Log.d("Cuaca", cuaca_res);
                Log.d("Image", image_res);
            }

            @Override
            public void onFailure(Throwable t) {
                // something went completely wrong (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });


    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}
