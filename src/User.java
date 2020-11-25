//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (Access Control: A program which simulates a login service with a 
//         data bank of users with varying administrative privileges)
// Files: (User; AccessControlTest; AccessControl)
// Course: (CS300, Fall, 2018)
//
// Author: (Dante Pizzini)
// Email: (pizzini@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (Joseph Cambio)
// Partner Email: (jcambio@wisc.edu)
// Partner Lecturer's Name: (Gary Dahl)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (none)
// Online Sources: (none)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class User {

  private final String USERNAME; // The user's name
  private String password; // The user's password
  private boolean isAdmin; // Whether or not the user has Admin powers

  /**
   * Creates a new user with the given username, password, and admin status
   * 
   * @param username Represents the login account name
   * @param password Represents the login password
   * @param isAdmin  Represents the account admin status
   */
  public User(String username, String password, boolean isAdmin) {
    USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * 
   * Takes the passed in string and compares it to the current password stored in the user object
   * 
   * @param password The string being passed in and compared to the current user password reference
   * @return true or false Returns true if the password object passed into the class matches the
   *         current user password object, returns false if the objects do not match
   */
  public boolean isValidLogin(String password) {// Report whether the password is correct
    if (password.equals(this.password) == true) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Calls and returns the current user objects login name
   * 
   * @return Returns the final String USERNAME
   */
  public String getUsername() { // Returns the users name
    return this.USERNAME;
  }

  /**
   * Reports whether the user is an admin
   * 
   * @return Returns the current user object's admin status
   */
  public boolean getIsAdmin() {
    return this.isAdmin;
  }

  /**
   * Sets the new password
   * 
   * @param password The new string passed into the user object and stored as the this.password
   *                 object
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Sets the user's admin status to be true, giving the user admin privileges
   * 
   * @param isAdmin
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = true;
  }

}
