public class Geo {
    private String lat = "00-00-00";
    private String lng = "00-00-00";

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Geo{");
        sb.append("lat='").append(lat).append('\'');
        sb.append(", lng='").append(lng).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
