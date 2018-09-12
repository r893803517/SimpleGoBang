package Game;

public class PlayerWithBlack implements Player {
	
	private static final int TAKINGWHITE = 1;
	private static final int TEAMBLACK = 1;

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
		return TEAMBLACK;
	}

}
