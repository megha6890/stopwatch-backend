package com.stopwatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class StopwatchApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(StopwatchApplication.class, args);
        System.out.println( "Stopwatch Spring Boot appication started!" );
    }
}
