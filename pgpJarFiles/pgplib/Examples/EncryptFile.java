/*
 * Copyright 2008 DidiSoft Ltd. All Rights Reserved.
 */
import com.didisoft.pgp.PGPLib;

/** 
 * This example demonstrates how to encrypt a file with the library API.
 */
public class EncryptFile {
	public static void main(String[] args) throws Exception{
		// create an instance of the library
		PGPLib pgp = new PGPLib();
		
		// if true the output file will be in ASCII armored format, 
		// otherwise will be in binary format
        boolean asciiArmor = true;
        // if true additional integrity check information is added
        // set to false for compatibility with older versions of PGP such as 6.5.8.
        boolean withIntegrityCheck = false;
                
        pgp.encryptFile("examples/DataFiles/INPUT.txt", 
        				"examples/DataFiles/public.key", 
        				"examples/DataFiles/encrypted.pgp", 
        				asciiArmor, 
        				withIntegrityCheck);
	}
}
