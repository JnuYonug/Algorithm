import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class RSACryptoTest {
	private static final Scanner sc = new Scanner(System.in);
	
	public static void showInitMenu() {
		System.out.println("1. Create encrypted file");
		System.out.println("2. Decode encrypted file");
		System.out.println("3. Print curDir FileList screen");
		System.out.println("4. Exit Program");
		System.out.println();
		System.out.print("your choice >> ");
	}

	public static BigInteger[] readFile(File encryptedFile) {
		LinkedList<BigInteger> list = new LinkedList<BigInteger>();
		BigInteger[] encrypted = null;
		
		try {
			FileInputStream fin = new FileInputStream(encryptedFile);
			BufferedInputStream bin = new BufferedInputStream(fin);
			ObjectInputStream objIn = new ObjectInputStream(bin);
			
			int len = (int)objIn.readObject();
			encrypted = new BigInteger[len];
			
			for(int i=0; i<len; i++) {
				list.add((BigInteger) objIn.readObject() );
			}
			
			objIn.close();
		} catch(IOException e) { 
			// e.printStackTrace();
			System.out.println("Error ocurred...");
			System.exit(2);
		} catch(ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("Error ocurred...");
			System.exit(3);
		}
		
		Iterator<BigInteger> itr=list.iterator();
		int i=0;
		while(itr.hasNext()) {
			encrypted[i++] = itr.next();
		}
		
		return encrypted;
	}
	public static void writeFile(String fileName, BigInteger[] encrypted) {
		try {
			FileOutputStream fout = new FileOutputStream(fileName);
			BufferedOutputStream bfout = new BufferedOutputStream(fout);
			ObjectOutputStream objOut = new ObjectOutputStream(bfout);
			
			objOut.writeObject(encrypted.length);
			for(int i=0; i<encrypted.length; i++) {
				objOut.writeObject(encrypted[i]);
			}
			
			objOut.close();
		} catch(IOException ex) {
			//ex.printStackTrace();
			System.out.println("Error ocurred...");
			System.exit(1);
		}
	}

	public static void createEncFile() {
		System.out.print("\nNew FileName : ");
		String fileName = sc.nextLine();
		
		System.out.println("=== Text(Just a line...) ===");
		String text = sc.nextLine();
		BigInteger[] encrypted = RSACrypto.encrypt(text);
		
		writeFile(fileName, encrypted);
		
		BigInteger privateKey = RSACrypto.getPrivateKey();
		BigInteger publicKey1 = RSACrypto.getPublicKey1();
		
		System.out.println("\ndecrypt key!!! : " + privateKey + ", " + publicKey1);
		
		RSACrypto.setNewKeys();
	}
	public static void decodeEncFile() {
		System.out.print("\nInput encrypted file's name : ");
		
		final File encryptedFile = new File(sc.nextLine());
		if(!encryptedFile.exists()) {
			System.out.println("The file isn't exist!!!");
			return;
		}
		
		BigInteger[] encrypted = readFile(encryptedFile);
		
		System.out.print("decryptKey1 : ");
		String str = sc.nextLine();
		BigInteger privateKey = new BigInteger(str);
		
		System.out.print("decryptKey2 : ");
		str = sc.nextLine();
		BigInteger publicKey1 = new BigInteger(str);
		
		String text = RSACrypto.decrypt(encrypted, privateKey, publicKey1);
		System.out.println("\ndecrypted Text : " + text);
	}
	public static void printCurDirFileList() {
		final File curDir = new File(System.getProperty("user.dir"));
		final File[] curDirList = curDir.listFiles();
		
		System.out.println("\n=== curDir FileList ===");
		for(int i=0; i<curDirList.length; i++) {
			if(curDirList[i].isDirectory()) {
				System.out.print("DIR\t");
			} else {
				System.out.print("FILE\t");
			}
			
			System.out.printf("%-30s \n", curDirList[i].getName());
		}
	}
	
	public static void main(String[] args) {
loop:	while(true) {
			showInitMenu();
			int sel = sc.nextInt(); sc.nextLine();
			
			while(sel < 1 || sel > 4) {
				System.out.println("Your input was illegal!!!\n");
				showInitMenu();
				sel = sc.nextInt();
				System.out.println(sc.nextLine());   // flush
			}
			
			switch(INIT_MENU.intToINIT_MENU(sel))
			{
			case CREATE :
				createEncFile();
				break;
			case DECODE :
				decodeEncFile();
				break;
			case FILE_LIST :
				printCurDirFileList();
				break;
			case EXIT :
				break loop;
			}
			
			System.out.println();
		}
	
		System.out.println("Bye! " + ":)  "+ " See you later~ \n");
	}
}
