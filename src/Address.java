public class Address {
    private String street = "Great Oaks Parkway";
    private String suite = "build.6087";
    private String city = "San Jose";
    private String zipcode = "95119";
    private Geo geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("street='").append(street).append('\'');
        sb.append(", suite='").append(suite).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", zipcode='").append(zipcode).append('\'');
        sb.append(", geo=").append(geo);
        sb.append('}');
        return sb.toString();
    }
}
