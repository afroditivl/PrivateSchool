package entities;

import java.util.Objects;

public class Grade {
    private int gid;
    private Student student;
    private Course course;
    private Assignment assignment;
    private double ormark;
    private double tmark;

    public Grade() {
    }

    public Grade(Course course, Assignment assignment, double ormark, double tmark) {
        this.course = course;
        this.assignment = assignment;
        this.ormark = ormark;
        this.tmark = tmark;
    }
    
    

    public Grade(Student student, Course course, Assignment assignment, double ormark, double tmark) {
        this.student = student;
        this.course = course;
        this.assignment = assignment;
        this.ormark = ormark;
        this.tmark = tmark;
    }

    public Grade(int gid, Student student, Course course, Assignment assignment, double ormark, double tmark) {
        this.gid = gid;
        this.student = student;
        this.course = course;
        this.assignment = assignment;
        this.ormark = ormark;
        this.tmark = tmark;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public double getOrmark() {
        return ormark;
    }

    public void setOrmark(double ormark) {
        this.ormark = ormark;
    }

    public double getTmark() {
        return tmark;
    }

    public void setTmark(double tmark) {
        this.tmark = tmark;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.gid;
        hash = 61 * hash + Objects.hashCode(this.student);
        hash = 61 * hash + Objects.hashCode(this.course);
        hash = 61 * hash + Objects.hashCode(this.assignment);
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.ormark) ^ (Double.doubleToLongBits(this.ormark) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.tmark) ^ (Double.doubleToLongBits(this.tmark) >>> 32));
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
        final Grade other = (Grade) obj;
        if (this.gid != other.gid) {
            return false;
        }
        if (Double.doubleToLongBits(this.ormark) != Double.doubleToLongBits(other.ormark)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tmark) != Double.doubleToLongBits(other.tmark)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return assignment + ", oral_mark=" + ormark + ", total_mark=" + tmark + ", COURSE=" + course;
    }
    
}
