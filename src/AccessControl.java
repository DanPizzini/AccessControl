import java.util.ArrayList;
import java.util.Scanner;

public class AccessControl {

  private static ArrayList<User> users; // An ArrayList of valid users
  private User currentUser; // Who is currently logged in, if anyone?
  private static final String DEFAULT_PASSWORD = "changeme";
  // Default password given to
  // new users or when we reset a user's password

  public static void main(String[] args) {
    AccessControl ac = new AccessControl();
    // If we have any persistent information to lead
    // this is where we load it.
    Scanner userIn = new Scanner(System.in);
    ac.loginScreen(userIn);
  }

/**
 * this method adds a new user to the the array users with the default user name and password with admin status
 */
  public AccessControl() {// A no-parameter constructor
   
    
      users = new ArrayList<User>(); // initialized the User into the array list users 
      User admin = new User("admin", "root", true); // passes the defult user name "admin", password "root"
      //and sets it as admin to be true 
      users.add(admin); // adds the the user: admin into the array of users


    this.currentUser = null; // since the user is still has the default user name and password it considered null
    // until the user name and password is change
  }

 /**
  * this method goes scans the array until it finds a user name that matches. if the user name is true
  * it then calls isValidLogin from the user class to see if the password is true
  * 
  * @param username
  * @param password
  * @return
  */
  public static boolean isValidLogin(String username, String password) {
    // Report whether a given user name/password pair is a valid login
    int i = 0; // integer for the used in the for loop to scan the array

    for (i = 0; i < users.size(); ++i) { // this for loop scans the users array to find a given user
      if (users.get(i).getUsername().equals(username) == true) { // Array list contains the given
                                                                 // username
        if (users.get(i).isValidLogin(password) == true) {
          return true;
        } else {
          return false;// if the above statement does not compile, the username was correct and the
                       // password was incorrect

        }
      }
    }
    return false; // if there are no users in the array it returns false 
  }

  /**
 * this method changes the the password of the currentUser with a new password
 * 
 * @param newPassword
 */
  public void changePassword(String newPassword) {// Change the current user's password
    currentUser.setPassword(newPassword); // calls the object current user to change it with a new password
  }
  
/**
 * this method logs out the user by changing the current user object to null
 */
  public void logout() {// Log out the current user
    currentUser = null;
  }

  /**
 * this method sets the currentUser's and locates its position in the array and sets it to current user
 * 
 * @param username
 */
  public void setCurrentUser(String username) {// A mutator you can use to write tests
    
    if(username == "admin") {
      currentUser = new User (username, "root", true);
    }
    else {
      currentUser = new User (username, DEFAULT_PASSWORD, false);
    }
    
    
    int i = 0;

    for (i = 0; i < users.size(); ++i) {
      if (users.get(i).getUsername().equals(username) == true) {
        currentUser = users.get(i);
        break;
      }
    }
  }
/**
 * this method adds a user to the user array with a default password and no admin status 
 * 
 * @param username
 * @return
 */
  public boolean addUser(String username) { 
    if(currentUser.getIsAdmin() == false) { // this should only be accessed by set current user
      return false;
    }
    
    int i = 0; // integer for the for loop

    for (i = 0; i < users.size(); ++i) { // this for loop goes through the users array 
      if (users.get(i).getUsername().equals(username)) { // finds if there a user name that is the same
        return false;
      }
    }
    users.add(new User(username, DEFAULT_PASSWORD, false));
    return true;
  } // Create a new user
  // With the default password and isAdmin==false
/**
 * Creates a new user and specifies their admin status then adds to the user array
 * 
 * @param username
 * @param isAdmin
 * @return
 */
  public boolean addUser(String username, boolean isAdmin) {
    if(currentUser.getIsAdmin() == false) { // this should only be accessed by set current user
      return false;
    }
    int i = 0; // integer used in for loop 

    for (i = 0; i < users.size(); ++i) { // scans the array for a user name that is the same
      if (users.get(i).getUsername().equals(username)) {
        return false; // returns false is a same username is found
      }
    }
    users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
    return true;
  } // Create a new user
  // and specify their admin status

 /**
 * removes a unique user from the users array
 * 
 * @param username
 * @return
 */
  public boolean removeUser(String username) {
    
    int i = 0; // integer used in for loop

    for (i = 0; i < users.size(); ++i) { // scans the array for the specific user name then removes the user from
      //the array
      if (users.get(i).getUsername().equals(username)) { 
        users.remove(i);
        return true;

      }
    }
    return false; // returns false if the user could not be found
  } // Remove a user (names should be unique)
/**
 * this method gives a user admin power
 * 
 * @param username
 * @return
 */
  public boolean giveAdmin(String username) {
    
    
    int i = 0; // integer used in for loop 

    for (i = 0; i < users.size(); ++i) { // scans the array to find the correct user
      if (users.get(i).getUsername().equals(username)) {
        users.get(i).setIsAdmin(true); // sets the user adim status to true
        return true;

      }
    }
    return false; // if user is not found returns false
  } 
/**
 * this method removes admin power from a user
 * 
 * @param username
 * @return
 */
  public boolean takeAdmin(String username) {
    
    int i = 0;  // integer used in for loop

    for (i = 0; i < users.size(); ++i) { // scans the array to find the correct user
      if (users.get(i).getUsername().equals(username)) {
        users.get(i).setIsAdmin(false); // removes the admin power by setting the admin status to false 
        return true;

      }
    }
    return false; // if the user is not found this returns false
  } 
/**
 * this method resets the password of a user
 * 
 * @param username
 * @return
 */
  public boolean resetPassword(String username) { 
    int i = 0; // integer used in for loop

    for (i = 0; i < users.size(); ++i) {   // scans the for loop to find the correct user
      if (users.get(i).getUsername().equals(username)) {
        users.get(i).setPassword(DEFAULT_PASSWORD); // changes the password to the defualt password
        return true;

      }
    }
    return false; // returns false if the user cannot be found
  } 
/**
 * this is the driver loop for the login screen 
 * prompts the user for username and password
 * 
 * @param userInputScanner
 */
  public void loginScreen(Scanner userInputScanner) { // Main driver loop.
    
        while (true) { // while true the loop keeps running

      System.out.println("Welcome to CS300 Program 3"); 
      System.out.println("Please enter your username: "); // prompts user for their username
      String login = userInputScanner.nextLine(); // takes the user next input as username
      System.out.println("Please enter your password: "); // prompts user for passowrd
      String password = userInputScanner.nextLine(); // take the users next input as password

      if (isValidLogin(login, password) == true) { // verifies if the information is correct using the
        // isValidLogin method
        sessionScreen(login, userInputScanner); //
      }

    }

  }
/**
 * this method sets the current user and allows them to change 
 * password or log out. if the current user is an admin, this will also give them access to admin options
 * 
 * @param username
 * @param userInputScanner
 */
  public void sessionScreen(String username, Scanner userInputScanner) {
    // Set the currentUser
    // Allows them to changePassword or logout
    // If they are an admin, gives access to admin methods
    // currentUser = User(username);

    setCurrentUser(username); // sets user as current user

    while (true) { // loop that runs user option until false

      System.out.println("Welcome back " + currentUser.getUsername()); // output that welcomes the user

      if (currentUser.getIsAdmin() == false) { // list of commands for non admin users
        System.out.println("You do not have administrative privileges");
        System.out.println("Please enter one of the following commands: ");
        System.out.println("Change my password: newpw [password]");
        System.out.println("Logout: logout");

        String command = userInputScanner.nextLine(); // takes the users next input as a command
        String[] commandArray = command.split(" "); // splits the code at every space and puts it into commandArray

        if (commandArray[0].equalsIgnoreCase("newpw") && commandArray.length == 2) { // sees if the newpw command is 
          //valid and takes [1] of the array and assigns that as the new password
          currentUser.setPassword(commandArray[1]);
        }
        if (commandArray[0].equalsIgnoreCase("logout")) { // logs the user out and exits the method
          logout();
          break;
        }
      }



      if (currentUser.getIsAdmin() == true) { // if a current user an admin it allows the user to use admin privileges
        System.out.println("You currently have administrative privileges");
        System.out.println("Please enter one of the following commands: ");
        System.out.println("    Change my password: newpw [password]");
        System.out.println("    Add a new user: adduser [username]");
        System.out
            .println("    Add a new user, specify privileges: adduser [username] [true or false]");
        System.out.println("    Remove a user: rmuser [username]");
        System.out.println("    Give a user administrative privileges: giveadmin [username]");
        System.out.println("    Remove a user's administrative privileges: rmadmin [username]");
        System.out.println("    Reset a user's password: resetpw [usernamer]");
        System.out.println("    Logout: logout");

        String command = userInputScanner.nextLine(); // takes the users next input as a command 
        String[] commandArray = command.split(" "); // adds the input into the command array and splits it at every
        //space

        if (commandArray[0].equalsIgnoreCase("newpw") && commandArray.length == 2) { // changes password 
          currentUser.setPassword(commandArray[1]);
        }

        if (commandArray[0].equalsIgnoreCase("adduser") && commandArray.length == 2) { // allows admin to add users
          //with no admin status
          addUser(commandArray[1]);
        }
        if (commandArray[0].equalsIgnoreCase("adduser") && commandArray.length == 3) { // allows admin to add user with 
          // admin status
          if (commandArray[2].equalsIgnoreCase("true")) { // recognizes the command to give user admin powers
            addUser(commandArray[1], true);
          } else if (commandArray[2].equalsIgnoreCase("false")) { 
            addUser(commandArray[1], false);
          } else {
            addUser(commandArray[1]); // adds user with no admin powers
          }
        }


        if (commandArray[0].equalsIgnoreCase("rmuser") && commandArray.length == 2) { // recognizes the removes user
          //command
          removeUser(commandArray[1]); // removes the user 
        }

        if (commandArray[0].equalsIgnoreCase("giveadmin")) { // gives user admin powers
          giveAdmin(commandArray[1]);
        }

        if (commandArray[0].equalsIgnoreCase("rmadmin")) { // takes away users admin powers
          takeAdmin(commandArray[1]);
        }

        if (commandArray[0].equalsIgnoreCase("resetpw")) { // resets the users password
          resetPassword(commandArray[1]);
        }


        if (commandArray[0].equalsIgnoreCase("logout")) { // allows the admin to log out
          logout();
          break;
        }
      }
    }


  }

}
