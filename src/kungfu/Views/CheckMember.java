/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kungfu.Views;

import java.time.LocalDate;
import java.time.Period;

import javax.swing.table.DefaultTableModel;

import kungfu.Classes.EquipmentDecorative;
import kungfu.Classes.Master;
import kungfu.Classes.Member;
import kungfu.Classes.Team;

import java.awt.Color;
/**
 * @version 1.0
 * @author magda
 */
public class CheckMember extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8775076664550737002L;
	/**
     * Creates new form CheckMember
     * @param m Member
     */
    public CheckMember(Member m) {
    	this.setMember(m);
        initComponents();
    }

    /**
     * initializes components
     */
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        imieNazwisko = new javax.swing.JLabel();
        dataUrodzenia = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        wiek = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        telefon = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        poprzedniKlub = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        dataDolaczenia = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        skladkaRoczna = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        skladkaMiesieczna = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        stopien = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        opiekunPrawny = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        telOpiekunPrawny = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        rola = new javax.swing.JLabel();
        mistrz = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        status = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grupy = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sprzety = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sprzetyDeco = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        grupyProwadzone = new javax.swing.JTable();
        mistrzowie = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Dane członka");

        jLabel1.setText("Imie i nazwisko");

        imieNazwisko.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        imieNazwisko.setText(member.getPerson().getFullName());

        dataUrodzenia.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dataUrodzenia.setText(member.getBirthDate().toString());

        jLabel4.setText("Data urodzenia");

        wiek.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        wiek.setText(new Integer(Period.between(member.getBirthDate().toLocalDate(), LocalDate.now()).getYears()).toString());

        jLabel7.setText("Wiek");

        telefon.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        telefon.setText(member.getPerson().getPhone());

        jLabel9.setText("Telefon kontaktowy");

        jLabel10.setText("Poprzedni klub");

        poprzedniKlub.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        poprzedniKlub.setText(member.getFormerClub() != null?member.getFormerClub():"Brak klubu");

        jLabel12.setText("Data dołączenia");

        dataDolaczenia.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dataDolaczenia.setText(member.getJoinDate().toString());

        jLabel14.setText("Składka roczna");

        skladkaRoczna.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        skladkaRoczna.setText(member.getYearFee().toString());

        jLabel16.setText("Składka miesięczna");

        skladkaMiesieczna.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        skladkaMiesieczna.setText(member.getMonthFee().toString());

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setText("stopień");

        stopien.setFont(new java.awt.Font("Tahoma", 1, 94)); // NOI18N
        stopien.setText(member.getStudent() == null ? member.getMaster().getGrade().toString(): CheckMember.member.getStudent().getGrade().toString());

        if(member.checkIfMinor()) {
        	jLabel18.setText("Opiekun prawny");
            opiekunPrawny.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
            opiekunPrawny.setText(member.checkIfMinor() ? member.getStudent().getCaretaker().getPerson().getFullName(): "Brak");

            jLabel22.setText("Telefon kontaktowy");
            telOpiekunPrawny.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
            telOpiekunPrawny.setText(member.checkIfMinor() ? member.getStudent().getCaretaker().getPerson().getPhone(): "Brak");

            jLabel24.setText("Rola");
            rola.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
            rola.setText(CheckMember.member.checkIfMinor() ? CheckMember.member.getStudent().getCaretaker().getType().toString(): "Brak");
        }
        

        mistrz.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        if(member.getStudent() == null)
        	mistrz.setForeground(Color.orange);
        else
        	mistrz.setForeground(Color.blue);
        mistrz.setText(member.getStudent() == null? "Mistrz":"Uczeń");

        jLabel27.setText("Typ członka");

        if(member.getStatus() == Member.Status.Active)
        	status.setForeground(Color.green);
        else
        	status.setForeground(Color.red);
        status.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        status.setText(member.getStatus().toString());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(wiek, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(telefon)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(37, 37, 37)
                                        .addComponent(imieNazwisko))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(37, 37, 37)
                                        .addComponent(dataUrodzenia))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(skladkaMiesieczna))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dataDolaczenia, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(skladkaRoczna, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(poprzedniKlub))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addGap(151, 151, 151)
                                    .addComponent(rola))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(telOpiekunPrawny))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(opiekunPrawny))
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(217, 217, 217)
                                .addComponent(stopien))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(36, 36, 36)
                                        .addComponent(mistrz))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.DEFAULT_SIZE)))))))
                .addGap(314, 324, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(420, 420, 420)
                    .addComponent(jLabel19)
                    .addContainerGap(428, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(imieNazwisko))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(dataUrodzenia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(wiek))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(status))
                    .addComponent(stopien))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(telefon)
                    .addComponent(jLabel27)
                    .addComponent(mistrz))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(poprzedniKlub)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(dataDolaczenia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(skladkaRoczna))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(skladkaMiesieczna))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(opiekunPrawny))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(telOpiekunPrawny)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(rola))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(jLabel19)
                    .addContainerGap(441, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Informacje", jPanel1);

        TeamMemberTable teamTableModel = new TeamMemberTable(member);
        
        grupy.setColumnSelectionAllowed(true);
        grupy.getTableHeader().setReorderingAllowed(false);
        grupy.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        grupy.getAccessibleContext().setAccessibleName("Lista grup członka");
        grupy.setModel(teamTableModel);
        jScrollPane2.setViewportView(grupy);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2)
                
        );

        jTabbedPane1.addTab("Grupy", jPanel2);
        RentalMemberTable rentalTableModel = new RentalMemberTable(member);
        sprzety.setModel(rentalTableModel);
        jScrollPane1.setViewportView(sprzety);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jTabbedPane1.addTab("Sprzęty sportowe", jPanel3);
        //**
        if(member.getMaster()!= null) {
        	String[][] data = new String[member.getMaster().getDecors().size()][3];
        	int i =0;
        	for(EquipmentDecorative eq: member.getMaster().getDecors()) {
        		data[i][0]=eq.getName();
        		data[i][1]=eq.getSerialNumber().toString();
        		data[i][2]=eq.getSchool().toString();
        		i++;	
        	}
        	String[] columnNames = {"Nazwa sprzętu", "Nr seryjny", "Szkoła"};
        	sprzetyDeco.setModel(new DefaultTableModel(data, columnNames));
        	javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4)
            );
            jScrollPane4.setViewportView(sprzetyDeco);
        	jTabbedPane1.addTab("Sprzęty dekoracyjne", jPanel4);
        }
        /**/
        //**
        if(member.getMaster()!= null) {
        	String[][] data = new String[member.getMaster().getLedTeams().size()][5];
        	int i =0;
        	for(Team t: member.getMaster().getLedTeams()) {
        		data[i][0]=t.getTeamNr().toString();
        		data[i][1]=t.getDays().toString();
        		data[i][2]=t.getHours().toString();
        		data[i][3]=t.getLocation().toString();
        		data[i][4]=t.getMemberTeams().size()+"";
        		i++;	
        	}
        	String[] columnNames = {"Numer grupy", "Dni zajęć", "Godziny zajęć", "Placówka", "Liczba uczniów"};
        	grupyProwadzone.setModel(new DefaultTableModel(data, columnNames));
        	javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane5)
            );
            jScrollPane5.setViewportView(grupyProwadzone);
        	jTabbedPane1.addTab("Prowadzone grupy", jPanel5);
        }
        /**/
      //**
        if(member.getMaster()!= null) {
        	String[][] data = new String[member.getMaster().getFollowers().size()][3];
        	int i =0;
        	for(Master t: member.getMaster().getFollowers()) {
        		data[i][0]=t.getMember().getPerson().getFullName();
        		data[i][1]=t.getGrade().toString();
        		data[i][2]=t.getLedTeams().toString();
        		i++;	
        	}
        	String[] columnNames = {"Imię i nazwisko", "Stopień", "Prowadzone grupy"};
        	mistrzowie.setModel(new DefaultTableModel(data, columnNames));
        	javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane6)
            );
            jScrollPane6.setViewportView(mistrzowie);
        	jTabbedPane1.addTab("Mistrzowie podrzędni", jPanel6);
        }
        /**/

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.DEFAULT_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(CheckMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckMember(member).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel poprzedniKlub;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel dataDolaczenia;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel skladkaRoczna;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel skladkaMiesieczna;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel imieNazwisko;
    private javax.swing.JLabel stopien;
    private javax.swing.JLabel opiekunPrawny;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel telOpiekunPrawny;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel rola;
    private javax.swing.JLabel mistrz;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel status;
    private javax.swing.JLabel dataUrodzenia;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel wiek;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel telefon;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable sprzety;
    private javax.swing.JTable sprzetyDeco;
    private javax.swing.JTable grupy;
    private javax.swing.JTable grupyProwadzone;
    private javax.swing.JTable mistrzowie;
    private static Member member;
    /**
     * Sets member for component
     * @param m Member
     */
	public void setMember(Member m) {
		member = m;
		
	}
}
