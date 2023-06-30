package com.samuelandrey.jenovabot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormDashboard extends javax.swing.JFrame {
    
    Boolean editKeyword = false;
    Boolean editBroadcast = false;
    
    JenovaBot jenova = new JenovaBot();
    ConnectionMysql connection;
    
    public FormDashboard() {
        initComponents();
        connection = new ConnectionMysql();
        
        setComboBoxBroadcast();
        readBroadcastInfo();
        readKeyword();
        
        activeFieldKeyword(false);
        setButtonKeyword(true);
        
    }
    
    private void setComboBoxBroadcast() {
        ArrayList<String> options = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            String selectQuery = "SELECT * FROM tb_user";
            statement = connection.getConnection().prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)tblBroadcast.getModel();
            tableModel.setRowCount(0); //reset data
            
            while (resultSet.next()) {
                String idUser = resultSet.getString("id_user");
                options.add(idUser);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JenovaBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultComboBoxModel<String> comboBox = new DefaultComboBoxModel<>(options.toArray(new String[0]));
        cmbIdUserBC.setModel(comboBox);
    }
    
    private void setFieldKeyword() {
        int row = tblKeyword.getSelectedRow();
        
        String idKeyword = Long.toString((Long)tblKeyword.getValueAt(row, 0));
        txtIdKey.setText(idKeyword);
        txtCommand.setText((String)tblKeyword.getValueAt(row, 1));
        txtResponse.setText((String)tblKeyword.getValueAt(row, 2));
    }
    
    private void setButtonKeyword(boolean t) {
        btnAddKey.setEnabled(t); 
        btnEditKey.setEnabled(t); 
        btnDeleteKey.setEnabled(t); 
        btnSaveKey.setEnabled(!t); 
        btnCancelKey.setEnabled(!t);
    }
    
    private void clearFieldKeyword() {
        txtIdKey.setText("");
        txtCommand.setText("");
        txtResponse.setText("");
    }
    
    private void activeFieldKeyword(boolean x) {
        txtIdKey.setEditable(x);
        txtCommand.setEditable(x);
        txtResponse.setEditable(x);
    }
    
    private void readBroadcastInfo() {
        PreparedStatement statement = null;
        try {
            String selectQuery = "SELECT * FROM tb_broadcast_info";
            statement = connection.getConnection().prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)tblBroadcast.getModel();
            tableModel.setRowCount(0); //reset data
            
            while (resultSet.next()) {
                Long idUser = resultSet.getLong("id_user");
                String userName = resultSet.getString("username");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                
                Object[] rowData = {idUser, userName, firstName, lastName};
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JenovaBot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void readKeyword() {
        PreparedStatement statement = null;
        try {
            String selectQuery = "SELECT * FROM tb_keyword";
            statement = connection.getConnection().prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)tblKeyword.getModel();
            tableModel.setRowCount(0); //reset data
            
            while (resultSet.next()) {
                Long idKeyword = resultSet.getLong("id_keyword");
                String command = resultSet.getString("command");
                String response = resultSet.getString("response");
                
                Object[] rowData = {idKeyword, command, response};
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JenovaBot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKeyword = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtCommand = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResponse = new javax.swing.JTextArea();
        btnAddKey = new javax.swing.JButton();
        btnSaveKey = new javax.swing.JButton();
        btnEditKey = new javax.swing.JButton();
        btnDeleteKey = new javax.swing.JButton();
        btnCancelKey = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBroadcast = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtBroadcast = new javax.swing.JTextArea();
        btnSendBroadcast = new javax.swing.JButton();
        btnInsertBC = new javax.swing.JButton();
        btnDeleteBC = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cmbIdUserBC = new javax.swing.JComboBox<>();
        txtUsernameBC = new javax.swing.JTextField();
        txtIdKey = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtFirstNameBC = new javax.swing.JTextField();
        txtLastNameBC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        tabHistory = new javax.swing.JMenu();
        tabUser = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.focusColor"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Jenova Bot");

        tblKeyword.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tblKeyword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblKeyword.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Command", "Response"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKeyword.setShowGrid(false);
        tblKeyword.getTableHeader().setReorderingAllowed(false);
        tblKeyword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKeywordMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKeyword);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Command");

        txtCommand.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Response");

        txtResponse.setColumns(20);
        txtResponse.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtResponse.setRows(5);
        txtResponse.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane2.setViewportView(txtResponse);

        btnAddKey.setBackground(java.awt.SystemColor.activeCaption);
        btnAddKey.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAddKey.setText("Add");
        btnAddKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKeyActionPerformed(evt);
            }
        });

        btnSaveKey.setBackground(java.awt.SystemColor.activeCaption);
        btnSaveKey.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSaveKey.setText("Save");
        btnSaveKey.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveKeyMouseClicked(evt);
            }
        });

        btnEditKey.setBackground(java.awt.SystemColor.activeCaption);
        btnEditKey.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditKey.setText("Edit");
        btnEditKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditKeyActionPerformed(evt);
            }
        });

        btnDeleteKey.setBackground(java.awt.SystemColor.activeCaption);
        btnDeleteKey.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteKey.setText("Delete");
        btnDeleteKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteKeyActionPerformed(evt);
            }
        });

        btnCancelKey.setBackground(java.awt.SystemColor.activeCaption);
        btnCancelKey.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelKey.setText("Cancel");
        btnCancelKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelKeyActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Keyword Manager");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Broadcast");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("List user to receive broadcast");

        tblBroadcast.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblBroadcast.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id User", "Username", "Firstname", "Lastname"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBroadcast.setShowGrid(false);
        tblBroadcast.setSurrendersFocusOnKeystroke(true);
        tblBroadcast.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblBroadcast);

        txtBroadcast.setColumns(20);
        txtBroadcast.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBroadcast.setRows(5);
        jScrollPane5.setViewportView(txtBroadcast);

        btnSendBroadcast.setBackground(java.awt.SystemColor.activeCaption);
        btnSendBroadcast.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSendBroadcast.setText("Send Broadcast");
        btnSendBroadcast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendBroadcastActionPerformed(evt);
            }
        });

        btnInsertBC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnInsertBC.setText("Insert");
        btnInsertBC.setFocusable(false);
        btnInsertBC.setOpaque(true);
        btnInsertBC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertBCMouseClicked(evt);
            }
        });

        btnDeleteBC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteBC.setText("Delete");
        btnDeleteBC.setFocusable(false);
        btnDeleteBC.setOpaque(true);
        btnDeleteBC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteBCActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Type broadcast message to user :");

        cmbIdUserBC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbIdUserBC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbIdUserBCItemStateChanged(evt);
            }
        });

        txtUsernameBC.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Id Keyword");

        txtFirstNameBC.setEditable(false);

        txtLastNameBC.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Firstname");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Lastname");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Id User");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Username");

        jMenuBar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenu1.setText("Dashboard");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuBar1.add(jMenu1);

        tabHistory.setText("History");
        tabHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabHistoryMouseClicked(evt);
            }
        });
        jMenuBar1.add(tabHistory);

        tabUser.setText("User");
        tabUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabUserMouseClicked(evt);
            }
        });
        jMenuBar1.add(tabUser);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnAddKey, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSaveKey, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEditKey, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDeleteKey, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelKey, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane2)
                        .addComponent(jLabel4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(txtCommand)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtIdKey, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSendBroadcast, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInsertBC, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeleteBC, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFirstNameBC)
                                    .addComponent(cmbIdUserBC, 0, 228, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsernameBC, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLastNameBC, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdKey, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddKey, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSaveKey, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditKey, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeleteKey, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelKey, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbIdUserBC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsernameBC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstNameBC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastNameBC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInsertBC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteBC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSendBroadcast, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendBroadcastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendBroadcastActionPerformed
        String broadcast = txtBroadcast.getText();
        try {
            jenova.sendBroadcastMessage(broadcast);
            JOptionPane.showMessageDialog(null, "Berhasil mengirim broadcast");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengirim broadcast");
        }  
    }//GEN-LAST:event_btnSendBroadcastActionPerformed

    private void btnDeleteBCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteBCActionPerformed
        
        int row = tblBroadcast.getSelectedRow();
        long idUser = (long)tblBroadcast.getValueAt(row,0);

        PreparedStatement statement = null;
        try {
            String updateQuery = "DELETE FROM tb_broadcast WHERE id_user = ?";
            statement = connection.getConnection().prepareStatement(updateQuery);
            statement.setLong(1, idUser);
            statement.executeUpdate();
            
            readBroadcastInfo();
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data");
        }
        
    }//GEN-LAST:event_btnDeleteBCActionPerformed

    private void btnDeleteKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteKeyActionPerformed
        int row = tblKeyword.getSelectedRow();
        long idKeyword = (long)tblKeyword.getValueAt(row,0);

        PreparedStatement statement = null;
        try {
            String updateQuery = "DELETE FROM tb_keyword WHERE id_keyword = ?";
            statement = connection.getConnection().prepareStatement(updateQuery);
            statement.setLong(1, idKeyword);
            statement.executeUpdate();
            
            readKeyword();
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data");
        }
        
    }//GEN-LAST:event_btnDeleteKeyActionPerformed

    private void tblKeywordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKeywordMouseClicked
        setFieldKeyword();
    }//GEN-LAST:event_tblKeywordMouseClicked

    private void btnAddKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKeyActionPerformed
        activeFieldKeyword(true);
        txtIdKey.setEditable(false);
        setButtonKeyword(false);
        clearFieldKeyword();
    }//GEN-LAST:event_btnAddKeyActionPerformed

    private void btnCancelKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelKeyActionPerformed
        activeFieldKeyword(false);
        setButtonKeyword(true);
        clearFieldKeyword();
    }//GEN-LAST:event_btnCancelKeyActionPerformed

    private void btnEditKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditKeyActionPerformed
        editKeyword = true;
        activeFieldKeyword(true); 
        setButtonKeyword(false); 
        txtIdKey.setEditable(false); 
    }//GEN-LAST:event_btnEditKeyActionPerformed

    private void btnSaveKeyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveKeyMouseClicked
        
        PreparedStatement statement = null;
        try {
            String command = txtCommand.getText();
            String response = txtResponse.getText();
           if (editKeyword == true) {
                int idKey = Integer.parseInt(txtIdKey.getText());
                String updateQuery = "UPDATE tb_keyword SET command = ?, response = ? WHERE id_keyword = ?";
                statement = connection.getConnection().prepareStatement(updateQuery);
                
                statement.setString(1, command);
                statement.setString(2, response);
                statement.setInt(3, idKey);
                statement.executeUpdate();
                readKeyword();
                JOptionPane.showMessageDialog(null, "Berhasil mengedit data");
                editKeyword = false;
            } else {
                String updateQuery = "INSERT INTO tb_keyword (command, response) VALUES (?, ?)";
                statement = connection.getConnection().prepareStatement(updateQuery);
                statement.setString(1, command);
                statement.setString(2, response);
                statement.executeUpdate();
                readKeyword();
                JOptionPane.showMessageDialog(null, "Berhasil menambah data");
            }
            activeFieldKeyword(false);
            setButtonKeyword(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSaveKeyMouseClicked

    private void cmbIdUserBCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbIdUserBCItemStateChanged
        String id = cmbIdUserBC.getSelectedItem().toString();
        long idUser = Long.parseLong(id);
        
        String username = "";
        String firstName = "";
        String lastName = "";
        
        PreparedStatement statement = null;
        try {
            String selectQuery = "SELECT * FROM tb_user WHERE id_user = ?";
            statement = connection.getConnection().prepareStatement(selectQuery);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                username = resultSet.getString("username");
                firstName = resultSet.getString("firstname");
                lastName = resultSet.getString("lastname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JenovaBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtUsernameBC.setText(username);
        txtFirstNameBC.setText(firstName);
        txtLastNameBC.setText(lastName);
        
    }//GEN-LAST:event_cmbIdUserBCItemStateChanged

    private void btnInsertBCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertBCMouseClicked
        PreparedStatement statement = null;
        try {
            
            String id = cmbIdUserBC.getSelectedItem().toString();
            long idUser = Long.parseLong(id);
            
            String updateQuery = "INSERT INTO tb_broadcast VALUES (?)";
            statement = connection.getConnection().prepareStatement(updateQuery);
            statement.setLong(1, idUser);
            statement.executeUpdate();
            
            readBroadcastInfo();
            JOptionPane.showMessageDialog(null, "Berhasil menambah data");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "User telah ditambahkan sebelumnya");
        }
    }//GEN-LAST:event_btnInsertBCMouseClicked

    private void tabHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabHistoryMouseClicked
        new FormHistory().setVisible(true);
    }//GEN-LAST:event_tabHistoryMouseClicked

    private void tabUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabUserMouseClicked
        new FormUser().setVisible(true);
    }//GEN-LAST:event_tabUserMouseClicked

    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddKey;
    private javax.swing.JButton btnCancelKey;
    private javax.swing.JButton btnDeleteBC;
    private javax.swing.JButton btnDeleteKey;
    private javax.swing.JButton btnEditKey;
    private javax.swing.JButton btnInsertBC;
    private javax.swing.JButton btnSaveKey;
    private javax.swing.JButton btnSendBroadcast;
    private javax.swing.JComboBox<String> cmbIdUserBC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JMenu tabHistory;
    private javax.swing.JMenu tabUser;
    private javax.swing.JTable tblBroadcast;
    private javax.swing.JTable tblKeyword;
    private javax.swing.JTextArea txtBroadcast;
    private javax.swing.JTextField txtCommand;
    private javax.swing.JTextField txtFirstNameBC;
    private javax.swing.JTextField txtIdKey;
    private javax.swing.JTextField txtLastNameBC;
    private javax.swing.JTextArea txtResponse;
    private javax.swing.JTextField txtUsernameBC;
    // End of variables declaration//GEN-END:variables
}
