package core;


import java.util.Date;
import java.util.Objects;

public class Found {
    /**
     * the total amount of the funding 
     */
    private final double amount;
    /**
     * the date the team received the funding
     */
    private final Date fDate;
    /**
     * the team that received the funding
     */
    private final Team team;
    /**
     * the sponsor of this team funding
     */
    private final Sponsor sponsor;
    
    /**
     * the funding constructor
     * <p><b>notice:<b> the Funding details can not be changed after
     * creating the Fund<p>
     * @param amount
     * @param fDate
     * @param sponsor
     * @param team
     */
    public Found(double amount, Date fDate, Sponsor sponsor, Team team) {
        this.amount = amount;
        this.fDate = new Date(fDate.getTime()); // Create a copy to ensure immutability
        this.sponsor = sponsor;
        this.team = team;
    }

    public double getAmount() {
        return amount;
    }

    public Date getfDate() {
        return new Date(fDate.getTime()); // Return a copy to ensure immutability
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public Team getTeam() {
        return team;
    }

	@Override
	public int hashCode() {
		return Objects.hash(amount, fDate, sponsor, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Found other = (Found) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(fDate, other.fDate) && Objects.equals(sponsor, other.sponsor)
				&& Objects.equals(team, other.team);
	}
    
}