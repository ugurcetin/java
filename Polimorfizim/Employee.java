package Polimorfizim;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Employee extends Person {

    private String jobName;
    private int salary;

    public Employee() {

    }

    public Employee(String jobName, int salary) {
        this.jobName = jobName;
        this.salary = salary;
    }

    public Employee(String name, int age, String jobName, int salary) {
        super(name, age);
        this.jobName = jobName;
        this.salary = salary;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
