package dana.cuaca;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dana on 1/13/16.
 */
public class KotaDB extends RealmObject {

    @PrimaryKey
    private String          name;
    private int             propinsi_id;

    // Standard getters & setters
    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public Integer getPropinsiID() { return propinsi_id; }
    public void   setPropinsiID(int propinsi_id) { this.propinsi_id = propinsi_id; }
}
