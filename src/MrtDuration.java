import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MrtDuration {
	private static final int ARRAY_SIZE = 30;
	private static final String MESSAGE_ENTER_CURRENT_LOCATION = "Enter current location: ";
	private static final String MESSAGE_ENTER_DESTINATION = "Enter destination: ";
	private static final String MESSAGE_FILE_DOES_NOT_EXIST = "File does not exist, program will exit now.\n";
	private static final String MESSAGE_TIME_TAKEN = "Time taken to travel from %s to %s is %s minutes. \n";
	private static final String MESSAGE_WELCOME = "Welcome to Mrt Duration Checker! Key in the two mrt stations to check total travel time on the train!\n";
	
	public static StationInfo[] stationInfo = new StationInfo[ARRAY_SIZE];
	public static String stationInfoTxtFile = "stationinfo.txt";
	public static String temp;
	public static String arrayTemp[] = new String[ARRAY_SIZE];
	public static String currentLocation;
	public static String currentLocationName;
	public static String currentLocationCode1;
	public static String currentLocationCode2;
	public static String currentLocationCode3;
	public static int currentLocationToEndOfLine;
	public static String destination;
	public static String destinationName;
	public static String destinationCode1;
	public static String destinationCode2;
	public static String destinationCode3;
	public static int destinationToEndOfLine;
	
	public static void main(String[] args) throws FileNotFoundException{
		initilization();
		while(true){
		readInputAndProcess();
		}
	}

	private static void readInputAndProcess() {
		readInput();
		
		//check if on same line
		
		for(int i=0; i<stationInfo.length; i++){
			if(stationInfo[i].getStationName().contains(currentLocation)){
				//printMessage(EWLine[i].getStationName() + " successfully located in array\n");
				currentLocationCode1 = stationInfo[i].getStationCode1();
				currentLocationCode2 = stationInfo[i].getStationCode2();
				currentLocationCode3 = stationInfo[i].getStationCode3();
				currentLocationToEndOfLine = stationInfo[i].getTimeFromFirstStation1();
				currentLocationName = stationInfo[i].getStationName();
			}
			if(stationInfo[i].getStationName().contains(destination)){
				//printMessage(EWLine[i].getStationName() + " successfully located in array\n");
				destinationCode1 = stationInfo[i].getStationCode1();
				destinationCode2 = stationInfo[i].getStationCode2();
				destinationCode3 = stationInfo[i].getStationCode3();
				destinationToEndOfLine = stationInfo[i].getTimeFromFirstStation1();
				destinationName = stationInfo[i].getStationName();
			}
		}
		
		
		//if same line, check time taken to travel direct to destination on this line only
		//check for interchange in between, if there is, change at the interchange and call this function again
		
		
		int result = destinationToEndOfLine - currentLocationToEndOfLine;
		if(result>0){printMessage(String.format(MESSAGE_TIME_TAKEN, currentLocationName, destinationName, result));}
		else {printMessage(String.format(MESSAGE_TIME_TAKEN, currentLocationName, destinationName, result*-1));}
	}

	private static void readInput() {
		Scanner input = new Scanner(System.in);
		printMessage(MESSAGE_ENTER_CURRENT_LOCATION);
		currentLocation = input.nextLine();
		if(currentLocation.equals("exit")){
			System.exit(1);
		}
		printMessage(MESSAGE_ENTER_DESTINATION);
		destination = input.nextLine();
		currentLocation = currentLocation.toLowerCase();
		destination = destination.toLowerCase();
	}

	//Opens up the file with train information and copy it into the array of train information
	private static void initilization() throws FileNotFoundException {
		printMessage(MESSAGE_WELCOME);
		initializeEWLine();
	}

	private static void initializeEWLine() throws FileNotFoundException {
		initilizeEWLineArray();
		BufferedReader br = new BufferedReader(new FileReader(stationInfoTxtFile)); //Read EW Line Info
		CopyEWLineInfo(br);
	}

	private static void CopyEWLineInfo(BufferedReader br) {
		try {
			int j = 0;
			while((temp = br.readLine()) != null){
				//System.out.println(temp);
				arrayTemp = temp.split("~", 8);
				
				/*
				for (int i=0; i<arrayTemp.length; i++){
				 printMessage(arrayTemp[i] + " ");
				}
				printMessage("\n");
				*/
				
				if(stationInfo[j] != null){
				stationInfo[j].setStationName(arrayTemp[0]);
				stationInfo[j].setNumOfLines(Integer.parseInt(arrayTemp[1]));
				stationInfo[j].setStationCode1(arrayTemp[2]);
				stationInfo[j].setTimeFromFirstStation1(Integer.parseInt(arrayTemp[3]));
				//System.out.println(EWLine[j].getStationName() + "1 read successfully!");
				if(arrayTemp.length > 4){
				stationInfo[j].setStationCode2(arrayTemp[4]);
				stationInfo[j].setTimeFromFirstStation2(Integer.parseInt(arrayTemp[5]));
				//System.out.println(EWLine[j].getStationName() + "2 read successfully!");
				}
				if(arrayTemp.length > 6){
				stationInfo[j].setStationCode3(arrayTemp[6]);
				stationInfo[j].setTimeFromFirstStation3(Integer.parseInt(arrayTemp[7]));
				//System.out.println(EWLine[j].getStationName() + "3 read successfully!");
				}
				}
				j++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void initilizeEWLineArray() {
		for(int i = 0; i < ARRAY_SIZE ; i++)
		{
		    stationInfo[i] = new StationInfo();
		}
	}
	
	//For printing out all messages
	private static void printMessage(String message) {
		System.out.print(message);
	}
}
		
		
		