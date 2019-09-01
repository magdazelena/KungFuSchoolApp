/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kungfu.Views;

import java.awt.event.ActionEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.hibernate.Session;

import kungfu.Controller;
import kungfu.Classes.Master;
import kungfu.Classes.Member;

import kungfu.Classes.Person;
import kungfu.Classes.Student;

/**
 * @version 1.0
 * @author magda
 */
public class AddMember extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -549138041904979525L;
	/**
     * Creates new form AddMember
     */
    public AddMember() {
        initComponents();
    }

    /**
     * initializes components
     */
    private void initComponents() {

        wpiszImieField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        wpiszNazwiskoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        wpiszPoprzedniKlubField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        wpiszDataUrField = new javax.swing.JTextField();
        wpiszSkladkaField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dodajCzlonkaButton = new javax.swing.JButton();
        anulujButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        studentRadio = new javax.swing.JRadioButton();
        mistrzRadio = new javax.swing.JRadioButton();
        
        jLabel7 = new javax.swing.JLabel();
        podajTelefon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        wpiszImieField.setToolTipText("Wpisz imię");

        jLabel1.setText("Imię:");

        jLabel2.setText("Nazwisko");

        wpiszNazwiskoField.setToolTipText("Wpisz nazwisko");

        jLabel3.setText("Poprzedni klub (opcjonalnie)");

        wpiszPoprzedniKlubField.setToolTipText("Poprzedni klub");

        jLabel4.setText("Data urodzenia (dd/mm/rrrr)");

        wpiszDataUrField.setToolTipText("dd/mm/rrrr");

        wpiszSkladkaField.setToolTipText("Wpisz wartość");

        jLabel5.setText("Składka miesięczna");

        dodajCzlonkaButton.setText("Dodaj nowego członka");
        dodajCzlonkaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajCzlonkaActionPerformed(evt);
            }
        });
        anulujButton.setText("Anuluj");
        anulujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anulujButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Dodaj nowego członka");

        studentRadio.setSelected(true);
        studentRadio.setText("Student");

        mistrzRadio.setText("Mistrz");

        jLabel7.setText("Stopień (opcjonalnie)");
        
        if(studentRadio.isSelected()) {
        	SpinnerModel sm = new SpinnerNumberModel(0, 0, 12, 1);
        	stopien = new javax.swing.JSpinner(sm);
        }else {
        	SpinnerModel sm = new SpinnerNumberModel(13, 13, 50, 1);
        	stopien = new javax.swing.JSpinner(sm);
        }
        studentRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mistrzRadio.setSelected(false);
                SpinnerModel sm = new SpinnerNumberModel(0, 0, 12, 1);
            	stopien.setModel(sm);
            }
        });
        mistrzRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	studentRadio.setSelected(false);
            	SpinnerModel sm = new SpinnerNumberModel(13, 13, 50, 1);
            	stopien.setModel(sm);
            }
        });
        podajTelefon.setToolTipText("podaj numer telefonu");

        jLabel8.setText("Numer telefonu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(wpiszImieField, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(wpiszNazwiskoField, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(wpiszDataUrField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(wpiszPoprzedniKlubField, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                .addComponent(podajTelefon))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dodajCzlonkaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(anulujButton, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(studentRadio)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(wpiszSkladkaField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(stopien, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(mistrzRadio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel7))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wpiszImieField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(studentRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mistrzRadio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wpiszNazwiskoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stopien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wpiszSkladkaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(dodajCzlonkaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wpiszDataUrField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wpiszPoprzedniKlubField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(podajTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anulujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
               )
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   /**
    * Disposes of unused window
    * @param evt ActionEvent
    */
    private void anulujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulujButtonActionPerformed
       this.dispose();
    }//GEN-LAST:event_anulujButtonActionPerformed
    /**
     * Adding member logic 
     * @param evt ActionEvent
     */
    private void dodajCzlonkaActionPerformed(ActionEvent evt) {
		try {
			
        	Person p = new Person(wpiszImieField.getText(), wpiszNazwiskoField.getText(), podajTelefon.getText());
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        	LocalDate date = LocalDate.parse(wpiszDataUrField.getText(), formatter);
        	Member m = Member.createMember(p, date, Integer.parseInt(wpiszSkladkaField.getText()));
        	if(wpiszPoprzedniKlubField.getText().length() >0) {
        		m.setFormerClub(wpiszPoprzedniKlubField.getText());
        	}
        	Session s = Controller.getSession();
        	s.beginTransaction();
        	
    		if(studentRadio.isSelected()) {
    			if(m.checkIfMinor()) {
        			AddCaretaker addCaretaker = new AddCaretaker();
        			addCaretaker.setVisible(true);
        			addCaretaker.setTitle("Zarejestuj opiekuna");
        			addCaretaker.setMember(m);
        			addCaretaker.setPersonMember(p);
        			if(stopien.getValue() != null) addCaretaker.setGrade((Integer)stopien.getValue());
        			addCaretaker.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        			addCaretaker.addHierarchyListener(new HierarchyListener() {
        				@Override
        				public void hierarchyChanged(HierarchyEvent arg0) {
        					dispose();
        				}
                	  });
    			}else {
    				Student st = Student.createStudent(m);
    				if(stopien.getValue() != null) st.setGrade((Integer)stopien.getValue());
    				s.save(st);
    				s.save(p);
    	        	s.save(m);
    	        	s.getTransaction().commit();
    	        	s.close();
    	        	dispose();
    			}    			
        	}else {
        		//we in master
        		if(m.checkIfMinor()) {
        			JOptionPane.showMessageDialog(null, "Mistrz nie może być niepełnoletni");
        		}else {
        			Master mst = Master.createMaster(m);
        			if(stopien.getValue() != null) mst.setGrade((Integer)stopien.getValue());
        			s.save(mst);
        			s.save(p);
                	s.save(m);
                	s.getTransaction().commit();
                	s.close();
                	dispose();
        		}
        	}
        }catch(Exception e) {
        	System.out.println(e);
        	JOptionPane.showMessageDialog(getRootPane(), "Sprawdz czy dane wprowadzono poprawnie");
        }
		
	}
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
            java.util.logging.Logger.getLogger(AddMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddMember().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dodajCzlonkaButton;
    private javax.swing.JButton anulujButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton studentRadio;
    private javax.swing.JRadioButton mistrzRadio;
    private javax.swing.JSpinner stopien;
    private javax.swing.JTextField wpiszImieField;
    private javax.swing.JTextField wpiszNazwiskoField;
    private javax.swing.JTextField wpiszPoprzedniKlubField;
    private javax.swing.JTextField wpiszDataUrField;
    private javax.swing.JTextField wpiszSkladkaField;
    private javax.swing.JTextField podajTelefon;
    // End of variables declaration//GEN-END:variables
}
