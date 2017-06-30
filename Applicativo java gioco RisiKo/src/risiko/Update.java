/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static risiko.Query.catturaErrori;

/**
 *
 * @author ikaros
 */
public class Update {
 
   /**
    * Descrzione: Update delle armate in un territorio.
    * Dettagli: Viene chiamata quando si eseguono azioni su un territorio per
    * settare il numero giusto di armate.
    * Parametri in: id_partita -> id della partita, id_terr -> id terriotrio,
    * qtruppe -> numero di turppe da impostare.
    * Parametri out: //
    * Eccezioni: SQLException in caso di errori.
    * @param id_partita
    * @param id_terr
    * @param qtruppe
    */
    public void updateArmateInTerritorio(String id_partita , int id_terr, int qtruppe)
    {
        /* Dichiarazione variabili */
        Connection conn = null;
        PreparedStatement rset = null;
        
        /* Query da effettuare */
        String query = "UPDATE TERRITORIO_OCCUPATO SET QUANTITA_TRUPPE = ? WHERE ((ID_PARTITA = ?) AND (ID_TERRITORIO = ?))";
        
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        // rset = conn.prepareStatement("UPDATE TERRITORIO_OCCUPATO SET QUANTITA_TRUPPE = ? WHERE ((ID_PARTITA = ?) AND (ID_TERRITORIO = ?))");
         rset.setInt(1,qtruppe);  
         rset.setString(2,id_partita);
         rset.setInt(3,id_terr);
         rset.executeUpdate();
         
         
         }catch(SQLException e)
            {
              catturaErrori(e);
            }
             finally 
                   { 
                     Query.conrsetClose(conn, rset);
                   }   
    }
    
   /**
    * Descrzione: Update delle armate totali di un giocatore.
    * Dettagli: Viene chiamata quando si effettuano operazioni sulle armate
    * di ogni territorio per mantere il numero corretto di armate totali.
    * Parametri in: id_partita -> id della partita, id_gamer -> id del giocatore,
    * qtruppe -> numero di turppe da impostare.
    * Parametri out: //
    * Eccezioni: SQLException in caso di errori.
    * @param id_partita
    * @param id_gamer
    * @param qtruppe
    */
    public void upDecrArmate(String id_partita , String id_gamer, int qtruppe)
    {
        /* Dichiarazione variabili */
        Connection conn = null;
        PreparedStatement rset = null;
        
        /* Query da effettuare */
        String query = "UPDATE GIOCATORE SET N_ARMATE_TOT = ? WHERE ((ID_PARTITA = ?) AND (ID_GAMER = ?))";
        
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        // rset = conn.prepareStatement("UPDATE GIOCATORE SET N_ARMATE_TOT = ? WHERE ((ID_PARTITA = ?) AND (ID_GAMER = ?))");
         rset.setInt(1,qtruppe);  
         rset.setString(2,id_partita);
         rset.setString(3,id_gamer);
         rset.executeUpdate();
       
         }catch(SQLException e)
            {
              catturaErrori(e);
            }
             finally 
                   { 
                     Query.conrsetClose(conn, rset);
                   }   
    }
    
  /**
   * Descrzione: Update del tipo di avanzamento.
   * Dettagli: Viene chiamata quando il giocatore dedice di spostare tutte
   * le sue armate in un territorio appena conquistato. Ulteriori informazioni
   * nella documentazione dell'applicativo.
   * Parametri in: id_partita -> id della partita, id_turno -> id del turno preso
   * in esame, id_comb -> identificativo del combattimento effettuato.
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_turno
   * @param id_comb
   */
   public void upSpostaAllArmate(String id_partita, String id_turno, int id_comb)
   {
        /* Dichiarazione variabili */
        Connection conn = null;
        PreparedStatement rset = null;
        
        /* Query da effettuare */
        String query = "UPDATE COMBATTIMENTO SET TIPO_AVANZAMENTO = ? WHERE((ID_PARTITA = ?) and (ID_TURNO = ?) and (ID_COMB = ?))";
        
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        // rset = conn.prepareStatement("UPDATE COMBATTIMENTO SET TIPO_AVANZAMENTO = ? WHERE((ID_PARTITA = ?) and (ID_TURNO = ?) and (ID_COMB = ?))");
         rset.setInt(1,2);
         rset.setString(2,id_partita);  
         rset.setString(3,id_turno);
         rset.setInt(4,id_comb);
         rset.executeUpdate();
             
         }catch(SQLException e)
            {
              catturaErrori(e);
            }
             finally 
                   { 
                     Query.conrsetClose(conn, rset);
                   }   
    }         
}
