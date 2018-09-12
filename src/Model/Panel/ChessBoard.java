package Model.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Game.Chess;
import Game.Game;
import Game.Game.gameStatus;
import Game.Player;

public class ChessBoard extends JPanel implements MouseListener{
	//private int chess[][] = new int[15][15];
	private Game game;
	private Player player;

	
	public Image boardImg;
	final private int ROWS = 15;
	final private int BLOCKS = 14;
	int chessman_width=30;
	
	int imgWidth,imgHeight;
	int FHeight,FWidth;
	int x,y;
	int offset;
	int lineStart ,pageStart,lineEnd,pageEnd;
	int blockWidth,blockHeight;
	
	int span = 4;
	 


	public ChessBoard() {
		
		boardImg = Toolkit.getDefaultToolkit().getImage("res/image/chessboard2.png");
		if(boardImg == null)
			System.err.println("png do not exist");
		
		this.setPreferredSize(new Dimension(577,400));	
		addMouseListener(this);
	}	
	@Override
	protected void paintComponent(Graphics g) {

		//确保棋盘总在面板的中间
		g.drawImage(boardImg, x, y, null);
		
		//图片的宽度和高度
		imgWidth = boardImg.getHeight(this);
		imgHeight = boardImg.getWidth(this);
		//JPanel面板的宽度和高度
		FWidth = getWidth();
		FHeight= getHeight();
		//宽度差和高度差
		x=(FWidth-imgWidth)/2;
		y=(FHeight-imgHeight)/2;
		
		//棋盘线的起始点
		offset = 35;
		lineStart = x+offset;
		pageStart = y+offset;
		lineEnd = FWidth - x - offset;
		pageEnd = FHeight - y - offset;

		//每个盘格的大小
		blockWidth = (lineEnd - lineStart)/(ROWS-1);
		blockHeight = (pageEnd - pageStart)/(ROWS-1);
		
		//画横线
		for(int i = 0 ; i < ROWS; i ++) {
			g.drawLine(lineStart, pageStart+i*blockWidth,lineStart+BLOCKS*blockWidth, pageStart+i*blockWidth);
		}
		
		//画竖线
		for(int i = 0 ; i < ROWS; i ++) {
			g.drawLine(lineStart+i*blockHeight,pageStart,lineStart+ i*blockHeight,pageStart+BLOCKS*blockHeight);
		}
		
		//画实棋子
		float radius_b=40;
		float radius_w=80;
		float[] fractions = new float[]{0f,1f};
		java.awt.Color[] colors_b = new java.awt.Color[]{Color.BLACK,Color.WHITE};
		Color[] colors_w = new Color[]{Color.WHITE,Color.BLACK};
		RadialGradientPaint paint = null;
		
		for(int i=0;i<ROWS;i++)
		{
			for(int j=0;j<ROWS;j++)
			{
				if(game.returnChess()[i][j]!=null&&game.returnChess()[i][j].ifPlaced()==true) {
					int pos_x = lineStart + i*blockWidth;
					int pos_y = pageStart + j*blockHeight;
//					System.out.println(pos_x);
//					System.out.println(pos_y);
					
					switch(game.returnChess()[i][j].getColor()) {
					case 0:
						paint = new RadialGradientPaint(pos_x-chessman_width/2f, pos_y-chessman_width/2f, radius_w*2, fractions, colors_w);
						break;
					case 1:
						paint = new RadialGradientPaint(pos_x-chessman_width/2f, pos_y-chessman_width/2f, radius_b*2, fractions, colors_b);
						break;
					}
					((Graphics2D)g).setPaint(paint);

					((Graphics2D)g).fillOval(pos_x-chessman_width/2,pos_y-chessman_width/2,chessman_width,chessman_width);
				
				}
			}
		}
		
		//画没有落的棋子
		if(game.detec_x != -1 && game.detec_y != -1) {
			int pos_x = lineStart +game.detec_x*blockWidth;
			int pos_y = pageStart + game.detec_y*blockHeight;
			switch(game.getTurns()) {
			case 0:
				paint = new RadialGradientPaint(pos_x-chessman_width/2f, pos_y-chessman_width/2f, radius_w*2, fractions, colors_w);
				break;
			case 1:
				paint = new RadialGradientPaint(pos_x-chessman_width/2f, pos_y-chessman_width/2f, radius_b*2, fractions, colors_b);
				break;
			}

//			}
			((Graphics2D)g).setPaint(paint);

			((Graphics2D)g).fillOval(pos_x-chessman_width/2,pos_y-chessman_width/2,chessman_width,chessman_width);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(game.getState() != gameStatus.MATCHING || game.getTurns() != player.getTaking()) {
			return;
		}
		
		
		int point_x=e.getX();
		int point_y=e.getY();
		
		if(point_x>lineEnd|| point_x<lineStart|| point_y > pageEnd|| point_y < pageStart) {
			System.out.println("out");
		}else {
			int x,y;
			if( (point_x - lineStart)%blockWidth < blockWidth/span) {
				x = (point_x - lineStart)/blockWidth;
			}
			else if ( (point_x - lineStart)%blockWidth > blockWidth*(1-span)/span) {
				x = (point_x - lineStart)/blockWidth+1;
			}else {
				return;
			}
			if( (point_y - pageStart)%blockHeight < blockHeight/span) {
				y = (point_y - pageStart)/blockWidth;
			}
			else if ( (point_y - pageStart)%blockHeight > blockHeight*(1-span)/span) {
				y =  (point_y - pageStart)/blockHeight+1;
			}else {
				return;
			}
			if(game.returnChess()[x][y].ifPlaced() == true) {
				return;
			}
			
			game.detec_x = x;
			game.detec_y = y;

			repaint();
			
		}
			
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	public void setGame(Game game) {
		this.game = game;
		
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	
}
