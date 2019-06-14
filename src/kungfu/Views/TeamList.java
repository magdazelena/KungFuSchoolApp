package kungfu.Views;

import java.util.ArrayList;
import java.util.List;

import kungfu.Controller;
import kungfu.Classes.Team;
import javax.swing.AbstractListModel;

import org.hibernate.Session;

public class TeamList extends AbstractListModel{

	private List<Team> teams = new ArrayList<>();
	
	public TeamList() {
		update("");
	}
	public void update(String params) {
		Session s = Controller.getSession();
		if(params.length() > 0)
			this.teams = s.createQuery( "from Team where teamNr like "+params ).list();
		else
			this.teams = s.createQuery("from Team").list();
		s.close();
	}
	@Override
	public Object getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return teams.get(arg0);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return teams.size();
	}
	
	public List<Team> getTeams(){
		return this.teams;
	}

}
