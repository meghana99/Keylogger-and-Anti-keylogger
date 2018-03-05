The following are the modules of the project.
	1. Keylogger implementation
		In this module, we implement the actual keylogger in the intent to capture the key strokes and also listen to the mouse events.
		This code is intented to understand how a code can become malicious and log user entered details on the keyboard and the mouse.
		
		The following source files comprise the keylogger implementation module.
			Client.java
			Server.java
		
		The following are the output of the keylogger implementation module.
			ServerOutputFile.txt
		
	2. Keylogger detection
		To detect the malicious softwares, a detection mechanism has been written for the purpose of undertanding the keylogger detection process.
		This detector scans for the signature of the software with the known set of signatures to detect if there are any known malicious software running in the system
		
		The following source files comprise the keylogger detection module.
					
			Detector.java
			Main.java
				
		These are the input files for the keylogger detection module
			signatures.txt
		
		These are the output files of the keylogger detection module.
			affected.txt
			result.txt
			errors.txt

		
RUNNING THE KEYLOGGER AND THE KEYLOGGER DETECTOR
		
To run the keylogger, perform the following actions.

	1.	Place the keylogger_client.jar, keylogger_server.jar, client.bat, server.bat in the same folder
	2.	Run the server using the following command
			> server.bat
		This will automatically start the server and start listening to the clients.
		If using Mac or Linux, go to the command line or terminal and enter the folowing commands to run the keylogger
			$ cd <folder where all these file resides>
			$ java -jar keylogger_server.jar
		open another terminal and cd into the same directory.
			$ java -jar keylogger_client.jar
	3.	Now the output of the keylogger should be visible in the server terminal.
	
To run the keylogger detector, perform the followng actions.
	
	1.	Place detect.bat and keydetect.jar file in the same folder
	2.	Run the detector using the following command	
			> detect.bat
		This will automatically start the detector then follow the instructions on screen
		If using Mac or Linux, go to the terminal or the command line and enter the following commands to start the keylogger detector.
			$ cd <folder where kelogger detector files reside>
			$ java -jar keydetect.jar
		Now follow the instructions on screen.
	