/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * General Toolbox class that contains several useful operations.
 * @author Seckin Savasci - 2008400078
 * @author Bogazici University CmpE - Cmpe250
 */
public class ToolBox
{
    /**
     * ToolBox contains all static methods, so no need to create an instance.
     * To avoid object creation constructor is set private.
     */
    private ToolBox()
    {
        // I don't want you to create an instance
    }
    /**
     * Method that checks if the given number is a prime
     * @param n integer to be tested
     * @return true if number is a prime number, otherwise false
     */
    public static boolean isPrime(int n)
    {
        boolean prime = true;
	for (int i = 3; i <= Math.sqrt(n); i += 2)
            if (n % i == 0)
            {
		prime = false;
		break;
            }
        if (( n%2 !=0 && prime && n > 2) || n == 2)
            return true;
	else return false;
    }
    /**
     * Method that finds and returns the previous prime number
     * @param n integer value that closest previous prime will be found
     * @return the value of the closest prime less than n
     */
    public static int previousPrime(int n)
    {
        n--;
        while(!isPrime(n))
            n--;
        return n;
    }
    public static int hash1(int value,int tableSize)
    {
        return value%tableSize;
    }
    public static int hash2(int value,int tableSize)
    {
        return previousPrime(tableSize) - (value%previousPrime(tableSize));
    }
}
