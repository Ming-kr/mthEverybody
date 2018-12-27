package com.teamb.mth.util;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

//메일전송 셋팅 및 쓰레드 생성
public class ThreadSendMail implements Runnable 
{
	private String toEmail;
	private String subject;
	private String sentMessage;
	private MailHandler sendMail;
	
	public ThreadSendMail(String toEmail, MailHandler sendMail)
	{
		this.toEmail = toEmail;
		this.sendMail = sendMail;
	}
	
	/*보낼 메세지 셋팅*/
	//인증키 전송 메세지
	public void setMessageForJoiningKey(String url)
	{
		this.subject = "[모두의 떨이 이메일 인증]";
		this.sentMessage = "<p style=\"text-align:center\"><span style=\"font-family: 'Black Han Sans', sans-serif;font-size:3em;\">"
							+ "모두의 떨이에 가입하신 걸 환영합니다." + "</span></p>" + "<a href='" + url + "'> 이메일 인증 </a>"
							+ "<p>위의 링크를 클릭하면 자동으로 메일 인증이 됩니다. 위의 링크는 24시간 동안 유효합니다.</p>";
	}
	
	//판매자 회원 승인관련
	public void setMessageForReqSeller(String response, String nick, String url)
	{
		switch(response)
		{
			case "update":
				this.subject = "[모두의 떨이] 판매자 회원 신청이 승인 완료 되었습니다.";
				this.sentMessage = "<p style=\"text-align:center\"><span style=\"font-family: 'Black Han Sans', sans-serif;font-size:3em;\">"
						+ "모두의 떨이를 이용해주셔서 감사합니다.</span></p>" 
						+ "<p>" + nick + "님의 회원구분이 판매자 회원으로 전환되었습니다.<br>"
						+ "편의점 등록 역시 승인절차를 거쳐야 하며 승인과정은 24시간 내외의 시간이 소요됩니다.<br>" 
						+ "감사합니다.</p>"
						+ "<h4><a href='" + url + "'>메인페이지</a>로 이동</h4>";
				break;
				
			case "reject":
				this.subject = "[모두의 떨이] 판매자 회원 신청이 승인 거부 되었습니다.";
				this.sentMessage = "<p style=\"text-align:center\"><span style=\"font-family: 'Black Han Sans', sans-serif;font-size:3em;\">"
						+ "모두의 떨이를 이용해주셔서 감사합니다." + "</span></p>" 
						+ "<p>" + nick + "님의 판매자 회원 신청이 거절되었습니다.<br>"
						+ "판매자 회원 신청시 입력하신 정보들을 다시 한번 확인해주시길 바랍니다.<br>"
						+ "기존에 입력한 이름, 전화번호, 사업자등록번호 정보는 승인 거절 즉시 삭제 조치 하고 있습니다.<br>"
						+ "판매자 승인 관련하여 기타 문의하실 사항이 있으시면 전화 또는 메일을 통해 관리자에게 문의바랍니다.<br>감사합니다.</p>"
						+ "<h4><a href='" + url + "'>메인페이지</a>로 이동</h4>";
				break;
				
			default:
				System.out.println("response값 입력 잘못됨.(update 또는 reject만 입력)");
		}
	}
	
	//편의점 등록 승인/거부/승인취소
	public void setMessageForReqConven(String response, String nick, String convenName, String url)
	{
		switch(response)
		{
			case "update":
				this.subject = "[모두의 떨이] " + convenName + " 편의점 등록이 승인완료 되었습니다.";
				this.sentMessage = "<p style=\"text-align:center\"><span style=\"font-family: 'Black Han Sans', sans-serif;font-size:3em;\">"
								+ "모두의 떨이를 이용해주셔서 감사합니다.</span></p>" 
								+ "<p>" + nick + "님이 요청하신 [" + convenName + "] 편의점 등록이 승인완료 되었습니다.<br>" 
								+ "감사합니다.</p>" 
								+ "<h4><a href='" + url + "'>메인페이지</a>로 이동</h4>";
				break;
				
			case "reject":
				this.subject = "[모두의 떨이] " + convenName + " 편의점 등록이 승인거부 되었습니다.";
				this.sentMessage = "<p style=\"text-align:center\"><span style=\"font-family: 'Black Han Sans', sans-serif;font-size:3em;\">"
								+ "모두의 떨이를 이용해주셔서 감사합니다." + "</span></p>" 
								+ "<p>" + nick + "님이 요청하신 [" + convenName + "] 편의점 등록이 승인거부 되었습니다.<br>"
								+ "편의점 등록은 <strong>사실정보 조회가 불가능</strong>하거나 편의점 <strong>위치 확인 불가</strong> 등의 이유로 거부될 수 있습니다.<br>"
								+ "편의점 등록 재신청시 입력정보를 다시 한번 확인해주시기 바랍니다.<br>"
								+ "편의점 승인 관련하여 기타 문의하실 사항이 있으시면 전화 또는 메일을 통해 관리자에게 문의바랍니다.<br>감사합니다.</p>" 
								+ "<h4><a href='" + url + "'>메인페이지</a>로 이동</h4>";
				break;
				
			case "cancel":
				this.subject = "[모두의 떨이] " + convenName + " 편의점이 승인취소 되었습니다.";
				this.sentMessage = "<p style=\"text-align:center\"><span style=\"font-family: 'Black Han Sans', sans-serif;font-size:3em;\">"
								+ "모두의 떨이를 이용해주셔서 감사합니다.</span></p>" 
								+ "<p>" + nick + "님이 요청하신 [" + convenName + "] 편의점 등록이 승인취소 되었습니다.<br>"
								+ "등록된 편의점은 <strong>사실정보 조회가 불가능</strong>하거나 편의점 <strong>위치 확인 불가</strong> 또는 <strong>합당한 이유의 신고 누적</strong> 등의 이유로 승인이 취소될 수 있습니다.<br>"
								+ "편의점 승인취소 및 조치이유 등에 대해 문의하실 사항이 있으시면 전화 또는 메일을 통해 관리자에게 문의바랍니다.<br>감사합니다.</p>"
								+ "<h4><a href='" + url + "'>메인페이지</a>로 이동</h4>";
				break;
						
			default:
				System.out.println("response값 입력 잘못됨.(update/reject/cancel만 입력)");
		}
	}
	
	//새로운 비밀번호 전송
	public void setMessageForNewPassword(String nick, String newPassword, String url)
	{
		this.subject = "[모두의 떨이 임시비밀번호 전송]";
		this.sentMessage = "<p style=\"text-align:center\"><span style=\"font-family: 'Black Han Sans', sans-serif;font-size:3em;\">"
				+ "모두의 떨이를 이용해주셔서 감사합니다.</span></p>" 
				+ "<p>" + nick + "님의 임시 비밀번호는 [" + newPassword + "] 입니다.<br>"
				+ "해당 이메일은 보안이 취약하기 때문에 로그인 즉시 <strong>비밀번호 변경</strong>을 진행해주시기 바랍니다.<br>"
				+ "기타 문의하실 사항이 있으시면 전화 또는 메일을 통해 관리자에게 문의바랍니다.<br>감사합니다.</p>"
				+ "<h4><a href='" + url + "'>메인페이지</a>로 이동</h4>";
	}
	
	//메일 전송 로직
	private void sendMailToMember() throws MessagingException, UnsupportedEncodingException 
	{
		System.out.println("메일 전송시작.. 받는 사람:"+toEmail+", 제목:"+subject);
		
		sendMail.setSubject(subject);
		sendMail.setText(sentMessage);
		sendMail.setFrom("mthTest180801@gmail.com", "모두의 떨이");
		sendMail.setTo(toEmail);
		sendMail.send();
	}
	
	@Override
	public void run() 
	{
		System.out.println("쓰레드 시작");
		try {
			sendMailToMember();
			return;
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
