import java.util.Scanner;
class pass
{
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        System.out.print("enter password:");
        String pass=sc.nextLine();
        boolean up=false;
        boolean lp=false;
        boolean dg=false;
        boolean sp=false;
        for(int i=0;i<pass.length();i++)
        {
            char ch=pass.charAt(i);
            if(Character.isUpperCase(ch))
            {
                up=true;
            }
            else if(Character.isLowerCase(ch))
            {
                lp=true;
            }
            else if(Character.isDigit(ch))
            {
                dg=true;
            }
            else
            {
                sp=true;
            }
            System.out.println("Password Length:"+pass.length());
            if(up)
            {
                System.out.println("Upper case:present");
            }
            else{
                System.out.println("upper case :not present");
            }
            if(lp)
            {
                System.out.println("lower case:present");
            }
            else{
                System.out.println("lower case :not present");
            }
            if(dg)
            {
                System.out.println("digit:present");
            }
            else{
                System.out.println("digit:not present");
            }
            if(sp)
            {
                System.out.println("specialcase:present");
            }
            else{
                System.out.println("special case :not present");
            }
            if(up&&lp&&dg&&sp)
            {
                System.out.println("password strenth: strong");
            }
            else{
                System.out.println("Password strenth: weak");
            }
        }
        sc.close();  
    }
}