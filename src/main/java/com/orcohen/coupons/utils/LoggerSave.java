package com.orcohen.coupons.utils;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.orcohen.coupons.beans.Logger_b;
import com.orcohen.coupons.enums.LogType;
import com.orcohen.coupons.repositories.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoggerSave {

	Logger logger = Logger.getLogger(LoggerSave.class.getName());
	boolean append = true;
	String filepath = "./Log/MyLogFile.log";
	FileHandler fh;

	@Autowired
	LoggerRepository log_rep ;

	public void SendLogerInfo(String Massage) {
		try {
			fh = new FileHandler(filepath, append);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
			logger.log(Level.INFO, Massage);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void SendLogerWarning(String Massage) {
		try {
			fh = new FileHandler(filepath, append);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
			logger.log(Level.WARNING, Massage);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void SendLogerAll(String Massage) {
		try {
			fh = new FileHandler(filepath, append);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
			logger.log(Level.ALL, Massage);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void SendLogerSevere(String Massage) {
		Logger_b logdb = new Logger_b(LogType.Accept,Massage,new Date());
		try {
			fh = new FileHandler(filepath, append);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
			logger.log(Level.SEVERE, Massage);
			log_rep.save(logdb);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
