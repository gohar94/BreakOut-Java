/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board
 *  Should not be used directly (use getWidth()/getHeight() instead).
 *  * */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx=1;
	private double vy=2;
	
	int turns=NTURNS;
	
	//screen settings
	int screenres_x=1440;
	int screenres_y=750;
	
	// paddle
	int paddlex=((screenres_x-APPLICATION_WIDTH)/2)+(APPLICATION_WIDTH/2);
	int paddley= (((screenres_y-APPLICATION_HEIGHT)/2)+APPLICATION_HEIGHT-PADDLE_Y_OFFSET+PADDLE_HEIGHT);
	GRect paddle = new GRect(paddlex, paddley, PADDLE_WIDTH, PADDLE_HEIGHT);
	
	//--
	int lowerbound= (screenres_x - APPLICATION_WIDTH)/2;
	int upperbound= lowerbound + APPLICATION_WIDTH - PADDLE_WIDTH;
	
	int ballx=720;
	int bally= 400;
	
	int startx= ((screenres_x-APPLICATION_WIDTH)/2)-BRICK_WIDTH-BRICK_SEP;
	int starty= (screenres_y-APPLICATION_HEIGHT)/2;
	int startywall = starty+BRICK_Y_OFFSET;
	int startxo= ((screenres_x-APPLICATION_WIDTH)/2)-BRICK_WIDTH-BRICK_SEP;
	int mousex=0;
	int bricks_remaining = NBRICKS_PER_ROW*NBRICK_ROWS;
	
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	
	public void mouseMoved(MouseEvent e){
		if(e.getX()<860 && e.getX()>520){
			mousex=e.getX();
			
			paddle.setBounds(mousex, paddley, PADDLE_WIDTH, PADDLE_HEIGHT);
			add(paddle);
		}
		if(e.getX()>860){
			paddle.setBounds(860, paddley, PADDLE_WIDTH, PADDLE_HEIGHT);
			add(paddle);
		}
		if(e.getX()<520){
			paddle.setBounds(520, paddley, PADDLE_WIDTH, PADDLE_HEIGHT);
			add(paddle);
		}
	}	
	
	public void board(){
		
		GLine nn1 = new GLine(startx+(BRICK_WIDTH)+(BRICK_SEP)-3,startywall-3,startx+(BRICK_WIDTH)+(BRICK_SEP)+3+APPLICATION_WIDTH,startywall-3);
		add(nn1);
		GLine nn2 = new GLine(startx+(BRICK_WIDTH)+(BRICK_SEP)-3,startywall-3,startx+(BRICK_WIDTH)+(BRICK_SEP)-3,startywall-1+APPLICATION_HEIGHT);
		add(nn2);
		GLine nn3 = new GLine(startx+(BRICK_WIDTH)+(BRICK_SEP)+3+APPLICATION_WIDTH,startywall-2,startx+(BRICK_WIDTH)+(BRICK_SEP)+3+APPLICATION_WIDTH,startywall-3+APPLICATION_HEIGHT);
		add(nn3);
		GLine nn4 = new GLine(startx+(BRICK_WIDTH)+(BRICK_SEP)-3,startywall-1+APPLICATION_HEIGHT,startx+(BRICK_WIDTH)+(BRICK_SEP)+3+APPLICATION_WIDTH,startywall-1+APPLICATION_HEIGHT);
		add(nn4);
		
		
		
		for(int n=0; n<NBRICK_ROWS; n++){
			
			for(int i=0; i<NBRICKS_PER_ROW; i++){
				startx= startx+(BRICK_WIDTH)+(BRICK_SEP);
				GRect brick = new GRect(startx,starty,BRICK_WIDTH,BRICK_HEIGHT);
				
				if(n==0 || n==1){
				brick.setFillColor(Color.RED);
				brick.setColor(Color.RED);
				brick.setFilled(true);
				add(brick);
				}
				
				if(n==2 || n==3){
					brick.setFillColor(Color.ORANGE);
					brick.setColor(Color.ORANGE);
					brick.setFilled(true);
					add(brick);
					}
				
				if(n==4 || n==5){
					brick.setFillColor(Color.YELLOW);
					brick.setColor(Color.YELLOW);
					brick.setFilled(true);
					add(brick);
					}
				
				if(n==6 || n==7){
					brick.setFillColor(Color.GREEN);
					brick.setColor(Color.GREEN);
					brick.setFilled(true);
					add(brick);
					}
				
				if(n==8 || n==9){
					brick.setFillColor(Color.CYAN);
					brick.setColor(Color.CYAN);
					brick.setFilled(true);
					add(brick);
					}
			}
			
			startx=startxo;
			starty=starty+BRICK_HEIGHT+BRICK_SEP;
			
		}
	}
	

	public void collision(){
		if(getElementAt(ballx, bally)!=null){
			GObject collision = getElementAt(ballx, bally);
			if(collision==paddle){
				vy=-vy;
				bounceClip.play();
			}
			else{
				remove(collision);
				bricks_remaining=bricks_remaining-1;
				vy=-vy;
				bounceClip.play();
			}
		}
		
		if(getElementAt(ballx+(2*BALL_RADIUS), bally)!=null){
			GObject collision = getElementAt(ballx+(2*BALL_RADIUS), bally);
			if(collision==paddle){
				vy=-vy;
				bounceClip.play();
			}
			else{
				remove(collision);
				bricks_remaining=bricks_remaining-1;
				vy=-vy;
				bounceClip.play();
			}
		}
		
		if(getElementAt(ballx, bally+(2*BALL_RADIUS))!=null){
			GObject collision = getElementAt(ballx, bally+(2*BALL_RADIUS));
			if(collision==paddle){
				vy=-vy;
				bounceClip.play();
			}
			else{
				remove(collision);
				bricks_remaining=bricks_remaining-1;
				vy=-vy;
				bounceClip.play();
			}
		}
		
		if(getElementAt(ballx+(2*BALL_RADIUS), bally+(2*BALL_RADIUS))!=null){
			GObject collision = getElementAt(ballx+(2*BALL_RADIUS), bally+(2*BALL_RADIUS));
			if(collision==paddle){
				vy=-vy;
				bounceClip.play();
			}
			else{
				remove(collision);
				bricks_remaining=bricks_remaining-1;
				vy=-vy;
				bounceClip.play();
			}
		}
		
		if(getElementAt(ballx+BALL_RADIUS+1, bally+(2*BALL_RADIUS)+1)!=null){
			GObject collision = getElementAt(ballx+BALL_RADIUS+1, bally+(2*BALL_RADIUS)+1);
			if(collision==paddle){
				vy=-vy;
				bounceClip.play();
			}
			else{
				remove(collision);
				bricks_remaining=bricks_remaining-1;
				vy=-vy;
				bounceClip.play();
			}
		}
		
		if(getElementAt(ballx+BALL_RADIUS+1, bally-1)!=null){
			GObject collision = getElementAt(ballx+BALL_RADIUS+1, bally-1);
			if(collision==paddle){
				vy=-vy;
				bounceClip.play();
			}
			else{
				remove(collision);
				bricks_remaining=bricks_remaining-1;
				vy=-vy;
				bounceClip.play();
			}
		}
	}
	
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		print(turns);
		board();
		
		add(paddle);
		paddle.setFillColor(Color.BLACK);
		paddle.setFilled(true);
		
		boolean done=false;
		

		GOval ball = new GOval(ballx, bally, 2*BALL_RADIUS, 2*BALL_RADIUS);
		add(ball);
		ball.setFillColor(Color.BLACK);
		ball.setFilled(true);
		
		addMouseListeners();
		
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		
		while(!done){
			
			collision();
		
			//hitting the walls on right and left
			
			if(ballx+(2*BALL_RADIUS)>=(((screenres_x-APPLICATION_WIDTH)/2)+APPLICATION_WIDTH) || ballx<=((screenres_x-APPLICATION_WIDTH)/2)){
				vx=-vx;
				bounceClip.play();
			}
			
			if(bally+(2*BALL_RADIUS)<100){
				vy=-vy;
				bounceClip.play();
			}
			
			if(bally>700){
				if(turns>0){
				turns=turns-1;
				print(turns);
				bally=400;
				ballx=720;
				}
				else{
					done=true;
				}
			}
			
			if(bricks_remaining<=0){
				done=true;
			}
			
			ballx+=vx;
			bally+=vy;
			ball.setBounds(ballx, bally, 2*BALL_RADIUS, 2*BALL_RADIUS);
			pause(8);
			
		}
		
		/* You fill this in, along with any subsidiary methods */
	}

}
