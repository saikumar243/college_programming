import java.util.Scanner;
class Student
{
    int age,regno;
    String name;
    double mark1,mark2,mark3;
    void details()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter register no:");
        regno=sc.nextInt();
        name=sc.nextLine();
        System.out.println("enter name:");
        name=sc.nextLine();
        System.out.println("enter date of birth(DDMMYYYY):");
        age=sc.nextInt();
        System.out.println("enter mark1:");
        mark1=sc.nextDouble();
        System.out.println("enter mark2:");
        mark2=sc.nextDouble();
        System.out.println("enter mark3:");
        mark3=sc.nextDouble();
        sc.close();
    }
        void display()
        {
            double total=mark1+mark2+mark3;
            System.out.println("-----Student Details-----");
            System.out.println("Register Number"+regno);
            System.out.println("Name:"+name);
            System.out.println("Age:"+age);
            System.out.println("Total marks:"+total);
            System.out.println("Average:"+((total)/3));
        }
}
public class Students
{
    public static void main(String[]args)
    {
        Student s=new Student();
        s.details();
        s.display();
    }
}