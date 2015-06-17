/*
 * Copyright 2008 DidiSoft Ltd. All Rights Reserved.
 */
import com.didisoft.pgp.PGPLib;

/**
 * This example demonstrates how to decrypt and verify a
 * one pass signed and encrypted OpenPGP archive.
 */
public class DecryptAndVerify {
	public static void main(String[] args) throws Exception{
		// create an instance of the library
		PGPLib pgp = new PGPLib();
	
		String privateKeyPassword = "changeit";
		boolean validSignature = 
			pgp.decryptAndVerifyFile("examples/DataFiles/encrypted.pgp", 
									"examples/DataFiles/private.key", 
									privateKeyPassword, 
									"examples/DataFiles/public.key", 
									"examples/DataFiles/OUTPUT.txt");
		if (validSignature) {
			System.out.println("Signature is valid.");
		} else {
			System.out.println("Signature is invalid!");
		}
	}
}
