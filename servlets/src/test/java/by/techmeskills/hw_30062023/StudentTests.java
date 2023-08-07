package by.techmeskills.hw_30062023;

import by.teachmeskills.hw_30062023.Sex;
import by.teachmeskills.hw_30062023.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentTests {
    private static Student student1;
    private static Student student2;
    private static Student student3;

    private static List<Student> actual;
    private static List<Student> actualBySexMale;

    private static List<Student> actualBySexFemale;


    @BeforeAll
    public static void setUp() {
        student1 = new Student("Ivan", 17, Sex.MALE);
        student2 = new Student("Anna", 19, Sex.FEMALE);
        student3 = new Student("Polina", 18, Sex.FEMALE);

        actual = new ArrayList<>();
        actual.add(student1);
        actual.add(student2);
        actual.add(student3);

        actualBySexMale = new ArrayList<>();
        actualBySexMale.add(student1);

        actualBySexFemale = new ArrayList<>();
        actualBySexFemale.add(student2);
        actualBySexFemale.add(student3);
    }

    @Test
    public void checkAllStudentsReturned() {
        List<Student> expected = Student.getAllStudents();
        assertEquals(expected, actual);
    }

    @Test
    public void checkAllStudentsReturned_NotNull() {
        List<Student> expected = Student.getAllStudents();
        assertNotNull(expected);
    }

    @Test
    public void getAllUsers_MALE() {
        List<Student> expected = Student.getAllStudentsBySex(Sex.MALE);
        assertEquals(expected, actualBySexMale);
    }
    @Test
    public void getAllUsers_FEMALE (){
        List<Student> expected = Student.getAllStudentsBySex(Sex.FEMALE);
        assertEquals(expected, actualBySexFemale);
    }
    @Test
    public void getStudentsCount_MALE() {
        int expected = Student.getStudentsCountWithSex(Sex.MALE);
        assertEquals(expected, actualBySexMale.size());
    }

    @Test
    public void getStudentsCount_FEMALE() {
        int expected = Student.getStudentsCountWithSex(Sex.FEMALE);
        assertEquals(expected, actualBySexFemale.size());
    }
    @Test
    public void checkSumOfAgeBySex_MALE() {
        int expected = Student.getSumOfAllStudentsAgeBySex(Sex.MALE);
        int result = 0;
        for (Student st : actualBySexMale) {
            result += st.getAge();
        }
        assertEquals(expected, result);
    }

    @Test
    public void checkSumOfAgeBySex_FEMALE() {
        int expected = Student.getSumOfAllStudentsAgeBySex(Sex.FEMALE);
        int result = 0;
        for (Student st : actualBySexFemale) {
            result += st.getAge();
        }
        assertEquals(expected, result);
    }
    @Test
    public void checkAverageOfAllStudentsAge() {
        int expected = Student.getAverageAgeOfAllStudents();
        int result = 0;
        for (Student st : actual) {
            result += st.getAge();
        }
        result /= actual.size();
        assertEquals(expected, result);
    }

    @Test
    public void checkAverageAgeBySex_MALE() {
        int expected = Student.getAverageOfAllStudentsBySex(Sex.MALE);
        int result = 0;
        for (Student st : actualBySexMale) {
            result += st.getAge();
        }
        result /= actualBySexMale.size();
        assertEquals(expected, result);
    }

    @Test
    public void checkAverageAgeBySex_FEMALE() {
        int expected = Student.getAverageOfAllStudentsBySex(Sex.FEMALE);
        int result = 0;
        for (Student st : actualBySexFemale) {
            result += st.getAge();
        }
        result /= actualBySexFemale.size();
        assertEquals(expected, result);
    }

    @Test
    public void checkSumOfAllStudentsAge() {
        int expected = Student.getSumOfAllStudentsAge();
        int result = 0;
        for (Student st : actual) {
            result += st.getAge();
        }
        assertEquals(expected, result);
    }
    @Test
    public void  checkStudentsCount() {
        int expected = Student.getStudentsCount();
        assertEquals(expected, actual.size());
    }
}
