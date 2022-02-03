package dev.hart.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
// this ConnectionUtil class will define the methods needed to create a connection to our DB
/**
 * <p>This ConnectionFactory class follows the Singleton Design Pattern and facilitates obtaining a connection to a Database for the ERS application.</p>
 * <p>Following the Singleton Design Pattern, the provided Constructor is private, and you obtain an instance via the {@link ConnectionFactory#getInstance()} method.</p>
 */
public class ConnectionFactory {

    // This ConnectionFactory class will define the methods needed to create a connection to our DB
    private static ConnectionFactory instance = null;
    private static Properties dbProps;

    // private constructor
    private ConnectionFactory() {
        //super();
        // Initialize properties object to hold our database credentials
        dbProps = new Properties();

        // Stream the credentials from our connection.properties file to this Properties Object
        InputStream props = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties");

        try {
            dbProps.load(props); // need to handle the exception load throws in our code
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
     * <p>It is invoked via:</p>
     *
     * {@code ConnectionFactory.getInstance()}
     */
    // public getter to return us an instance of this ConnectionUtil class
    public static synchronized ConnectionFactory getInstance() { //getConnectionUtil
        // first check if an instance already exists
        if(instance == null) {
            // then call the private constructor (if null -> create one)
            instance = new ConnectionFactory();
        }
        // otherwise, just return the existing instance (else return the one that exists)
        return instance;
    }

    /**
     * <p>The {@link /ConnectionFactory#getConnection()} method is responsible for leveraging a specific Database Driver to obtain an instance of the {@link Connection} interface.</p>
     * <p>Typically, this is accomplished via the use of the {@link java.sql.DriverManager} class.</p>
     */
    // create a method that establishes connection between java and database
    public Connection getConnection() {
        Connection conn = null;

        // force PostgreSQL Driver to load
        try{
            Class.forName(dbProps.getProperty("driver"));
            }catch(ClassNotFoundException e){
            e.printStackTrace();
        }


        // Get the credentials needed to access the DB from the Properties Object we created above (which gets those from the connection.properties)
        String url = dbProps.getProperty("url"); // takes URL string from connection.properties and saves into reference variable url
        String username = dbProps.getProperty("username");
        String password = dbProps.getProperty("password");

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // getConnection can throw a sql exception, so we need a try catch
        }

        return conn;
    }
    public static void main(String[] args) {

        Connection connection = getInstance().getConnection();
        try {
            if (connection != null) {
                System.out.println("Connection Successful");
                connection.close(); // to prevent memory leaks
            } else {
                System.out.println("Something went wrong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
