/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe di servizio per la connessione UNICA al DataBase
 */
public class DbHelper {

    // costruisco l'URL di connessione al DB.
    // Questi sono attributi del DB
    private final static String protocol = "jdbc:postgresql" + "://";
    private final static String host = "localhost/";
    private final static String resource = "centrivaccinalidb"; // nome del DB
    private final static String url = protocol + host + resource; // jdbc:postgresql://localhost/centrivaccinalidb

    // Credenziali di login
    private final static String username = "postgres"; // installazione di default
    private final static String password = "postgres"; // installazione di dafault
    private final static String dbAddress = "localhost"; //porta 5432

    private static Connection connection = null;

    private static Statement statement = null;


    public DbHelper(){ }

    /**
     * Crea la connessione con il DB
     * @throws SQLException
     */
    // uso il DRIVER MANAGER per creare la connessione, NON c'è indicazione del driver da utilizzare.
    public static Connection getConnection() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }

    /**
     * Chiude la connessione con il DB
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }

    /**
     * Restituisce lo statement della connessione
     * @throws SQLException
     */
    public static Statement getStatement() throws SQLException {

        if (statement == null) {

            Connection connection = getConnection();
            statement = connection.createStatement();
        }

        return statement;
    }

    /**
     * Restituisce url della connessione
     * @throws SQLException
     */
    public static String getUrl() {
        return url;
    }

    /**
     * Restituisce username della connessione
     * @throws SQLException
     */
    public static String getUsername() {
        return username;
    }

    /**
     * Restituisce la password della connessione
     * @throws SQLException
     */
    public static String getPassword() {
        return password;
    }

    /**
     * Restituisce l'indirizzo del DB
     * @throws SQLException
     */
    public static String getDbAddress() {
        return dbAddress;
    }

}//END_DbHelper