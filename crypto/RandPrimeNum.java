import java.util.Random;
import java.math.BigInteger;
import java.util.HashSet;

public final class RandPrimeNum {
	private static final int bitLength = 10;
	private static BigInteger prime = BigInteger.probablePrime(bitLength, new Random());	
	
	private static final HashSet<BigInteger> set = new HashSet<BigInteger>();
	
	private RandPrimeNum() { } // same with abstract class :)

	public static BigInteger nextPrime() {
		while(set.contains(prime)) {
			prime = prime.nextProbablePrime();
		}
		set.add(prime);
		
		return prime;
	}
}
