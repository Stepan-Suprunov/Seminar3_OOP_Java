import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Sample02 {

    private static Random random = new Random();

    /**
     * TODO: Переработать метод generateEmployee(). Метод должен вощвращать и работников и фрилансеров.
     *
     * @return
     */
    public static Worker generateEmployee() {
        String[] names = new String[]{"Василий", "Пётр", "Юрий", "Григорий", "Евгений", "Николай", "Алексей"
                , "Максим", "Роман", "Андрей"};
        String[] surnames = new String[]{"Васильев", "Петров", "Юрьев", "Глебов", "Евгеньев", "Николаев"
                , "Алексеев", "Максимов", "Романов", "Андреев"};

        int salary = random.nextInt(900, 1500);
        int salaryIndex = random.nextInt(28, 31);

        return new Worker(names[random.nextInt(10)], surnames[random.nextInt(10)]
                , salary * salaryIndex);
    }

    public static void main(String[] args) {

//        Worker worker = new Worker("Глеб", "Григорьев", 120000);
//        System.out.println(worker);

        Employee[] employees = new Employee[10];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = generateEmployee();
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Arrays.sort(employees, new SurNameComparator());

        System.out.println("\n*** Отсортированный массив сотрудников ***\n");

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Arrays.sort(employees);

        System.out.println("\n*** Отсортированный массив сотрудников ***\n");

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}

class SalaryComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o1.calculateSalary(), o2.calculateSalary());
    }
}

class SurNameComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.surName.compareTo(o2.surName);
    }
}

abstract class Employee implements Comparable<Employee> {

    protected String firstName;
    protected String surName;
    protected double salary;

    public Employee(String firstName, String surName, double salary) {
        this.firstName = firstName;
        this.surName = surName;
        this.salary = salary;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s; Ставка: %.2f; Среднемесячная заработная плата: %.2f\n"
                , surName, firstName, salary, calculateSalary());
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(calculateSalary(), o.calculateSalary());
    }
}

class Worker extends Employee {

    public Worker(String firstName, String surName, double salary) {
        super(firstName, surName, salary);
    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s; Среднемесячная заработная плата: %.2f\n"
                , surName, firstName, calculateSalary());
    }
}

/**
 * TODO: Разработать самостоятельно в рамках ДЗ class Freelancer extends Employee
 */
