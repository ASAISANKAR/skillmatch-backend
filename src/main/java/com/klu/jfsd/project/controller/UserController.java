package com.klu.jfsd.project.controller;


import java.time.LocalDateTime;


import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.klu.jfsd.project.entity.Log;
import com.klu.jfsd.project.entity.Professional;
import com.klu.jfsd.project.entity.User;
import com.klu.jfsd.project.service.ClientService;
import com.klu.jfsd.project.service.LogService;
import com.klu.jfsd.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userser;
	@Autowired
	ClientService clser;
	@Autowired
	private LogService logser;
	
	
	@GetMapping("/")
	public void ip(HttpServletRequest request, 
            @RequestHeader(value = "User-Agent") String userAgent)
	{
		String ipAddress = getClientIp(request);
		 LocalDateTime currentDateTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateTime = currentDateTime.format(formatter);
		 Log log=new Log();
		 log.setIp(ipAddress);
		 log.setDatetime(formattedDateTime);
		 logser.LogRegistration(log);
		 System.out.println("Data captured...!!!!!");
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody User user)
	{
		userser.createUser(user);
		return ResponseEntity.ok("Done");
	}
	
	@PostMapping("/check")
	public User check(@RequestBody User u,HttpServletRequest request)
	{		 
		User rec=userser.verify(u);
		if(rec!=null)
		{
			
		System.out.println(rec.getId());
		return rec;
		}
		else
			return null;
	}
	
	private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // If multiple IPs are present (common in X-Forwarded-For), take the first one
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0];
        }
        return ipAddress;
    }
	
	@GetMapping("/getprof")
	public List<Professional> getprof()
	{
		return clser.getProf();
	}
	
	@GetMapping("/retrieve")
	public List<User> retrieve()
	{
		return userser.retUsers();
	}
	
	


}
