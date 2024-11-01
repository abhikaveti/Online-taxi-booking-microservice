package com.calltaxi.api_gateway.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController("Booking")
public class BookingController {
	
	@Value("{BookingApi}")
	private String url;
	
	
}
