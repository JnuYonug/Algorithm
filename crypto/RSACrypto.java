import java.math.BigInteger;

public final class RSACrypto {
	private static BigInteger publicKey1, publicKey2;
	private static BigInteger privateKey;
	
	static { setNewKeys(); }
	
	private RSACrypto() { } // same with abstract class :)
	
	public static BigInteger getPrivateKey() { return privateKey; }
	public static BigInteger getPublicKey1() { return publicKey1; }
	public static BigInteger getPublicKey2() { return publicKey2; }
	
	private static void setPublicKey1(BigInteger p1, BigInteger p2) {
		publicKey1 = p1.multiply(p2);
	}
	private static void setPublicKey2(BigInteger p1, BigInteger p2) {
		BigInteger tmp = RandPrimeNum.nextPrime();
		while(!isComprime(tmp, p1.subtract(BigInteger.ONE)) 
														|| !isComprime(tmp, p2.subtract(BigInteger.ONE))) {
			tmp = RandPrimeNum.nextPrime();
		}
		publicKey2=tmp;
	}
	private static boolean isComprime(BigInteger i1, BigInteger i2) {
		BigInteger gcd = i1.gcd(i2);   // greatest common divisor
		
		if(gcd.equals(BigInteger.ONE)) {
			return true;
		} else {
			return false;
		}
	}
	private static void setPrivateKey(BigInteger p1, BigInteger p2) {
		BigInteger tmp = p1.subtract(BigInteger.ONE);
		tmp = tmp.multiply(p2.subtract(BigInteger.ONE));
		
		privateKey = publicKey2.modInverse(tmp);
	}
	public static void setNewKeys() {
		BigInteger prime1 = RandPrimeNum.nextPrime();
		BigInteger prime2 = RandPrimeNum.nextPrime();
		// Step1. get random prime number
		
		setPublicKey1(prime1, prime2);
		// Step2. set publicKey1
		
		setPublicKey2(prime1, prime2);
		// Step3. set publicKey2
		
		setPrivateKey(prime1, prime2);
		// Step4. set privateKey
	}
	
	public static void showAllKeys() {
		System.out.println("pub1 : " + publicKey1);
		System.out.println("pub2 : " + publicKey2);
		System.out.println("priv :  " + privateKey);
	}
	
	public static BigInteger encrypt(BigInteger b) {
		BigInteger y = b.mod(publicKey1);
		BigInteger m=BigInteger.ZERO, r=BigInteger.ONE;
		
		while(true) {
			if(publicKey2.testBit(m.intValue())) {
				r = r.multiply(y).mod(publicKey1);
			}
			y = y.multiply(y).mod(publicKey1);
			m = m.add(BigInteger.ONE);
			if(m.bitLength() > publicKey2.bitLength()) {
				break;
			}
		}
		
		return r;
	}
	public static BigInteger[] encrypt(String str) {
		char[] temp = new char[str.length()];
		str.getChars(0, str.length(), temp, 0);
		
		BigInteger[] arr = new BigInteger[temp.length];
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = encrypt( BigInteger.valueOf(temp[i]) );
		}
		
		return arr;
	}
	
	public static char decrypt(BigInteger b) {
		return decrypt(b, privateKey, publicKey1);
	}
	public static char decrypt(BigInteger b, BigInteger privateKey, BigInteger publicKey1) {
		BigInteger y = b.mod(publicKey1);
		BigInteger m=BigInteger.ZERO, r=BigInteger.ONE;
		
		while(true) {
			if(privateKey.testBit(m.intValue())) {
				r = r.multiply(y).mod(publicKey1);
			}
			y = y.multiply(y).mod(publicKey1);
			m = m.add(BigInteger.ONE);
			if(m.bitLength() > privateKey.bitLength()) {
				break;
			}
		}
		
		return (char)r.intValue();
	}
	public static String decrypt(BigInteger[] code) {
		return decrypt(code, privateKey, publicKey1);
	}	
	public static String decrypt(BigInteger[] code, BigInteger privateKey, BigInteger publicKey1) {
		char[] arr = new char[code.length];
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = decrypt(code[i], privateKey, publicKey1);
		}
		
		return new String(arr);
	}	
}

/*
	<Tested Code>
Scanner sc = new Scanner(System.in);

System.out.print("암호화할 문자열 : ");
String str = sc.nextLine();

BigInteger[] encrypted = RSACrypto.encrypt(str);
str = null;

String decrypted = RSACrypto.decrypt(encrypted);
System.out.println("복호화된 문자열 : " + decrypted);
*/