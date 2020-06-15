    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.bll.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The SecurityException is a class. 
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class SecurityManager implements ISecurityManager {

    /*We run password through this method and it will return hash of the
      password and we store it in database.
      One way function, means you can't recreate password from hash. When user
      put in password, the function convert it to hash and after compare with 
      database hashpass.
      Hashing is used to taking data, encrypting it and creating unpredictable 
      output, making it unreadable for humans and computers alike.
      Same is done for a new password
    */
    @Override
    public String hashPassword(String password) throws SecurityException 
    {
      try {
            String base = password; // Takes password and runs it through method.
            MessageDigest digest = null; // Encrypts password with a MessageDigest.
            //MessageDigest are one-way hash functions and outputs a fixed-length hash value.
            digest = MessageDigest.getInstance("SHA-256"); // Then the hashing algorithm or languagues is chosen.
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8)); //password changed into bytes and encrypts with SHA-256.
            StringBuffer hexString = new StringBuffer(); // new string for bytearray
            for (int i = 0; i < hash.length; i++) { // Turns every character of the password into a hash value.
                String hex = Integer.toHexString(0xff & hash[i]); // Turns part of the byte array into a hexidacimal string.
                if (hex.length() == 1) hexString.append('0'); // Checks the length of the hexstring if it's length is 1 and then adds 0 to end.                
                hexString.append(hex); // Appends the current string to the stringbuffer.
            }
            return hexString.toString(); //Returning whole stringBuffer into a string value.
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e.getMessage());
        }
    }
    
}
