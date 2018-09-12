package Model.Frame;

import Controller.Controller;
import Game.Game;

public interface GameViewer {
	void refreshGame();
	void refreshInfo();
	void boundGame(Controller controller);
}
