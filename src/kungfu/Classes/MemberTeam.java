package kungfu.Classes;

import java.time.LocalDate;

import javax.persistence.*;
import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;
/**
 * MemberTeam class
 * @see java.lang.Object
 */
@Entity(name="MemberTeam")
public class MemberTeam {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private Team team;
	private Member member;
	private Date joinDate;
	private Date leaveDate = null;
	public MemberTeam() {}
	/**
	 * MemberTeam
	 * @param Member
	 * @param Team
	 */
	public MemberTeam(Member p, Team g) {
		this.member = p;
		this.team = g;
		this.joinDate = Date.valueOf(LocalDate.now());
		this.member.addMemberTeam(this);
		this.team.addMemberTeam(this);
	}
/**
 * Gets id
 * @return long
 */
	 	@Id
	    @GeneratedValue(generator="increment")
	    @GenericGenerator(name="increment", strategy = "increment")
	    public long getId() {
	        return id;
	    }
/**
 * Sets id
 * @param long
 */
	    private void setId(long id) {
	        this.id = id;
	    }
	    /**
	     * Gets Team
	     * @return Team
	     */
	@ManyToOne
	public Team getTeam() {
		return this.team;
	}
	/**
	 * Sets team
	 * @param Team
	 */
	public void setTeam(Team Team) {
		this.team = Team;
		
		
	}
	/**
	 * Gets Member
	 * @return Member
	 */
	@ManyToOne
	public Member getMember() {
		return this.member;
	}
	/**
	 * Sets member
	 * @param Member
	 */
	public void setMember(Member p) {
		this.member = p;
		
	}
	/**
	 * Gets joinDate
	 * @return LocalDate
	 */
	@Basic 
	public Date getJoinDate() {
		return this.joinDate;
	}
	/**
	 * Sets joinDate
	 * @param LocalDate
	 */
	public void setJoinDate(Date date) {
		this.joinDate = date;
	}
	/**
	 * Overrides to String
	 */
	@Override
	public String toString() {
		return getMember().getPerson().getFullName()+" do grupy "+getTeam().getTeamNr()+" dołączyła: "+getJoinDate();
	}
	/**
	 * Gets leave date
	 * @return LocalDate
	 */
	@Basic
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	
}
