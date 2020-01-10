package projectpartb;

import dataAccessObject.*;
import entities.*;
import java.time.LocalDate;
import java.util.*;

public abstract class Insert {

    public static void insertStudent(Scanner scanner) {
        int a;
        do {
            StudentDao sdao = new StudentDao();
            String fname = Scanners.scanString(scanner, "student's first name");
            String lname = Scanners.scanString(scanner, "student's last name");
            LocalDate dob = Scanners.scanDate(scanner, "student's date of birth");
            double tfees = Scanners.scanPositiveDouble(scanner, "student's tuition fees");
            Student s = new Student(fname, lname, dob, tfees);
            sdao.create(s);
            a = Method.askYesOrNo(scanner, "add another student?");
        } while (a == 1);
    }

    public static void insertTrainer(Scanner scanner) {
        int a;
        do {
            TrainerDao tdao = new TrainerDao();
            String fname = Scanners.scanString(scanner, "trainer's first name");
            String lname = Scanners.scanString(scanner, "trainer's last name");
            String subject = Scanners.scanString(scanner, "trainer's subject");
            Trainer t = new Trainer(fname, lname, subject);
            tdao.create(t);
            a = Method.askYesOrNo(scanner, "add another trainer?");
        } while (a == 1);
    }

    public static void insertAssignment(Scanner scanner) {
        int b;
        do {
            AssignmentDao adao = new AssignmentDao();
            String title = Scanners.scanString(scanner, "assignment's title");
            String description = Scanners.scanString(scanner, "assignment's description");
            LocalDate subdate = Scanners.scanDate(scanner, "assignment's submission date");
            double ormark = Scanners.scanPositiveDouble(scanner, "assignment's oral mark");
            double tmark = Scanners.scanPositiveDouble(scanner, "assignment's total mark");
            Assignment a = new Assignment(title, description, subdate, ormark, tmark);
            adao.create(a);
            b = Method.askYesOrNo(scanner, "add another assignment?");
        } while (b == 1);
    }

    public static void insertCourse(Scanner scanner) {
        int b;
        do {
            CourseDao cdao = new CourseDao();
            String title = Scanners.scanString(scanner, "course's title");
            String stream = Scanners.scanString(scanner, "course's stream");
            String type = Scanners.scanString(scanner, "course's type");
            LocalDate sdate = Scanners.scanDate(scanner, "course's start date");
            LocalDate edate = null;
            do {
                edate = Scanners.scanDate(scanner, "course's end date");
            } while (edate.isBefore(sdate));
            Course c = new Course(title, stream, type, sdate, edate);
            cdao.create(c);
            b = Method.askYesOrNo(scanner, "add another course?");
        } while (b == 1);
    }

    public static void insertStudentCourse(Scanner scanner) {
        int b, w;
        do {
            StudentDao sdao = new StudentDao();
            CourseDao cdao = new CourseDao();
            StudentCourseDao scdao = new StudentCourseDao();
            Student s = new Student();
            Course c = new Course();
            List<Student> students = new ArrayList();
            List<Course> courses = new ArrayList();
            students = sdao.findAll();
            courses = cdao.findAll();
            for (int i = 0; i < students.size(); i++) {
                System.out.println("Number " + (i + 1) + ": " + students.get(i));
            }
            int index1 = -1, index2 = -1;
            do {
                index1 = Scanners.scanPositiveInt(scanner, "the number of the student");
            } while (index1 > students.size());
            int sid = students.get(index1 - 1).getSid();
            do {
                for (int j = 0; j < courses.size(); j++) {
                    System.out.println("Number " + (j + 1) + ": " + courses.get(j));
                }
                do {
                    index2 = Scanners.scanPositiveInt(scanner, "the number of the course");
                } while (index2 > courses.size());
                int cid = courses.get(index2 - 1).getCid();
                s = sdao.findById(sid);
                c = cdao.findById(cid);
                StudentCourse sc = new StudentCourse(s, c);
                scdao.create(sc);
                w = Method.askYesOrNo(scanner, "add this student to another course?");
            } while (w == 1);
            b = Method.askYesOrNo(scanner, "add another student to a course?");
        } while (b == 1);
    }

    public static void insertTrainerCourse(Scanner scanner) {
        int b, w;
        do {
            TrainerDao tdao = new TrainerDao();
            CourseDao cdao = new CourseDao();
            TrainerCourseDao tcdao = new TrainerCourseDao();
            Trainer t = new Trainer();
            Course c = new Course();
            List<Trainer> trainers = new ArrayList();
            List<Course> courses = new ArrayList();
            trainers = tdao.findAll();
            courses = cdao.findAll();
            for (int i = 0; i < trainers.size(); i++) {
                System.out.println("Number " + (i + 1) + ": " + trainers.get(i));
            }
            int index1 = -1, index2 = -1;
            do {
                index1 = Scanners.scanPositiveInt(scanner, "the number of the trainer");
            } while (index1 > trainers.size());
            int tid = trainers.get(index1 - 1).getTid();
            do {
                for (int j = 0; j < courses.size(); j++) {
                    System.out.println("Number " + (j + 1) + ": " + courses.get(j));
                }
                do {
                    index2 = Scanners.scanPositiveInt(scanner, "the number of the course");
                } while (index2 > courses.size());
                int cid = courses.get(index2 - 1).getCid();
                t = tdao.findById(tid);
                c = cdao.findById(cid);
                TrainerCourse sc = new TrainerCourse(t, c);
                tcdao.create(sc);
                w = Method.askYesOrNo(scanner, "add this trainer to another course?");
            } while (w == 1);
            b = Method.askYesOrNo(scanner, "add another trainer to a course?");
        } while (b == 1);
    }

    public static void insertGrades(Scanner scanner) {
        int b, w, e, k;
        do {
            StudentDao sdao = new StudentDao();
            CourseDao cdao = new CourseDao();
            AssignmentDao adao = new AssignmentDao();
            StudentCourseDao scdao = new StudentCourseDao();
            AssignmentCourseDao acdao = new AssignmentCourseDao();
            GradesDao gdao = new GradesDao();
            List<Student> students = new ArrayList();
            List<Course> courses = new ArrayList();
            List<Assignment> assignments = new ArrayList();
            students = sdao.findAll();
            for (int i = 0; i < students.size(); i++) {
                System.out.println("Number " + (i + 1) + ": " + students.get(i));
            }
            int index1 = -1, index2 = -1;
            do {
                index1 = Scanners.scanPositiveInt(scanner, "the number of the student");
            } while (index1 > students.size());
            int sid = students.get(index1 - 1).getSid();
            courses = scdao.findCoursesByStudent(sid);
            do {
                for (int j = 0; j < courses.size(); j++) {
                    System.out.println("Number " + (j + 1) + ": " + courses.get(j));
                }
                do {
                    index2 = Scanners.scanPositiveInt(scanner, "the number of the course");
                } while (index2 > courses.size());
                int cid = courses.get(index2 - 1).getCid();
                assignments = acdao.findAsByCourse(cid);
                do {
                    for (int j = 0; j < assignments.size(); j++) {
                        System.out.println("Number " + (j + 1) + ": " + assignments.get(j));
                    }
                    if(assignments.size() == 0){
                        System.out.println("There are no assignments for this course");
                        break;
                    }
                    do {
                        index2 = Scanners.scanPositiveInt(scanner, "the number of the assignment that you want to enter grades");
                    } while (index2 > assignments.size());
                    int aid = assignments.get(index2 - 1).getAid();
                    double ormark = Scanners.scanPositiveDouble(scanner, "student's oral mark for this assignment");
                    double tmark = Scanners.scanPositiveDouble(scanner, "student's total mark for this assignment");
                    k = gdao.findGrades(sid, cid, aid);
                    if(k==0){
                        Grade g = new Grade(sdao.findById(sid), cdao.findById(cid), adao.findById(aid), ormark, tmark);
                        gdao.create(g);
                    }else{
                        gdao.updateGradesOral(sid, cid, aid, ormark);
                        gdao.updateGradesTotal(sid, cid, aid, tmark);
                    }
                    assignments.remove(adao.findById(aid));
                    if (assignments.size() == 0) {
                        break;
                    }
                    e = Method.askYesOrNo(scanner, "add grades for this student in another assignment of this course?");
                } while (e == 1);
                courses.remove(cdao.findById(cid));
                if (courses.size() == 0) {
                    break;
                }
                w = Method.askYesOrNo(scanner, "add grades for this student in another course's assignments?");
            } while (w == 1);
            b = Method.askYesOrNo(scanner, "add grades to another student?");
        } while (b == 1);
    }
}
