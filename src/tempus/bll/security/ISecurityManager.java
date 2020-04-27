/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.bll.security;

/**
 *
 * @author dpank
 */
public interface ISecurityManager {
    
    String hashPassword(String password) throws SecurityException;
    
}
