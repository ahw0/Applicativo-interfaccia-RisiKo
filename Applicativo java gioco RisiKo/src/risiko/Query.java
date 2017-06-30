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
import javax.swing.JOptionPane;
import java.awt.Component;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Types;

/**
 *
 * @author ikaros
 */
public class Query {
    
    /**
     * Descrzione: Cancellazione file partita.
     * Dettagli: Viene chiamata quando si elimina una partita.
     * Verifica se è presente il file con il nome della partita specificato.
     * Se è presente si procede alla sua eliminazione. Altrimenti si avvisa
     * l'utente informandolo su come comportarsi.
     * Parametri in: del -> stringa contenente il nome della partita.
     * Parametri out: //
     * Eccezioni: //  
     * @param del
     */ 
    static public void delFile(String del)
    {
       /* Dichiarazione variabili */
       Component err = null;
       String nomepartita = del;
       
       /* Al nome della partita si aggiunge _log.txt */
       nomepartita = nomepartita + "_log.txt";
       
       /* Componente per verificare che il file sia presente per poi eliminarlo */
       File datafile = new File(nomepartita);
      
       if(datafile.delete())
        JOptionPane.showMessageDialog(err,"Il file '" + nomepartita + "' è stato eliminato","Eseguito",JOptionPane.INFORMATION_MESSAGE);
       else
        JOptionPane.showMessageDialog(err,"Il file '" + nomepartita + "' non è stato eliminato.\nSi consiglia di eliminarlo manualmente per evitare possibili problemi futuri.","Attenzione",JOptionPane.WARNING_MESSAGE); 
    }
    
    /**
     * Descrzione: Cancellazione della vista relativa alla partita.
     * Dettagli: Viene chiamata quando si elimina una partita, procedendo all'eliminazione
     * della vista creata dal database.
     * Parametri in: id_partita -> id della partita usato per la creazione del nome della
     * vista da eliminare.
     * Parametri out: //
     * Eccezioni: SQLException in caso di errori.
     * @param id_partita
     */ 
    public void delVista(String id_partita)
    {
        /* Dichiarazione variabili */
        Connection conn = null;
        PreparedStatement rset = null;
        
        /* Creo il nome della vista per eliminarla */
        String elimina = " RECAP_GAMER_"+ id_partita;
             
        /* Query da effettuare */
        String query = "DROP VIEW "+ elimina;
    
        try
         {
            conn = ConnessioneDB.getDefaultConnection();
            rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rset.executeUpdate();
           
         }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   } 
    }
      
    /**
     * Descrzione: Cancellazione della partita durante la fase preliminare.
     * Dettagli: Viene chiamata quando si chiude l'applicativo durante la fase di gioco
     * preliminare (fase in cui ogni giocatore posiziona un massimo di tre armate nei territori
     * da lui assegnatogli). Si agisce eliminando la partita.
     * Parametri in: nome_partita -> stringa contenente il nome della partita.
     * Parametri out: //
     * Eccezioni: SQLException in caso di errori nell'eliminazione di una partita.
     * @param nome_partita
     */ 
    static public void uscitaForzataDel(String nome_partita)
    {
        /* Dichiarazione variabili */
        Connection conn = null;
        PreparedStatement rset = null;
        
        /* Query da effettuare */
        String query = "DELETE FROM PARTITA WHERE NOME_PARTITA = ?";
    
        try
         {
            conn = ConnessioneDB.getDefaultConnection();
            rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         // rset = conn.prepareStatement("DELETE FROM PARTITA WHERE NOME_PARTITA = ?");
            rset.setString(1,nome_partita);
            rset.executeUpdate();
           
         }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
    }
       
  /**
   * Descrzione: Display dell'errore ricevuto.
   * Dettagli: Viene chiamata quando si verifica un errore relativo al database o
   * alla gestione file. 
   * Parametri in: e -> "stringa" contenente l'errore.
   * Parametri out: //
   * Eccezioni: //
   * @param e
   */ 
   static public void catturaErrori(SQLException e)
   {
      /* Dichiarazione variabili */
      Component err = null;
      String msg;
     
      msg = e.getMessage() + "\n";

      JOptionPane.showMessageDialog(err, msg, "Errore " + e.getErrorCode(),JOptionPane.ERROR_MESSAGE);
   }
 
   /**
   * Descrzione: Gestione connessione del database.
   * Dettagli: Viene chiamata dopo ogni interrogazione effettuata al database
   * Parametri in: conn, rset -> connessione e utlity su cui lavora il database
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param conn
   * @param rset
   */ 
   static public void conrsetClose(Connection conn, PreparedStatement rset) 
   {
     try{
           if (conn != null) 
             {
               conn.commit();
               conn.close();
             }
           
           if (rset != null) rset.close();
                  
        } catch (SQLException e) 
                {
                     catturaErrori(e);
                }
   }
   
   /**
   * Descrzione: Seleziona l'ID di una partita.
   * Dettagli: Viene chiamata per ottenere l'ID di una partita usando il nome.
   * Parametri in: nome_partita -> stringa contenente il nome della partita.
   * Parametri out: id_p -> ID della partita
   * Eccezioni: SQLException in caso di errori.
   * @param nome_partita
   * @return id_p
   */ 
   public String idPartita(String nome_partita)
    {
        /* Dichiarazione variabili */
        String id_p = null;
        Connection conn = null;
        PreparedStatement rset = null;
        
        /* Query da effettuare */
        String query = "SELECT ID_PARTITA FROM PARTITA WHERE NOME_PARTITA = ?";
       
        try
        {
            conn = ConnessioneDB.getDefaultConnection();
            rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       //   rset = conn.prepareStatement("SELECT ID_PARTITA FROM PARTITA WHERE NOME_PARTITA = ?");
            rset.setString(1,nome_partita);
            ResultSet idp = rset.executeQuery();
            
            /* Essendo il nome unico per ogni partita il risultato sarà sempre uno */
            while(idp.next())
            {
               id_p = idp.getString("ID_PARTITA");  
            }                   
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return id_p;
    }   
   
  /**
   * Descrzione: Verifica la presenza di una partita.
   * Dettagli: Viene chiamata per verificare se esiste già una partita con lo stesso
   * nome scelto dall'utente.
   * Parametri in: nome_partita -> stringa contenente il nome della partita.
   * Parametri out: partita_presente -> Se 0 non esiste una partita con il nome scelto, altrimenti 1.
   * Eccezioni: SQLException in caso di errori.
   * @param nome_partita
   * @return partita_presente
   */ 
   public int checkNomePartita(String nome_partita)
    {
       /* Dichiarazione variabili */
       int partita_presente = 0;
       Connection conn = null;
       PreparedStatement rset = null;
        
       /* Query da effettuare */
       String query = "SELECT ID_PARTITA FROM PARTITA WHERE NOME_PARTITA = ?";
        
       try
       {
            conn = ConnessioneDB.getDefaultConnection();
            rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //  rset = conn.prepareStatement("SELECT ID_PARTITA FROM PARTITA WHERE NOME_PARTITA = ?");
            rset.setString(1,nome_partita);
            ResultSet controllo_nome_partita = rset.executeQuery();
            
            /* Essendo l'id unico per ogni partita il risultato sarà sempre uno */
            while(controllo_nome_partita.next())
            {
                partita_presente = controllo_nome_partita.getInt("ID_PARTITA");
            }
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
      // System.out.println("Ritorno"+partita_presente);
       return partita_presente;
    }
  
  /**
   * Descrzione: Seleziona l'id massimo di una partita.
   * Dettagli: Viene chiamata per ottenere l'id massimo di una partita
   * che corrisponde al turno del giocatore che deve compiere le azioni di gioco
   * Parametri in: id_partita -> stringa contenente l'id della partita.
   * Parametri out: max_turno -> stringa contenente l'id massimo della partita.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @return max_turno
   */ 
   public String maxIDTurno(String id_partita)
   {
       /* Dichiarazione variabili */
       String max_turno = null;
       Connection conn = null;
       PreparedStatement rset = null;
      // String query = "SELECT MAX(ID_TURNO) AS TURNO FROM TURNO WHERE ID_PARTITA = ?";
       
       try
       {
           conn = ConnessioneDB.getDefaultConnection();
      //   rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
           
           /* Query da effettuare */
           rset = conn.prepareStatement("SELECT MAX(ID_TURNO) AS TURNO FROM TURNO WHERE ID_PARTITA = ? ");
           rset.setString(1,id_partita);
           ResultSet id_turno_max = rset.executeQuery();
            
            /* Essendo l'id max unico per ogni partita il risultato sarà sempre uno */
            while(id_turno_max.next())
              {
                 max_turno = id_turno_max.getString("TURNO");
              }
                 
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
       return max_turno;
   }
   
  /**
   * Descrzione: Seleziona l'id del giocatore.
   * Dettagli: Viene chiamata per ottenere l'id del giocatore del turno specificato
   * Di norma il tuorno è sempre turno max, ovvero si ottiene l'id del giocatore 
   * a cui tocca giocare.
   * Parametri in: id_partita -> stringa contenente l'id della partita. id_turno -> id del turno.
   * Parametri out: id_gamer -> stringa contenente l'id del giocatore.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_turno
   * @return id_gamer
   */  
   public String idGamer(String id_partita, String id_turno)
   {
       /* Dichiarazione variabili */
       String id_gamer = null;
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "SELECT ID_GAMER FROM TURNO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))";
       
       try
       {
           conn = ConnessioneDB.getDefaultConnection();
           rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         //   rset = conn.prepareStatement("SELECT ID_GAMER FROM TURNO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))");
           rset.setString(1,id_partita);
           rset.setString(2,id_turno);
           ResultSet giocatore = rset.executeQuery();
           
           /* Essendo l'id ganer unico per ogni partita il risultato sarà sempre uno */
           while(giocatore.next())
             {
                 id_gamer = giocatore.getString("ID_GAMER");
             }
            
            
        }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
       
       return id_gamer;
   }
   
  /**
   * Descrzione: Seleziona le armate totali di un giocatore.
   * Dettagli: Viene chiamata per ottenere il numero di armate totali (tutte le armate
   * che il giocatore ha su i propri territori).
   * Parametri in: id_partita -> stringa contenente l'id della partita. id_gamer -> id del giocatore.
   * Parametri out: armate_tot -> stringa contenente il numero di armate.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_gamer
   * @return armate_tot
   */ 
   public String armateTotali(String id_partita, String id_gamer)
   {
       /* Dichiarazione variabili */
       String armate_tot = null;
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "SELECT N_ARMATE_TOT FROM GIOCATORE WHERE ((ID_PARTITA = ?) AND (ID_GAMER = ?))";
       
       try
       {
            conn = ConnessioneDB.getDefaultConnection();
            rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     //     rset = conn.prepareStatement("SELECT N_ARMATE_TOT FROM GIOCATORE WHERE ((ID_PARTITA = ?) AND (ID_GAMER = ?))");
            rset.setString(1,id_partita);
            rset.setString(2,id_gamer);
            ResultSet n_armate_tot = rset.executeQuery();
            
            /* Essendo il numero di armate unico per ogni giocatore il risultato sarà sempre uno */
            while(n_armate_tot.next())
            {
               armate_tot = n_armate_tot.getString("N_ARMATE_TOT");       
            }
            
            
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
       return armate_tot;
   }
   
  /**
   * Descrzione: Somma le armate di un giocatore.
   * Dettagli: Viene chiamata per ottenere la somma del numero di armate in ogni territorio.
   * Parametri in: id_partita -> stringa contenente l'id della partita. gocc -> id del giocatore.
   * Parametri out: armate -> intero contente il numero di armate. 
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param gocc
   * @return armate
   */ 
   public int sommArmate(String id_partita, String gocc)
   {
       /* Dichiarazione variabili */
       int armate = 0;
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "SELECT SUM(QUANTITA_TRUPPE) AS TOT FROM TERRITORIO_OCCUPATO WHERE ((ID_PARTITA = ?) AND (GIOCATORE_OCCUPANTE = ?))";
       
       try
       {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
          rset.setString(1,id_partita);
          rset.setString(2,gocc);
          ResultSet armate_totali = rset.executeQuery();
          
          /* Essendo il numero di armate unico per ogni giocatore il risultato sarà sempre uno */
          while(armate_totali.next())
          {
             armate = armate_totali.getInt("TOT"); 
          } 
          
       
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
       return armate;
   }
   
  /**
   * Descrzione: Somma di tutte le armate di ogni giocatore.
   * Dettagli: Viene chiamata per ottenere la somma totale di tutte le armate di ogni giocatore.
   * Usata nella fase preliminare per verificare quando passare alla fase Posizionamento (se il
   * ritorno è 0 allora tutti i giocatori hanno conluso la fase preliminare, 1 altrimenti).
   * Parametri in: id_partita -> stringa contenente l'id della partita.
   * Parametri out: armate_tot -> intero contente il numero di armate totali. 
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @return armate_tot
   */ 
   public int armateTotaliInGioco(String id_partita)
   {
       /* Dichiarazione variabili */
       int armate_tot = 0;
   
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "SELECT SUM(N_ARMATE_TOT) AS TOT FROM GIOCATORE WHERE ID_PARTITA = ?";
       
       try
       {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      //  rset = conn.prepareStatement("SELECT SUM(N_ARMATE_TOT) AS TOT FROM GIOCATORE WHERE ID_PARTITA = ?");
          rset.setString(1,id_partita);
          ResultSet armate_totali = rset.executeQuery();
                        
          /* Essendo la somma delle armate un valore unico il risultato sarà sempre uno */
          while(armate_totali.next())
          {
             armate_tot = armate_totali.getInt("TOT"); 
          } 
            
            
            
        }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
             
       return armate_tot;
   }    
   
  /**
   * Descrzione: Seleziona il nickname del giocatore.
   * Dettagli: Viene chiamata per ottenere il nickname del giocatore basandosi
   * sulla partita e il relativo id che gli è stato dato.
   * Parametri in: id_partita -> stringa contenente l'id della partita. id_gamer -> id del giocatore.
   * Parametri out: nick -> nickname del giocatore. 
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_gamer
   * @return nick
   */ 
   public String nicknameGiocatore(String id_gamer, String id_partita)
   {
       /* Dichiarazione variabili */
       String nick = null;
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
      String query = "SELECT NICKNAME FROM GIOCATORE WHERE ((ID_GAMER = ?) AND (ID_PARTITA = ?))";
      
       try
       {
            conn = ConnessioneDB.getDefaultConnection();
            rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //rset = conn.prepareStatement("SELECT NICKNAME FROM GIOCATORE WHERE ((ID_GAMER = ?) AND (ID_PARTITA = ?))");
            rset.setString(1,id_gamer);
            rset.setString(2,id_partita);
            ResultSet nickname_giocatore = rset.executeQuery();
            
            /* Essendo il nickname unico per ogni giocatore il risultato sarà sempre uno */
            while(nickname_giocatore.next())
            {
                nick = nickname_giocatore.getString("NICKNAME"); 
            }
            
           
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                     // Query.conrsetClose(conn, rset);
                   }
       
       return nick;
   }
   
  /**
   * Descrzione: Seleziona il nome del territorio.
   * Dettagli: Viene chiamata per ottenere il nome del territorio in base 
   * al sui id territorio.
   * Parametri in: id_territorio -> identificativo del territorio.
   * Non necessita della partita essendo i territori unici in ogni partita.
   * Parametri out: nomeTerr -> nome del territorio. 
   * Eccezioni: SQLException in caso di errori.
   * @param id_territorio
   * @return nomeTerr
   */ 
   public String nomeTerritorio(String id_territorio)
   {
       /* Dichiarazione variabili */
       String nomeTerr = null;
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
        String query = "SELECT NOME_TERRITORIO FROM TERRITORIO WHERE ID_TERRITORIO = ?";
        
        try
       {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         // rset = conn.prepareStatement("SELECT NOME_TERRITORIO FROM TERRITORIO WHERE ID_TERRITORIO = ?");
          rset.setString(1,id_territorio);
          ResultSet nome_terr_giocatore = rset.executeQuery();
          
          /* Essendo il nome del territorio unico il risultato sarà sempre uno */
          while(nome_terr_giocatore.next())
          {
              nomeTerr = nome_terr_giocatore.getString("NOME_TERRITORIO"); 
          }
       
        }catch(SQLException e)
            {
              catturaErrori(e);
            }
           
            
      return nomeTerr;
   }
   
  /**
   * Descrzione: Seleziona l'id di un territorio.
   * Dettagli: Viene chiamata per l'id del territorio in base al nome di
   * quest ultimo.
   * Parametri in: nome_territorio -> nome del territorio
   * Non necessita della partita essendo i territori unici in ogni partita.
   * Parametri out: id_terr -> id del territorio. 
   * Eccezioni: SQLException in caso di errori.
   * @param nome_territorio
   * @return id_terr
   */ 
   public int idTerritorio(String nome_territorio)
   {
       /* Dichiarazione variabili */
       int id_terr = 0;
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
        String query = "SELECT ID_TERRITORIO FROM TERRITORIO WHERE NOME_TERRITORIO = ?";
        
       try
       {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     //   rset = conn.prepareStatement("SELECT ID_TERRITORIO FROM TERRITORIO WHERE NOME_TERRITORIO = ?");
          rset.setString(1,nome_territorio);
          ResultSet terr = rset.executeQuery();
          
          /* Essendo l'id del territorio unico il risultato sarà sempre uno */
          while(terr.next())
          {
             id_terr = terr.getInt("ID_TERRITORIO");            
          }
          
       
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
       
       return id_terr;
   }
   
  /**
   * Descrzione: Seleziona il giocatore occupante di un territorio.
   * Dettagli: Viene chiamata per selezionare il giocatore che occupa con
   * le sue armate un territorio in base alla partita scelta.
   * Parametri in: id_partita -> id della partita. id_territorio -> id del territorio.
   * Parametri out: giocatore_occ -> id del giocatore. 
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_territorio
   * @return giocatore_occ
   */ 
   public int giocatoreOccupante(String id_partita, int id_territorio)
   {
       /* Dichiarazione variabili */
         int giocatore_occ = 0;
       
     Connection conn = null;
     PreparedStatement rset = null;
     
     /* Query da effettuare */
     String query = "SELECT GIOCATORE_OCCUPANTE FROM TERRITORIO_OCCUPATO WHERE ((ID_PARTITA = ?) AND (ID_TERRITORIO = ?))";
       
     try
      {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      //  rset = conn.prepareStatement("SELECT GIOCATORE_OCCUPANTE FROM TERRITORIO_OCCUPATO WHERE ((ID_PARTITA = ?) AND (ID_TERRITORIO = ?))");
          rset.setString(1,id_partita);
          rset.setInt(2,id_territorio);
          ResultSet c_terr = rset.executeQuery();

          /* Essendo l'id del giocatore unico il risultato sarà sempre uno */
          while(c_terr.next())
          {
            giocatore_occ = c_terr.getInt("GIOCATORE_OCCUPANTE");   
          }
          
          
          
      }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
    
     return giocatore_occ;
   }
 
  /**
   * Descrzione: Seleziona le truppe di un territorio.
   * Dettagli: Viene chiamata per selezionare il numero singolo di turppe in un territorio.
   * Parametri in: id_partita -> id della partita. id_territorio -> id del territorio.
   * Parametri out: armate -> numero di armate presenti. 
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_territorio
   * @return armate
   */ 
   public int armateInTerritorio(String id_partita, int id_territorio)
   {
       /* Dichiarazione variabili */
       int armate = 0;
   
     Connection conn = null;
     PreparedStatement rset = null;
     
     /* Query da effettuare */
     String query = "SELECT QUANTITA_TRUPPE FROM TERRITORIO_OCCUPATO WHERE ((ID_PARTITA = ?) AND (ID_TERRITORIO = ?))";
       
     try
      {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      // rset = conn.prepareStatement("SELECT QUANTITA_TRUPPE FROM TERRITORIO_OCCUPATO WHERE ((ID_PARTITA = ?) AND (ID_TERRITORIO = ?))");
         rset.setString(1,id_partita);
         rset.setInt(2,id_territorio);
         ResultSet q_truppe = rset.executeQuery();

          /* Essendo il numero di turppe univoco per un territorio il risultato sarà sempre uno */
          while(q_truppe.next())
          {
            armate = q_truppe.getInt("QUANTITA_TRUPPE");   
          }
          
          
      }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
   
       return armate;
   }    
   
  /**
   * Descrzione: Seleziona le truppe di un territorio.
   * Dettagli: Viene chiamata per selezionare il simbolo presente sulla carta.
   * Parametri in: id_carta -> identificativo della carta.
   * Parametri out: idc -> stringa contenente il simbolo (A,C,F,J) della carta.
   * Eccezioni: SQLException in caso di errori.
   * @param id_carta
   * @return idc
   */ 
   public String simboloCarta(String id_carta)
   {
       /* Dichiarazione variabili */
       String idc = null;
       
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
        String query = "SELECT SIMBOLO_CARTA FROM CARTA_TERRITORIO WHERE ID_CARTA = ?";
       
     try
      {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       // rset = conn.prepareStatement("SELECT SIMBOLO_CARTA FROM CARTA_TERRITORIO WHERE ID_CARTA = ?");
          rset.setString(1,id_carta);
          ResultSet simbolo_carta = rset.executeQuery();
          
          /* Essendo l'id della carta univoco il risultato sarà sempre uno */
          while(simbolo_carta.next())
          {
            idc = simbolo_carta.getString("SIMBOLO_CARTA");                      
          } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      //Query.conrsetClose(conn, rset);
                   }
       
       
       return idc;
   }
   
  /**
   * Descrzione: Verifica il numero di armate perse durante un combattimento.
   * Dettagli: Viene chiamata verificare tramite la procedura Controllo_Vittoria_Dadi
   * il numero di armate perse dei giocatori che si sono affrontati durante lo scontro.
   * Parametri in: dadoNatt/dif -> contengono il valore dei dadi che sono stati lanciati.
   * Sono stringhe in quando ai dadi non lanciati viene fatto corrispondere il valore null.
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param dado1att
   * @param dado2att
   * @param dado3att
   * @param dado1dif
   * @param dado2dif
   * @param dado3dif
   */ 
   public void callVittoriaDadi(String dado1att, String dado2att, String dado3att, String dado1dif, String dado2dif, String dado3dif)
   {
       /* Dichiarazione variabili */
        Connection conn = null;
        PreparedStatement rset = null;
        Component err = null;
        int dado1_att = 0;
        int dado2_att = 0;
        int dado3_att = 0;
        int dado1_dif = 0;
        int dado2_dif = 0;
        int dado3_dif = 0;
        int persi_att = 0;
        int persi_dif = 0;
        
        
       try{
              conn = ConnessioneDB.getDefaultConnection();
              CallableStatement cs =conn.prepareCall ("call Controllo_Vittoria_Dadi(?,?,?,?,?,?,?)");
              
              /* Si registrano la variazione di parametri dei dadi */
              /* La funzione inserisce il valore 1 al dado se il giocatore ha perso un'armata */
              cs.registerOutParameter(1,Types.CHAR);
              cs.registerOutParameter(2,Types.CHAR);
              cs.registerOutParameter(3,Types.CHAR);
              cs.registerOutParameter(4,Types.CHAR);
              cs.registerOutParameter(5,Types.CHAR);
              cs.registerOutParameter(6,Types.CHAR);
              cs.setString(1,dado1att);
              cs.setString(2,dado2att);
              cs.setString(3,dado3att);
              cs.setString(4,dado1dif);
              cs.setString(5,dado2dif);
              cs.setString(6,dado3dif);
              cs.registerOutParameter(7,Types.CHAR);     
              cs.execute();
            
              /* Prendo i valori */
              dado1att = cs.getNString(1);
              dado2att = cs.getNString(2);
              dado3att = cs.getNString(3);
              
              dado1dif= cs.getNString(4);
              dado2dif = cs.getNString(5);
              dado3dif = cs.getNString(6);

            
             System.out.println("1:" + dado1att + " 2:" + dado2att + " 3:" + dado3att + " 1:" +dado1dif + " 2:"+dado2dif+ " 3:"+dado3dif);
            
             /* Le stringhe in infresso vengono convertite in interi. I valori null non vengono considerati in quando già inizializzati a zero */
             if(dado1att != null)
             {
                 dado1_att = Integer.parseInt(dado1att);
             }
             
             if(dado2att != null)
             {
                 dado2_att = Integer.parseInt(dado2att);
             }
             
             if(dado3att != null)
             {
                 dado3_att = Integer.parseInt(dado3att);
             }
              
             if(dado1dif != null)
             {
                 dado1_dif = Integer.parseInt(dado1dif);
             }
             
             if(dado2dif != null)
             {
                 dado2_dif = Integer.parseInt(dado2dif);
             }
              
             if(dado3dif != null)
             {
                 dado3_dif = Integer.parseInt(dado3dif);
             }
             
             
             /* Avendo messo valore 1 quando un giocatore ha perso un carro, la somma permette di ottenere il numero di armate perse */
             persi_att = dado1_att + dado2_att + dado3_att;
             persi_dif = dado1_dif + dado2_dif + dado3_dif;
             
             /* Avviso il giocatore sull'esito dello scontro e provvedo a scriverlo nel file log */
             JOptionPane.showMessageDialog(err,"Il giocatore attaccante ha perso (" + persi_att + ") carri.\nIl giocatore difensore ha perso (" + persi_dif +") carri.","Esito del combattimento",JOptionPane.INFORMATION_MESSAGE);
             Insert.logFile("");
             Insert.logFile("Esito del combattimento:");
             Insert.logFile("Il giocatore attaccante ha perso(" + persi_att + ") armate. Il giocatore difensore ha perso (" + persi_dif + ") armate");
     
         }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
   
   }
   
  /**
   * Descrzione: Seleziona il valore numerico determinato dalla conquista di un territorio.
   * Dettagli: Viene chiamata per selezionare il valore associato (null=il giocatore non ha conquistato
   * il territorio, 1=altrimenti) a uno scontro.
   * Parametri in: id_partita -> identificativo della partita. id_turno -> id del turno preso in esame.
   * Parametri out: vinc -> valore contenente null o 1.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_turno
   * @return vinc
   */ 
   public String checkVincente(String id_partita, String id_turno)
   {
       /* Dichiarazione variabili */
       String vinc = null;
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "SELECT VINCENTE FROM COMBATTIMENTO WHERE (ID_COMB = (SELECT  MAX(ID_COMB) FROM COMBATTIMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?)))AND (ID_PARTITA = ? ) AND (ID_TURNO = ?))";
       
       
      try
      {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         // rset = conn.prepareStatement("SELECT VINCENTE FROM COMBATTIMENTO WHERE (ID_COMB = (SELECT  MAX(ID_COMB) FROM COMBATTIMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?)))AND (ID_PARTITA = ? ) AND (ID_TURNO = ?))");
          rset.setString(1,id_partita);
          rset.setString(2,id_turno);
          rset.setString(3,id_partita);
          rset.setString(4,id_turno);
         //rset = conn.prepareStatement("SELECT VINCENTE FROM COMBATTIMENTO WHERE ID_COMB = (SELECT  MAX(ID_COMB) FROM COMBATTIMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?)))");
         // rset.setString(1,id_partita);
         // rset.setString(2,id_turno);
          ResultSet vincente = rset.executeQuery();
          
          /* Essendo il vicente unico il risultato sarà sempre uno */
          while(vincente.next())
          {
            vinc = vincente.getString("VINCENTE");                      
          } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
  
       return vinc;
   }
 
  /**
   * Descrzione: Seleziona l'id combattimento.
   * Dettagli: Viene chiamata per selezionare l'ultimo combattimento effettuato.
   * La relazione è data da combattimento->lancio dadi.
   * Parametri in: id_partita -> identificativo della partita. id_turno -> id del turno preso in esame.
   * Parametri out: idComb -> identificativo del combattimento.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_turno
   * @return idComb
   */ 
   public int idCombattimento(String id_partita, String id_turno)
   {
       /* Dichiarazione variabili */
       int idComb = 0;
       
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
        String query = "SELECT MAX(ID_COMB) AS TOT FROM COMBATTIMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))";
       
     try
      {
          conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      //    rset = conn.prepareStatement("SELECT MAX(ID_COMB) AS TOT FROM COMBATTIMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))");
          rset.setString(1,id_partita);
          rset.setString(2,id_turno);
          ResultSet vincente = rset.executeQuery();
          
          /* Essendo l'id combattimeento unico il risultato sarà sempre uno */
          while(vincente.next())
          {
            idComb = vincente.getInt("TOT");                      
          } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
       return idComb;
   }
   
  /**
   * Descrzione: Seleziona l'id gamer vincitore.
   * Dettagli: Viene chiamata per l'id vincitore di una partita. Esso sarà
   * null nel caso in cui non ci sia ancora un vincitore, altrimenti sarà presente
   * il l'identificativo del giocatore vincente.
   * Parametri in: nome_partita -> identificativo della partita.
   * Parametri out: checkvinc -> identificativo del giocatore vincente, se presente.
   * Eccezioni: SQLException in caso di errori.
   * @param nome_partita
   * @return checkvinc
   */ 
   public int checkVincitore(String nome_partita)
   {
       /* Dichiarazione variabili */
       int checkvinc = 0;
       String vinc = null;
       Connection conn = null;
       PreparedStatement rset = null;
      //String query = "SELECT ID_GAMER_VINC FROM PARTITA WHERE NOME_PARTITA = ?";
       
     try
      {
          conn = ConnessioneDB.getDefaultConnection();
        // rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        
          /* Query da effettuare */
          rset = conn.prepareStatement("SELECT ID_GAMER_VINC FROM PARTITA WHERE NOME_PARTITA = ?");
          rset.setString(1,nome_partita);
          ResultSet vincente = rset.executeQuery();
          
          /* Essendo l'id del vincente unico il risultato sarà sempre uno */
          while(vincente.next())
          {
            vinc = vincente.getString("ID_GAMER_VINC");                      
          } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
        /* Se c'è un giocatore vincente converto il risultato della query in un intero */
        /* Altrimenti ritorno il valore dell'inizializzazione 0 */
       if(vinc != null)
       {
           checkvinc = Integer.parseInt(vinc);
       }
   
       return checkvinc;
   }
   
  /**
   * Descrzione: Verifica se un giocatore ha perso.
   * Dettagli: Viene chiamata per verificare se un giocatore non ha più 
   * territori. In questo caso la funzione restituisce il valore 0 decretando 
   * il giocatore come perdente. Caso opposto (valore 1) il giocatore è ancora in gioco.
   * Parametri in: id_partita -> identificativo della partita. id_gamer -> id del giocatore da verificare.
   * Parametri out: checkvinc -> valore 0 o 1 se il giocatore ha perso o meno.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_gamer
   * @return checkvinc
   */
   public int callCheckGamerInGame(String id_partita, String id_gamer)
   {
       /* Dichiarazione variabili */
      Connection conn = null;
      CallableStatement cs = null;
      int perdente = 0;
       
      try{
            conn = ConnessioneDB.getDefaultConnection();
            cs =conn.prepareCall ("{? = call Check_Gamer_in_game(?,?)}");
            cs.registerOutParameter(1,Types.NUMERIC);
            cs.setString(2,id_partita);
            cs.setString(3,id_gamer);
            cs.execute();
                
            perdente = cs.getInt(1);
          
          }catch (SQLException e) 
            {
                Query.catturaErrori(e);
            }
   
      return perdente;
   }
   
  /**
   * Descrzione: Seleziona la descrizione di una carta obiettivo.
   * Dettagli: Viene chiamata per mostrare all'utente il suo obiettivo.
   * Parametri in: id_partita -> identificativo della partita. id_gamer -> id del giocatore da verificare.
   * Parametri out: carta -> descrizione dell'obiettivo.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_gamer
   * @return carta
   */
   public String CartaOb(String id_partita, String id_gamer)
   {
       /* Dichiarazione variabili */
       String carta = null;
       
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "SELECT DESCRIZIONE FROM CARTA_OBIETTIVO WHERE ID_CARTA = (SELECT ID_CARTA_OBIETTIVO FROM GIOCATORE WHERE ((ID_PARTITA = ?) AND (ID_GAMER = ?)))";
       
     try
      {
          conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      //    rset = conn.prepareStatement("SELECT DESCRIZIONE FROM CARTA_OBIETTIVO WHERE ID_CARTA = (SELECT ID_CARTA_OBIETTIVO FROM GIOCATORE WHERE ((ID_PARTITA = ?) AND (ID_GAMER = ?)))");
          rset.setString(1,id_partita);
          rset.setString(2,id_gamer);
          ResultSet cartaob = rset.executeQuery();
          
          /* Essendo la descrizione della carta unico il risultato sarà sempre uno */
          while(cartaob.next())
          {
            carta = cartaob.getString("DESCRIZIONE");                      
          } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       
       
       return carta;
   }
   
  /**
   * Descrzione: Verifica se è presente almeno una riga in posizionamento armata.
   * Dettagli: Viene chiamata per dall'applicativo per poter riprende una partita
   * in corso. (ulteriori info sulla documentazione dell'applicativo)
   * Parametri in: id_partita -> identificativo della partita. id_turno -> id del turno.
   * Parametri out: check -> 0 se non presente nessuna riga, N altrimenti.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_turno
   * @return check
   */
   public int checkPosizionamento(String id_partita, String id_turno)
   {
       /* Dichiarazione variabili */
       int check = 0;
   
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "SELECT ID_PARTITA FROM POSIZIONAMENTO_ARMATA WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))";
       
     try
      {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //  rset = conn.prepareStatement("SELECT ID_PARTITA FROM POSIZIONAMENTO_ARMATA WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))");
          rset.setString(1,id_partita);
          rset.setString(2,id_turno);
          ResultSet checkriga = rset.executeQuery();
          
          /* Viene preso ID_Partita in quando attributo sempre presente con un valore */
          while(checkriga.next())
          {
            check = checkriga.getInt("ID_PARTITA");                      
          } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }

       return check;
   }
   
  /**
   * Descrzione: Verifica se è presente almeno una riga in combattimento.
   * Dettagli: Viene chiamata per dall'applicativo per poter riprende una partita
   * in corso. (ulteriori info sulla documentazione dell'applicativo)
   * Parametri in: id_partita -> identificativo della partita. id_turno -> id del turno.
   * Parametri out: check -> 0 se non presente nessuna riga, N altrimenti.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_turno
   * @return check
   */
   public int checkCombattimento(String id_partita, String id_turno)
   {
       /* Dichiarazione variabili */
       int comb = 0;
   
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "SELECT ID_PARTITA FROM COMBATTIMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))";
       
     try
      {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      //   rset = conn.prepareStatement("SELECT ID_PARTITA FROM COMBATTIMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))");
          rset.setString(1,id_partita);
          rset.setString(2,id_turno);
          ResultSet checkriga = rset.executeQuery();
          
          /* Viene preso ID_Partita in quando attributo sempre presente con un valore */
          while(checkriga.next())
          {
            comb = checkriga.getInt("ID_PARTITA");
          } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
       return comb;
   }
   
  /**
   * Descrzione: Verifica se è presente almeno una riga in spostamento.
   * Dettagli: Viene chiamata per dall'applicativo per poter riprende una partita
   * in corso. (ulteriori info sulla documentazione dell'applicativo)
   * Parametri in: id_partita -> identificativo della partita. id_turno -> id del turno.
   * Parametri out: check -> 0 se non presente nessuna riga, N altrimenti.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_turno
   * @return check
   */
   public int checkSpostamento(String id_partita, String id_turno)
   {
       /* Dichiarazione variabili */
       int spost = 0;
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "SELECT ID_PARTITA FROM SPOSTAMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))";
       
     try
      {
          conn = ConnessioneDB.getDefaultConnection();
          rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //  rset = conn.prepareStatement("SELECT ID_PARTITA FROM SPOSTAMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))");
          rset.setString(1,id_partita);
          rset.setString(2,id_turno);
          ResultSet checkriga = rset.executeQuery();
          
          /* Viene preso ID_Partita in quando attributo sempre presente con un valore */
          while(checkriga.next())
          {
            spost = checkriga.getInt("ID_PARTITA");                      
          } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }

       return spost;
   }
    
  /**
   * Descrzione: Verifica la presenza di una combinazione di carte.
   * Dettagli: Viene chiamata per verificare se il giocatore dispone di quelle carte
   * per poter attivare la combinazione assegnandogli un numero maggiore di armate
   * in base alle regole del gioco.
   * Parametri in: id_partita -> identificativo della partita. id_gamer -> id del giocatore.
   * combinazione -> valore identificativo (da 2 a 8) che identifica la conbinazione di carte.
   * Ulteriori info sulle combinazioni nel manuale dell'applicativo.
   * Parametri out: comb -> 0 se il giocatore non ha la combinazione selezionata, valore != 0 altrimenti.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_gamer
   * @param combinazione
   * @return comb
   */
  public int callCombinazione(String id_partita, String id_gamer, int combinazione)
  {
      /* Dichiarazione variabili */
      int comb = 0;
      Connection conn = null;
      PreparedStatement rset = null;

       
        try{
            conn = ConnessioneDB.getDefaultConnection();
            CallableStatement cs =conn.prepareCall ("{? = call Combinazione(?,?,?)}");
            cs.setString(2,id_partita);
            cs.setString(3,id_gamer);
            cs.setInt(4,combinazione);
           
            cs.registerOutParameter(1,Types.NUMERIC);
    
            cs.execute();
            
            comb = cs.getInt(1);
            

        
        }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return comb;
  }
  
  /**
   * Descrzione: Calcola il totale dei combattimenti.
   * Dettagli: Viene chiamata per mostrare il numero totale di combattimenti svolti in tutto
   * il database.
   * Parametri in: id_partita -> identificativo della partita.
   * Parametri out: comb -> numero dei combattimenti.
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @return comb
   */
  public int toolsTotCombattimenti(String id_partita)
  {
    /* Dichiarazione variabili */
    int comb = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM COMBATTIMENTO WHERE (ID_PARTITA = ?)";
       
    try
    {
      conn = ConnessioneDB.getDefaultConnection();
      rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rset.setString(1,id_partita);
      ResultSet ncomb = rset.executeQuery();
        
      /* Il risultato sarà sempre un unico valore */
      while(ncomb.next())
      {
         comb = ncomb.getInt("TOT");                      
      }      
    }catch(SQLException e)
     {
       catturaErrori(e);
     }finally 
      { 
        Query.conrsetClose(conn, rset);
      }
   
    return comb;
  }
 
 /**
  * Descrzione: Calcola il totale dei turni.
  * Dettagli: Viene chiamata per mostrare il numero totale di turni svolti in tutto
  * il database.
  * Parametri in: id_partita -> identificativo della partita.
  * Parametri out: turn -> numero dei turni.
  * Eccezioni: SQLException in caso di errori.
  * @param id_partita
  * @return turn
  */
  public int toolsTotTurni(String id_partita)
  {
     /* Dichiarazione variabili */
      int turn = 0;
      Connection conn = null;
      PreparedStatement rset = null;
       
      /* Query da effettuare */
      String query = "SELECT COUNT(*) AS TOT FROM TURNO WHERE (ID_PARTITA = ?)";
       
     try
      {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rset.setString(1,id_partita);
        ResultSet nturn = rset.executeQuery();
          
        /* Il risultato sarà sempre un unico valore */
        while(nturn.next())
        {
          turn = nturn.getInt("TOT");                      
        }    
       }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
            { 
              Query.conrsetClose(conn, rset);
            }
     
    return turn;
  }
  
 /**
  * Descrzione: Seleziona l'id del giocatore.
  * Dettagli: Viene chiamata per selezionare l'id di un giocatore in base alla partita e al nickname fornito.
  * Parametri in: id_partita -> identificativo della partita, nik -> nickname del giocatore.
  * Parametri out: id -> id del giocatore.
  * Eccezioni: SQLException in caso di errori.
  * @param id_partita
  * @param nik
  * @return id
  */
  public int InfoIdGiocatore(String id_partita, String nik)
  {

    /* Dichiarazione variabili */
    int id = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT ID_GAMER FROM GIOCATORE WHERE ((ID_PARTITA = ?) AND (NICKNAME = ?))";
       
     try
      {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rset.setString(1,id_partita);
        rset.setString(2,nik);
        ResultSet nturn = rset.executeQuery();
          
        /* In ogni parta c'è sempre un e un solo id giocatore associato al nickname */
        while(nturn.next())
        {
          id = nturn.getInt("ID_GAMER");                      
        }        
       }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
            { 
              Query.conrsetClose(conn, rset);
            }

    return id;
  }
  
 /**
  * Descrzione: Seleziona il colore di un giocatore
  * Dettagli: Viene chiamata per selezionare il colore di un giocatore in base alla partita e al suo id.
  * Parametri in: id_partita -> identificativo della partita, id_giocatore -> id del giocatore.
  * Parametri out: colore -> colore del giocatore.
  * Eccezioni: SQLException in caso di errori.
  * @param id_partita
  * @param id_giocatore
  * @return colore
  */
  public String InfoColore(String id_partita, int id_giocatore)
  {
    /* Dichiarazione variabili */
    String colore = null;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COLORE FROM GIOCATORE WHERE ((ID_PARTITA = ?) AND (ID_GAMER = ?))";
       
     try
      {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rset.setString(1,id_partita);
        rset.setInt(2,id_giocatore);
        ResultSet color = rset.executeQuery();
          
        /* Ogni giocatore detiene in una partita un unico colore */
        while(color.next())
        {
          colore = color.getString("COLORE");                      
        } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
     
      return colore;
  }
  
 /**
  * Descrzione: Seleziona il numero di combattimenti svolti dal giocatore.
  * Dettagli: Viene chiamata per selezionare il numero di combattimenti svolti da un singolo giocatore.
  * Parametri in: id_partita -> identificativo della partita, id_giocatore -> id del giocatore.
  * Parametri out: comb -> numero dei combattimenti.
  * Eccezioni: SQLException in caso di errori.
  * @param id_partita
  * @param id_giocatore
  * @return comb
  */
  public int InfoCombattimenti(String id_partita, int id_giocatore)
  {
    /* Dichiarazione variabili */
    int comb = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM COMBATTIMENTO C JOIN TURNO T ON (C.ID_TURNO = T.ID_TURNO) WHERE ((T.ID_GAMER = ?) AND (C.ID_PARTITA = ?))";
       
     try
      {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rset.setInt(1,id_giocatore);
        rset.setString(2,id_partita);
        ResultSet combat = rset.executeQuery();
          
        /* Verrà sempre restituito un unico valore */
        while(combat.next())
        {
          comb = combat.getInt("TOT");                      
        }      
       }catch(SQLException e)
           {
             catturaErrori(e);
           }finally 
              { 
                Query.conrsetClose(conn, rset);
              }
    
      return comb;
  }
  
 /**
  * Descrzione: Seleziona il numero di combattimenti svolti dal giocatore che hanno portato a una conquista.
  * Dettagli: Viene chiamata per selezionare il numero di combattimenti svolti da un singolo giocatore che ha conquistato il territorio.
  * Parametri in: id_partita -> identificativo della partita, id_giocatore -> id del giocatore.
  * Parametri out: conq -> numero dei combattimenti.
  * Eccezioni: SQLException in caso di errori.
  * @param id_partita
  * @param id_giocatore
  * @return conq
  */
  public int InfoCombConquistato(String id_partita, int id_giocatore)
  {
    /* Dichiarazione variabili */
    int conq = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM COMBATTIMENTO C JOIN TURNO T ON (C.ID_TURNO = T.ID_TURNO) WHERE ((T.ID_GAMER = ?) AND (C.ID_PARTITA = ?) AND (VINCENTE = 1))";
       
     try
      {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rset.setInt(1,id_giocatore);
        rset.setString(2,id_partita);
        ResultSet conquist = rset.executeQuery();
          
        /* Verrà sempre restituito un unico valore */
        while(conquist.next())
        {
          conq = conquist.getInt("TOT");                      
        } 
          
          
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
    
      return conq;
 
  }
  
 /**
  * Descrzione: Seleziona il numero di volte in cui il giocatore ha lanciato i dadi.
  * Dettagli: Viene chiamata per selezionare il numero di volte totale in cui il giocatore ha lanciato i dadi.
  * Parametri in: id_partita -> identificativo della partita, id_giocatore -> id del giocatore.
  * Parametri out: dadi -> numero dei dadi.
  * Eccezioni: SQLException in caso di errori.
  * @param id_partita
  * @param id_giocatore
  * @return dadi
  */
  public int InfoLancioDadi(String id_partita, int id_giocatore)
  {
    /* Dichiarazione variabili */
    int dadi = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM LANCIO_DADI C JOIN TURNO T ON (C.ID_TURNO = T.ID_TURNO) WHERE ((T.ID_GAMER = ?) AND (C.ID_PARTITA = ?))";
       
     try
      {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rset.setInt(1,id_giocatore);
        rset.setString(2,id_partita);
        ResultSet ndadi = rset.executeQuery();
        
        /* Verrà sempre restituito un unico valore in quanto si esegue un count */
        while(ndadi.next())
        {
          dadi = ndadi.getInt("TOT");                      
        }    
       }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
            { 
              Query.conrsetClose(conn, rset);
            }
    
      return dadi;
  }
  
 /**
  * Descrzione: Seleziona l'id del giocatore in base al turno.
  * Dettagli: Viene chiamata per selezionare l'identificativo del giocatore in base alla partita e al turno specificato.
  * Parametri in: id_partita -> identificativo della partita, id_turno -> id del turno scelto.
  * Parametri out: idg -> id del giocatore.
  * Eccezioni: SQLException in caso di errori.
  * @param id_partita
  * @param id_turno
  * @return idturno
  */
  public int InfoIDTurno(String id_partita, int id_turno)
  {
    /* Dichiarazione variabili */
    int idg = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT ID_GAMER FROM TURNO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rset.setString(1,id_partita);
        rset.setInt(2,id_turno);
        ResultSet ndadi = rset.executeQuery();
        
        /* Verrà sempre restituito un unico valore in quanto a un turno corrisponde un solo id giocatore */
        while(ndadi.next())
        {
          idg = ndadi.getInt("ID_GAMER");                      
        } 
               
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return idg;
  }
  
 /**
  * Descrzione: Seleziona il numero dei combattimenti di un turno.
  * Dettagli: Viene chiamata per selezionare il numero di combattimenti svolti dal giocatore in un turno specificato.
  * Parametri in: id_partita -> identificativo della partita, id_turno -> id del turno scelto.
  * Parametri out: ncomb -> numero dei combattimenti.
  * Eccezioni: SQLException in caso di errori.
  * @param id_partita
  * @param id_turno
  * @return ncomb
  */
  public int InfoCombTurno(String id_partita, int id_turno)
  {
      /* Dichiarazione variabili */
    int ncomb = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM COMBATTIMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rset.setString(1,id_partita);
        rset.setInt(2,id_turno);
        ResultSet ndadi = rset.executeQuery();
        
        /* Verrà sempre restituito un unico valore in quanto si svolge un count */
        while(ndadi.next())
        {
          ncomb = ndadi.getInt("TOT");                      
        }           
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return ncomb;
  }
  
 /**
  * Descrzione: Seleziona il numero degli spostamenti di un turno.
  * Dettagli: Viene chiamata per selezionare il numero di spostamenti svolti dal giocatore in un turno specificato.
  * Parametri in: id_partita -> identificativo della partita, id_turno -> id del turno scelto.
  * Parametri out: spost -> numero degli spostamenti.
  * Eccezioni: SQLException in caso di errori.
  * @param id_partita
  * @param id_turno
  * @return spost
  */
  public int InfoSpostTurno(String id_partita, int id_turno)
  {
     /* Dichiarazione variabili */
    int spost = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM SPOSTAMENTO WHERE ((ID_PARTITA = ?) AND (ID_TURNO = ?))";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rset.setString(1,id_partita);
        rset.setInt(2,id_turno);
        ResultSet sp = rset.executeQuery();
        
        /* Verrà sempre restituito un unico valore dato dalla count */
        while(sp.next())
        {
          spost = sp.getInt("TOT");                      
        }           
       }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
             { 
               Query.conrsetClose(conn, rset);
             }
 
      return spost;  
  }

 /**
  * Descrzione: Seleziona il numero di tutte le partite.
  * Dettagli: Viene chiamata per selezionare il numero di tutte le partite presenti nel database.
  * Parametri in: //
  * Parametri out: npartite -> numero delle partite
  * Eccezioni: SQLException in caso di errori.
  * @return npartite
  */
  public int InfoGenerPartita()
  {
    /* Dichiarazione variabili */
    int npartite = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM PARTITA";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
        
        /* Verrà restituito sempre un unico valore dovuto al count */
        while(pa.next())
        {
          npartite = pa.getInt("TOT");                      
        }                
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
               { 
                 Query.conrsetClose(conn, rset);
               }
    
    return npartite;  
  }
  
 /**
  * Descrzione: Seleziona il numero di tutti i giocatori.
  * Dettagli: Viene chiamata per selezionare il numero di tutti i giocatori presenti nel database.
  * Parametri in: //
  * Parametri out: ngamer -> numero dei giocatori.
  * Eccezioni: SQLException in caso di errori.
  * @return ngamer
  */
  public int InfoGenerGiocatori()
  {
    /* Dichiarazione variabili */
    int ngamer = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM GIOCATORE";
       
    try
     {
       conn = ConnessioneDB.getDefaultConnection();
       rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       ResultSet pa = rset.executeQuery();
       
       /* Verrà restituito sempre un unico valore dovuto al count */
       while(pa.next())
       {
         ngamer = pa.getInt("TOT");                      
       } 
     }catch(SQLException e)
       {
         catturaErrori(e);
       }finally 
         { 
           Query.conrsetClose(conn, rset);
         }
 
    return ngamer;  
  }
 
 /**
  * Descrzione: Seleziona il numero di tutti i lanci di dadi.
  * Dettagli: Viene chiamata per selezionare il numero di tutti i dadi lanciati nel database.
  * Parametri in: //
  * Parametri out: ndadi -> numero dei dadi.
  * Eccezioni: SQLException in caso di errori.
  * @return ndadi
  */
  public int InfoGenerDadi()
  {
    /* Dichiarazione variabili */
    int ndadi = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM LANCIO_DADI";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* Verrà restituito sempre un unico valore dovuto al count */
        while(pa.next())
        {
          ndadi = pa.getInt("TOT");                      
        }             
      }catch(SQLException e)
         {
           catturaErrori(e);
         }finally 
           { 
             Query.conrsetClose(conn, rset);
           }
 
      return ndadi;  
  }
    
 /**
  * Descrzione: Seleziona il numero di tutte le armate.
  * Dettagli: Viene chiamata per selezionare il numero di tutte le armate presenti nel database.
  * Parametri in: //
  * Parametri out: arm -> numero delle armate.
  * Eccezioni: SQLException in caso di errori.
  * @return arm
  */
  public int InfoGenerArmate()
  {
    /* Dichiarazione variabili */
    int arm = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT SUM(N_ARMATE_TOT) AS TOT FROM GIOCATORE";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
        
        /* Verrà restituito sempre un unico valore dovuto alla somma */
        while(pa.next())
        {
          arm = pa.getInt("TOT");                      
        } 
               
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return arm;  
  }  
 
 /**
  * Descrzione: Seleziona il numero di tutti i turni.
  * Dettagli: Viene chiamata per selezionare il numero di tutti i turni presenti nel database.
  * Parametri in: //
  * Parametri out: turn -> numero dei turni.
  * Eccezioni: SQLException in caso di errori.
  * @return turn
  */
  public int InfoGenerTurni()
  {
    /* Dichiarazione variabili */
    int turn = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM TURNO";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
        
        /* Verrà restituito sempre un unico valore dovuto al count */
        while(pa.next())
        {
          turn = pa.getInt("TOT");                      
        }               
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return turn;  
  }  
  
 /**
  * Descrzione: Seleziona il numero di tutti i combattimenti.
  * Dettagli: Viene chiamata per selezionare il numero di tutti i combattimenti presenti nel database.
  * Parametri in: //
  * Parametri out: comb -> numero dei combattimenti.
  * Eccezioni: SQLException in caso di errori.
  * @return comb
  */
  public int InfoGenerCombattimenti()
  {
    /* Dichiarazione variabili */
    int comb = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM COMBATTIMENTO";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* Verrà restituito sempre un unico valore dovuto al count */
        while(pa.next())
        {
          comb = pa.getInt("TOT");                      
        }             
    }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return comb;  
  }  
 
 
 /**
  * Descrzione: Seleziona il numero di tutti i posizionamenti.
  * Dettagli: Viene chiamata per selezionare il numero di tutti i posizionamenti presenti nel database.
  * Parametri in: //
  * Parametri out: pos -> numero dei posizionamenti.
  * Eccezioni: SQLException in caso di errori.
  * @return pos
  */
  public int InfoGenerPosizionamenti()
  {
    /* Dichiarazione variabili */
    int pos = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM POSIZIONAMENTO_ARMATA";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* Verrà restituito sempre un unico valore dovuto al count */
        while(pa.next())
        {
          pos = pa.getInt("TOT");                      
        }            
    }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return pos;  
  }
  
 /**
  * Descrzione: Seleziona il numero di tutti i spostamenti.
  * Dettagli: Viene chiamata per selezionare il numero di tutti i spostamenti presenti nel database.
  * Parametri in: //
  * Parametri out: spos -> numero dei spostamenti.
  * Eccezioni: SQLException in caso di errori.
  * @return spos
  */
  public int InfoGenerSpostamenti()
  {
    /* Dichiarazione variabili */
    int spos = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM SPOSTAMENTO";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* Verrà restituito sempre un unico valore dovuto al count */
        while(pa.next())
        {
          spos = pa.getInt("TOT");                      
        }            
       }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
               { 
                 Query.conrsetClose(conn, rset);
               }
 
      return spos;  
  } 
 
 /**
  * Descrzione: Seleziona il numero di tutti i posizionamenti preliminari.
  * Dettagli: Viene chiamata per selezionare il numero di tutti i posizionamenti preliminari presenti nel database.
  * Parametri in: //
  * Parametri out: prel -> numero dei posizionamenti.
  * Eccezioni: SQLException in caso di errori.
  * @return prel
  */
  public int InfoGenerPreliminari()
  {
    /* Dichiarazione variabili */
    int prel = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM POSIZIONAMENTO_ARMATA WHERE TIPO_POSIZIONAMENTO = 0";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* Verrà restituito sempre un unico valore dovuto al count */
        while(pa.next())
        {
          prel = pa.getInt("TOT");                      
        }             
      }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return prel;  
  } 
    
  /**
  * Descrzione: Seleziona il numero di tutti i posizionamenti classici.
  * Dettagli: Viene chiamata per selezionare il numero di tutti i posizionamenti classici presenti nel database.
  * Parametri in: //
  * Parametri out: cla -> numero dei posizionamenti.
  * Eccezioni: SQLException in caso di errori.
  * @return cla
  */
  public int InfoGenerClassici()
  {
    /* Dichiarazione variabili */
    int cla = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM POSIZIONAMENTO_ARMATA WHERE TIPO_POSIZIONAMENTO = 1";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* Verrà restituito sempre un unico valore dovuto al count */
        while(pa.next())
        {
          cla = pa.getInt("TOT");                      
        }            
     }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
 
      return cla;  
  } 
    
 /**
  * Descrzione: Seleziona il numero di tutti i posizionamenti con carte.
  * Dettagli: Viene chiamata per selezionare il numero di tutti i posizionamenti con combinazione carte presenti nel database.
  * Parametri in: //
  * Parametri out: cart -> numero dei posizionamenti.
  * Eccezioni: SQLException in caso di errori.
  * @return cart
  */
  public int InfoGenerCarte()
  {
    /* Dichiarazione variabili */
    int cart = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT COUNT(*) AS TOT FROM POSIZIONAMENTO_ARMATA WHERE TIPO_POSIZIONAMENTO > 1";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* Verrà restituito sempre un unico valore dovuto al count */
        while(pa.next())
        {
          cart = pa.getInt("TOT");  
        }           
      }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
            { 
               Query.conrsetClose(conn, rset);
            }
 
      return cart;  
  }    
      
 /**
  * Descrzione: Seleziona il territorio con maggiore numero di truppe.
  * Dettagli: Viene chiamata per selezionare il territorio (uno solo dovuto al break) con il maggior numero di armate.
  * In caso siano presenti più territori con lo stesso numero di armate viene sempre scelta la prima riga.
  * Parametri in: //
  * Parametri out: terr -> id del territorio.
  * Eccezioni: SQLException in caso di errori.
  * @return terr
  */   
  public int InfoGenerTerrPiu()
  {
    /* Dichiarazione variabili */
    int terr = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT ID_TERRITORIO , SUM(QUANTITA_TRUPPE) AS TOT FROM TERRITORIO_OCCUPATO GROUP BY ID_TERRITORIO ORDER BY TOT DESC";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* La scelta si basa sulla prima riga, sempre presente, che viene fornita dalla query */
        while(pa.next())
        {
          terr = pa.getInt("ID_TERRITORIO");  
          break;
        }         
      }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
             { 
                Query.conrsetClose(conn, rset);
             }
 
      return terr;  
  }  

/**
  * Descrzione: Seleziona il territorio con il minor numero di truppe.
  * Dettagli: Viene chiamata per selezionare il territorio (uno solo dovuto al break) con il minor numero di armate.
  * In caso siano presenti più territori con lo stesso numero di armate viene sempre scelta la prima riga.
  * Parametri in: //
  * Parametri out: terr -> id del territorio.
  * Eccezioni: SQLException in caso di errori.
  * @return terr
  */    
  public int InfoGenerTerrMeno()
  {
    /* Dichiarazione variabili */
    int terr = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT ID_TERRITORIO, SUM(QUANTITA_TRUPPE) AS TOT FROM TERRITORIO_OCCUPATO GROUP BY ID_TERRITORIO ORDER BY TOT ASC";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* La scelta si basa sulla prima riga, sempre presente, che viene fornita dalla query */
        while(pa.next())
        {
          terr = pa.getInt("ID_TERRITORIO");  
          break;
        }         
      }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
             { 
               Query.conrsetClose(conn, rset);
             }
 
      return terr;  
  } 
  
 /**
  * Descrzione: Seleziona il territorio che ha subito il maggior numero di attacchi.
  * Dettagli: Viene chiamata per selezionare il territorio (uno solo dovuto al break) che ha subito il maggior numero di attacchi
  * da parte dei giocatori di tutte le partite.
  * In caso siano presenti più territori con lo stesso numero di armate viene sempre scelta la prima riga.
  * Parametri in: //
  * Parametri out: terr -> id del territorio.
  * Eccezioni: SQLException in caso di errori.
  * @return terr
  */  
  public int InfoGeneraleTerrPiu()
  {
    /* Dichiarazione variabili */
    int terr = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "SELECT ID_TERRITORIO, max(N_RIPETIZIONI) as Max_R from (SELECT T1.ID_TERRITORIO, COUNT(C1.ID_TERRITORIO_ATTACCATO) AS N_RIPETIZIONI FROM Combattimento C1 JOIN TERRITORIO T1 ON C1.ID_TERRITORIO_ATTACCATO = T1.ID_TERRITORIO GROUP BY T1.ID_TERRITORIO order by N_Ripetizioni desc) where rownum = 1 group by ID_Territorio order by max_R";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* La scelta si basa sulla prima riga, sempre presente, che viene fornita dalla query */
        while(pa.next())
        {
          terr = pa.getInt("ID_TERRITORIO");  
          break;
        }         
      }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
             { 
               Query.conrsetClose(conn, rset);
             }
 
      return terr; 
  }
  
 /**
  * Descrzione: Seleziona l'obiettivo che è più presente.
  * Dettagli: Viene chiamata per selezionare la descrizione dell'obiettivo che è stato assegnano maggiormente
  * ai giocatori di tutte le partite.
  * In caso siano presenti più obiettivi con lo stesso numero di armate viene sempre scelta la prima riga.
  * Parametri in: //
  * Parametri out: ob -> descerizione dell'obiettivo.
  * Eccezioni: SQLException in caso di errori.
  * @return ob
  */  
  public String InfoGeneraleOb()
  {
    /* Dichiarazione variabili */
    String ob = null;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "select Descrizione, max(N_Obiettivi) as Max_O from (  select CO.ID_Carta, CO.DESCRIZIONE, COUNT(g1.ID_Carta_Obiettivo) as N_Obiettivi from Giocatore G1 join Carta_Obiettivo CO ON g1.ID_Carta_Obiettivo = CO.ID_Carta group by CO.ID_Carta, CO.DESCRIZIONE order by N_Obiettivi desc) where rownum = 1 group by Descrizione order by Max_O";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* La scelta si basa sulla prima riga, sempre presente, che viene fornita dalla query */
        while(pa.next())
        {
          ob = pa.getString("Descrizione");  
          break;
        }         
      }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
             { 
               Query.conrsetClose(conn, rset);
             }
 
      return ob; 
  }
  
 /**
  * Descrzione: Seleziona l'inserimento carte più usato.
  * Dettagli: Viene chiamata per selezionare il numero (da 1 a 8) relativi agli inserimenti delle carte.
  * Parametri in: //
  * Parametri out: pos -> combinazione carta.
  * Eccezioni: SQLException in caso di errori.
  * @return pos
  */  
  public int InfoGeneraleTipoPos()
  {
    /* Dichiarazione variabili */
    int pos = 0;
    Connection conn = null;
    PreparedStatement rset = null;
       
    /* Query da effettuare */
    String query = "select PA.TIPO_POSIZIONAMENTO, count(PA.TIPO_POSIZIONAMENTO) from Posizionamento_Armata PA where ((PA.TIPO_POSIZIONAMENTO > 1)) group by PA.TIPO_POSIZIONAMENTO order by PA.TIPO_POSIZIONAMENTO";
       
    try
     {
        conn = ConnessioneDB.getDefaultConnection();
        rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet pa = rset.executeQuery();
          
        /* La scelta si basa sulla prima riga, sempre presente, che viene fornita dalla query */
        while(pa.next())
        {
          pos = pa.getInt("TIPO_POSIZIONAMENTO");  
          break;
        }         
      }catch(SQLException e)
          {
            catturaErrori(e);
          }finally 
             { 
               Query.conrsetClose(conn, rset);
             }
 
      return pos; 
  }
}
