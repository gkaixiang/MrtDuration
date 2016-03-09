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
	
	public static StationInfo[] EWLine = new StationInfo[ARRAY_SIZE];
	public static String EWLineInfo = "stationinfo.txt";
	public static String temp;
	public static String arrayTemp[] = new String[ARRAY_SIZE];
	public static String currentLocation;
	public static String currentLocationName;
	public static String currentLocationCode;
	public static int currentLocationToEndOfLine;
	public static String destination;
	public static String destinationName;
	public static String destinationCode;
	public static int destinationToEndOfLine;
	
	public static void main(String[] args) throws FileNotFoundException{
		initilization();
		while(true){
		readInputAndProcess();
		}
	}

	private static void readInputAndProcess() {
		readInput();
		
		for(int i=0; i<EWLine.length; i++){
			if(EWLine[i].getStationName().contains(currentLocation)){
				//printMessage(EWLine[i].getStationName() + " successfully located in array\n");
				currentLocationCode = EWLine[i].getStationCode1();
				currentLocationToEndOfLine = EWLine[i].getTimeFromFirstStation1();
				currentLocationName = EWLine[i].getStationName();
			}
			if(EWLine[i].getStationName().contains(destination)){
				//printMessage(EWLine[i].getStationName() + " successfully located in array\n");
				destinationCode = EWLine[i].getStationCode1();
				destinationToEndOfLine = EWLine[i].getTimeFromFirstStation1();
				destinationName = EWLine[i].getStationName();
			}
		}
		
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
		BufferedReader br = new BufferedReader(new FileReader(EWLineInfo)); //Read EW Line Info
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
				
				if(EWLine[j] != null){
				EWLine[j].setStationName(arrayTemp[0]);
				EWLine[j].setNumOfLines(Integer.parseInt(arrayTemp[1]));
				EWLine[j].setStationCode1(arrayTemp[2]);
				EWLine[j].setTimeFromFirstStation1(Integer.parseInt(arrayTemp[3]));
				//System.out.println(EWLine[j].getStationName() + "1 read successfully!");
				if(arrayTemp.length > 4){
				EWLine[j].setStationCode2(arrayTemp[4]);
				EWLine[j].setTimeFromFirstStation2(Integer.parseInt(arrayTemp[5]));
				//System.out.println(EWLine[j].getStationName() + "2 read successfully!");
				}
				if(arrayTemp.length > 6){
				EWLine[j].setStationCode3(arrayTemp[6]);
				EWLine[j].setTimeFromFirstStation3(Integer.parseInt(arrayTemp[7]));
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
		    EWLine[i] = new StationInfo();
		}
	}
	
	//For printing out all messages
	private static void printMessage(String message) {
		System.out.print(message);
	}
}
		
		
		