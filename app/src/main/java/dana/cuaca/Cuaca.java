package dana.cuaca;

/**
 * Created by Dana on 1/6/16.
 */
public class Cuaca {
    private String id;
    private String name;
    private String cuaca;
    private String image;
    private String tanggal;
    private String cuaca_besok;
    private String image_besok;
    private String tanggal_besok;

    public void setId(String Id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuaca(String cuaca) {
        this.cuaca = cuaca;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    //Get DB
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCuaca() {
        return cuaca;
    }

    public String getImage() {
        return image;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getCuacaBesok() {
        return cuaca_besok;
    }

    public String getImageBesok() {
        return image_besok;
    }

    public String getTanggalBesok() {
        return tanggal_besok;
    }
}
