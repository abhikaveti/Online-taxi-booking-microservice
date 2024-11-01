package com.calltaxi.api_gateway.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.calltaxi.api_gateway.DTO.Ride;
import com.calltaxi.api_gateway.DTO.Rides;

@RestController
@RequestMapping("/api/trip")
public class TripManagerController {

	
	@Value("${TripManagerApi}")
	private String url;
	
	@Autowired
	private RestOperations restops;
	
	@GetMapping("/check")
	public String checking() {
		return "Working";
	}
	
	@GetMapping("/getrides/{latitude}:{longitude}/{dropLatitude}:{dropLongitude}")
	public List<Rides> getAvailableRides(@PathVariable double latitude, @PathVariable double longitude,
										@PathVariable double dropLatitude, @PathVariable double dropLongitude){
		
		
		
		return restops.exchange(url+"/trip/getrides/"+latitude+":"+longitude+"/"+dropLatitude+":"+dropLongitude, HttpMethod.GET, null, List.class).getBody();
	}
	
	@PostMapping("/confirmride/{userId}")
	public String confirmRide(@RequestBody Ride ride, @PathVariable String userId) {
		
		
		return restops.exchange(url+"/trip/confirmride/"+userId, HttpMethod.POST, new HttpEntity<Ride>(ride),String.class).getBody();
	}
	
	@GetMapping("/getrides/best/{latitude}:{longitude}/{dropLatitude}:{dropLongitude}")
	public Rides getBestFare(@PathVariable double latitude, @PathVariable double longitude,
			@PathVariable double dropLatitude, @PathVariable double dropLongitude) {
		
		return restops.exchange(url+"/trip/getrides/best/"+latitude+":"+longitude+"/"+dropLatitude+":"+dropLongitude, HttpMethod.GET, null, Rides.class).getBody();
	}
	
	@PostMapping("/completeride/{rideid}")
	public void completeRide(@PathVariable String rideid) {
		restops.exchange(url+"/trip/completeride/"+rideid, HttpMethod.POST, null, void.class);
	}
	
}
