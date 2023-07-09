package com.uttara.projectApp;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
public class Logger {
	// control whether the log data should be printed to the monitor
	public static final boolean LOGTOMONTOR = true;
	private static Logger obj = null;
	 // Method to log the provided data
	public void log(final String data) {
		// Proffering logging operation
		new Thread(new Runnable() {
			public void run() {
				BufferedWriter bw = null;
				try {
					// Creating a new thread
					bw = new BufferedWriter(new FileWriter("log.txt", true));
					Date dt = new Date();
					// Write the time and date to log file
					bw.write(dt.toString() + ":" + data);
					bw.newLine();
					// validing logger mode
					if (Constants.LOGGER_MODE) {
						System.out.println("data");
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (bw != null)

						try {
							 // Closing the BufferedWriter
							bw.close();
						}

						catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
		}).start();
	}
	private Logger() {

	}
	// Singleton pattern
	public static Logger getInstance() {
		if (obj == null)
			obj = new Logger();
		return obj;

	}
}
