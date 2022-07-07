public class Company {
    private String name = "CA Police";
    private String catchPhrase = "Stay safe, work hard, and never forget what your badge stands for";
    private String bs = "combating crime";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("name='").append(name).append('\'');
        sb.append(", catchPhrase='").append(catchPhrase).append('\'');
        sb.append(", bs='").append(bs).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
