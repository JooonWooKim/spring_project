let index = {
	init: function(){
		$("#btn-save").on("click",()=>{	//function(){}, ()=>{} this를 바인딩하기 위해서
			this.save();
		});
		$("#btn-login").on("click",()=>{
			this.login();
		});
	},

	save: function(){
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
			type:"POST",
			url: "/blog/api/user",
			data: JSON.stringify(data),	//http body데이터
			contentType: "application/json; charset=utf-8",	
			dataType: "json"
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		//ajax 통신을 이용해서 3개의 객체를 json으로 변경하여 insert요청
	},
	
	login: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}
		
		//console.log(data);
		
		//ajax호출시 default가 비동기 호출
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data),	//http body데이터
			contentType: "application/json; charset=utf-8",	
			dataType: "json"
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
			location.href = "/blog";
		}).fail(function(error){
			alert("로그인 실패");
			//alert(JSON.stringify(error));
		});
		//ajax 통신을 이용해서 3개의 객체를 json으로 변경하여 insert요청
	}	
}

index.init();