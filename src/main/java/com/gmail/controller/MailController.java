package com.gmail.controller;

import java.util.List;

import com.gmail.repository.MailDao;
import com.gmail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.module.Mail;

@RestController
public class MailController {

	@Autowired
	private MailService mailService;

	@GetMapping(value = "/inbox")
	public ResponseEntity<List<Mail>> inbox(){

		List<Mail> inboxList = mailService.inbox();
		return new ResponseEntity<>(inboxList, HttpStatus.ACCEPTED);
	}
	
	//Raj
	@GetMapping(value = "/sent")
	public ResponseEntity<List<Mail>> sent(){
		
		return null;
	}
	
	//Shivam
	@GetMapping(value = "/starred")
	public ResponseEntity<List<Mail>> starred(){
		
		return null;
	}

	//Raj
	@GetMapping(value = "/draft")
	public ResponseEntity<List<Mail>> draft(){
		
		return null;
	}

	//Shivam
	@GetMapping(value = "/trash")
	public ResponseEntity<List<Mail>> trash(){
		
		return null;
	}
	
}
