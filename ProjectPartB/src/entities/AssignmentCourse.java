package entities;

import java.util.Objects;

public class AssignmentCourse {
    
    private int acid;
    private Assignment assignment;
    private Course course;
    
    public AssignmentCourse() {
    }

    public AssignmentCourse(Assignment assignment, Course course) {
        this.assignment = assignment;
        this.course = course;
    }

    public AssignmentCourse(int acid, Assignment assignment, Course course) {
        this.acid = acid;
        this.assignment = assignment;
        this.course = course;
    }

    public int getAcid() {
        return acid;
    }

    public void setAcid(int acid) {
        this.acid = acid;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.acid;
        hash = 11 * hash + Objects.hashCode(this.assignment);
        hash = 11 * hash + Objects.hashCode(this.course);
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
        final AssignmentCourse other = (AssignmentCourse) obj;
        if (this.acid != other.acid) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssignmentCourse{" + "acid=" + acid + ", " + assignment + ", " + course + '}';
    }
    
}
