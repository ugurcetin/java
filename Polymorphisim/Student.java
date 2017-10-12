package Polimorfizim;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Student extends Person {

    private String no;
    private String department;

    public Student() {
    }

    public Student(String no, String department) {
        this.no = no;
        this.department = department;
    }

    public Student(String name, int age, String no, String department){
        super(name, age);
        this.no = no;
        this.department = department;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
