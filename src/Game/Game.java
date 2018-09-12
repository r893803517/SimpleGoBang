package Game;

public class Game {
	
	final private int ROWS = 15;
	private Chess chess[][]  = new Chess[ROWS][ROWS];
	


	private gameStatus gameState;
	private volatile int turns = 1; //0代表白方 1代表黑方 黑方先下
	public int detec_x = -1,detec_y = -1; //本方预下的棋子
	public int pre_x = -1,pre_y = -1; //上一回合落下的棋子
	
	public enum gameStatus{
		OVER,
		MATCHING,
		WHITEWIN,
		BLACKWIN,
		STOP;
	}
	
	public void clearDetec() {
		detec_x = -1;
		detec_y = -1;
	}
	
	
	public gameStatus getGameState() {
		return gameState;
	}

	

	public boolean place(int x,int y,int color) {
		if(chess[x][y].ifPlaced()) {
			return false;
		}
		chess[x][y].setColor(color);
		chess[x][y].Placed(true);
		return true;
	}
	
	public boolean validDetec() {
		if(detec_x != -1 && detec_y != -1){
			return true;
		}
		
		return false;
	}
	
	
	public boolean admit(int color) {
			if(!validDetec()) {
				return false;
			}
	
			pre_x = detec_x;
			pre_y = detec_y;
			
			clearDetec();
			
			if(chess[pre_x][pre_y].ifPlaced()) {
				return false;
			}
			
			chess[pre_x][pre_y].setColor(color);
			chess[pre_x][pre_y].Placed(true);
			
			return true;
	
	}
	
	public int getTurns() {
		return turns; 
	}
	public void nextTurns() {
		turns = 1-turns;
	}
	public void setState(gameStatus state) {
		gameState = state;
	}
	public gameStatus getState() {
		return gameState;
	}
	
	public Game(){
		initGame();
		gameState = gameStatus.MATCHING;
	}
	
	public Chess[][] returnChess(){
		return chess;
	}
	
	public void initGame() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < ROWS; j++) {
				chess[i][j] = new Chess(-1,false);
			}
		}
		turns = 1;
	}
	
	
		
	
	public int isWin()
	{	
		for(int i=0;i<ROWS;i++)
		{
			for(int j=0;j<ROWS;j++)
			{
				//横向查找
				if(chess[i][j]!=null&&chess[i][j].ifPlaced()==true)
				{
					int matchColor = chess[i][j].getColor();
					//向右侧查找
					for(int n=1;n<=4;n++)
					{
						if((i+n>=0)&&(i+n)<=ROWS)
						{
							if(chess[i+n][j]!=null&&chess[i+n][j].ifPlaced()==true&&chess[i+n][j].getColor()==matchColor)
							{
								chess[i][j].matchCount++;
								//System.out.println("pos:"+i+" "+j+" right count++:"+(i+n)+" "+j+" count:"+chess[i][j].matchCount);
								if(chess[i][j].matchCount==5)
								{
									return matchColor;
								}
							}else
							{
								break;
							}	
						}
					}
					//向左侧查找
					for(int n=1;n<=4;n++)
					{
						if((i-n>=0)&&(i-n)<=ROWS)
						{
							if(chess[i-n][j]!=null&&chess[i-n][j].ifPlaced()==true&&chess[i-n][j].getColor()==matchColor)
							{
								chess[i][j].matchCount++;
								//System.out.println("pos:"+i+" "+j+" "+"left count++:"+(i-n)+" "+j+" count:"+chess[i][j].matchCount);
								if(chess[i][j].matchCount==5)
								{
									return matchColor;
								}
							}else
							{
								if(chess[i-n][j]!=null)
								{
									chess[i][j].matchCount = 1;
								}
								break;
							}
						}
					}
					chess[i][j].matchCount=1;//refresh count
				}
			}
		}

		for(int i=0;i<ROWS;i++)
		{
			for(int j=0;j<ROWS;j++)
			{
				//纵向
				if(chess[i][j]!=null&&chess[i][j].ifPlaced()==true)
				{
					int matchColor = chess[i][j].getColor();
					//向下查找，左上角为坐标原点，y轴正方向向下
					for(int n=1;n<=4;n++)
					{
						if((j+n>=0)&&(j+n)<=ROWS)
						{
							if(chess[i][j+n]!=null&&chess[i][j+n].ifPlaced()==true&&chess[i][j+n].getColor()==matchColor)
							{
								chess[i][j].matchCount++;
								//System.out.println("pos:"+i+" "+j+" up count++:"+(i)+" "+(j+n)+" count:"+chess[i][j].matchCount);
								if(chess[i][j].matchCount==5)
								{
									return matchColor;
								}
							}else
							{
								break;
							}	
						}
					}
					//向上查找
					for(int n=1;n<=4;n++)
					{
						if((j-n>=0)&&(j-n)<=ROWS)
						{
							if(chess[i][j-n]!=null&&chess[i][j-n].ifPlaced()==true&&chess[i][j-n].getColor()==matchColor)
							{
								chess[i][j].matchCount++;
								//System.out.println("pos:"+i+" "+j+" "+"left count++:"+(i)+" "+(j-n)+" count:"+chess[i][j].matchCount);
								if(chess[i][j].matchCount==5)
								{
									return matchColor;
								}
							}else
							{
								if(chess[i][j-n]!=null)
								{
									chess[i][j].matchCount = 1;
								}
								break;
							}
						}
					}
					chess[i][j].matchCount=1;//refresh count
				}
			}
		}

		//方向：左上右下
		for(int i=0;i<ROWS;i++)
		{
			for(int j=0;j<ROWS;j++)
			{
				//左上
				if(chess[i][j]!=null&&chess[i][j].ifPlaced()==true)
				{
					int matchColor = chess[i][j].getColor();
					//向下查找，左上角为坐标原点，y轴正方向向下
					for(int n=1;n<=4;n++)
					{
						if((j-n>=0)&&(j-n)<=ROWS&&(i-n)>=0&&(i-n)<=ROWS)
						{
							if(chess[i-n][j-n]!=null&&chess[i-n][j-n].ifPlaced()==true&&chess[i-n][j-n].getColor()==matchColor)
							{
								chess[i][j].matchCount++;
								//System.out.println("pos:"+i+" "+j+" up count++:"+(i-n)+" "+(j-n)+" count:"+chess[i][j].matchCount);
								if(chess[i][j].matchCount==5)
								{
									return matchColor;
								}
							}else
							{
								break;
							}	
						}
					}
					//右下
					for(int n=1;n<=4;n++)
					{
						if((j+n>=0)&&(j+n)<=ROWS&&(i+n)>=0&&(i+n)<=ROWS)
						{
							if(chess[i+n][j+n]!=null&&chess[i+n][j+n].ifPlaced()==true&&chess[i+n][j+n].getColor()==matchColor)
							{
								chess[i][j].matchCount++;
								//System.out.println("pos:"+i+" "+j+" "+"left count++:"+(i+n)+" "+(j+n)+" count:"+chess[i][j].matchCount);
								if(chess[i][j].matchCount==5)
								{
									return matchColor;
								}
							}else
							{
								if(chess[i+n][j+n]!=null)
								{
									chess[i][j].matchCount = 1;
								}
								break;
							}
						}
					}
					chess[i][j].matchCount=1;//refresh count
				}
			}
		}

		//方向：左下右上
		for(int i=0;i<ROWS;i++)
		{
			for(int j=0;j<ROWS;j++)
			{
				//左下
				if(chess[i][j]!=null&&chess[i][j].ifPlaced()==true)
				{
					int matchColor = chess[i][j].getColor();
					//向下查找，左上角为坐标原点，y轴正方向向下
					for(int n=1;n<=4;n++)
					{
						if((j+n>=0)&&(j+n)<=ROWS&&(i-n)>=0&&(i-n)<=ROWS)
						{
							if(chess[i-n][j+n]!=null&&chess[i-n][j+n].ifPlaced()==true&&chess[i-n][j+n].getColor()==matchColor)
							{
								chess[i][j].matchCount++;
								//System.out.println("pos:"+i+" "+j+" up count++:"+(i-n)+" "+(j+n)+" count:"+chess[i][j].matchCount);
								if(chess[i][j].matchCount==5)
								{
									return matchColor;
								}
							}else
							{
								break;
							}	
						}
					}
					//右上
					for(int n=1;n<=4;n++)
					{
						if((j-n>=0)&&(j-n)<=ROWS&&(i+n)>=0&&(i+n)<=ROWS)
						{
							if(chess[i+n][j-n]!=null&&chess[i+n][j-n].ifPlaced()==true&&chess[i+n][j-n].getColor()==matchColor)
							{
								chess[i][j].matchCount++;
								//System.out.println("pos:"+i+" "+j+" "+"left count++:"+(i+n)+" "+(j-n)+" count:"+chess[i][j].matchCount);
								if(chess[i][j].matchCount==5)
								{
									return matchColor;
								}
							}else
							{
								if(chess[i+n][j-n]!=null)
								{
									chess[i][j].matchCount = 1;
								}
								break;
							}
						}
					}
					chess[i][j].matchCount=1;//refresh count
				}
			}
		}		

		return -1;	
	}
}
