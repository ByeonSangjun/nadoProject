/**
 *  Made by Jun
 */

// 클라우드 로그인 유효성검사


$("#cloudLoginForm").validate({
	rules : {
		cloud_user_id : {
			required : true
		},
		cloud_user_pwd : {
			required : true
		}
	},
	messages : {
		cloud_user_id : {
			required : "아이디를 입력하세요."
		},
		cloud_user_pwd : {
			required : "비밀번호를 입력하세요."
		}
	},
	submitHandler : cloudSubmitForm
});

$("#email").blur(function() {
	var email_check = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var email = $(this).val();
	if(!email_check.test(email)){
		$("#emailAlertBox").html("<small class='id1'>이메일 형식에 맞지 않습니다.</small>").css("color", "darkred");
	}else{
		$.ajax({
			url : "/nado/empemch",
			type : "post",
			data : {
				email : email
			},
			success : function(result) {
				if (result > 0) {
					$("#emailAlertBox").html("<small>이미 사용중인 메일입니다.</small>")
							.css("color", "darkred");
				} else {
					$("#emailAlertBox").html("<small>사용가능합니다.</small>").css(
							"color", "green");
				}
			}
		});
	}
}); // 이메일 확인 끝

$("#phone").blur(function() {
	var phone_check = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	var phone = $(this).val();
	if(!phone_check.test(phone)){
		$("#phoneAlertBox").html("<small class='id1'>핸드폰 형식에 맞지 않습니다.</small>").css("color", "darkred");
	}else{
		$.ajax({
			url : "/nado/empphch",
			type : "post",
			data : {
				phone : phone
			},
			success : function(result) {
				if (result > 0) {
					$("#phoneAlertBox").html("<small>이미 사용중인 번호입니다.</small>")
							.css("color", "darkred");
				} else {
					$("#phoneAlertBox").html("<small>사용가능합니다.</small>").css(
							"color", "green");
				}
			}
		});
	}
}); // 핸드폰 확인 끝

//클라우드 로그인 ajax
function cloudSubmitForm(){
	var clData = $("#cloudLoginForm").serialize();
	$.ajax({
		url : "/nado/clli",
		type : "post",
		data : clData,
		success : function(result){
			if (result == 1) {
				setTimeout("location.href='/nado/index.jsp'", 800);
			} else if (result == 2) {
				$("#messageBox2").html("<div class='alert alert-danger'>비밀번호를 확인하세요.</div>");
				$("#cloud_user_pwd").select();
			} else {
				$("#messageBox2").html("<div class='alert alert-danger'>아이디가 없어요!</div>");
				$("#cloud_user_id").select();
			}
		}
	});
}

//회사 입력후 로그인 유효성검사
$("#loginForm").validate({
	rules : {
		user_id : {
			required : true
		},
		user_pwd : {
			required : true
		}
	},
	messages : {
		user_id : {
			required : "아이디를 입력하세요."
		},
		user_pwd : {
			required : "비밀번호를 입력하세요."
		}
	},
	submitHandler : submitForm
});

//회사 입력후 로그인 ajax
		function submitForm() {
			var data = $("#loginForm").serialize();
			$.ajax({
						url : "/nado/empli",
						type : "post",
						data : data,
						success : function(result) {
							if (result == 1) {
								setTimeout("location.href='/nado/views/groupware/main.jsp'", 800);
							} else if (result == 2) {
								$("#messageBox").html("<div class='alert alert-danger'>승인처리중인 아이디입니다.</div>");
								$("#user_id").select();
							} else if (result == 3) {
								$("#messageBox").html("<div class='alert alert-danger'>비밀번호를 확인하세요.</div>");
								$("#user_pwd").select();
							} else {
								$("#messageBox").html("<div class='alert alert-danger'>아이디가 없어요!</div>");
								$("#user_id").select();
							}
						}
					});
		}
		$("#user_id").blur(function(){
			var id_check = /^\w{5,20}$/;
			var user_id = $(this).val();
			if(!id_check.test(user_id)){
				$("#idAlertBox").html("<small class='id1'>5~20자의 영문 소문자, 숫자와 특수기호 '_'만 사용 가능합니다.</small>").css("color", "darkred");
			}else{
				$.ajax({
					url : "/nado/empich",
					type : "post",
					data : {id : user_id},
					success : function(result){
						if(result >0){
							$("#idAlertBox").html("<small>이미 사용중인 아이디입니다.</small>").css("color", "darkred");							
						}else{					
							$("#idAlertBox").html("<small>아이디 생성 성공!</small>").css("color", "green");
						}
					}
				});
			}	
		}); //아이디 확인 끝
		
		//비밀번호 검사
		$("#user_pwd2").keyup(function(){
			var user_pwd = $("#user_pwd").val();
			if(user_pwd != $(this).val()){
				$("#pwdComfirm").html("<small>비밀번호가 다릅니다!</small>").css("color", "darkred");
			}else{
				$("#pwdComfirm").html("<small>비밀번호 일치!</small>").css("color", "green");
			}
		});
		
		$.validator.addMethod("name_check", function(value, element) {
			//이름 정규표현식
			var name_ch = /^[가-힣]{2,10}$/;

			if (name_ch.test(value))
				return true;
			else 
				return false;		
		});
		
		$.validator.addMethod("emp_no_check", function(value, element) {
			//주민등록번호 정규표현식
			var emp_no_ch = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))[1-4][0-9]{6}$/;

			if (emp_no_ch.test(value))
				return true;
			else 
				return false;		
		});
		
		$.validator.addMethod("pwd_check", function(value, element) {
			//암호 정규표현
			var pwd_ch = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

			if (pwd_ch.test(value))
				return true;
			else 
				return false;		
		});
		
		$.validator.addMethod("phone_check", function(value, element) {
			//핸드폰 정규표현
			var phone_ch = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;

			if (phone_ch.test(value))
				return true;
			else 
				return false;		
		});

		$("#enrollForm").validate({
			rules : {
				emp_name : {
					required : true,
					name_check : true
				},
				user_id : {
					required : true	
				},
				user_pwd : {
					required : true,
					pwd_check : true
				},
				phone : {
					required : true,
					digits : true,
					phone_check : true
				},
				email : {
					required : true,
					email : true
				},
				emp_no : {
					required : true,
					emp_no_check : true
				},
				marriage : {
					required : true
				},
				address : {
					required : true
				},
				detailAddress : {
					required : true
				}
			},
			messages : {
				emp_name : {
					required : "이름을 입력하세요.",
					name_check : "2~10자 이내로 입력하세요."
				},
				user_id : {
					required : "필수 입력 항목입니다."	
				},
				user_pwd : {
					required : "비밀번호를 입력하세요.",
					pwd_check : "특수문자/문자/숫자 포함 8~15자리 이내로 작성하셔야합니다."
				},
				phone : {
					required : "핸드폰 번호를 입력하세요.",
					phone_check : "입력양식을 확인하세요."
				},
				email : {
					required : "이메일을 입력하세요."
				},
				emp_no : {
					required : "주민등록 번호를 입력하세요",
					emp_no_check : "잘못입력하셨습니다."
				},
				marriage : {
					required : "결혼여부를 체크하세요."
				},
				address : {
					required : "우편번호 찾기를 클릭하세요."
				},
				detailAddress : {
					required : "상세주소를 입력하세요."
				}
			},
				submitHandler : employeeForm
		})
		
		function employeeForm(){
			var data = $("#enrollForm").serialize();
			$.ajax({
				url : "/nado/emper",
				type : "post",
				data : data,
				success : function(result){
					if(result > 0){
						location.href="/nado/index.jsp";
					}else{
						alert("회원가입실패");
					}
				}
					
			});
		}

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}
