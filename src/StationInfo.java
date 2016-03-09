
public class StationInfo {
	private String StationName;
	private int NumOfLines;
	private String StationCode1;
	private int TimeFromFirstStation1;
	private String StationCode2;
	private int TimeFromFirstStation2;
	private String StationCode3;
	private int TimeFromFirstStation3;
	
	public StationInfo(){
		StationName = "Unknown";
		NumOfLines = 0;
		StationCode1 = "Unknown";
		TimeFromFirstStation1 = 0;
		StationCode2 = "Unknown";
		TimeFromFirstStation2 = 0;
		StationCode3 = "Unknown";
		TimeFromFirstStation3 = 0;
	}
	
	public void setStationName(String _stationName){
		StationName = _stationName;
	}
	
	public void setNumOfLines(int _NumOfLines){
		NumOfLines = _NumOfLines;
	}
	
	public void setStationCode1(String _stationCode){
		StationCode1 = _stationCode;
	}
	
	public void setTimeFromFirstStation1(int _time){
		TimeFromFirstStation1 = _time;
	}
	
	public void setStationCode2(String _stationCode){
		StationCode2 = _stationCode;
	}
	
	public void setTimeFromFirstStation2(int _time){
		TimeFromFirstStation2 = _time;
	}
	
	public void setStationCode3(String _stationCode){
		StationCode3 = _stationCode;
	}
	
	public void setTimeFromFirstStation3(int _time){
		TimeFromFirstStation3 = _time;
	}
	
	public String getStationName(){
		return StationName;
	}
	
	public int getNumOfLines(){
		return NumOfLines;
	}
	
	public String getStationCode1(){
		return StationCode1;
	}
	
	public int getTimeFromFirstStation1(){
		return TimeFromFirstStation1;
	}
	
	public String getStationCode2(){
		return StationCode2;
	}
	
	public int getTimeFromFirstStation2(){
		return TimeFromFirstStation2;
	}
	
	public String getStationCode3(){
		return StationCode3;
	}
	
	public int getTimeFromFirstStation3(){
		return TimeFromFirstStation3;
	}
}
