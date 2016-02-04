package dana.cuaca;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    ImageView imageView2;
    TextView cuacaView2;
    TextView tanggalView2;
    TextView namaView;
    private ProgressDialog progress;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuaca);
        //Show loading
        progress=new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.show();
        //Receive post data
        btnSubmit=(Button)findViewById(R.id.setting);
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
                String tanggal_res2=response.body().getTanggalBesok();
                String cuaca_res2=response.body().getCuacaBesok();
                String image_res2=response.body().getImageBesok();
                cuacaView=(TextView) findViewById(R.id.cuaca);
                tanggalView=(TextView) findViewById(R.id.tanggal);
                namaView=(TextView) findViewById(R.id.nama);
                imageView=(ImageView) findViewById(R.id.image_cuaca);
                cuacaView.setText(cuaca_res);
                tanggalView.setText(tanggal_res);
                namaView.setText(name_res);

                cuacaView2=(TextView) findViewById(R.id.cuaca_besok);
                tanggalView2=(TextView) findViewById(R.id.tanggal_besok);
                imageView2=(ImageView) findViewById(R.id.image_cuaca_besok);
                cuacaView2.setText(cuaca_res2);
                tanggalView2.setText(tanggal_res2);

                int resId = getResources().getIdentifier(image_res, "drawable", getPackageName());
                imageView.setImageResource(resId);

                int resId2 = getResources().getIdentifier(image_res2, "drawable", getPackageName());
                imageView2.setImageResource(resId2);
                progress.hide();
                progress.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                // something went completely wrong (like no internet connection)
                Log.d("Error", t.getMessage());
            }


        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                finish();
                startActivity(new Intent(this, AboutActivity.class));
                return true;

            case R.id.setting:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
