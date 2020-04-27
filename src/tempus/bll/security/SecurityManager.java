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
 *
 * @author dpank
 */
public class SecurityManager implements ISecurityManager {

    /*We run password throw this method and it will return hash of the
      password and we store it in database.
      One way function, means you cant recreate pass from hash. When user
      put in password, function convert it to hash and after compare with 
      database hashpass.*/
    @Override
    public String hashPassword(String password) throws SecurityException 
    {
      try {
            String base = password;
            MessageDigest digest = null;
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e.getMessage());
        }
    }
    
}
