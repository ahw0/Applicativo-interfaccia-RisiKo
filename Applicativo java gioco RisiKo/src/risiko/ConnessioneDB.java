/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package risiko;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author ikaros
 */
/*
    DESCRIZIONE GENERALE

Classe adibita alle specifiche di connessione del database 

*/
public class ConnessioneDB {
     /**
    * Indirizzo del server Oracle.
    */
   static public String HOST = "localhost";
   /**
    * Nome del SERVIZIO.
    */
   static public String SERVIZIO = "xe";
   /**
    * Porta utilizzata per la connessione.
    */
   static public int PORTA = 1521;
   /**
    * Nome utente per la connessione.
    */
   static public String user = "";
   /**
    * Password corrispondente all'utente specificato.
    */
   static public String password = "";
   /**
    * Oggetto DataSource utilizzato nella connessione al DB
    */
   static private OracleDataSource ods;
   /**
    * Variabile che contiene la connessione attiva, se esiste
    */
   static private Connection defaultConnection;

   /**
    * Restituisce la connessione di default al DB.
    *
    * @return Connessione di default
    * @throws SQLException In caso di problemi di connessione
    */
   static public Connection getDefaultConnection() throws SQLException {
      if (defaultConnection == null || defaultConnection.isClosed()) {
         defaultConnection = nuovaConnessione();
      }

      return defaultConnection;
   }

   /**
    * Imposta una connessione specificata in input come default.
    *
    * @param c Connessione al DB
    */
   static public void setDefaultConnection(Connection c) throws SQLException {
      defaultConnection = c;
   }

   /**
    * Restituisce una nuova connessione al DB.
    *
    * @return Connessione al DB secondo i parametri attualmente impostati
    */
   static public Connection nuovaConnessione() throws SQLException {
      ods = new OracleDataSource();
      ods.setDriverType("thin");
      ods.setServerName(HOST);
      ods.setPortNumber(PORTA);
      ods.setUser(user);
      ods.setPassword(password);
      ods.setDatabaseName(SERVIZIO);
      return ods.getConnection();
   }
}
