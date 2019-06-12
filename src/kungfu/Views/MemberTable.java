package kungfu.Views;
import java.util.*;

import kungfu.Controller;
import kungfu.Classes.*;
import javax.swing.table.AbstractTableModel;

import org.hibernate.Session;

public class MemberTable extends AbstractTableModel{

	private List<Member> members = new ArrayList<>();
	public MemberTable() {
		this.updateData();
	}
	@SuppressWarnings("unchecked")
	public void updateData() {
		Session s = Controller.getSession();
		this.members = s.createQuery( "from Member" ).list();
		s.close();
		this.fireTableDataChanged();
	}
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return members.size();
	}
	@Override 
	public String getColumnName(int colNr) {
		if(colNr == 0) return "Imię i nazwisko";
		if(colNr ==1) return "Opiekun prawny";
		if(colNr ==2 ) return "Numer grupy";
		return "";
	}

	@Override
	public Object getValueAt(int rowNr, int colNr) {
		Member member = members.get(rowNr);
		if(colNr == 0) return member.getPerson().getFullName();
		if(colNr == 1) return member.checkIfMinor() ? "Przypisano opiekuna" : "Brak";
		if(colNr == 2) {
			if(member.getMemberTeams().size() == 0) {
				return "Nie ustawiono";
			}else {
				String groups = "";
				for(MemberTeam mt: member.getMemberTeams()) {
					groups += mt.getTeam().getTeamNr().toString()+"\n";
				}
				return groups;
			}
		}
		
		return null;
	}
	public List<Member> getMembers(){
		return this.members;
	}
}
