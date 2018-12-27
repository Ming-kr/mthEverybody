var checkAllValue = false;
 
 function requestSellerFormCheck()
 {
	 //input 요소 유효여부 체크
	 var checkInputName = false;
	 var checkInputPhone = false;
	 var checkInputCompanynum = false;
	 
	 //input 요소
	 var inputName = document.getElementById('input-name');
	 var inputPhone = document.getElementById('input-phone');
	 var inputCompanynum = document.getElementById('input-companynum');
	 var agreeJoinChk = document.getElementById('agree-request');
	 
	 //정규식
	 var nameRegExp = /^[가-힣ㄱ-ㅎㅏ-ㅣ]{2,20}$/;
	 var PhoneRegExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	 var companynumRegExp =  /^[0-9]{10}$/;
	 
	 //각 항목 정규식 체크
	 if(nameRegExp.test(inputName.value) == true) { checkInputName = true; }
	 if(PhoneRegExp.test(inputPhone.value) == true) { checkInputPhone = true; }
	 if(companynumRegExp.test(inputCompanynum.value) == true) { checkInputCompanynum = true; }
	 
	 //약관동의 체크
	 if(!agreeJoinChk.checked)
	 {
		alert('약관에 동의해주세요.');
		return false;
	 }
	 else
	 {
		 //모든 요소 체크값이 true 이면 checkAllValue true로 전환
		 if((checkInputName && checkInputPhone && checkInputCompanynum)==true) { checkAllValue=true; }
	 }
	 
	 //submit 함수 호출
	 submitRequestSellerForm();
 }