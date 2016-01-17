package dana.cuaca;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dana on 1/13/16.
 */
public class CuacaDB extends RealmObject{

    @PrimaryKey
    private String          name;
    private String          tanggal;
    private int             propinsi_id;
    private int             kota_id;

    // Standard getters & setters
    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public String getTanggal() { return tanggal; }
    public void   setTanggal(String tanggal) { this.tanggal = tanggal; }
    public int getPropinsi_id() { return propinsi_id; }
    public void   setPropinsi_id(int propinsi_id) { this.propinsi_id = propinsi_id; }
    public int getKota_id() { return kota_id; }
    public void   setKota_id(int kota_id) { this.kota_id = kota_id; }
}
