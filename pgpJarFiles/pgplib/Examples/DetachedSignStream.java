/*
 * Copyright 2012 DidiSoft Ltd. All Rights Reserved.
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.didisoft.pgp.PGPLib;

/** 
 * This example demonstrates how to OpenPGP sign an input stream.
 * Using the stream methods we are not limited only to files.
 */
public class DetachedSignStream {

    public static void main(String[] args) throws Exception {
        // create instance of the library
        PGPLib pgp = new PGPLib();

        // if true the output file will be in ascii armored format, 
        // otherwise will be in binary format       
        boolean asciiArmor = false;
        
        InputStream dataStream = new FileInputStream("examples/DataFiles/INPUT.txt");
        InputStream privateKeyStream = new FileInputStream("examples/DataFiles/private.key");
        String privateKeyPassword = "changeit";
        OutputStream signedStream = new FileOutputStream("examples/DataFiles/INPUT.txt.sig");
        
        pgp.detachedSignStream(dataStream, 
                        privateKeyStream, 
                        privateKeyPassword, 
                        signedStream, 
                        asciiArmor);
        
        dataStream.close();
        privateKeyStream.close();
        signedStream.close();
    }

}
