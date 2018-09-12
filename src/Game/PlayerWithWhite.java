package Game;

public class PlayerWithWhite implements Player{
	
	public static final int  TAKINGWHITE = 0;
	public static final int  TEAMWHITE =0;
	
	@Override
	public String getName() {
		return null;
	}

	@Override
	public int getTaking() {
		return TAKINGWHITE;
	}

	@Override
	public int getTeam() {
		return TEAMWHITE;
	}
	

}
