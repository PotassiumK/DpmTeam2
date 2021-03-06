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
 * Removed LCD printing, added optional debug print statements
 */
package trotty02;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

/*
 * This class opens a wifi connection, waits for the data
 * and then allows access to the data after closing the wifi socket.
 * 
 * It should be used by calling the constructor which will automatically wait for
 * data without any further user command
 * 
 * Then, once completed, it will allow access to an instance of the Transmission
 * class which has access to all of the data needed
 */
public class WifiConnection {
	/**
	 * 
	 */
	public HashMap<String, Integer> StartData;
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
	public static int xCenter;
	public static int yCenter;

	public static int TEAM_NUMBER = 2;
	/**
	 * 
	 * @param serverIP receives address to connect to server using
	 * @param teamNumber which bot we are 
	 * @throws IOException to account for errors
	 */
	public WifiConnection(String serverIP, int teamNumber) throws IOException {
		this(serverIP, teamNumber, true);
	}

	/**
	 * 
	 * @param serverIP receives address to connect to server using
	 * @param teamNumber which bot we are 
	 * @param debugPrint boolean that can turn print statements on and off
	 * @throws IOException in case of errors
	 */
	public WifiConnection(String serverIP, int teamNumber, boolean debugPrint) throws IOException {

		// Open connection to the server and data streams
		int port = 2000 + teamNumber; // semi-abritrary port number"
		Socket socketClient = new Socket(serverIP, port);

		DataOutputStream dos = new DataOutputStream(socketClient.getOutputStream());
		DataInputStream dis = new DataInputStream(socketClient.getInputStream());

		if (debugPrint) {
			System.out.println("Connected\nWaiting for data");
		}

		// Wait for the server transmission to arrive
		while (dis.available() <= 0)
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

		// Parse transmission
		this.StartData = ParseTransmission.parseData(dis);

		if (debugPrint) {
			//System.out.println("Data received");
		}

		role = 1;
		if((StartData.get("BTN")) == TEAM_NUMBER)
			role = 1; //builder
		if((StartData.get("CTN")) == TEAM_NUMBER)
			role = 2; //collector

		corner = 1;
		if(role == 1)
			corner = ((int)(StartData.get("BSC")));
		if(role == 2)
			corner = ((int)(StartData.get("CSC")));

		if (role == 1){
			xHalf = ((int)(((StartData.get("LGZx") + ((StartData.get("UGZx")-StartData.get("LGZx"))/2)))/6));
			xCenter = ((int)(((StartData.get("LGZx") + ((StartData.get("UGZx")-StartData.get("LGZx"))/2)))));

		}else{
			xHalf = ((int) (((StartData.get("LRZx") + ((StartData.get("URZx")-StartData.get("LRZx"))/2)))/6));
			xCenter = xHalf = ((int) (((StartData.get("LRZx") + ((StartData.get("URZx")-StartData.get("LRZx"))/2)))));

		}
		if (role == 1){
			yHalf = ((int) (((StartData.get("LGZy") + ((StartData.get("UGZy")-StartData.get("LGZy"))/2)))/6));
			yCenter = ((int) (((StartData.get("LGZy") + ((StartData.get("UGZy")-StartData.get("LGZy"))/2)))));
		}else{
			yHalf = ((int) (((StartData.get("LRZy") + ((StartData.get("URZy")-StartData.get("LRZy"))/2)))/6));
			yCenter = ((int) (((StartData.get("LRZy") + ((StartData.get("URZy")-StartData.get("LRZy"))/2)))));

		}
		LRZy = StartData.get("LRZy");
		UGZy = StartData.get("UGZy");
		LRZx = StartData.get("LRZx");
		UGZx = StartData.get("UGZx");
		LGZy = StartData.get("LGZy");
		LGZx = StartData.get("LGZx");
		URZy = StartData.get("URZy");
		URZx = StartData.get("URZx");



		// End the wifi connection
		dis.close();
		dos.close();
		socketClient.close();

	}

	/**
	 * gets information received from the wifi
	 * @return information received over wifi as a hashmap so we can access it
	 */
	HashMap<String, Integer> getStartData(){
		System.out.println(this.StartData);
		return this.StartData;
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

	public int getxCenter(){
		return this.xCenter;
	}
	public int getyCenter(){
		return this.yCenter;
	}
	
}