/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgp_firstproject;
import com.didisoft.pgp.KeyPairInformation;
import com.didisoft.pgp.KeyStore;
import com.didisoft.pgp.PGPException;
import com.didisoft.pgp.PGPLib;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author pbi
 */
public class PGP_firstProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
            makePgpKeyStore();
            ListPgpKeystore();
         
           encrypt();
      
          decrypt();
    }
    
    
    public static void makePgpKeyStore(){
         try {
            // creates new or opens existing file based KeyStore
            KeyStore keyStore = new KeyStore("resources/Mypgp.keystore", getKeystorePasword());
            
             try {
    // import private key
    keyStore.importPrivateKey("resources/myPrivateKey.asc");
 
    // import public key
    keyStore.importPublicKey("resources/myPublicKey.asc");
 	
  } catch (PGPException e) {
    System.out.println("Error reading key files : " + e.getMessage());
  } catch (IOException e) {
    System.out.println(e.getMessage());
  }
   
        } catch (IOException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PGPException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void ListPgpKeystore(){
        KeyStore keyStore = null;	
        try {
            keyStore = new KeyStore("resources/Mypgp.keystore", getKeystorePasword());
        } catch (IOException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PGPException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    System.out.println("Type | Size | Key ID | User ID");
 
    KeyPairInformation[] keys = keyStore.getKeys();
    for (int i=0; i < keys.length; i++) {
	KeyPairInformation pair = keys[i];            
	System.out.print("Type: "+pair.getAlgorithm()+" | ");
	System.out.print("Size: "+pair.getKeySize()+" | ");
	System.out.print("Key ID: "+pair.getKeyIDHex()+" | ");
        System.out.println();
          System.out.println("Found: "+pair.getUserIDs().length+" Key id´s");
	for (int j=0; j < pair.getUserIDs().length; j++) {
		System.out.println("Key ID: "+pair.getUserIDs()[j]+"");
	}           			
	
    }
    }
    
    
    public static void encrypt(){
        
        InputStream inStream = null;
        try {
            // create an instance of the KeyStore
            KeyStore keyStore = null;
            try {
                keyStore = new KeyStore("resources/Mypgp.keystore", getKeystorePasword());
            } catch (IOException ex) {
                Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PGPException ex) {
                Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
            }
            String recipientUserId = "peter.l.bilberg@icloud.com";
            // create an instance of the library
            PGPLib pgp = new PGPLib();
            // is output ASCII or binary
            boolean asciiArmor = true;
            // should integrity check information be added
            // set to false for compatibility with older versions of PGP such as 6.5.8.
  boolean withIntegrityCheck = false;
            // obtain the streams
  inStream = new FileInputStream("resources/MyFirstINPUT.txt");
  OutputStream outStream = new FileOutputStream("resources/MyFirstencrypted.pgp");
  // Here "INPUT.txt" is just a string to be written in the
  // OpenPGP data packet which contains:
  // file name string, timestamp, and the actual data bytes
  pgp.encryptStream(inStream, "MyFirstINPUT.txt",
          keyStore,
          recipientUserId,
          outStream,
			asciiArmor,
			withIntegrityCheck);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PGPException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inStream.close();
            } catch (IOException ex) {
                Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
    }
    
     public static void decrypt() {
        try {
            // initialize the KeyStore instance
            KeyStore keyStore = new KeyStore("resources/Mypgp.keystore", getKeystorePasword());
            
            // initialize the library instance
            PGPLib pgp = new PGPLib();
            
            // obtain the encrypted stream
            InputStream encryptedStream = new FileInputStream("resources/MyFirstencrypted.pgp");
            // specify the decrypted output stream
            OutputStream decryptedStream = new FileOutputStream("resources/MyDecryptOUTPUT.txt");
            
            String decryptionKeyPassword = getPrivateKeyPassword();
            
            pgp.decryptStream(encryptedStream,
                    keyStore,
                    decryptionKeyPassword,
                    decryptedStream);
        } catch (IOException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PGPException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
     
     public static String getKeystorePasword(){
         InputStream inputStreamFromPropertiesFile = null;
           String result = "";
        try {
          
            Properties p = new Properties();
            inputStreamFromPropertiesFile = new FileInputStream(new File("resources/Properties.txt"));
            p.load(inputStreamFromPropertiesFile);
            result = p.getProperty("keystorePassword");
            // p.put("privateKeyPassword", "Lillevagn01!");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStreamFromPropertiesFile.close();
            } catch (IOException ex) {
                Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
     }
     
     public static String getPrivateKeyPassword(){
         InputStream inputStreamFromPropertiesFile = null;
           String result = "";
        try {
          
            Properties p = new Properties();
            inputStreamFromPropertiesFile = new FileInputStream(new File("resources/Properties.txt"));
            p.load(inputStreamFromPropertiesFile);
            result = p.getProperty("privateKeyPassword");
       
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStreamFromPropertiesFile.close();
            } catch (IOException ex) {
                Logger.getLogger(PGP_firstProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
     }
    
}
