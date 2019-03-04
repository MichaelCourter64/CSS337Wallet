/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JFrame;
import css337walletassignment.MoneyTransfer;

/**
 *
 * @author Michael
 */
public class ReceiveFundsMenu extends javax.swing.JFrame {
    private JFrame previousMenu;
    
    /**
     * Creates new form ReceiveFundsMenu
     */
    public ReceiveFundsMenu(JFrame previousMenu) {
        this.previousMenu = previousMenu;
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnReturnToPreviousMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TxtFldTransactionToken = new javax.swing.JTextField();
        BtnProcessTransaction = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnReturnToPreviousMenu.setText("<---");
        BtnReturnToPreviousMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReturnToPreviousMenuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Transaction Token:");

        BtnProcessTransaction.setText("Process Transaction");
        BtnProcessTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProcessTransactionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BtnReturnToPreviousMenu)
                .addGap(0, 326, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(TxtFldTransactionToken, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(BtnProcessTransaction)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BtnReturnToPreviousMenu)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtFldTransactionToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnProcessTransaction)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnReturnToPreviousMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReturnToPreviousMenuActionPerformed
        this.setVisible(false);

        previousMenu.setVisible(true);
    }//GEN-LAST:event_BtnReturnToPreviousMenuActionPerformed

    private void BtnProcessTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProcessTransactionActionPerformed
        MoneyTransfer.recieve(TxtFldTransactionToken.getText());
    }//GEN-LAST:event_BtnProcessTransactionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnProcessTransaction;
    private javax.swing.JButton BtnReturnToPreviousMenu;
    private javax.swing.JTextField TxtFldTransactionToken;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
