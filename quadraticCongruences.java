

/*
 * Project Title: Quadratic Congruences Solver
 * Course: CSC281 Discrete Mathematics for Computer Science Students
 * Semester: First Semester 1445 AH (FALL ’23)
 *
 * Project Description:
 * This program solves quadratic congruences of the form ax^2 + bx + c ≡ 0 (mod p),
 * where a, b, c are integers and p is a prime number. The solutions are computed using
 * mathematical algorithms and modular arithmetic principles.
 *
 * Author: Abdullah Helal Amutairi
 *
 * Date: [2023/11/27]
 */


import java.util.Scanner;

public class quadraticCongruences {

    // The varibles that are make the equation. Globally.
    static int a = 0, b = 0, c = 0, p = 0 ;

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        System.out.println("\nQuadratic Congruences Solver!");
        System.out.println("\n#Quadratic congruences of the form, ax^2 + bx + c = 0 mod p, where a,b,c are integers, and p is a prime.\n");



        int choice = 0;

      do{

            System.out.print("Enter a: ");
            a = input.nextInt();

            System.out.print("Enter b: ");
            b = input.nextInt();

            System.out.print("Enter c: ");
            c = input.nextInt();

            System.out.print("Enter p: ");
            p = input.nextInt();

         

            int divisble = modulo(a);

            if (divisble == 0 || ! isOddPrime(p)) {
                System.out.println("\nSomething went wrong!, please try again \n** make sure p is odd AND prime, and a is NOT divisible by p. **\n");
                continue;
            }

            if ( !hasSolution() )
                System.out.println("The equation has NO SOLUTION ");
            
            else 
                calculate(); 		


            System.out.print("\nTo exit enter -1: ");
            choice = input.nextInt();

         }while(choice != -1);

         System.out.print("\nGoodbbye!");

    }// End of main.


    public static void calculate(){

        int d = computeD();
        int alpha = alpha(d);
        int res1 = modulo(alpha - b);
        int res2 = modulo(-alpha - b);
        int inv = 2*a;

        int sol1 = modulo(res1 * inverse(inv, p));
        int sol2 = modulo(res2 * inverse(inv, p));

        System.out.println("The solutions are: " + "{" + sol1 +","+ sol2 + "}" );

    }

    // This method return True if p is prime and odd, flase othewise.
    public static boolean isOddPrime(int p){
        boolean isOdd = p%2 != 0;
        boolean isPrime = p > 1 && isPrime(p);

        return isOdd && isPrime;
    }

    private static boolean isPrime(int p){
        for (int i = 2; i <= Math.sqrt(p); i++) {
            if( p % i == 0)
               return false; 
        }
        return true;
    }
    

    public static int computeD(){
        int d = (b * b) - 4 * a * c;;
        return d;
    }


    // This method return the inverse of given "a", and p. Tht's mean it returns (inverse of a in modulo p).
    public static int inverse(int a, int p){

        a = modulo(a); 

        // Trial & error.
        for (int i = 1; i < p; i++) {
            if (modulo(a * i ) == 1) {
                return i; // The inverse.
            }
        }
        return -1;
    }


    // This method returns the modulo of a given intger.
    public static int modulo(int a){
        int mod = a % p;

        if(mod < 0){
            if(p < 0)
                mod = mod - p;
                
            else
                mod = mod + p;
        }
        return mod;
    }


    // This method takes d as parameter and retuen alpha. d = b^2 - 4ac.
    public static int alpha(double d){

        boolean isPerfectSquare = isPerfectSquare(d);

        if (isPerfectSquare) {
            return (int)(Math.sqrt(d));
        }
        return alpha(d+p);
    }


    // This method return True if the val is a perfect square, false otherwise.
    public static boolean isPerfectSquare(double val){
        double alpha = Math.sqrt(val);

        return (alpha - Math.floor(alpha) == 0);
    }


    // This method check if there are solutions. returns True if so, false otherwise.
    public static boolean hasSolution(){

        int exp = (p - 1)/2;
        int base = computeD();

        if (ModularExp(base, exp) == 1) {
            return true;
        }
        else if (ModularExp(base, exp) == -1) {
            return false;
        }
        return false;
    }

    	
 // This method compute the Modular Exponentiation (fermat rule).
 public static int ModularExp (int base , int exp ){   
    int result = 1;      
     
    base = modulo(base); 
  
    while (exp > 0){ 
        if( (exp & 1) == 1 )
            result = modulo( (result * base) );
  
        exp = exp >> 1;  
        base = modulo( (base * base) );
    } 
    return result; 
} 

}