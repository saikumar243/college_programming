import java.util.Scanner;
class evenThread extends Thread
{
    int start,end;
    evenThread(int start,int end)
    {
        this.start=start;
        this.end=end;
    }
    public void run()
    {
        System.out.print("even numbers:");
        for(int i=start;i<=end;i++)
        {
            if(i%2==0)
            {
                System.out.println(i);
            }
        }
    }
}
class oddThread extends Thread
{
    int start,end;
    oddThread(int start,int end)
    {
        this.start=start;
        this.end=end;
    }
    public void run()
    {
        System.out.print("odd numbers:");
        for(int i=start;i<=end;i++)
        {
            if(i%2!=0)
            {
                System.out.println(i);
            }
        }
    }
}
public class Thr
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("enter starting number:");
        int start=sc.nextInt();
        System.out.print("enter ending number:");
        int end=sc.nextInt();
        evenThread even=new evenThread(start,end);
        oddThread odd=new oddThread(start,end);
        even.start();
        odd.start();
        sc.close();
    }
}