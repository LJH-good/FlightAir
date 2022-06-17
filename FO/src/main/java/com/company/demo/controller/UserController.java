package com.company.demo.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.service.UserService;
import com.company.dto.QnADto;
import com.company.dto.UserDto;

/**
* <pre>
* 1. 클래스명 : UserController
* 2. 설명 : 사용자 컨트롤러
* 3. 작성일 : 2022-04-30
* 4. 작성자 : LJH
* </pre>
*/
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired private UserService service;
	@Autowired private PasswordEncoder passwordEncoder;
	
	/**
	 * 아이디 중복 검사
	 * 
	 * @param email
	 * @return String
	 * SignUp.vue
	 */
	@PostMapping("/idChecked")
	public String idChecked(String email) {
		if (service.idCheck(email) == null) {
			return "true";
		}
		return "false";
	}
	
	/**
	 * 회원 가입
	 * 
	 * @param dto
	 * @return String 
	 * SignUp.vue
	 */
	@PostMapping("/join")
	public String insertUser(UserDto dto) {
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		dto.setPassword(encodedPassword);
		if (service.insertUser(dto) > 0) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 로그인 
	 * @param dto
	 * @return String 
	 * Login.vue
	 */
	@PostMapping("/login")
	public String login(UserDto dto) {
		UserDto dto2 = service.login(dto);
		if (dto2 == null) {
			return "아이디를 확인해주세요.";
		} else if (!passwordEncoder.matches(dto.getPassword(), dto2.getPassword())) {
			return "비밀번호를 확인해주세요";
		}
		return "success" + dto2.getEmail();
	}
	
	/**
	 * 아이디 찾기
	 * 
	 * @param dto
	 * @return List<UserDto>
	 * FindId.vue
	 */
	@PostMapping("/findId")
	public List<UserDto> FindId(UserDto dto) {
		if (service.FindId(dto).isEmpty()) {
			return null;
		} else {
			return service.FindId(dto);
		}
	}
	
	/**
	 * 비밀번호 찾기 - 인증번호 메일 발송
	 * 
	 * @param dto
	 * @return String
	 * FindPassword.vue
	 */
	@PostMapping("/findPass")
	public String FindPassword(UserDto dto) {
		if (service.FindPassword(dto) != null) {
			String subject = "이메일 인증 번호";
			String content = "";
			for (int i = 0; i < 6; i++) {
				content += (int) (Math.random() * 10);
			}
			String host = "smtp.naver.com";
			final String user = "!";
			final String password = "!";
			String to = dto.getEmail();
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.ssl.trust", "smtp.naver.com");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(subject);
				message.setText(content);
				message.setContent(content, "text/html;charset=euc-kr");
				Transport.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return content;
		} else {
			return null;
		}
	}
	
	/**
	 * 비밀번호 찾기 - 비밀번호 변경
	 * 
	 * @param dto
	 * @return String
	 * FindPassword.vue
	 */
	@PostMapping("/changepassword")
	public String changepassword(UserDto dto) {
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		dto.setPassword(encodedPassword);
		if (service.changepassword(dto) > 0) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 마이페이지 로그인 
	 * 
	 * @param dto
	 * @return String
	 * MyPagePassword.vue
	 */
	@PostMapping("/mypagelogin")
	public String mypagelogin(UserDto dto) {
		UserDto dto2 = service.mypagelogin(dto.getEmail());
		if (!passwordEncoder.matches(dto.getPassword(), dto2.getPassword())) {
			return "비밀번호가 일치하지 않습니다.";
		}
		return "success";
	}
	
	/**
	 * 마이페이지 - 회원 정보 수정
	 * 
	 * @param dto
	 * @return String
	 * MyPage.vue
	 */
	@PostMapping("/updateinfo")
	public String updateinfo(UserDto dto) {
		if (service.updateinfo(dto) > 0) {
			return "success";
		}
		return null;
	}
	
	/**
	 * 마이페이지 - 회원 정보 조회
	 * 
	 * @param dto
	 * @return UserDto
	 * MyPage.vue
	 */
	@PostMapping("/userinfo")
	public UserDto userinfo(UserDto dto) {
		return service.userinfo(dto.getEmail());
	}

	/**
	 * 마이페이지 - 회원 탈퇴	
	 * 
	 * @param dto
	 * @return String
	 * DeleteUser.vue
	 */
	@PostMapping("/deleteuser")
	public String deleteuser(UserDto dto) {
		if (service.deleteuser(dto) > 0) {
			return "success";
		}
		return null;
	}
	
	/**
	 * 마이페이지 - 회원 탈퇴 시 진행 중인 예약 확인
	 * 
	 * @param dto
	 * @return String
	 * DeleteUser.vue
	 */
	@PostMapping("/deletereservation")
	public String deletereservation(UserDto dto) {
		if (service.deletereservation(dto) > 0) {
			return "success";
		}
		return null;
	}
	
	/**
	 * 마이페이지 - 비밀번호 변경
	 * 
	 * @param dto
	 * @return String	
	 * ChangePassword.vue
	 */
	@PostMapping("/updatepassword")
	public String updatepassword(UserDto dto) {
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		dto.setPassword(encodedPassword);
		if (service.updatepassword(dto) > 0) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 기존 비밀번호 확인
	 * 
	 * @param dto 
	 * @return String
	 * ChangePassword.vue
	 */
	@PostMapping("/oripass")
	public String oripass(UserDto dto) {
		UserDto dto2 = service.oripass(dto.getEmail());
		if (!passwordEncoder.matches(dto.getPassword(), dto2.getPassword())) {
			return "현재 비밀번호가 일치하지 않습니다.";
		}
		return "success";
	}
	
	/**
	 * 상담글 등록
	 * 
	 * @param dto
	 * @return String
	 * QnA.vue
	 */
	@PostMapping("/insertqna")
	public String insertqna(QnADto dto) {
		if(service.insertqna(dto)>0) 
		{return "success";}
		return "fail";
		
	}
	
	/**
	 * 상담글 목록
	 * 
	 * @param dto
	 * @return List<QnADto> 	
	 * ChangePassword.vue
	 */
	@PostMapping("/qnanumber")
	public List<QnADto> qnanumber(QnADto dto){
		if(service.qnanumber(dto).isEmpty()) {return null;}
		else {return service.qnanumber(dto);}
	}
	
	/**
	 * 상담글 상세
	 * 
	 * @param dto
	 * @return QnADto
	 * ChangePassword.vue
	 */
	@PostMapping("/qnadetail")
	public QnADto qnadetail(QnADto dto) {
		return service.qnadetail(dto);
	}
	
	/**
	 * 상담글 삭제
	 * 
	 * @param dto
	 * @return String
	 * ChangePassword.vue
	 */
	@PostMapping("/deleteqna")
	public String deleteqna(QnADto dto) {
		if(service.deleteqna(dto)>0) {return "success";}
		return "fail";
	}
}
