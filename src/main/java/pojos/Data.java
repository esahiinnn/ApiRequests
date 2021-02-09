package pojos;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Data {

    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    private String profile_image;


    public Data() {
    }

    public Data(int id, String employee_name, int employeeSalary, int employeeAge, String profileImage) {
        super();
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employeeSalary;
        this.employee_age = employeeAge;
        this.profile_image = profileImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(int employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("employeeName", employee_name).append("employeeSalary", employee_salary).append("employeeAge", employee_age).append("profileImage", profile_image).toString();
    }

}
