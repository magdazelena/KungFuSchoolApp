package kungfu.Classes;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * Team class
 * @version 1.0
 * @author magda
 * @see java.lang.Object
 */
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
/**
 * Constructor
 * @param nr Team number
 * @param master Master leading the team
 * @throws Exception if there is no master
 */
	public Team(Integer nr, Master master) throws Exception {
		if(master == null) throw new Exception("Master may not be null");
		this.TeamNr = nr;
		setMaster(master);
	}
	/**
	 * Gets id
	 * @return id
	 */
	 @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }
	/**
	 * Sets id
	 * @param id
	 */
	    @SuppressWarnings("unused")
		private void setId(long id) {
	        this.id = id;
	    }
	    /**
	     * Gets team number
	     * @return TeamNr
	     */
	   @Basic 
	   @Column(unique=true)
	   public Integer getTeamNr() {
		   return this.TeamNr;
	   }
	   /**
	    * Sets team number
	    * @param nr Integer team number
	    */
	   public void setTeamNr(Integer nr) {
		   this.TeamNr = nr;
	   }
	   /**
	    * Gets association classes connecting team and member
	    * 
	    * @return memberTeams
	    */
	   @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
	   public List<MemberTeam> getMemberTeams(){
		   return this.memberTeams;
	   }
	   /**
	    * Adding single MemberTeam object to list
	    * @param pg MemberTeam to be added
	    */
	   public void addMemberTeam(MemberTeam pg) {
		   getMemberTeams().add(pg);
		   pg.setTeam(this);
	   }
	   /**
	    * Removing single MemberTeam from list
	    * @param pg MemberTeam to be removed
	    */
	   public void removeMemberTeam(MemberTeam pg) {
		   getMemberTeams().remove(pg);
		   pg.setTeam(null);
	   }
	   /**
	    * Sets list of MemberTeams
	    * @param pg MemberTeam list
	    */
	   public void setMemberTeams(List<MemberTeam> pg) {
		   this.memberTeams = pg;
	   }
	   /**
	    * To string method printing nr and leader of group
	    */
	   @Override 
	   public String toString() {
		   return String.format("Grupa nr: %s, prowadzący %s", getTeamNr(), getMaster());
	   }
	   /**
	    * Association of team and master
	    * @return master
	    */
	@ManyToOne
	public Master getMaster() {
		return master;
	}
	/**
	 * Sets Master for the team
	 * @param master Master to be set
	 */
	public void setMaster(Master master) {
		if(this.master == null) {
			this.master = master;
			master.addTeam(this);
		}
		
	}
	/**
	 * Gets days of team
	 * @return days
	 */
	@ElementCollection
	public List<String> getDays() {
		return days;
	}
	/**
	 * Sets days of team
	 * @param days Days List String
	 */
	public void setDays(List<String> days) {
		this.days = days;
	}
	/**
	 * Gets hours of team
	 * 
	 * @return hours
	 */
	@ElementCollection
	public List<String> getHours() {
		return hours;
	}
	/**
	 * Sets hours of team
	 * @param hours Hours List String
	 */
	public void setHours(List<String> hours) {
		this.hours = hours;
	}
	/**
	 * Gets Location
	 * @return location
	 */
	@ManyToOne
	public Location getLocation() {
		return location;
	}
	/**
	 * Sets Location
	 * @param location Location to be set
	 */
	public void setLocation(Location location) {
		if(this.location != location) {
			if(this.location != null)
				this.location.removeTeam(this);
			this.location = location;
			location.addTeam(this);
		}
	}
	/**
	 * Modify month fee of all team members
	 * @param change Integer on modified fee
	 */
	public void modifyMonthFee(Integer change) {
		for(MemberTeam mt : memberTeams) {
			mt.getMember().setMonthFee(change);
		}
	}
	   
}
