package utils;
import java.io.Serializable;

public enum Role implements Serializable {

	QB ( "quarterback" 		, "offense"),
	C  ( "center" 			, "offense"),
	RB ( "running back" 	, "offense"),
	FB ( "fullback" 		, "offense"),
	WR ( "wide receiver"	, "offense"),
	TE ( "tight end" 		, "offense"),
	LG ( "left guard" 		, "offense"),
	RG ( "right guard"		, "offense"),
	LT ( "left tackle"		, "offense"),
	RT ( "right tackle"		, "offense"),
	
	DT ( "defensive tackle" , "defense"),
	DE ( "defensive end"	, "defense"),
	LB ( "line backer"		, "defense"),
	S  ( "safety"			, "defense"),
	CB ( "corner back"		, "defense"),
	GK ( "goal keeper"		, "defense"),
	UN ( "Unknown"			, "Unknown");
	
    public static Role getRoleByAcronym(String acronym) {
        switch (acronym) {
            case "GK": return GK;
            case "RB": return RB;
            case "CB": return CB;
            case "DT": return DT;
            case "WR": return WR;
            case "UN": return UN;
            case "LT": return LT;
            case "DE": return DE;
            case "LB": return LB;
            case "RT": return RT;
            
            default: return null;
        }
    }
	
	private final String fullName ; 
	private final String status ; 
	
	Role(String fullName,String status){
		this.fullName = fullName;
		this.status	  = status;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getStatus() {
		return status;
	}
	
//	public static Role getRoleByAcronym(String acronym){
//		for (Role r : values())
//			if (r.toString().equals((acronym)))
//				return r;
//		return UN;
//	}
	
	public String printRoll(){
		return String.format("Role:%s\t(%s,%s)",this,getFullName(),getStatus() );
	}
}
