/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kungfu.Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import kungfu.Classes.Member;
import kungfu.Classes.Team;

/**
 * @version 1.0
 * @author magda
 */
public class GeneralView extends javax.swing.JFrame {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = -329515552666129134L;
	/**
     * Creates new form GeneralView
     */
    public GeneralView() {
        initComponents();
    }
    MemberTable memberTableModel = new MemberTable();
    TeamTable teamTableModel = new TeamTable();
    private void initComponents() {

        zakladki = new javax.swing.JTabbedPane();
        szkola = new javax.swing.JPanel();
        zarzadzajCzlonkamiButton = new javax.swing.JButton();
        zarzadzajPracownikamiButton = new javax.swing.JButton();
        zarzadzajGrupamiButton = new javax.swing.JButton();
        zarzadzajPlacowkamiButton = new javax.swing.JButton();
        grupy = new javax.swing.JPanel();
        poleWyszukania = new javax.swing.JTextField();
        szukajGrupyButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        grupyTabela = new javax.swing.JTable();
        dodajGrupeButton = new javax.swing.JButton();
        wybierzGrupeLabel = new javax.swing.JLabel();
        zarzadzajCzlonkamiButton2 = new javax.swing.JButton();
        zarzadzajCzlonkamiButton3 = new javax.swing.JButton();
        czlonkowie = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        CzlonkowieTable = new javax.swing.JTable();
        poleWyszukaniaCzlonka2 = new javax.swing.JTextField();
        szukajCzlonkaButton = new javax.swing.JButton();
        dodajCzlonkaButton = new javax.swing.JButton();
        wypiszCzlonkaButton = new javax.swing.JButton();
        wybierzCzlonkaLabel = new javax.swing.JLabel();
        przeniesCzlonkaButton = new javax.swing.JButton();
        wykluczCzlonkaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        zakladki.setBackground(new java.awt.Color(200, 200, 240));
        zakladki.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        zakladki.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        zakladki.setMaximumSize(new java.awt.Dimension(1200, 800));
        zakladki.setMinimumSize(new java.awt.Dimension(300, 300));

        zarzadzajCzlonkamiButton.setFont(new java.awt.Font("Source Sans Pro", 0, 18)); // NOI18N
        zarzadzajCzlonkamiButton.setText("Członkowie");

        zarzadzajPracownikamiButton.setFont(new java.awt.Font("Source Sans Pro", 0, 18)); // NOI18N
        zarzadzajPracownikamiButton.setText("Pracownicy");

        zarzadzajGrupamiButton.setFont(new java.awt.Font("Source Sans Pro", 0, 18)); // NOI18N
        zarzadzajGrupamiButton.setText("Grupy");

        zarzadzajPlacowkamiButton.setFont(new java.awt.Font("Source Sans Pro", 0, 18)); // NOI18N
        zarzadzajPlacowkamiButton.setText("Placówki");

        javax.swing.GroupLayout szkolaLayout = new javax.swing.GroupLayout(szkola);
        szkola.setLayout(szkolaLayout);
        szkolaLayout.setHorizontalGroup(
            szkolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(szkolaLayout.createSequentialGroup()
                .addGroup(szkolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zarzadzajPracownikamiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(zarzadzajCzlonkamiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(zarzadzajGrupamiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(zarzadzajPlacowkamiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                )
        );
        szkolaLayout.setVerticalGroup(
            szkolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(szkolaLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(zarzadzajPracownikamiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(zarzadzajCzlonkamiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(zarzadzajPlacowkamiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zarzadzajGrupamiButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        zarzadzajPracownikamiButton.getAccessibleContext().setAccessibleDescription("Akcje dotyczące pracowników");

        zakladki.addTab("Szkoła", null, szkola, "Akcje dla szkoły");
        szkola.getAccessibleContext().setAccessibleName("Zakładka z akcjami ogólnymi");
        szkola.getAccessibleContext().setAccessibleDescription("Tu możesz wykonać akcje zwiazane ze szkołą");
        szkola.getAccessibleContext().setAccessibleParent(szkola);

        poleWyszukania.setText("Wpisz hasło by wyszukać");

        szukajGrupyButton.setText("Szukaj");
      //Grupy
        grupyTabela.setModel(teamTableModel);
        grupyTabela.setColumnSelectionAllowed(true);
        grupyTabela.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(grupyTabela);
        grupyTabela.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        grupyTabela.getAccessibleContext().setAccessibleName("Lista moich grup");
        grupyTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grupyTabelaMouseClicked(evt);
            }
        });
        dodajGrupeButton.setBackground(new java.awt.Color(12, 240, 20));
        dodajGrupeButton.setText("Dodaj nową grupę");

        wybierzGrupeLabel.setText("Wybierz grupę z listy po lewej stronie");

        zarzadzajCzlonkamiButton2.setText("Zmień dni zajęć");

        zarzadzajCzlonkamiButton3.setText("Zmień godziny zajęć");

        javax.swing.GroupLayout grupyLayout = new javax.swing.GroupLayout(grupy);
        grupy.setLayout(grupyLayout);
        grupyLayout.setHorizontalGroup(
            grupyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grupyLayout.createSequentialGroup()
                
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addGroup(grupyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(grupyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(grupyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(grupyLayout.createSequentialGroup()
                                .addComponent(poleWyszukania, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(szukajGrupyButton))
                            .addComponent(dodajGrupeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        )
                    .addGroup(grupyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(grupyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wybierzGrupeLabel)
                            .addGroup(grupyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(zarzadzajCzlonkamiButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(zarzadzajCzlonkamiButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        )))
        );
        grupyLayout.setVerticalGroup(
            grupyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grupyLayout.createSequentialGroup()
                .addGroup(grupyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(grupyLayout.createSequentialGroup()
                        .addGroup(grupyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(szukajGrupyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(poleWyszukania, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(wybierzGrupeLabel)
                        .addGap(19, 19, 19)
                        .addComponent(zarzadzajCzlonkamiButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zarzadzajCzlonkamiButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(175, 175, 175)
                        .addComponent(dodajGrupeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE))
               )
        );

        zakladki.addTab("Moje grupy", null, grupy, "Akcje zarządzania grupami");
        grupy.getAccessibleContext().setAccessibleName("Zakładka zarządzania moimi grupami");
        grupy.getAccessibleContext().setAccessibleDescription("Tu możesz wykonać akcje dla grup");

        
        CzlonkowieTable.setModel(memberTableModel);
        CzlonkowieTable.setColumnSelectionAllowed(true);
        CzlonkowieTable.getTableHeader().setReorderingAllowed(false);
        CzlonkowieTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CzlonkowieTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(CzlonkowieTable);
        CzlonkowieTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        CzlonkowieTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                Integer status = (Integer)table.getModel().getValueAt(row,4);
                if (status >= 13) {
                    setBackground(Color.orange);
                    setForeground(Color.DARK_GRAY);
                    setFont(table.getFont().deriveFont(Font.BOLD, 14f));
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }       
                return this;
            }   
        });
        poleWyszukaniaCzlonka2.setToolTipText("Wpisz imie lub nazwisko by wyszukać");
        poleWyszukaniaCzlonka2.setText("Wpisz imie lub nazwisko by wyszukać");
        szukajCzlonkaButton.setText("Szukaj");
        szukajCzlonkaButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		memberTableModel.updateData(poleWyszukaniaCzlonka2.getText());
        		CzlonkowieTable.repaint();
        	}
        });
        dodajCzlonkaButton.setBackground(new java.awt.Color(12, 240, 20));
        dodajCzlonkaButton.setText("Dodaj nowego członka");
        dodajCzlonkaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                	AddMember frame = new AddMember();
                	frame.setTitle("Szkoła kung fu - dodaj członka");
            		frame.setName("Szkoła kung fu - dodaj członka");
            		frame.setVisible(true);
            		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            		frame.addHierarchyListener(new HierarchyListener() {
        				@Override
        				public void hierarchyChanged(HierarchyEvent arg0) {
        					memberTableModel.updateData("");
        					CzlonkowieTable.repaint();
        				}
                	  });
                }catch(Exception e){
                	e.printStackTrace();
                }
            }
        });

        wypiszCzlonkaButton.setText("Wypisz członka z grupy");
        wypiszCzlonkaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wypiszCzlonkaButtonActionPerformed(evt);
            }
        });

        wybierzCzlonkaLabel.setText("Wybierz członka z listy po lewej stronie");

        przeniesCzlonkaButton.setText("Przypisz członka do grupy");
        przeniesCzlonkaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                przeniesCzlonkaButtonActionPerformed(evt);
            }
        });

        wykluczCzlonkaButton.setForeground(new java.awt.Color(255, 0, 0));
        wykluczCzlonkaButton.setText("Wyklucz członka");


        javax.swing.GroupLayout czlonkowieLayout = new javax.swing.GroupLayout(czlonkowie);
        czlonkowie.setLayout(czlonkowieLayout);
        czlonkowieLayout.setHorizontalGroup(
            czlonkowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(czlonkowieLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(czlonkowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(czlonkowieLayout.createSequentialGroup()
                        .addComponent(poleWyszukaniaCzlonka2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(szukajCzlonkaButton))
                    .addComponent(dodajCzlonkaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wybierzCzlonkaLabel)
                    .addGroup(czlonkowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(wypiszCzlonkaButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(przeniesCzlonkaButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wykluczCzlonkaButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                )
        );
        czlonkowieLayout.setVerticalGroup(
            czlonkowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, czlonkowieLayout.createSequentialGroup()
                .addGroup(czlonkowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(czlonkowieLayout.createSequentialGroup()
                        .addGroup(czlonkowieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(szukajCzlonkaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(poleWyszukaniaCzlonka2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addComponent(wybierzCzlonkaLabel)
                        .addGap(19, 19, 19)
                        .addComponent(przeniesCzlonkaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(wypiszCzlonkaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(wykluczCzlonkaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(dodajCzlonkaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE))
            		)
        );

        zakladki.addTab("Członkowie", null, czlonkowie, "Akcje zarządzania uczniami");
        czlonkowie.getAccessibleContext().setAccessibleName("Akcje zarządzania uczniami");
        czlonkowie.getAccessibleContext().setAccessibleDescription("Tutaj możesz zarządzać uczniami");
        czlonkowie.getAccessibleContext().setAccessibleParent(zakladki);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zakladki, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zakladki, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addContainerGap())
        );

        zakladki.getAccessibleContext().setAccessibleName("Członkowie");
        zakladki.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * Display member details on double click 
	 * @param evt MouseEvent
	 */
    private void CzlonkowieTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CzlonkowieTableMouseClicked
        // TODO add your handling code here:
    	if( evt.getClickCount() == 2) {
    		int index = CzlonkowieTable.getSelectedRow();
            Member m = memberTableModel.getMembers().get(index);
            CheckMember check = new CheckMember(m);
            check.setVisible(true);
            check.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          
    	}
    }//GEN-LAST:event_CzlonkowieTableMouseClicked
    /**
     * Widget on double click on group
     * @param evt MouseEvent
     */
    private void grupyTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CzlonkowieTableMouseClicked
        // TODO add your handling code here:
    	if( evt.getClickCount() == 2) {
    		int index = grupyTabela.getSelectedRow();
            Team m = teamTableModel.getTeams().get(index);
            CheckGroup check = new CheckGroup(m);
            check.setVisible(true);
            check.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          
    	}
    }//GEN-LAST:event_CzlonkowieTableMouseClicked
    /**
     * Transfer member to another group actions
     * @param evt ActionEvent
     */
    private void przeniesCzlonkaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_przeniesCzlonkaButtonActionPerformed
    	  int index = CzlonkowieTable.getSelectedRow();
    	  if(CzlonkowieTable.getSelectedRows().length ==0) {
    		  JOptionPane.showMessageDialog(null, "Wybierz członka z listy po lewej");
    		  return;
    	  }
          Member m = memberTableModel.getMembers().get(index);
          ChangeGroup change = new ChangeGroup();
          change.setVisible(true);
          change.setMember(m);
          change.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          change.addHierarchyListener(new HierarchyListener() {
				@Override
				public void hierarchyChanged(HierarchyEvent arg0) {
					memberTableModel.updateData("");
					CzlonkowieTable.repaint();
				}
        	  });
    }//GEN-LAST:event_przeniesCzlonkaButtonActionPerformed
    /** Unassign member 
     * 
     * @param evt ActionEvent
     */
    private void wypiszCzlonkaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wypiszCzlonkaButtonActionPerformed
    	int index = CzlonkowieTable.getSelectedRow();
  	  if(CzlonkowieTable.getSelectedRows().length ==0) {
  		  JOptionPane.showMessageDialog(null, "Wybierz członka z listy po lewej");
  		  return;
  	  }
        Member m = memberTableModel.getMembers().get(index);
        RemoveTeam change = new RemoveTeam(m);
        change.setVisible(true);
        change.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        change.addHierarchyListener(new HierarchyListener() {
				@Override
				public void hierarchyChanged(HierarchyEvent arg0) {
					memberTableModel.updateData("");
					CzlonkowieTable.repaint();
				}
      	  });
    	
    }//GEN-LAST:event_wypiszCzlonkaButtonActionPerformed


    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(GeneralView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeneralView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeneralView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeneralView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeneralView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CzlonkowieTable;
    private javax.swing.JButton zarzadzajCzlonkamiButton;
    private javax.swing.JButton przeniesCzlonkaButton;
    private javax.swing.JButton wykluczCzlonkaButton;
    private javax.swing.JButton zarzadzajCzlonkamiButton2;
    private javax.swing.JButton zarzadzajCzlonkamiButton3;
    private javax.swing.JButton zarzadzajPracownikamiButton;
    private javax.swing.JButton zarzadzajGrupamiButton;
    private javax.swing.JButton zarzadzajPlacowkamiButton;
    private javax.swing.JButton szukajGrupyButton;
    private javax.swing.JButton dodajGrupeButton;
    private javax.swing.JButton szukajCzlonkaButton;
    private javax.swing.JButton dodajCzlonkaButton;
    private javax.swing.JButton wypiszCzlonkaButton;
    private javax.swing.JLabel wybierzCzlonkaLabel;
    private javax.swing.JLabel wybierzGrupeLabel;
    private javax.swing.JPanel szkola;
    private javax.swing.JPanel grupy;
    private javax.swing.JPanel czlonkowie;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane zakladki;
    private javax.swing.JTable grupyTabela;
    private javax.swing.JTextField poleWyszukania;
    private javax.swing.JTextField poleWyszukaniaCzlonka2;
    // End of variables declaration//GEN-END:variables
}
