package projectpartb;

import dataAccessObject.*;
import entities.*;
import java.util.*;
import java.util.Scanner;

public abstract class Method {

    Scanner scanner = new Scanner(System.in);

    public static void welcome(Scanner scanner) {
        System.out.println("WELCOME TO THE APPLICATION!");
        do {
            System.out.println("_________Main Menu_________");
            int a = seeOrTypeData(scanner);
            if (a == 1) {
                printData(scanner);
            }
            if (a == 2) {
                typeData(scanner);
            }
            if (a == 0) {
                System.out.println("Thank you for using my application!");
                break;
            }
        } while (true);
    }

    public static int seeOrTypeData(Scanner scanner) {
        String b;
        boolean valid;
        int d = 2;
        do {
            do {
                try {
                    System.out.println("If you want to see data, enter 1 \nIf you want to insert data, enter 2 \nFor exit, enter 0");
                    b = scanner.next();
                    d = Integer.parseInt(b);
                    valid = true;
                } catch (NumberFormatException e1) {
                    System.out.println("***INVALID INPUT***");
                    valid = false;
                }
            } while (!valid);
        } while (d != 1 && d != 2 && d != 0);
        return d;
    }

    public static int askYesOrNo(Scanner scanner, String text) {
        String b;
        boolean valid;
        int d = 2;
        do {
            do {
                try {
                    System.out.println("Do you want to " + text + "For yes, enter 1. For no, enter 2");
                    b = scanner.next();
                    d = Integer.parseInt(b);
                    valid = true;
                } catch (NumberFormatException e1) {
                    System.out.println("***INVALID INPUT***");
                    valid = false;
                }
            } while (!valid);
        } while (d != 1 && d != 2);
        return d;
    }

    public static void printData(Scanner scanner) {
        int f = -1;
        do {
            int b = userDecidesOutput(scanner);
            switch (b) {
                case 1:
                    printStudents();
                    break;
                case 2:
                    printTrainers();
                    break;
                case 3:
                    printAssignments();
                    break;
                case 4:
                    printCourses();
                    break;
                case 5:
                    printStudentsPerCourse(scanner);
                    break;
                case 6:
                    printTrainersPerCourse(scanner);
                    break;
                case 7:
                    printAssignmentsPerCourse(scanner);
                    break;
                case 8:
                    printGrades(scanner);
                    break;
                default:
                    StudentsWithMoreCourses(scanner);
            }
            f = askYesOrNo(scanner, "see more data?");
        } while (f == 1);
    }

    public static void printStudents() {
        StudentDao sdao = new StudentDao();
        List<Student> students = new ArrayList();
        students = sdao.findAll();
        System.out.println("------Students------");
        for (Student st : students) {
            System.out.println(st);
        }
    }

    public static void printAssignments() {
        AssignmentDao adao = new AssignmentDao();
        List<Assignment> assignments = new ArrayList();
        System.out.println("------Assignments------");
        assignments = adao.findAll();
        for (Assignment as : assignments) {
            System.out.println(as);
        }
    }

    public static void printTrainers() {
        TrainerDao tdao = new TrainerDao();
        List<Trainer> trainers = new ArrayList();
        System.out.println("------Trainers------");
        trainers = tdao.findAll();
        for (Trainer tr : trainers) {
            System.out.println(tr);
        }
    }

    public static void printCourses() {
        CourseDao cdao = new CourseDao();
        List<Course> courses = new ArrayList();
        System.out.println("------Courses------");
        courses = cdao.findAll();
        for (Course co : courses) {
            System.out.println(co);
        }
    }
    
    public static void printStudentsPerCourse(Scanner scanner){
        StudentCourseDao scdao = new StudentCourseDao();
        List<Student> students = new ArrayList();
        System.out.println("------Students per Course------");
        System.out.println("Please choose a course to see it's students: ");
        int id = chooseCourseById(scanner);
        students = scdao.findStByCourse(id);
        for(Student s : students) System.out.println(s);
    }
    
    public static void printTrainersPerCourse(Scanner scanner){
        TrainerCourseDao tcdao = new TrainerCourseDao();
        List<Trainer> trainers = new ArrayList();
        System.out.println("------Trainers per Course------");
        System.out.println("Please choose a course to see it's trainers: ");
        int id = chooseCourseById(scanner);
        trainers = tcdao.findTrByCourse(id);
        for(Trainer t : trainers) System.out.println(t);
    }
    
    public static void printAssignmentsPerCourse(Scanner scanner){
        AssignmentCourseDao acdao = new AssignmentCourseDao();
        List<Assignment> assignments = new ArrayList();
        System.out.println("------Assignments per Course------");
        System.out.println("Please choose a course to see it's assignments: ");
        int id = chooseCourseById(scanner);
        assignments = acdao.findAsByCourse(id);
        if(assignments.size()==0) System.out.println("There are not assignments for this course");
        for(Assignment a : assignments) System.out.println(a);
    }
    
    public static void printGrades(Scanner scanner){
        GradesDao gdao = new GradesDao();
        List<Grade> list = new ArrayList();
        System.out.println("------Assignments per Course per Student------");
        System.out.println("Please choose a student to see his/her assignments per course: ");
        int id = chooseStudentById(scanner);
        list = gdao.findGradesById(id);
        if(list.size()==0) System.out.println("There are not assignments for this student");
        for(Grade g : list) System.out.println(g);
    }
    
    public static void StudentsWithMoreCourses(Scanner scanner){
        StudentCourseDao scdao = new StudentCourseDao();
        List<Student> students = new ArrayList();
        students = scdao.StudentsWithManyCourses();
        for(Student s : students) System.out.println(s);
    }
    
    public static int chooseCourseById(Scanner scanner){
        int index;
        CourseDao cdao = new CourseDao();
        List<Course> courses = new ArrayList();
        courses = cdao.findAll();
        for(int j=0; j<courses.size(); j++){
            System.out.println("Number " + (j+1) + ": " + courses.get(j));
        }
        do{
            index = Scanners.scanPositiveInt(scanner, "the number of the course");
        }while(index>courses.size());
        int cid = courses.get(index-1).getCid();
        return cid;
    }
    
    public static int chooseStudentById(Scanner scanner){
        int index;
        StudentDao sdao = new StudentDao();
        List<Student> students = new ArrayList();
        students = sdao.findAll();
        for(int j=0; j<students.size(); j++){
            System.out.println("Number " + (j+1) + ": " + students.get(j));
        }
        do{
            index = Scanners.scanPositiveInt(scanner, "the number of the student");
        }while(index>students.size());
        int sid = students.get(index-1).getSid();
        return sid;
    }

    public static int userDecidesOutput(Scanner scanner) {
        String b;
        boolean valid;
        int d = 2;
        do {
            do {
                try {
                    System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n", "Please choose the type of data you want to see \n (enter the corresponding noumber):",
                            "1.Students", "2.Trainers", "3.Assignments", "4.Courses", "5.Students per Course", "6.Trainers per Course", "7.Assignments per Course",
                            "8.Assignments per Course per Student", "9.Students that belong to more than one course");
                    b = scanner.next();
                    d = Integer.parseInt(b);
                    valid = true;
                } catch (NumberFormatException e1) {
                    System.out.println("***INVALID INPUT***");
                    valid = false;
                }
            } while (!valid);
        } while (d < 1 || d > 9);
        return d;
    }

    public static int userDecidesInput(Scanner scanner) {
        boolean valid;
        String b;
        int a = -1;
        do {
            do {
                System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n", "Please choose the type of data you want to enter \n (enter the corresponding noumber):",
                        "1.Students", "2.Trainers", "3.Assignments", "4.Courses", "5.Students per course", "6.Trainers per course", "7.Assignments per student per course (--> students' grades)");
                try {
                    b = scanner.next();
                    a = Integer.parseInt(b);
                    valid = true;
                } catch (NumberFormatException e1) {
                    System.out.println("***INVALID INPUT***");
                    valid = false;
                }
            } while (!valid);
        } while (a < 1 || a > 7);
        return a;
    }

    public static void typeData(Scanner scanner) {
        int f = -1;
        do {
            int b = userDecidesInput(scanner);
            switch (b) {
                case 1:
                    Insert.insertStudent(scanner);
                    break;
                case 2:
                    Insert.insertTrainer(scanner);
                    break;
                case 3:
                    Insert.insertAssignment(scanner);
                    break;
                case 4:
                    Insert.insertCourse(scanner);
                    break;
                case 5:
                    Insert.insertStudentCourse(scanner);
                    break;
                case 6:
                    Insert.insertTrainerCourse(scanner);
                    break;
                default:
                    Insert.insertGrades(scanner);
            }
            f = askYesOrNo(scanner, "type more data?");
        } while (f == 1);
    }
}
