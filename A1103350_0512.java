import java.util.Scanner;
import java.security.SecureRandom;
import java.util.*;


class TimerThread extends Thread{
    Eat e;
    String name;
    public TimerThread(Eat e, String name){
        this.e=e;
        this.name=name;
    }

    public void run() {
        try {
            for (int i = 0; i <= 9000; i++) {
                synchronized (e) {
                    e.eat(this.name);
                    Thread.sleep(3000);
                }
                String result = String.format("%-10s", this.name);
                System.out.println(result);
            }
        } catch (InterruptedException e) {
        }
    }
}

class Eat{
    int pork = 5000;
    int beef = 3000;
    int vegetable = 1000;
    int flavor = 0;
    int num = 0;
    public synchronized void eat(String name) {
        SecureRandom random = new SecureRandom();
        flavor = random.nextInt(3) +1;  
        num = random.nextInt(40) +10;
        if(flavor==1 && pork >= num){
            pork= pork - num;
            System.out.printf("%s點了%d顆豬肉水餃, ", name, num);
        }else if (flavor==2 && beef >= num){
            beef= beef - num;
            System.out.printf("%s點了%d顆牛肉水餃, ", name, num);
        }else if (flavor == 3 && vegetable >= num){
            vegetable= vegetable - num;
            System.out.printf("%s點了%d顆蔬菜水餃, ", name, num);
        }else if(beef < 10 && pork < 10 && vegetable < 10){
            System.out.println("水餃沒了");
            System.exit(0);
        }
        System.out.println("還剩下"+pork+"顆豬肉水餃, "+beef+"顆牛肉水餃, "+vegetable+"顆蔬菜水餃");
    }

    public void starteat(int cus) {
        TimerThread[] eater = new TimerThread[cus];
        for (int j = 0; j < cus; j++) {
            eater[j] = new TimerThread(this, "eater" + (j + 1));
            eater[j].start();
        }
    }
}

public class A1103350_0512{
    public static void main(String[] argv) throws Exception{
        System.out.print("請輸入同時光顧的顧客數目");
        Scanner input = new Scanner(System.in);
        int cus = input.nextInt();
            Eat e = new Eat();
            e.starteat(cus); 
        
                   
    }

}