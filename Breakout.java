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
	private double vx=3;
	private double vy=3;
	
	int screenres_x=1440;
	int screenres_y=750;
	
	int paddlex=((screenres_x-APPLICATION_WIDTH)/2)+(APPLICATION_WIDTH/2);
	int paddley= (((screenres_y-APPLICATION_HEIGHT)/2)+APPLICATION_HEIGHT-PADDLE_HEIGHT-PADDLE_Y_OFFSET);
	GRect paddle = new GRect(paddlex, paddley, PADDLE_WIDTH, PADDLE_HEIGHT);
	
	int lowerbound= (screenres_x - APPLICATION_WIDTH)/2;
	int upperbound= lowerbound + APPLICATION_WIDTH - PADDLE_WIDTH;
	
	int ballx=720;
	int bally= 400;
	
	public void mouseMoved(MouseEvent e){
		if(e.getX()<860 && e.getX()>520){
			paddle.setBounds(e.getX(), paddley, PADDLE_WIDTH, PADDLE_HEIGHT);
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
		//board
		// ---
		
		int startx= ((screenres_x-APPLICATION_WIDTH)/2)-BRICK_WIDTH-BRICK_SEP;
		int starty= (screenres_y-APPLICATION_HEIGHT)/2-BRICK_Y_OFFSET;
		int startxo= ((screenres_x-APPLICATION_WIDTH)/2)-BRICK_WIDTH-BRICK_SEP;
		
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
	
	public void ball(){
		
//		
//		GOval ball = new GOval(ballx, bally, BALL_RADIUS, BALL_RADIUS);
//		add(ball);
//		ball.setFillColor(Color.BLACK);
//		ball.setFilled(true);
//		
//		vx = rgen.nextDouble(1.0, 3.0);
//		if (rgen.nextBoolean(0.5)) vx = -vx;
//		System.out.println(vx);
//		ball.move(vx, vy);
//		pause(1);
//		
	}
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		
		board();
		
		
		add(paddle);
		paddle.setFillColor(Color.BLACK);
		paddle.setFilled(true);
		
		boolean done=false;
		

		GOval ball = new GOval(ballx, bally, BALL_RADIUS, BALL_RADIUS);
		add(ball);
		ball.setFillColor(Color.BLACK);
		ball.setFilled(true);
		
		addMouseListeners();
		
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		
		while(!done){
			
			if(ballx+BALL_RADIUS>(((screenres_x-APPLICATION_WIDTH)/2)+APPLICATION_WIDTH) || ballx<((screenres_x-APPLICATION_WIDTH)/2)){
				vx=-vx;
			}
			
			if(bally+BALL_RADIUS>700 || bally<100){
				vy=-vy;
			}
			
			ballx+=vx;
			bally+=vy;
			ball.setBounds(ballx, bally, BALL_RADIUS,BALL_RADIUS);
			pause(1);
			
		}
		
		
		
		
		
		
		/* You fill this in, along with any subsidiary methods */
	}

}
