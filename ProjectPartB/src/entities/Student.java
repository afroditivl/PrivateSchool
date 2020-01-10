package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Student {
    private int sid;
    private String sfname;
    private String lname;
    private LocalDate sdob;
    private double tfees;

    public Student() {
    }

    public Student(int sid, String sfname, String lname, LocalDate sdob, double tfees) {
        this.sid = sid;
        this.sfname = sfname;
        this.lname = lname;
        this.sdob = sdob;
        this.tfees = tfees;
    }

    public Student(String sfname, String lname, LocalDate sdob, double tfees) {
        this.sfname = sfname;
        this.lname = lname;
        this.sdob = sdob;
        this.tfees = tfees;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSfname() {
        return sfname;
    }

    public void setSfname(String sfname) {
        this.sfname = sfname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getSdob() {
        return sdob;
    }

    public void setSdob(LocalDate sdob) {
        this.sdob = sdob;
    }

    public double getTfees() {
        return tfees;
    }

    public void setTfees(int tfees) {
        this.tfees = tfees;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.sid;
        hash = 59 * hash + Objects.hashCode(this.sfname);
        hash = 59 * hash + Objects.hashCode(this.lname);
        hash = 59 * hash + Objects.hashCode(this.sdob);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.tfees) ^ (Double.doubleToLongBits(this.tfees) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.sid != other.sid) {
            return false;
        }
        if (this.tfees != other.tfees) {
            return false;
        }
        if (!Objects.equals(this.sfname, other.sfname)) {
            return false;
        }
        if (!Objects.equals(this.lname, other.lname)) {
            return false;
        }
        if (!Objects.equals(this.sdob, other.sdob)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID=" + sid + " " + sfname + ", " + lname + ", date of birth=" + sdob + ", tuition fees=" + tfees;
    }
    
    
}
