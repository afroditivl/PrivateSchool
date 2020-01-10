package entities;

import java.util.Objects;


public class TrainerCourse {
    
    private int tcid;
    private Trainer trainer;
    private Course course;

    public TrainerCourse() {
    }

    public TrainerCourse(Trainer trainer, Course course) {
        this.trainer = trainer;
        this.course = course;
    }

    public TrainerCourse(int tcid, Trainer trainer, Course course) {
        this.tcid = tcid;
        this.trainer = trainer;
        this.course = course;
    }

    public int getTcid() {
        return tcid;
    }

    public void setTcid(int tcid) {
        this.tcid = tcid;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.tcid;
        hash = 11 * hash + Objects.hashCode(this.trainer);
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
        final TrainerCourse other = (TrainerCourse) obj;
        if (this.tcid != other.tcid) {
            return false;
        }
        if (!Objects.equals(this.trainer, other.trainer)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrainerCourse{" + "tcid=" + tcid + ", " + trainer + ", " + course + '}';
    }

}
