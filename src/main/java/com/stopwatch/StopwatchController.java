package com.stopwatch;

import java.time.Instant;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stopwatch")
@CrossOrigin(origins="https://stopwatch-frontend.netlify.app")// Allows your React frontend (running on port 3000) to access 
public class StopwatchController {
	//variable to hold state of stopwatch
private Instant startTime;
private long accumulatedTime=0;

//handles the post request to start th stopwatch
@PostMapping("/start" )
public void startStopwatch() {
	if(startTime==null) {//only start if not already running
		startTime=Instant.now();
		System.out.println("Stopwatch started at: "+startTime);
	}
}

@PostMapping("/stop")
   public void stopStopwatch() {
	if(startTime!=null) {
		long currentElapsed=Instant.now().toEpochMilli()-startTime.toEpochMilli();
		accumulatedTime+=currentElapsed;
		startTime=null;
		System.out.println("Stopwatch stopped. Accumulated time: "+accumulatedTime+" ms");
	}
}

@PostMapping("/reset")
public void resetStopwatch() {
	startTime=null;
	accumulatedTime=0;
	System.out.println("Stopwatch reset.");
}

@GetMapping("/time")
public long getCurrentTime() {
	if(startTime!=null) {
		long currentRunningTime=Instant.now().toEpochMilli()-startTime.toEpochMilli();
		return accumulatedTime+currentRunningTime;
	}
	return accumulatedTime;
}
}
