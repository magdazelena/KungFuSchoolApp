package kungfu.Classes;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="Team")
public class Team {

	private long id;
	private Integer TeamNr;
	private List<MemberTeam> memberTeams = new ArrayList<>();
	private Master master;
	private List<String> days = new ArrayList<>();
	private List<String> hours = new ArrayList<>();
	private Location location = null;
	public Team() {}
	public Team(Integer nr, Master master) {
		this.TeamNr = nr;
		setMaster(master);
	}
	 @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }
	   @Basic 
	   @Column(unique=true)
	   public Integer getTeamNr() {
		   return this.TeamNr;
	   }
	   public void setTeamNr(Integer nr) {
		   this.TeamNr = nr;
	   }
	   @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
	   private List<MemberTeam> getMemberTeams(){
		   return this.memberTeams;
	   }
	   
	   public void addMemberTeam(MemberTeam pg) {
		   getMemberTeams().add(pg);
		   pg.setTeam(this);
	   }
	   public void removeMemberTeam(MemberTeam pg) {
		   getMemberTeams().remove(pg);
		   pg.setTeam(null);
	   }
	   public void setMemberTeams(List<MemberTeam> pg) {
		   this.memberTeams = pg;
	   }
	   @Override 
	   public String toString() {
		   return String.format("Grupy: %s", getMemberTeams());
	   }
	   
	@ManyToOne
	public Master getMaster() {
		return master;
	}
	public void setMaster(Master master) {
		if(this.master == null) {
			this.master = master;
			master.addTeam(this);
		}
		
	}
	@ElementCollection
	public List<String> getDays() {
		return days;
	}
	public void setDays(List<String> days) {
		this.days = days;
	}
	@ElementCollection
	public List<String> getHours() {
		return hours;
	}
	public void setHours(List<String> hours) {
		this.hours = hours;
	}
	@ManyToOne
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		if(this.location != location) {
			if(this.location != null)
				this.location.removeTeam(this);
			this.location = location;
			location.addTeam(this);
		}
	}
	   
}
