package trotty02;
/*
 * @author Sean Lawlor
 * @date November 3, 2011
 * @class ECSE 211 - Design Principle and Methods
 * 
 * Modified by F.P. Ferrie
 * February 28, 2014
 * Changed parameters for W2014 competition
 * 
 * Modified by Francois OD
 * November 11, 2015
 * Ported to EV3 and wifi (from NXT and bluetooth)
 * Changed parameters for F2015 competition
 * 
 * Modified by Michael Smith
 * November 1, 2016
 * Cleaned up print statements, old code, formatting
 * 
 */
import java.io.IOException;
import java.util.HashMap;

import trotty02.WifiConnection;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;

public class WifiTest {
	/*
	 * Example call of the transmission protocol 
	 * We use System.out.println() instead of LCD printing so that 
	 * full debug output (e.g. the very long string containing the transmission) 
	 * can be read on the screen or a remote console such as the 
	 * EV3Control program via Bluetooth or WiFi
	 */

	/* *** INSTRUCTIONS ***
	 * There are two variables to set manually on the EV3 client:
	 * 1. SERVER_IP: the IP address of the computer running the server application
	 * 2. TEAM_NUMBER: your project team number
	 * */

	private static final String SERVER_IP = "192.168.2.9";
	private static final int TEAM_NUMBER = 2;
	public static HashMap<String, Integer> t;
	public static int role;
	public static int corner;
	public static int xHalf;
	public static int yHalf;
	public static int LRZy;
	public static int UGZy;
	public static int LRZx;
	public static int UGZx;
	public static int LGZy;
	public static int LGZx;
	public static int URZy;
	public static int URZx;

	private static TextLCD LCD = LocalEV3.get().getTextLCD();

	public WifiTest() {
	}

	public void run(){
		LCD.clear();

		/*
		 * WiFiConnection will establish a connection to the server and wait for data
		 * If the server is not running, this will throw an IOException
		 * If the server is running but the user has yet to press start on the Java GUI with some data,
		 * this will wait forever
		 * During the competition, this means you can start your code, place it on the field, and it will wait
		 * for data from the professor's computer
		 * If you need it to stop, access the robot via the EV3Control program and click "Stop Program"
		 * Alternatively, you can reset the robot but you risk SD card corruption
		 * Note that you can set the final argument debugPrint as false to disable printing to the LCD if desired.
		 */ 
		WifiConnection conn = null;
		try {
			System.out.println("             C");
			conn = new WifiConnection(SERVER_IP, TEAM_NUMBER, true);
		} catch (IOException e) {
			System.out.println("             C");
		}

		LCD.clear();

		/*
		 * This section of the code reads and prints the data received from the server,
		 * stored as a HashMap with String keys and Integer values.
		 */
		if (conn != null) {
			HashMap<String, Integer> t = conn.StartData;
			if (t == null) {
				System.out.println("        ftr");
			} else {
				System.out.println("          read" );
				//+ t.toString());

			}
		}

		// Wait until user decides to end program
		//	Button.waitForAnyPress();

		role = conn.role;
		corner = conn.corner;
		xHalf = conn.xHalf;
		yHalf = conn.yHalf;
			LRZy = conn.LRZy;
			UGZy = conn.UGZy;
			LRZx = conn.LRZx;
			UGZx = conn.UGZx;
			LGZy = conn.LGZy;
			LGZx = conn.LGZx;
			URZy = conn.URZy;
			URZx = conn.URZx;


	}

	public int getRole(){
		return this.role;
	}

	public int getCorner(){
		return this.corner;
	}

	public int getxHalf(){
		return this.xHalf;
	}

	public int getyHalf(){
		return this.yHalf;
		}

	public int getLRZy(){
		return this.LRZy;
	}

	public int getUGZy(){
		return this.UGZx;
	}

	public int getLRZx(){
		return this.LRZx;
	}

	public int getUGZx(){
		return this.UGZx;
	}

	public int getLGZy(){
		return this.LGZy;
	}

	public int getLGZx(){
		return this.LGZx;
	}

	public int getURZy(){
		return this.URZy;
	}

	public int getURZx(){
		return this.URZx;
	}


}