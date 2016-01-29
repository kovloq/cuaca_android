package dana.cuaca;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by Dana on 1/27/16.
 */
public class SplashScreenActivity extends AppCompatActivity {
    Realm realm = null;

    //Add Animation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        final SettingDB setting = new SettingDB();
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        try {
            realm = Realm.getInstance(SplashScreenActivity.this);
        } catch (RealmMigrationNeededException r) {
            Realm.deleteRealm(config);
            realm = Realm.getInstance(SplashScreenActivity.this);
        }
        // Build the query looking at all users:
        RealmQuery<SettingDB> query = realm.where(SettingDB.class);
        //Select Where ID =1 ;
        query.equalTo("id", 1);
        // Execute the query:
        final RealmResults<SettingDB> result = query.findAll();
        Log.d("Size", Integer.toString(result.size()));

        //Check if setting=0
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                if (result.size() == 0) {
                    //Insert to database

                    realm.beginTransaction();
                    setting.setId(1);
                    setting.setKota("");
                    setting.setPropinsi("");
                    realm.commitTransaction();

                    //Go to main activity
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, CuacaActivity.class);
                    intent.putExtra("propinsi", "Aceh");
                    intent.putExtra("kota", "Tapaktuan");
                    startActivity(intent);
                }
            }
        }, 2000);


    }
}
