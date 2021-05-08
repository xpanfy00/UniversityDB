package cz.vutbr.project.entity;

import cz.vutbr.project.Connection;
import lombok.Data;
import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Salary {

    @Id
    @Column
    private Long teacher_id;
    @Column
    private Float salary;
    @Column(name = "salary_after_tax")
    private Float salaryAfterTax;


    public Float Salary(Long id) {

        Connection connection = new Connection();
        Session session = connection.SessionFactory().openSession();
        User user = session.get(User.class, id);
        Salary salary = session.get(Salary.class, id);
        float value = 0;

        if (user.getId().equals(salary.getTeacher_id())) {
            value = salary.getSalary();
            return value;
        } else {
            return value;
        }
    }

    public double CalculateSalaryAfterTax(Long id) {
        double value = 0;
        double social = 0;
        double medical = 0;
        double tax = 0;
        Connection connection = new Connection();
        Session session = connection.SessionFactory().openSession();
        User user = session.get(User.class, id);
        Salary salary = session.get(Salary.class, id);

        if (salary.getSalaryAfterTax() == null) {
            session.getTransaction().begin();
            social = getSalary() * 0.24;
            medical = getSalary() * 0.09;
            tax = getSalary() * 0.15;
            value = salary.getSalary() - (social + medical + tax);

            salary.setSalaryAfterTax(Float.parseFloat(String.valueOf(value)));
            session.save(salary);
            session.getTransaction().commit();
        } else {
            value = salary.getSalaryAfterTax();
        }

        return value;
    }

}
