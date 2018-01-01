package recyclerview;

/**
 * Created by samrith on 2/1/18.
 */


public class Professor extends RowItem {

    int rank;
    String office;
    String phone;

    public Professor(int id, String name, String image, int rank, String office, String phone) {
        super(id, name, image);
        this.rank = rank;
        this.office = office;
        this.phone = phone;
    }

    public Professor(int id, String name, int rank, String office, String phone) {
        super(id, name);
        this.rank = rank;
        this.office = office;
        this.phone = phone;
        this.image ="professor";
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


