package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Course {
    
    private int cid;
    private String ctitle;
    private String cstream;
    private String ctype;
    private LocalDate sdate;
    private LocalDate edate;

    public Course() {
    }

    public Course(int cid, String ctitle, String cstream, String ctype, LocalDate sdate, LocalDate edate) {
        this.cid = cid;
        this.ctitle = ctitle;
        this.cstream = cstream;
        this.ctype = ctype;
        this.sdate = sdate;
        this.edate = edate;
    }

    public Course(String ctitle, String cstream, String ctype, LocalDate sdate, LocalDate edate) {
        this.ctitle = ctitle;
        this.cstream = cstream;
        this.ctype = ctype;
        this.sdate = sdate;
        this.edate = edate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCstream() {
        return cstream;
    }

    public void setCstream(String cstream) {
        this.cstream = cstream;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public LocalDate getSdate() {
        return sdate;
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate;
    }

    public LocalDate getEdate() {
        return edate;
    }

    public void setEdate(LocalDate edate) {
        this.edate = edate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.cid;
        hash = 97 * hash + Objects.hashCode(this.ctitle);
        hash = 97 * hash + Objects.hashCode(this.cstream);
        hash = 97 * hash + Objects.hashCode(this.ctype);
        hash = 97 * hash + Objects.hashCode(this.sdate);
        hash = 97 * hash + Objects.hashCode(this.edate);
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
        final Course other = (Course) obj;
        if (this.cid != other.cid) {
            return false;
        }
        if (!Objects.equals(this.ctitle, other.ctitle)) {
            return false;
        }
        if (!Objects.equals(this.cstream, other.cstream)) {
            return false;
        }
        if (!Objects.equals(this.ctype, other.ctype)) {
            return false;
        }
        if (!Objects.equals(this.sdate, other.sdate)) {
            return false;
        }
        if (!Objects.equals(this.edate, other.edate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID=" + cid + ", ctitle=" + ctitle + ", cstream=" + cstream + ", ctype=" + ctype + ", sdate=" + sdate + ", edate=" + edate;
    }
}
