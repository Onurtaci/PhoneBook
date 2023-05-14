package phonebook;

import java.util.ArrayList;

public class Student implements Comparable<Student> {

    private int studentNum;
    private String name;
    private ArrayList<String> telNumbers;

    public Student() {
        studentNum = 0;
        name = null;
    }

    public Student(int studentNum, String name, ArrayList telNumbers) {
        this.studentNum = studentNum;
        this.name = name;
        this.telNumbers = telNumbers;

    }

    public Student(Student s) {
        studentNum = s.studentNum;
        name = s.name;
        telNumbers = new ArrayList<>(s.telNumbers);
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTelNumbers() {
        return telNumbers;
    }

    public void setTelNumbers(ArrayList<String> telNumbers) {
        this.telNumbers = telNumbers;
    }

    // formatlı şekilde öğrencinin bilgilerini ekrana yazdıran metot.
    public void showInfos() {
        System.out.printf("%-12d%-20s", studentNum, name);
        System.out.print(telNumbers);
        System.out.println();
    }

    @Override
    public String toString() {
        return ("Öğrenci No: " + studentNum + "  " + "Adı Soyadı: " + name + "  " + "Telefon Numara(ları)sı: " + telNumbers);
    }

    @Override
    public int compareTo(Student s) {
        if (this.studentNum < s.studentNum) {
            return 1;
        } else if (this.studentNum == s.studentNum) {
            return 0;
        }
        return -1;
    }
}
