package dana.cuaca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by lutfianasari on 1/27/16.
 */
public class SplashScreenActivity extends AppCompatActivity {
    Realm realm = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        // Use them like regular java objects
        SettingDB setting = new SettingDB();
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
        RealmResults<SettingDB> result = query.findAll();
        Log.d("Size",Integer.toString(result.size()));
        //Check if setting=0
        if(result.size()==0){
            //Go to main activity
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(SplashScreenActivity.this, CuacaActivity.class);
            intent.putExtra("propinsi", "Aceh");
            intent.putExtra("kota", "Tapaktuan");
            startActivity(intent);
        }
    }
}
