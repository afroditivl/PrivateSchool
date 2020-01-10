package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Assignment {
    
    private int aid;
    private String title;
    private String description;
    private LocalDate subDate;
    private double oralMark;
    private double totalMark;

    public Assignment() {
    }
    
    public Assignment(String title, String description, LocalDate subDate, double oralMark, double totalMark) {
        this.title = title;
        this.description = description;
        this.subDate = subDate;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public Assignment(int aid, String title, String description, LocalDate subDate, double oralMark, double totalMark) {
        this.aid = aid;
        this.title = title;
        this.description = description;
        this.subDate = subDate;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSubDate() {
        return subDate;
    }

    public void setSubDate(LocalDate subDate) {
        this.subDate = subDate;
    }

    public double getOralMark() {
        return oralMark;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.aid;
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.subDate);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.oralMark) ^ (Double.doubleToLongBits(this.oralMark) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.totalMark) ^ (Double.doubleToLongBits(this.totalMark) >>> 32));
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
        final Assignment other = (Assignment) obj;
        if (this.aid != other.aid) {
            return false;
        }
        if (Double.doubleToLongBits(this.oralMark) != Double.doubleToLongBits(other.oralMark)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalMark) != Double.doubleToLongBits(other.totalMark)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.subDate, other.subDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID=" + aid + " " + title + ", " + description + ", subDate=" + subDate + ", max_oral_mark=" + oralMark + ", max_total_mark=" + totalMark;
    }

}
