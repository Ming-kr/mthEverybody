//마지막 유효성 체크 위해 필요한 변수
var joinIdValuedCheck = false;
var joinPwValuedCheck = false;
var samePwAsPwchkCheck = false;
var joinNickCheck = false;
var joinEmailCheck = false;
var allValueCheck;

//비밀번호 체크(onkeyup, onblur)
function checkJoinPw(){
	var inputPw = document.getElementById('input-pw');
	
	if(inputPw.value.length < 8 || inputPw.value.length > 15)
	{
		document.getElementById('joinMember-pw-chk1').innerHTML = '패스워드의 길이(8자 이상 15자 이내)를 확인해주세요.';
		document.getElementById('joinMember-pw-chk1').style.color = "red";
	}
	else
	{
		document.getElementById('joinMember-pw-chk1').innerHTML = '사용가능한 패스워드입니다.';
		document.getElementById('joinMember-pw-chk1').style.color = "#00a000";
		joinPwValuedCheck = true;
	}
}
//비밀번호 확인 체크(onkeyup, onblur)
function checkPwAndPwchk(){
	var inputPw = document.getElementById('input-pw');
	var inputPwChk = document.getElementById('input-pw-chk');
	
	if(inputPw.value != inputPwChk.value || inputPwChk.value == "" || inputPwChk.value == null)
	{
		document.getElementById('joinMember-pw-chk2').innerHTML = '패스워드가 동일하지 않습니다.';
		document.getElementById('joinMember-pw-chk2').style.color = "red";
	}
	else
	{
		document.getElementById('joinMember-pw-chk2').innerHTML = '패스워드 확인을 완료했습니다.';
		document.getElementById('joinMember-pw-chk2').style.color = "#00a000";
		samePwAsPwchkCheck = true;
	}
}
//닉네임 체크(onkeyup, onblur)
function checkJoinNick(){
	var inputNick = document.getElementById('input-nick');
	var exptext = /^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|0-9]*$/;
	
	if(inputNick.value.length < 3 || inputNick.value.length > 15)
	{
		document.getElementById('joinMember-nick-chk').innerHTML = '닉네임의 길이(3자 이상 15자 이내)를 확인해주세요.';
		document.getElementById('joinMember-nick-chk').style.color = "red";
	}
	else if(exptext.test(inputNick.value) == false)
	{
		document.getElementById('joinMember-nick-chk').innerHTML = '닉네임은 영문자/한글/숫자만 가능합니다.';
		document.getElementById('joinMember-nick-chk').style.color = "red";
	}
	else
	{
		document.getElementById('joinMember-nick-chk').innerHTML = '사용가능한 닉네임입니다.';
		document.getElementById('joinMember-nick-chk').style.color = "#00a000";
		joinNickCheck = true;
	}
}
//이메일 체크(onkeyup, onblur)
function checkJoinEmail(){
	var inputEmail = document.getElementById('input-email');
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	
	if(inputEmail.value =="" || inputEmail.value ==null)
	{
		document.getElementById('joinMember-email-chk').innerHTML = '이메일을 입력해주세요.';
		document.getElementById('joinMember-email-chk').style.color = "red";
	}
	else if(exptext.test(inputEmail.value) == false)
	{
		document.getElementById('joinMember-email-chk').innerHTML = '유효한 이메일 형식을 입력해주세요.';
		document.getElementById('joinMember-email-chk').style.color = "red";
	}
	else
	{
		document.getElementById('joinMember-email-chk').innerHTML = '유효한 이메일 형식입니다.';
		document.getElementById('joinMember-email-chk').style.color = "#00a000";
		joinEmailCheck = true;
	}
}
//전체 joinMemberForm 체크(onclick)
function joinMemberFormCheck()
{
	//약관동의 체크박스 엘리먼트
	var agreeJoinChk = document.getElementById('agree-join');
	allValueCheck = true;
	
	//각각 항목이 유효한지 체크
	if(!joinIdValuedCheck) allValueCheck = false;
	if(!joinPwValuedCheck) allValueCheck = false;
	if(!samePwAsPwchkCheck) allValueCheck = false;
	if(!joinNickCheck) allValueCheck = false;
	if(!joinEmailCheck) allValueCheck = false;
	if(!agreeJoinChk.checked)
	{
		alert('약관에 동의해주세요.');
		allValueCheck = false;
		return false;
	}
	
	//모든 항목 유효하면 joinMemberForm.jsp에서 submitAction() 호출
	if(allValueCheck == true) { submitAction(); }
	else 
	{
		alert('회원가입에 실패하였습니다.\n입력값, 인증 여부를 확인해주세요.');
		return false;
	}
}