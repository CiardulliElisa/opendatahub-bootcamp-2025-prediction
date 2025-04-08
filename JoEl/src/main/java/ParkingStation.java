package JoEl.src.main.java;

import java.util.Date;

public class ParkingStation {
	String _timestamp;
	int mperiod;
	//freie parkplätze
	int mvalue;
	
	
	public String get_timestamp() {
		return _timestamp;
	}
	public void set_timestamp(String _timestamp) {
		this._timestamp = _timestamp;
	}
	public int getMperiod() {
		return mperiod;
	}
	public void setMperiod(int mperiod) {
		this.mperiod = mperiod;
	}
	public int getMvalue() {
		return mvalue;
	}
	public void setMvalue(int mvalue) {
		this.mvalue = mvalue;
	}

}
