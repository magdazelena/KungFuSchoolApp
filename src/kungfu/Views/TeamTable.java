package kungfu.Views;
import java.util.*;

import kungfu.Controller;
import kungfu.Classes.*;
import javax.swing.table.AbstractTableModel;

import org.hibernate.Session;

public class TeamTable extends AbstractTableModel{

	private List<Team> teams = new ArrayList<>();
	public TeamTable() {
		this.updateData();
	}
	@SuppressWarnings("unchecked")
	public void updateData() {
		Session s = Controller.getSession();
		this.teams = s.createQuery( "from Team" ).list();
		s.close();
		this.fireTableDataChanged();
	}
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return teams.size();
	}
	@Override 
	public String getColumnName(int colNr) {
		if(colNr == 0) return "Numer grupy";
		if(colNr ==1) return "Dni zajęć";
		if(colNr ==2 ) return "Godziny zajęć";
		return "";
	}

	@Override
	public Object getValueAt(int rowNr, int colNr) {
		Team team = teams.get(rowNr);
		if(colNr == 0) return team.getTeamNr().toString();
		if(colNr == 1) {
			if(team.getDays().size() == 0) {
				return "Nie ustawiono";
			}else {
				String days = "";
				for(String day : team.getDays()) {
					days += day+" ";
				}
				return days;
			}
		}
		if(colNr == 2) {
			if(team.getHours().size() == 0) {
				return "Nie ustawiono";
			}else {
				String hours = "";
				for(String hour : team.getHours()) {
					hours += hour+" ";
				}
				return hours;
			}
		}
		return null;
	}
	public List<Team> getTeams(){
		return this.teams;
	}
}
