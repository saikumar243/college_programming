import java.util.Scanner;
class Main
{
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("enter student name:");
        String name=sc.nextLine();
        int space =name.indexOf(" ");
        String FN=name.substring(0,space);
        String LN=name.substring(space+1);
        System.out.println("Total Character:"+ name.length());
        System.out.println("First name:"+FN);
        System.out.println("Last name:"+LN);
        System.out.println("Upper Case:"+name.toUpperCase());
        System.out.println("Upper Case:"+name.toLowerCase());
        System.out.println("name with hyphen:"+name.replace(" ","-"));
        sc.close();
    }
}