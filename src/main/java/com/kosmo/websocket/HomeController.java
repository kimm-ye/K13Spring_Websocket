package com.kosmo.websocket;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/Notification/01WebNoti.do", method=RequestMethod.GET)
	public String webNoti() {
		
		return "02Notification/01WebNoti";
	}
	
	//웹소켓채팅
	@RequestMapping(value="/Notification/02WebSocket.do", method=RequestMethod.GET)
	public String webSocket() {
		return "02Notification/02WebSocket";
	}
	@RequestMapping(value="/Notification/02WebChat.do", method=RequestMethod.GET)
	public String WebChat() {
		return "02Notification/02WebChat";
	}
	@RequestMapping(value="/Notification/02WebChatUI.do", method=RequestMethod.GET)
	public String WebChatUI() {
		return "02Notification/02WebChatUI";
	}
	
	
	//웹노티+웹소켓 활용한 쪽지보내기
	
	@RequestMapping(value="/Notification/03SendMain.do", method=RequestMethod.GET)
	public String webMain() {
		return "02Notification/03SendMain";
	}
	
	@RequestMapping(value="/Notification/03SendMessage.do", method=RequestMethod.GET)
	public String webMessage(HttpSession session, HttpServletRequest req) {
		
		//본인이 사용할 아이디로 UUID 사용
		//session.setAttribute("chat_id", UUID.randomUUID());
		
		//파라미터 사용
		session.setAttribute("chat_id", req.getParameter("chat_id"));
		session.setAttribute("chat_room", req.getParameter("chat_room"));
		
		return "02Notification/03SendMessage";
	}
	
	
	
	
}
