package dana.cuaca;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dana on 1/13/16.
 */
public class PropinsiDB extends RealmObject {

    @PrimaryKey
    private String          name;
    private String          today_url;
    private String          tomorrow_url;

    // Standard getters & setters
    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public String getToday_url() { return today_url; }
    public void   setToday_url(String today_url) { this.today_url = today_url; }
    public String getTomorrow_url() { return tomorrow_url; }
    public void   setTomorrow_url(String tomorrow_url) { this.tomorrow_url = tomorrow_url; }
}
