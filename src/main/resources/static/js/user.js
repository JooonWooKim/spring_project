let index = {
	init: function() {
		$("#btn-save").on("click", () => {	//function(){}, ()=>{} this를 바인딩하기 위해서
			this.save();
		});
		$("#btn-update").on("click", () => {	//function(){}, ()=>{} this를 바인딩하기 위해서
			this.update();
		});
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		//console.log(data);

		//ajax호출시 default가 비동기 호출
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),	//http body데이터
			contentType: "application/json; charset=utf-8",
			//dataType: "json"
		}).done(function(resp) {
			if (resp.status == 400) {
				alert("회원가입에 실패하었습니다.");

				if (resp.data.hasOwnProperty('valid_username')) {
					$('#valid_username').text(resp.data.valid_username);
					$('#valid_username').css('color', 'red');
				}
				else $('#valid_username').text('');

				if (resp.data.hasOwnProperty('valid_password')) {
					$('#valid_password').text(resp.data.valid_password);
					$('#valid_password').css('color', 'red');
				}
				else $('#valid_password').text('');

				if (resp.data.hasOwnProperty('valid_email')) {
					$('#valid_email').text(resp.data.valid_email);
					$('#valid_email').css('color', 'red');
				}
				else $('#valid_email').text('');
			}
			else {
				alert("회원가입이 완료되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		//ajax 통신을 이용해서 3개의 객체를 json으로 변경하여 insert요청
	},

	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		if(!data.username|| data.username.trim() === "" || !data.password || data.password.trim() === "") {            
			alert("공백 또는 입력하지 않은 부분이 있습니다.");            
			return false;        
		} else if(!/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/.test(data.password)) {            
			alert("비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");            
			$('#password').focus();            
			return false;        
		}

		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),	//http body데이터
			contentType: "application/json; charset=utf-8",
			//dataType: "json"
		}).done(function(resp) {
			console.log(resp.status);
			if(resp.status === 500){
				alert("회원수정이 실패하였습니다.");
				return false;
			}
			alert("회원수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		//ajax 통신을 이용해서 3개의 객체를 json으로 변경하여 insert요청
	}
}

index.init();