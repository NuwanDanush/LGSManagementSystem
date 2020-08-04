package lgsapp.helpers;

public class Secratary{
    String fname;
    String lname;
    String wop;
    String office;
    String contact;
    String gender;
    String bday;
    String fappdate;
    String upgradedate;
    String retdate;
    String incdate;
    String inc;
    String beg;
    String mid;
    String end ;
    
    public Secratary(String fname, String lname, String wop, String office, String contact, String bday, String fappdate, String upgradedate, String retdate, String incdate, String inc, String beg, String mid, String end) {
        this.fname = fname;
        this.lname = lname;
        this.wop = wop;
        this.office = office;
        this.contact = contact;
        this.gender = gender;
        this.bday = bday;
        this.fappdate = fappdate;
        this.upgradedate = upgradedate;
        this.retdate = retdate;
        this.incdate = incdate;
        this.inc = inc;
        this.beg = beg;
        this.mid = mid;
        this.end = end;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setWop(String wop) {
        this.wop = wop;
    }

}