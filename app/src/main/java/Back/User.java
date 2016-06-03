package Back;

/**
 * Created by Stephan on 03-06-2016.
 */
public class User {

    private String name,password;

    public User(String username,String pass){
        this.name = username;
        this.password = pass;

    }

    public String getUsername(){

        return name;

    }

}
