/*
 * Copyright 2008 DidiSoft Ltd. All Rights Reserved.
 */
import com.didisoft.pgp.PGPLib;

public class DecryptFile {
	public static void main(String[] args) throws Exception{
		// initialize the library instance
		PGPLib pgp = new PGPLib();
		
		String privateKeyFile = "examples/DataFiles/private.key";
		String privateKeyPass = "changeit";

		// The decrypt method returns the original name of the file
		// that was encrypted. We can use it afterwards,
		// to rename OUTPUT.txt to it for example.		
		String originalFileName = pgp.decryptFile("examples/DataFiles/encrypted.pgp", 
		                                        privateKeyFile, 
												privateKeyPass, 
        										"examples/DataFiles/OUTPUT.txt");
	}
}
