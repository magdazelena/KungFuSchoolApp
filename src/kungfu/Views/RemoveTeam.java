/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kungfu.Views;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import kungfu.Controller;
import kungfu.Classes.Member;
import kungfu.Classes.MemberTeam;

/**
 *
 * @author magda
 */
public class RemoveTeam extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7873480686008465884L;


	/**
     * Creates new form RemoveTeam
     * @param m Member
     */
    public RemoveTeam(Member m) {
    	member = m;
        initComponents();
    }

    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(teamNames(getMemberTeams(member))));

        jLabel1.setText("Wybierz grupę z której chcesz wypisać członka");

        jButton1.setText("Wypisz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
       
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jComboBox1.getAccessibleContext().setAccessibleName("Lista grup czlonka");

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
            java.util.logging.Logger.getLogger(RemoveTeam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemoveTeam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemoveTeam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoveTeam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RemoveTeam(member).setVisible(true);
            }
        });
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) 	{//GEN-FIRST:event_anulujButtonActionPerformed
    	try {
			MemberTeam team = getMemberTeams(member).get(jComboBox1.getSelectedIndex());
			team.setLeaveDate(Date.valueOf(LocalDate.now()));
			Session s = Controller.getSession();
			s.beginTransaction();
			s.update(team);
			s.update(member);
			s.getTransaction().commit();
			s.close();
			this.dispose();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Wybierz grupę z listy");
		}
     }//GEN-LAST:event_anulujButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private static Member member;
    // End of variables declaration//GEN-END:variables
	
	
	public List<MemberTeam> getMemberTeams(Member m){
		List<MemberTeam> list = new ArrayList<>();
		for(MemberTeam t: m.getMemberTeams()) {
			if(t.getLeaveDate()==null)
				list.add(t);
		}
		return list;
	}
	public String[] teamNames(List<MemberTeam> l){
		String[] s = new String[l.size()];
		for(int i =0; i< l.size();i++) {
			s[i] = l.get(i).getTeam().getTeamNr()+" mistrz: "+l.get(i).getTeam().getMaster().getMember().getPerson().getFullName();
		}
		return s;
	}
}
