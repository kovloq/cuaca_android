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
        Realm.setDefaultConfiguration(config);
        try {
            realm = Realm.getDefaultInstance();
        } catch (RealmMigrationNeededException r) {
            //Realm.deleteRealm(config);
            realm = Realm.getDefaultInstance();
        }

        // Build the query looking at all users:
        final RealmResults<SettingDB> result = realm.where(SettingDB.class).equalTo("id", 1).findAll();
        // Execute the query:

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
                    SettingDB set = realm.createObject(SettingDB.class);
                    set.setId(1);
                    //Set Default to Aceh
                    set.setKota("Tapaktuan");
                    set.setPropinsi("Aceh");
                    realm.commitTransaction();

                    //Go to main activity
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    SettingDB results1 = realm.where(SettingDB.class).equalTo("id", 1).findFirst();

//                    Log.d("hasil",Integer.toString(results1.getId()));
                    Intent intent = new Intent(SplashScreenActivity.this, CuacaActivity.class);
                    intent.putExtra("propinsi",results1.getPropinsi());
                    intent.putExtra("kota", results1.getKota());
                    startActivity(intent);
                }
            }
        }, 2000);


    }
}
