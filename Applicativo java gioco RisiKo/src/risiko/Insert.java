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
//import javax.swing.JOptionPane;
//import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import static risiko.Query.catturaErrori;

/**
 *
 * @author ikaros
 */
public class Insert {
    
   /**
    * Descrzione: Inserimento informazioni relative alla partita nel file.
    * Dettagli: Viene chiamata ogni volta che si verificano delle azioni 
    * durante la partita (dalla fase preliminare fino alla fine della partita).
    * Il file viene utilizzato per poter ripercorrere una partita passo passo.
    * Parametri in: ins -> stringa contenente tutte le info da scrivere nel
    * file.
    * Parametri out: //
    * Eccezioni: IOException nella gestione del file.
    * @param ins
    */ 
    public static void logFile(String ins)
    {
       /* Prendo il nome della partita */
       String nomepartita = MenuNewPartita.salvaPartita.nomePartita;
       
       /* Aggiungo a fine stringa la firma usata per i file */
       nomepartita = nomepartita + "_log.txt";
       
       /* Creo tipo file per effettuare verifiche sul file */
       File datafile = new File(nomepartita);
       
       try {
             /* File cancellato o rimosso dal path principale */
             if (!datafile.exists()) 
             {
              // System.out.println("\nFile cancellato!");
               /* Creo il file e provvedo ad iformare l'utente che il file non è più presente */
               FileWriter fileout = new FileWriter(nomepartita,true);
               BufferedWriter filebuf = new BufferedWriter(fileout);
               PrintWriter printout = new PrintWriter(filebuf);
               printout.println("Il file della partita è stato cancellato. I dati precedenti sono andati persi. :(");
               printout.close();
             }
             
             /* "Apro" il file e inserisco la stringa */
             FileWriter fileout = new FileWriter(nomepartita,true);
             BufferedWriter filebuf = new BufferedWriter(fileout);
             PrintWriter printout = new PrintWriter(filebuf);
   
             printout.println(ins);
             printout.close();
           
       } catch (IOException e) {
                e.printStackTrace();
             }
    }
    
  /**
   * Descrzione: Inserimento nome partita
   * Dettagli: Viene chiamata ogni volta che viene creata una partita.
   * Parametri in: nome_partita -> nome scelto dall'utnte per la creazione
   * di una partita.
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param nome_partita
   */ 
   public void insertPartita(String nome_partita)
   {
       /* Dichiarazione variabili */
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "INSERT INTO PARTITA(NOME_PARTITA) VALUES(?)";
       
       try
       {
           conn = ConnessioneDB.getDefaultConnection();
           rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         //  rset = conn.prepareStatement("INSERT INTO PARTITA(NOME_PARTITA) VALUES(?)");
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
   * Descrzione: Inserimento giocatori e colori.
   * Dettagli: Viene chiamata  dopo la creazione di una partita
   * inserendo le informazioni di gioco quali nickname dei giocatori
   * e i ripsettivi colori scelti.
   * Parametri in: nome_giocatore -> nome del giocatore scelto,
   * colore -> colore scelto
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param nome_giocatore
   * @param colore 
   */ 
   public void insertGiocatore(String nome_giocatore , String colore)
   {
      /* Dichiarazione variabili */
      Connection conn = null;
      PreparedStatement rset = null;
     
      /* Query da effettuare */
      String query = "INSERT INTO GIOCATORE(NICKNAME,COLORE) VALUES(?,?)";
        
      try
       {
         // System.out.println("INSERISCO GIOCATORE:"+nome_giocatore +" COLORE:"+colore);
           conn = ConnessioneDB.getDefaultConnection();
           rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         //  rset = conn.prepareStatement("INSERT INTO GIOCATORE(NICKNAME,COLORE) VALUES(?,?)");
           rset.setString(1,nome_giocatore);
           rset.setString(2,colore);
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
   * Descrzione: Inserimento per l'incremento del turno.
   * Dettagli: Viene chiamata ad ogni fine turno per passare al
   * giocatore successivo.
   * Parametri in: id_partita -> identificativo della partita.
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   */ 
   public void incrementaTurno(String id_partita)
   {
        /* Dichiarazione variabili */ 
        Connection conn = null;
        PreparedStatement rset = null;
       // String query = "INSERT INTO TURNO(ID_PARTITA) VALUES(?)";
          
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         //rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         rset = conn.prepareStatement("INSERT INTO TURNO(ID_PARTITA) VALUES(?)");
         rset.setString(1,id_partita);
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
   * Descrzione: Inserimento delle informazioni nella fase preliminare.
   * Dettagli: Viene chiamata solo nella fase preliminare per l'inserimento
   * nella tabella posizionmanto armata (informazioni relative alle truppe inserite 
   * in un territorio). Ulterioti informazioni presenti 
   * nel manuale dell'applicativo.
   * Parametri in: id_partita -> identificativo della partita, id_terr -> territorio
   * in cui vengono iserite le armate, armate -> numero di armate da aggiungere
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_terr
   * @param armate
   */ 
   public void insertPosArmataPrel(String id_partita, int id_terr, int armate)
   {
       /* Dichiarazione variabili */
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "INSERT INTO POSIZIONAMENTO_ARMATA(TIPO_POSIZIONAMENTO,ID_PARTITA,ID_TERRITORIO_POS,RINFORZI_TRUPPE) VALUES(0,?,?,?)";
        
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
 //       rset = conn.prepareStatement("INSERT INTO POSIZIONAMENTO_ARMATA(TIPO_POSIZIONAMENTO,ID_PARTITA,ID_TERRITORIO_POS,RINFORZI_TRUPPE) VALUES(0,?,?,?)");
         rset.setString(1,id_partita);  
         rset.setInt(2,id_terr);
         rset.setInt(3,armate);
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
   * Descrzione: Inserimento delle informazioni nella fase di gioco. 
   * Dettagli: Viene chiamata quando si effettua l'iserimento di armate
   * mediante l'uso di una combinazione di carte.
   * Ulterioti informazioni presenti nel manuale dell'applicativo.
   * Parametri in: id_partita -> identificativo della partita, id_terr -> id territorio
   * dove posizionare le armate, armate -> numero di armate, tipo_pos -> numero identificativo
   * della combinazione di carte utilizzata (vedi manuale).
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_terr
   * @param armate
   * @param tipo_pos
   */ 
   public void insertPosArmataCarte(String id_partita, int id_terr, int armate, int tipo_pos)
   {
       /* Dichiarazione variabili */
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "INSERT INTO POSIZIONAMENTO_ARMATA(TIPO_POSIZIONAMENTO,ID_PARTITA,ID_TERRITORIO_POS,RINFORZI_TRUPPE) VALUES(?,?,?,?)";
        
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       //  rset = conn.prepareStatement("INSERT INTO POSIZIONAMENTO_ARMATA(TIPO_POSIZIONAMENTO,ID_PARTITA,ID_TERRITORIO_POS,RINFORZI_TRUPPE) VALUES(?,?,?,?)");
         rset.setInt(1,tipo_pos);
         rset.setString(2,id_partita);  
         rset.setInt(3,id_terr);
         rset.setInt(4,armate);
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
   * Descrzione: Inserimento delle informazioni nella fase di gioco. 
   * Dettagli: Viene chiamata quando si effettua l'iserimento di armate
   * mediante l'inserimento classico. Ulteriori info nella documentazione
   * dell'applicativo.
   * Parametri in: id_partita -> identificativo della partita, id_terr -> id territorio
   * dove posizionare le armate, armate -> numero di armate da inserire
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_terr
   * @param armate
   */ 
   public void isertPosArmataClassica(String id_partita, int id_terr, int armate)
   {
       /* Dichiarazione variabili */
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "INSERT INTO POSIZIONAMENTO_ARMATA(TIPO_POSIZIONAMENTO,ID_PARTITA,ID_TERRITORIO_POS,RINFORZI_TRUPPE) VALUES(1,?,?,?)";
        
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       //  rset = conn.prepareStatement("INSERT INTO POSIZIONAMENTO_ARMATA(TIPO_POSIZIONAMENTO,ID_PARTITA,ID_TERRITORIO_POS,RINFORZI_TRUPPE) VALUES(1,?,?,?)");
         rset.setString(1,id_partita);  
         rset.setInt(2,id_terr);
         rset.setInt(3,armate);
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
   * Descrzione: Inserimento delle informazioni di combattimento.
   * Dettagli: Viene chiamata quando il giocatore si prepara al combattimento
   * inserendo le infomazioni relative ai territori di attacco e difesa.
   * Parametri in: id_partita -> identificativo della partita, id_giocatore_dif -> id del giocatore
   * che si difende, idterr_att -> id del territorio da cui si attacca, idterr_dif -> territorio che 
   * si difende.
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_giocatore_dif
   * @param idterr_att
   * @param idterr_dif
   */   
  public void insertCombattimento(String id_partita, int id_giocatore_dif, int idterr_att, int idterr_dif)
  {
     /* Dichiarazione variabili */
     Connection conn = null;
     PreparedStatement rset = null;
     
     /* Query da effettuare */
     String query = "INSERT INTO COMBATTIMENTO (ID_PARTITA,ID_G_DIFENSORE,ID_TERRITORIO_ATTACCANTE,ID_TERRITORIO_ATTACCATO) VALUES(?,?,?,?)";
        
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         //rset = conn.prepareStatement("INSERT INTO COMBATTIMENTO (ID_PARTITA,ID_G_DIFENSORE,ID_TERRITORIO_ATTACCANTE,ID_TERRITORIO_ATTACCATO) VALUES(?,?,?,?)");
         rset.setString(1,id_partita);  
         rset.setInt(2,id_giocatore_dif);
         rset.setInt(3,idterr_att);
         rset.setInt(4,idterr_dif);
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
   * Descrzione: Inserimento delle informazioni di combattimento.
   * Dettagli: Viene chiamata quando il giocatore si prepara al combattimento
   * inserendo le infomazioni relative ai territori di attacco e difesa.
   * Parametri in: id_partita -> identificativo della partita, dadoNatt -> valore dei
   * dadi lanciati dall'attaccante, dadoNdiff -> valore dei dadi lanciati
   * dal giocatore difensore (i dadi sono stringhe in quanto i dadi non lanciati vengono
   * considerati pari a null).
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param dado1att
   * @param dado2att
   * @param dado3att
   * @param dado1dif
   * @param dado2dif
   * @param dado3dif
   */ 
   public void insertLancioDadi(String id_partita, String dado1att, String dado2att, String dado3att, String dado1dif, String dado2dif, String dado3dif)
   {
       /* Dichiarazione variabili */
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "INSERT INTO LANCIO_DADI(ID_PARTITA,DADO1_ATT, DADO2_ATT, DADO3_ATT, DADO1_DIFF, DADO2_DIFF, DADO3_DIFF) VALUES(?,?,?,?,?,?,?)";
        
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //rset = conn.prepareStatement("INSERT INTO LANCIO_DADI(ID_PARTITA,DADO1_ATT, DADO2_ATT, DADO3_ATT, DADO1_DIFF, DADO2_DIFF, DADO3_DIFF) VALUES(?,?,?,?,?,?,?)");
         rset.setString(1,id_partita);  
         rset.setString(2,dado1att);
         rset.setString(3,dado2att);
         rset.setString(4,dado3att);
         rset.setString(5,dado1dif);
         rset.setString(6,dado2dif);
         rset.setString(7,dado3dif);
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
   * Descrzione: Inserimento delle infomazioni relative allo spostamento.
   * Dettagli: Viene chiamata quando nella terza fase di gioco quando il giocatore
   * decide di spostare le sue armate da un territorio a un altro.
   * Parametri in: id_partita -> identificativo della partita, id_terr_partenza -> id del
   * territorio da cui prendere le armate, id_terr_arrivo -> id territorio dove spostare le armate,
   * truppe_da_spostare -> numero di armate da spostare.
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori.
   * @param id_partita
   * @param id_terr_partenza
   * @param id_terr_arrivo
   * @param truppe_da_spostare
   */
   public void insertSpostamento(String id_partita, int id_terr_partenza, int id_terr_arrivo, int truppe_da_spostare)
   {
       /* Dichiarazione variabili */
       Connection conn = null;
       PreparedStatement rset = null;
       
       /* Query da effettuare */
       String query = "INSERT INTO SPOSTAMENTO (ID_PARTITA,ID_TERRITORIO_PARTENZA,ID_TERRITORIO_ARRIVO,TRUPPE_SPOSTATE) VALUES(?,?,?,?)";
        
       try
       {
         conn = ConnessioneDB.getDefaultConnection();
         rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        // rset = conn.prepareStatement("INSERT INTO SPOSTAMENTO (ID_PARTITA,ID_TERRITORIO_PARTENZA,ID_TERRITORIO_ARRIVO,TRUPPE_SPOSTATE) VALUES(?,?,?,?)");
         rset.setString(1,id_partita);  
         rset.setInt(2,id_terr_partenza);
         rset.setInt(3,id_terr_arrivo);
         rset.setInt(4,truppe_da_spostare);
      
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
    
