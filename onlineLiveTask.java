import java.util.*;

public class LiveExam{

     public static void main(String []args){
        
        EmployeeManagement myEM = new EmployeeManagement(0, "Furkan", 10000, 1000);
        myEM.inputEmployee();
        myEM.sortEmployeeBySalary();
        
        myEM.showEmployee();

        myEM.deleteEmployee(2);
        
        myEM.deleteHighPaid(3500);
        
        System.out.print("\nAFTER DELETING\n\n" );

        
        myEM.showEmployee();
        
        
        
        myEM.calculateSumSalary(0);

        
     }
}


class EmployeeManagement {
    
    ArrayList<Employee> Employees;
    
    
    public EmployeeManagement(int id, String name, float salary, float
    bonussalary){
        Employees = new ArrayList<Employee>();
        Employee newEmp = new Employee(id, name, salary, bonussalary);
        Employees.add(newEmp);

    };
    public void inputEmployee(){
        Employee newEmp;
        Random rand = new Random(); 
        float randomFloat;
        for(int i = 0; i< 7; i++)
        {
            newEmp = new Employee(Employees.size(), "Kamil" + String.valueOf(i), 3000+rand.nextFloat()*1000, rand.nextFloat()*500 );
            Employees.add(newEmp);
        }
        
    }; //input at least 7 employees
    public void showEmployee(){
        for(int i = 0; i< Employees.size(); i++)
        {
            System.out.print("Employee[" + i + "]: " + Employees.get(i).getName() + "\n" );
                System.out.print("\tSalary: " + Employees.get(i).getSalary() + "\tBonus Salary: " + Employees.get(i).getBonusSalary() + "\n\n");
        }
    };
    public void sortEmployeeBySalary(){
        
        ArrayList<Employee> newVec = new ArrayList<Employee>();
        float max = 0;
        int ind = 0;
        while(Employees.size()>0)
        {
            max = 0;
            ind = 0;
            for(int i = 0; i< Employees.size(); i++)
            {
                if(Employees.get(i).getSalary() > max)
                {
                    max = Employees.get(i).getSalary();
                    ind = i;
                }
            }
            newVec.add(Employees.get(ind));
            Employees.remove(ind);
        }
        Employees = newVec;
        
    };
    public float calculateSumSalary(int id){
        int ind = 0;
        for(int i = 0; i< Employees.size(); i++)
        {
            if(Employees.get(i).getID() == id)
            {
                ind = i;
                break;
            }
        }
        float salary = Employees.get(ind).getSalary();
        float bonussalary = Employees.get(ind).getBonusSalary();

        System.out.print("Salary of " + Employees.get(ind).getName() + ": " + salary + bonussalary + "\n" );

        return salary + bonussalary;
        
    }; //
    public void deleteEmployee(int id){
        Employees.remove(id);
    }; 
    
    public void deleteHighPaid(float max){
        for(int i = 0; i< Employees.size(); i++)
        {
            if(Employees.get(i).getSalary() > max)
            {
                Employees.remove(i);
                i = i-1;
            }
        }
    };
    
    //delete employees with sumsalary > 1000
}

class Employee {
    int Id;
    String Name;
    float Salary;
    float BonusSalary;
    
    
    
    public int getID(){return Id;}
    public String getName(){return Name;}
    public float getSalary(){return Salary;}
    public float getBonusSalary(){return BonusSalary;}
    
    
    
    
    
    
    
    
    
    
    public Employee(int id, String name, float salary, float
    bonussalary)
    {
        Id = id;
        Name = name;
        Salary = salary;
        BonusSalary = bonussalary;
    }
}