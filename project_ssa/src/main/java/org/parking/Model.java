package org.parking;

import java.time.LocalDateTime;

public interface Model {
	
	public void modelData();
	
	public void getPrediction(LocalDateTime date);
	
	public void getPrediction(LocalDateTime startDate, LocalDateTime endDate);

}
