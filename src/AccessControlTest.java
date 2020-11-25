
public class AccessControlTest {

  public static void main(String[] args) {


    int fails = 0;

    // call to test methods to make sure AccessControl compiles
    if (!testLogin1()) {
      System.out.println("testLogin1 [bad username] failed");
      fails++;
    }
    if (!testLogin2()) {
      System.out.println("testLogin2 [good login] failed");
      fails++;
    }
    if (!testLogin3()) {
      System.out.println("testLogin1 [bad username with default password] failed");
      fails++;
    }
    if (!testAddUser1()) {
      System.out.println("testAddUser1 [attempts to add a user without admin privilege] failed");
      fails++;
    }
    if (!testLogin4()) {
      System.out.println("testLogin4 [re logging in after reseting password] failed");
      fails++;
    }
    if (!testRemoveUser1()) {
      System.out.println("testRemoveUser1 [removing the admin user from the data bank] failed");
      fails++;
    }
    if (!testTakeAdmin1()) {
      System.out.println("testTakeAdmin1 [removing admin privileges from default admin] failed");
      fails++;
    }
    if (fails == 0)
      System.out.println("All tests passed!");
  }

  public static boolean testTakeAdmin1() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
    // initialized
    ac.setCurrentUser("admin");
    return ac.takeAdmin("admin"); // returns true if admin privileges were revoked
  }

  public static boolean testRemoveUser1() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
    // initialized
    ac.setCurrentUser("admin");
    ac.removeUser("admin");
    return !AccessControl.isValidLogin("admin", "root"); // should return false now that admin has
                                                         // been removed
  }

  public static boolean testLogin4() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
    // initialized
    ac.setCurrentUser("admin");
    ac.resetPassword("admin");

    return AccessControl.isValidLogin("admin", "changeme"); // should return true if the login
                                                            // recognizes the new password
  }

  private static boolean testLogin3() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
    // initialized
    String user = "DanteAndJoeCrushedThisCode";
    String pw = "changeme";
    return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
  }

  private static boolean testLogin2() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
    // initialized
    String user = "admin";
    String pw = "root";
    return AccessControl.isValidLogin(user, pw); // isValidLogin should return true
  }

  /*
   * This test tries to log in a user that doesn't exist
   * 
   * @return boolean test passed
   */
  public static boolean testLogin1() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
                                            // initialized
    String user = "probablyNotInTheSystem1234";
    String pw = "password";
    return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
  }


  /*
   * Create a new AccessControl and do not log in an admin. Verify that addUser(String username)
   * returns false and that the new user is not added.
   * 
   * @return boolean test passed
   */
  public static boolean testAddUser1() {
    AccessControl ac = new AccessControl();
    String user = "alexi";
    ac.setCurrentUser("testUser");
    boolean addUserReport = ac.addUser(user);

    if (addUserReport) {
      return false; // addUserReport should be false
    }
    // Make sure user wasn't added anyway
    return !AccessControl.isValidLogin(user, "changeme");
  }

}
