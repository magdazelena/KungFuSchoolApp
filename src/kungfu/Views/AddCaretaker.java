/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kungfu.Views;

import javax.swing.JOptionPane;
import org.hibernate.Session;


import kungfu.Controller;
import kungfu.Classes.Caretaker;
import kungfu.Classes.Member;
import kungfu.Classes.Person;
import kungfu.Classes.Student;

/**
 * @version 1.0
 * @author magda
 */
public class AddCaretaker extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7952599666193672321L;
	/**
     * Creates new form AddCaretaker
     */
	private Member member;
    public AddCaretaker() {
        initComponents();
    }
    private CaretakerList ctrList = new CaretakerList();
	/**
	 * Intializes components
	 */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wpiszNazwiskoField = new javax.swing.JTextField();
        dodajOpiekunaButton = new javax.swing.JButton();
        anulujButton = new javax.swing.JButton();
        wpiszImieField = new javax.swing.JTextField();
        wpiszTelefonField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        matkaRadio = new javax.swing.JRadioButton();
        ojciecRadio = new javax.swing.JRadioButton();
        innyRadio = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaOpiekun = new javax.swing.JList<>();
        wybierzListaRadio = new javax.swing.JRadioButton();
        utworzRadio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        wpiszNazwiskoField.setToolTipText("Wpisz nazwisko");

        dodajOpiekunaButton.setText("Dodaj nowego opiekuna członka");
        dodajOpiekunaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajOpiekunaButtonActionPerformed(evt);
            }
        });

        anulujButton.setText("Anuluj");
        anulujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anulujButtonActionPerformed(evt);
            }
        });

        wpiszImieField.setToolTipText("Wpisz imię");


        jLabel1.setText("Imię:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Dodaj nowego opiekuna dla członka");

        jLabel2.setText("Nazwisko");
        jLabel3.setText("Telefon");
        wpiszTelefonField.setToolTipText("Podaj numer telefonu");
        jLabel7.setText("Rola");

        matkaRadio.setSelected(true);
        matkaRadio.setText("Matka");

        ojciecRadio.setText("Ojciec");

        innyRadio.setText("Inny");

        innyRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ojciecRadio.setSelected(false);
                matkaRadio.setSelected(false);
            }
        });
        ojciecRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	innyRadio.setSelected(false);
                matkaRadio.setSelected(false);
            }
        });
        matkaRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ojciecRadio.setSelected(false);
               innyRadio.setSelected(false);
            }
        });
        
        
        listaOpiekun.setModel(ctrList);
        listaOpiekun.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaOpiekun.setEnabled(false);
        jScrollPane1.setViewportView(listaOpiekun);

        wybierzListaRadio.setText("Wybierz opiekuna z listy");

        utworzRadio.setSelected(true);
        utworzRadio.setText("Dodaj nowego opiekuna");
        
        utworzRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wybierzListaRadio.setSelected(false);
                listaOpiekun.setEnabled(false);
                wpiszImieField.setEnabled(true);
                wpiszNazwiskoField.setEnabled(true);
                ojciecRadio.setEnabled(true);
                matkaRadio.setEnabled(true);
                innyRadio.setEnabled(true);
                wpiszTelefonField.setEnabled(true);
            }
        });
        wybierzListaRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utworzRadio.setSelected(false);
                listaOpiekun.setEnabled(true);
                wpiszImieField.setEnabled(false);
                wpiszNazwiskoField.setEnabled(false);
                ojciecRadio.setEnabled(false);
                matkaRadio.setEnabled(false);
                innyRadio.setEnabled(false);
                wpiszTelefonField.setEnabled(false);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wpiszImieField, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wpiszNazwiskoField, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wpiszTelefonField, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(matkaRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ojciecRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(innyRadio))
                            .addComponent(wybierzListaRadio)
                            .addComponent(utworzRadio))
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dodajOpiekunaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(anulujButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dodajOpiekunaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anulujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(wybierzListaRadio)
                        .addGap(18, 18, 18)
                        .addComponent(utworzRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wpiszImieField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wpiszNazwiskoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wpiszTelefonField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(matkaRadio)
                            .addComponent(ojciecRadio)
                            .addComponent(innyRadio))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Disposing of window on cancel button
     * @param evt Java ActionEvent
     */
    private void anulujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulujButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_anulujButtonActionPerformed

    /**
     * Adding Caretaker based on inserted data
     * @param evt ActionEvent
     */
    private void dodajOpiekunaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajOpiekunaButtonActionPerformed
        try {
        	if(utworzRadio.isSelected()) {
        		Caretaker.Type t = null;
        		if(matkaRadio.isSelected()) t = Caretaker.Type.Mother;
        		if(ojciecRadio.isSelected()) t = Caretaker.Type.Father;
        		if(innyRadio.isSelected()) t = Caretaker.Type.Other;
        		Person p = new Person(wpiszImieField.getText(), wpiszNazwiskoField.getText(), wpiszTelefonField.getText());
        		Caretaker cr = Caretaker.createCaretaker(p, t);
        		Student s = Student.createStudent(member, cr);
        		s.setGrade(this.grade);
        		Session session = Controller.getSession();
        		session.beginTransaction();
        		session.save(member);
        		session.save(personMember);
        		session.save(p);
        		session.save(cr);
        		session.save(s);
        		session.getTransaction().commit();
        		session.close();
        		this.dispose();
        	}else {
        		if(listaOpiekun.isSelectionEmpty()) {
        			JOptionPane.showMessageDialog(null, "Wybierz opiekuna z listy lub utwórz nowego");
        			return;
        		}
        		int index = listaOpiekun.getSelectedIndex();
        		Caretaker cr = ctrList.getElementAt(index);
        		Student s = Student.createStudent(member, cr);
        		s.setGrade(this.grade);
        		Session session = Controller.getSession();
        		session.beginTransaction();
        		session.save(member);
        		session.save(personMember);
        		session.update(cr);
        		session.save(s);
        		session.getTransaction().commit();
        		session.close();
        		this.dispose();
        	}
        }catch(Exception e) {
        	JOptionPane.showMessageDialog(null, "Sprawdz poprawność wprowadzonych danych");
        }
    }//GEN-LAST:event_dodajOpiekunaButtonActionPerformed
    private Person personMember;
    /**
     * Sets person for the component
     * @param person Person set for component
     */
    public void setPersonMember(Person person) {
    	this.personMember = person;
    }
    /**
     * Sets member for the component
     * @param member Member set for component
     */
    public void setMember(Member member) {
    	this.member = member;
    }
    /**
     * Sets grade of future student
     * @param value Integer
     */
  	public void setGrade(Integer value) {
  		this.grade = value;
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
            java.util.logging.Logger.getLogger(AddCaretaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCaretaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCaretaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCaretaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCaretaker().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Integer grade;
    private javax.swing.JButton dodajOpiekunaButton;
    private javax.swing.JButton anulujButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> listaOpiekun;
    private javax.swing.JRadioButton matkaRadio;
    private javax.swing.JRadioButton ojciecRadio;
    private javax.swing.JRadioButton innyRadio;
    private javax.swing.JRadioButton wybierzListaRadio;
    private javax.swing.JRadioButton utworzRadio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField wpiszImieField;
    private javax.swing.JTextField wpiszNazwiskoField;
    private javax.swing.JTextField wpiszTelefonField;
  
}
