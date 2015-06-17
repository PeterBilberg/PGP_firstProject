/*
 * Copyright 2008 DidiSoft Ltd. All Rights Reserved.
 */
import com.didisoft.pgp.PGPLib;

/**
 * Demonstrates OpenPGP signing and encryption in one pass.
 * The signature format is in OpenPGP version 3 (supported by PGP 2.x and older systems).
 */
public class SignAndEncryptFileV3 {

	public static void main(String[] args) throws Exception {
	    // create an instance of the library
		PGPLib pgp = new PGPLib();

		// should output be ASCII or binary
        boolean asciiArmor = false;
        // should integrity check information be added
        // set to false for compatibility with older versions of PGP such as 6.5.8.
        boolean withIntegrityCheck = false;
        
		pgp.signAndEncryptFileVersion3("examples/DataFiles/INPUT.txt", 
		                                "examples/DataFiles/private.key", 
		                                "changeit", 
		                                "examples/DataFiles/public.key", 
		                                "examples/DataFiles/encrypted.pgp", 
		                                asciiArmor, 
		                                withIntegrityCheck);
	}

}
