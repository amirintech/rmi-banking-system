package frontend;

import models.Account;
import rmi.LocalMaster;
import services.BranchesService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignInFrame extends javax.swing.JFrame {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> branchList;
    private javax.swing.JButton createAccountButton;
    private javax.swing.JTextField emailInput;
    private javax.swing.JLabel invalidLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField passwordInput;
    private javax.swing.JButton signInButton;
    public SignInFrame() {
        initComponents();

        setLocationRelativeTo(null);
        populateBranches();
        invalidLabel.setVisible(false);
    }

    public static void main(String[] args) {
        new SignInFrame().setVisible(true);
    }

    private void populateBranches() {
        List<String> branches = new ArrayList<>();
        for (var b : BranchesService.WestBranches.values()) branches.add(b.getName());
        for (var b : BranchesService.EastBranches.values()) branches.add(b.getName());

        for (var b : branches) branchList.addItem(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        signInButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        createAccountButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        branchList = new javax.swing.JComboBox<>();
        passwordInput = new javax.swing.JTextField();
        invalidLabel = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Sign In");

        jLabel2.setText("Email");

        jLabel3.setText("Password");

        signInButton.setText("Sign in");
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Do not have an account?");

        createAccountButton.setText("Create account");
        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Branch");

        branchList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));

        invalidLabel.setBackground(new java.awt.Color(255, 51, 51));
        invalidLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        invalidLabel.setForeground(new java.awt.Color(255, 0, 51));
        invalidLabel.setText("Invalid Credintials");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(84, Short.MAX_VALUE)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(createAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(152, 152, 152)
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel5))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(emailInput)
                                                        .addComponent(branchList, 0, 263, Short.MAX_VALUE)
                                                        .addComponent(passwordInput))))
                                .addGap(38, 38, 38))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(invalidLabel)
                                        .addComponent(signInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(134, 134, 134))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1)
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(branchList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(invalidLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(signInButton)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(createAccountButton))
                                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        String branch = branchList.getSelectedItem().toString();
        String email = emailInput.getText();
        String password = passwordInput.getText();

        try {
            String masterName = BranchesService.isWestBranch(branch) ? "WestLocalMaster" : "EastLocalMaster";
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            LocalMaster master = (LocalMaster) registry.lookup(masterName);

            Account account = master.login(branch, email, password);
            if (account == null) {
                invalidLabel.setVisible(true);
                return;
            }
            new MainFrame(account).setVisible(true);
            this.setVisible(false);
            this.dispose();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(SignUpFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_signInButtonActionPerformed

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccountButtonActionPerformed
        this.setVisible(false);
        new SignUpFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_createAccountButtonActionPerformed
    // End of variables declaration//GEN-END:variables
}
