package org.buddy.parking.website;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParkingBuddyController{
	
    @GetMapping("/")
    public String home() {
        return "home"; //opens home.html
    }
}


