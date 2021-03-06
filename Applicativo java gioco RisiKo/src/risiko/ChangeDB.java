/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;


/**
 *
 * @author ikaros
 */

/* DESCRIZIONE GENERICA
   
Classe adibita alla personalizzazione dei parametri d'accesso al database.
L'utente può usufruire della possibilità di cambiare informazioni di accesso quali:
-Host
-Servizio
-Porta

I cambiamenti vengono eddettuati agendo sul file ConnessioneDB.java.
Nonostante la personalizzazione possa essere caratterizzata da un uso da parte di 
soggetti più esperti, sono stati introdotti parametri user friendly come il pulsante
per ripristinare i parametri di default e gli stessi mostrati ogni volta viene chiamato
form.

*/
public class ChangeDB extends javax.swing.JFrame {

    /**
     * Creates new form ChangeDB
     */
    public ChangeDB() {
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

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Opzioni connessione");
        setResizable(false);

        jTextField1.setText("localhost");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton1.setText("Applica");
        jButton1.setToolTipText("Applica i cambiamenti");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ripristina predefiniti");
        jButton2.setToolTipText("In caso di problemi puoi ripristinare i valori di default");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField2.setText("xe");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jTextField3.setText("1521");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("• Host");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("• Servizio");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("• Porta");

        jButton3.setText("Indietro");
        jButton3.setToolTipText("Torna indietro");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("- Personalizzazione dei parametri d'accesso al database -");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(jLabel4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BOTTONE: APPLICA
        
        /* Dichiarazione variabili */
        Component err = null;
        
        /* Verifica che tutti i parametri inseriri non siano vuoti */
        if(jTextField1.getText().equals("") || jTextField2.getText().equals("") || jTextField3.getText().equals("") )
        {
            JOptionPane.showMessageDialog(err,"Verificare che tutti i campi siano riempiti!","Attenzione",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            /* Prelevo le informazioni inserite */
            String h = jTextField1.getText();
            String s = jTextField2.getText();
            String p = jTextField3.getText();
            
            int count = 0, i = 0;
            
            /* Verifico che i numeri inseriti non superino i 10 caratteri */
            for(i=0; i < p.length();i++)
            {
                count++; 
            }
            
            /* Se <= 10 allora non si solleva l'eccezzione e si prosegue */
            if(count <= 10)
            {
                /* Converto la porta specificata in un intero */
                int porta = Integer.parseInt(p);

                /* Setto i parametri dati */
                ConnessioneDB.PORTA = porta;
                ConnessioneDB.SERVIZIO = s;
                ConnessioneDB.HOST = h;

                /* Informo l'utente */
                JOptionPane.showMessageDialog(err,"Sono stati impostati come parametri\nHost: " + h +"\nServizio: " + s +"\nPorta: " + p,"Eseguito",JOptionPane.INFORMATION_MESSAGE);

               this.dispose();
            }
            else
                JOptionPane.showMessageDialog(err,"Il velore numerico della porta non deve superare i 10 caratteri numerici!","Attenzione",JOptionPane.WARNING_MESSAGE);
        }    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // BOTTONE: INDIETRO

        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // KEY EVENT PER NON ACCETTARE CARATTERI NELL'INSERIMENDO DELLA PORTA
        
        /* Dichiarazione variabili */
        char vchar = evt.getKeyChar();
        
        if(!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
        {
           evt.consume();
        } 
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BOTTONE: RIPRISTINA PREDEFINITI
        
         /* Dichiarazione variabili */ 
         Component es = null;
         
         /* Ripristino i parametri di default per la connessione al dbms */
         ConnessioneDB.PORTA = 1521;
         ConnessioneDB.SERVIZIO = "xe";
         ConnessioneDB.HOST = "localhost";
         
         /* Informo l'utente */
         JOptionPane.showMessageDialog(es,"Sono stati impostati come parametri di default\nHost: localost\nServizio: xe\nPorta: 1521","Eseguito",JOptionPane.INFORMATION_MESSAGE);
         
         this.dispose();    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // KEY TYPED PER LA VERIFICA DELLA LUNGHEZZA DEL'HOST 
      
       if(jTextField1.getText().length()>= 20) evt.consume();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
       // KEY TYPED PER LA VERIFICA DELLA LUNGHEZZA DEL servizio
       
       /* Elimino la possibilità di usare incolla */
       jTextField2.setTransferHandler(null);
       if(jTextField2.getText().length()>= 10) evt.consume();
    }//GEN-LAST:event_jTextField2KeyTyped

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
