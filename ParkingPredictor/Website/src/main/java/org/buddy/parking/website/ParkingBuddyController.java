package org.buddy.parking.website;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParkingBuddyController{
	
    @GetMapping("/")
    public String home() {
        return "home"; //opens home.html
    }

    
    @GetMapping("/api/points")
    @ResponseBody
    public List<Point> getPoints() {
    	return List.of(
    			new Point("Sarntal", 46.638780, 11.350111),
    			new Point("Location B", 34.0522, -118.2437)
    			);
    }
    
    public static class Point {
        public String name;
        public double lat;
        public double lng;

        public Point(String name, double lat, double lng) {
            this.name = name;
            this.lat = lat;
            this.lng = lng;
        }
    }

}


