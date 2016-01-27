package dana.cuaca;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dana on 1/27/16.
 */
public class SettingDB extends RealmObject {

    @PrimaryKey
    private Integer          id;
    private String          kota;
    private String          propinsi;

    // Standard getters & setters

    public String getKota() { return kota; }
    public void   setKota(String kota) { this.kota = kota; }

    public String getPropinsi() { return propinsi; }
    public void   setPropinsi(String propinsi) { this.propinsi = propinsi; }

    public Integer getId() { return id; }
    public void   setId(Integer id) { this.id = id; }
}
