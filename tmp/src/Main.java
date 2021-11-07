import java.util.Scanner;
 
public class Main {
     
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int c=0; c<T; c++) {
            int P = in.nextInt();
            int K = in.nextInt();
             
            int N = (int)(3.0*P/(1+(9.0/K)));
            System.out.println("\nN: " + N);
            while(N/3+N/K*3<P) ++N;
            System.out.println((P==N/3+N/K*3)? N: -1);
        }
        in.close();
    }
 
}