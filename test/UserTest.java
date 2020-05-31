/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import tempus.be.User;
import tempus.dal.DalManager;
import tempus.dal.IDalFacade;

/**
 *
 * @author chris
 */
public class UserTest {
    private IDalFacade dalModel;
    
    private User usToExpect;
    public UserTest() {
    }
    
    @Before
    public void setUp() {
         dalModel = new DalManager();
         usToExpect = new User("Test", "McTest", "Test@gmail.com", "Admin");
           usToExpect.setPassword("123");
            usToExpect.setPostcode(4200);
            usToExpect.setPhone(855555);
            usToExpect.setAddress("Test Street 23");
    }
    //createUser(String fName, String lName, String hashedPassword, String email, String role, String address, int phone, int postcode)
    @Test
    public void create_User_Acceptance_Test() 
    {
        User actual = dalModel.createUser("Test", "McTest", "123", "Test@gmail.com", "Admin", "Test Street 23", 855555, 4200);
     
            //Otherwise we would need to override BE toEquals() method. 
            //This is good when we dont want to insert code into be 
        assertEquals(usToExpect.getId(), actual.getId());
        assertEquals(usToExpect.getFName(), actual.getFName());
        assertEquals(usToExpect.getLName(), actual.getLName());
        assertEquals(usToExpect.getPassword(), actual.getPassword());
        assertEquals(usToExpect.getEmail(), actual.getEmail());
        assertEquals(usToExpect.getRole(), actual.getRole());
        assertEquals(usToExpect.getAddress(), actual.getAddress());
        assertEquals(usToExpect.getPhone(), actual.getPhone());
        assertEquals(usToExpect.getPostcode(), actual.getPostcode());
    }
    
    //If we have time. We should make exception tests
    @Test(expected=NullPointerException.class)
    public void select_User_Acceptance_Test() 
    {
        User gottenUser = dalModel.getUser("Test@gmail.com", "123");
        usToExpect.setId(gottenUser.getId());
        assertEquals(usToExpect.getId(), gottenUser.getId());
        assertEquals(usToExpect.getFName(), gottenUser.getFName());
        assertEquals(usToExpect.getLName(), gottenUser.getLName());
        assertEquals(usToExpect.getPassword(), gottenUser.getPassword());
        assertEquals(usToExpect.getEmail(), gottenUser.getEmail());
        assertEquals(usToExpect.getRole(), gottenUser.getRole());
        assertEquals(usToExpect.getAddress(), gottenUser.getAddress());
        assertEquals(usToExpect.getPhone(), gottenUser.getPhone());
        assertEquals(usToExpect.getPostcode(), gottenUser.getPostcode());
    }
    //editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address, String imageURL, String password)
    
    @Test
    public void edit_User_Acceptance_Test() 
    {
        User actual = dalModel.editUser(usToExpect.getId(),"NameTest", "McTest", "NameTest@gmail.com",855555,4200, "Test Street 23", "FakeImage", "None");
        
        assertEquals(usToExpect.getId(), actual.getId());
        assertEquals("NameTest", actual.getFName()); //Expected difference 
        assertEquals(usToExpect.getLName(), actual.getLName());
        assertEquals("NameTest@gmail.com", actual.getEmail()); //Expected difference 
        assertEquals(usToExpect.getAddress(), actual.getAddress());
        assertEquals(usToExpect.getPhone(), actual.getPhone());
        assertEquals(usToExpect.getPostcode(), actual.getPostcode());
    }
    @Test
    public void delete_User_Acceptance_Test() 
    {
        User gottenUser = dalModel.getUser("Test@gmail.com", "123");
        usToExpect.setId(gottenUser.getId());
        User actual = dalModel.deleteUser(gottenUser);          
        
        assertEquals(usToExpect.getId(), actual.getId());
        assertEquals(usToExpect.getFName(), actual.getFName());
        assertEquals(usToExpect.getLName(), actual.getLName());
        assertEquals(usToExpect.getPassword(), actual.getPassword());
        assertEquals(usToExpect.getEmail(), actual.getEmail());
        assertEquals(usToExpect.getRole(), actual.getRole());
        assertEquals(usToExpect.getAddress(), actual.getAddress());
        assertEquals(usToExpect.getPhone(), actual.getPhone());
        assertEquals(usToExpect.getPostcode(), actual.getPostcode());
    }
}
