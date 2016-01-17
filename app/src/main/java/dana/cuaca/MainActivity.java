package dana.cuaca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
