/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static risiko.MenuNewPartita.verificaCarattere;
import static risiko.Query.catturaErrori;

/**
 *
 * @author ikaros
 */
/* 
    DESCRIZIONE GENERALE

Quando viene chiuso l'applicativo si procede a "salvare" la partita per poi
eventualmente riprenderla.
Il processo prevede l'interrogazione delle tre tabelle delle fasi principali:
- Posizionamento
- Combattimento
- Spostamento

Si parte nel prelevare l'ultimo id turno (ovvero il giocatore a cui tocca) per poi 
verificare che esista o meno (partendo da Posizionamento fino a Spostamento) almeno una riga.
Si procede finchè non si trova una tabella che in quel turno non ha righe e quindi si procede
a chiamare la fase prescelta.

*/
public class MenuCarica extends javax.swing.JFrame {

    /**
     * Creates new form MenuCarica
     */
    public MenuCarica() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Riprendi una partita");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Partite presenti");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Inserisci il nome della partita");

        jTextField1.setText("...");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton1.setText("Riprendi partita");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Indietro");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BOTTONE: INDIETRO
    
        /* Chiamo il form MenuBase */
        this.setVisible(false);
        dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new MenuBase().setVisible(true);
                 }
            });   
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // APERTURA DI MENU CARICA
        
        /* Dichiarazione variabili */
        Connection conn = null;
        PreparedStatement rset = null;
        String partita = null;
        
        /* Query da effettuare */
        String query = "SELECT NOME_PARTITA FROM PARTITA";
        
      try
         {
            conn = ConnessioneDB.getDefaultConnection();
            //rset = conn.prepareStatement("SELECT NOME_PARTITA FROM PARTITA");
            rset = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet respartita = rset.executeQuery();
            
            /* Prelevo e mostro ogni partita */
            while(respartita.next())
            {
               partita = respartita.getString("NOME_PARTITA");
               jTextArea1.append(partita + "\n");        
            }
           
         }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }   
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BOTTONE: RIPRENDI PARTITA
        
      /* Dichiarazione variabili */
      Query dbms = new Query();
      Component err = null;
      
      /* Prendo il nome partita scritto dall'utente */
      String nomep = jTextField1.getText();
      String idp = dbms.idPartita(nomep);
      
      /* Prendo l'ultimo turno */
      String idt = dbms.maxIDTurno(idp);
     // System.out.println("P:" + idp + " T:" + idt);
  
     /* Verifico che la partita non sia finita */
     int check = dbms.checkVincitore(nomep);
    
     /* La partita può essere finita oppure si è inserito un nome partita non valido*/
     if(check != 0 || idp == null )
     {
         if(idp == null)
         {
             JOptionPane.showMessageDialog(err,"La partita " + nomep + " non esiste!","Attenzione",JOptionPane.WARNING_MESSAGE);    
         }
         else
         {
             JOptionPane.showMessageDialog(err,"Non puoi riprendere la partita " + nomep + " perchè è finita!","Attenzione",JOptionPane.WARNING_MESSAGE);    
         }
     }
     else
     {
        /* Parita presente e in corso */
        
        /* Salvo il nome delle partita per futuri utilizzi */
        MenuNewPartita.salvaPartita.nomePartita = nomep;
        
        /* Verfico che ci sia almeno una riga in posizionamento */
        check = dbms.checkPosizionamento(idp, idt);
        
        /* Significa che non ci sono righe in nella tabella
           posizionamento. Quindi provvedo a chiare il form
           relativo alla fase di posizionamento.
        */
        if(check == 0)
        {
            deleteTurno(idt);
            //System.out.println("Chiamo posizionamento");
            this.setVisible(false);
            dispose();
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 new Posizionamento().setVisible(true);
                 }
            });             
        }
        else
        {
            /* Verfico che ci sia almeno una riga in combattimento */
            check = dbms.checkCombattimento(idp, idt);
            
            /* Significa che non ci sono righe in nella tabella
           combattimneto. Quindi provvedo a chiare il form
           relativo alla fase di posizionamento.
            */
            if(check == 0)
            {
              //  System.out.println("Chiamo combattimento");
                this.setVisible(false);
                dispose();
                java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                     new Combattimento().setVisible(true);
                     }
                });   
            }
            else
            {
               /* Verfico che ci sia almeno una riga in spostamento */
               check = dbms.checkSpostamento(idp, idt);
               
               /* Significa che non ci sono righe in nella tabella
                  posizionamento quindi provvedo a chiare il form
                 relativo alla fase di posizionamento.
               */
               if(check == 0)
               {
                 // System.out.println("Chiamo spostamento");
                  this.setVisible(false);
                    dispose();
                    java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                         new Spostamento().setVisible(true);
                         }
                    });                    
               }
            } 
        }
     }
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // KEY TYPED PER EVITARE CARATTERI NON CONSONI PER IL RICHIAMO DELLA PARTITA
      
        /* Prendo il carattere digitato */
       char c = evt.getKeyChar();
       /* Verifico che sia accettabile */
       int i = verificaCarattere(c);
       /* Carattere non consono lo scarto */
       if(i != 1) evt.consume();
       /* Imposto lunghezza massima di 20 caratteri */
       if(jTextField1.getText().length()>= 20) evt.consume();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
         // KEY PRESSED PER CARICARE LA PARTITA
        
        /* Elimino la possibilità di usare incolla */
        jTextField1.setTransferHandler(null);
    }//GEN-LAST:event_jTextField1KeyPressed

  /**
   * Descrzione: Eliminazione dell'ultimo turno
   * Dettagli: Viene chiamata quando si carica la partita e si passa alla fase
   * di posizionamento. Questo è dovuto perchè il form posizionamento incrementa l'id
   * turno.
   * Parametri in: id_turno -> ultimo id turno.
   * Parametri out: //
   * Eccezioni: SQLException in caso di errori nell'eliminazione di una partita.
   * @param id_turno
   */ 
   public void deleteTurno(String id_turno)
   {
       /* Dichiarazione variabili */
       Connection conn = null;
       PreparedStatement rset = null;
       
      try
       {
           conn = ConnessioneDB.getDefaultConnection();
           rset = conn.prepareStatement("DELETE FROM TURNO WHERE ID_TURNO = ?");
           rset.setString(1,id_turno);
           rset.executeUpdate();
        
       }catch(SQLException e)
            {
              catturaErrori(e);
            }finally 
                   { 
                      Query.conrsetClose(conn, rset);
                   }
   }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}