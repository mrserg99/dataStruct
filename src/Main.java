import Many2Many.ManyToMany;

public class Main {
    public static void main(String[] args) {
            ManyToMany m = new ManyToMany();
            m.addStudentToCourse("Bob".toCharArray(), 2);
            m.addStudentToCourse("Bob".toCharArray(), 1);
            m.addStudentToCourse("Alice".toCharArray(), 1);
            m.addStudentToCourse("Alice".toCharArray(), 3);
            m.addStudentToCourse("John".toCharArray(), 2);

            System.out.print("Список курсов студента Bob: ");
            m.printCoursesOfStudent("Bob".toCharArray());
            System.out.println();
            System.out.print("Список курсов студента Alice: ");
            m.printCoursesOfStudent("Alice".toCharArray());
            System.out.println();
            System.out.print("Все студенты на первом курсе: ");
            m.printStudentsOfCourse(1);
            System.out.println();
            System.out.print("Все студенты на втором курсе: ");
            m.printStudentsOfCourse(2);
            System.out.println();
            System.out.println("Удаляем всех студентов со второго курса");
            m.removeCourse(2);
            System.out.println();
            System.out.print("Все студенты на втором курсе: ");
            m.printStudentsOfCourse(2);
            System.out.println();
    }
}
