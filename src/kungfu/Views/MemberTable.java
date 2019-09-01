package kungfu.Views;
import java.util.*;

import kungfu.Controller;
import kungfu.Classes.*;
import javax.swing.table.AbstractTableModel;

import org.hibernate.Session;

public class MemberTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2586643852700533422L;
	private List<Member> members = new ArrayList<>();
	public MemberTable() {
		this.updateData("");
	}
	@SuppressWarnings("unchecked")
	public void updateData(String params) {
		Session s = Controller.getSession();
		if(params.length() ==0)
			this.members = s.createQuery( "from Member" ).list();
		else
			this.members = s.createQuery("from Member where fk_person in (select id from kungfu.Classes.Person where name like '%"+params+"%' or lastName like '%"+params+"%')").list();
		s.close();
		this.fireTableDataChanged();
	}
	@Override
	public int getColumnCount() {
		return 5;
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
		if(colNr ==3) return "Status";
		if(colNr== 4) return "Stopień";
		return "";
	}

	@Override
	public Object getValueAt(int rowNr, int colNr) {
		Member member = members.get(rowNr);
		if(colNr == 0) return member.getPerson().getFullName();
		if(colNr == 1) return member.checkIfMinor() ? member.getStudent().getCaretaker().getPerson().getFullName() : "Brak";
		if(colNr == 2) {
			if(member.getMemberTeams().size() == 0) {
				return "Nie ustawiono";
			}else {
				String groups = "";
				int i =0;
				for(MemberTeam mt: member.getMemberTeams()) {
					if(mt.getLeaveDate() == null) groups +=  mt.getTeam().getTeamNr().toString()+ (i==member.getMemberTeams().size()-1?"":", ");
					i++;
				}
				return groups;
			}
		}
		if(colNr ==3 ) return member.getStatus();
		if(colNr ==4 ) return member.getStudent() == null? member.getMaster().getGrade(): member.getStudent().getGrade();
		return null;
	}
	public List<Member> getMembers(){
		return this.members;
	}
}
