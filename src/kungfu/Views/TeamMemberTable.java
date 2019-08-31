package kungfu.Views;
import java.util.*;

import kungfu.Controller;
import kungfu.Classes.*;
import javax.swing.table.AbstractTableModel;

import org.hibernate.Session;

public class TeamMemberTable extends AbstractTableModel{

	private List<MemberTeam> memteams = new ArrayList<>();
	public TeamMemberTable(Member m) {
		this.memteams = m.getMemberTeams();
	}
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return memteams.size();
	}
	@Override 
	public String getColumnName(int colNr) {
		if(colNr == 0) return "Numer grupy";
		if(colNr ==1) return "Mistrz";
		if(colNr ==2 ) return "Data zapisania";
		if(colNr ==3 ) return "Data wypisania";
		return "";
	}

	@Override
	public Object getValueAt(int rowNr, int colNr) {
		MemberTeam team = memteams.get(rowNr);
		if(colNr == 0) return team.getTeam().getTeamNr().toString();
		if(colNr == 1) return team.getTeam().getMaster().getMember().getPerson().getFullName();
		if(colNr == 2) return team.getJoinDate().toString();
		if(colNr == 3) {
			if(team.getLeaveDate() != null) return team.getLeaveDate().toString();
			else return "Wciąż w grupie";
		}
		return null;
	}
	public List<MemberTeam> getTeams(){
		return this.memteams;
	}
}
