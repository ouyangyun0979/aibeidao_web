$(function(){
		$(".userTxt,.pwdTxt,.codeTxt").focus(function(){
			var value = $.trim($(this).val());
				$(this).prev('.placeholder').hide();
		});
		$(".userTxt,.pwdTxt,.codeTxt").blur(function(){
			var value = $.trim($(this).val());
			if(value==""){
				$(this).prev('.placeholder').css('display','inline-block');
			}
		});
		$(".checkbox").click(function(){
			if($(this).hasClass("active")){
				$(this).removeClass("active");
			}else{
				$(this).addClass("active");
			}
		});
		$('#username').bind('input propertychange', function() {  
		    $(this).prev().hide();
		});  
		$('#password').bind('input propertychange', function() {  
		    $(this).prev().hide();
		});  
		
		if($('#username').val() != ''){
			$('#username').prev().hide();
		}
		if($('#password').val() != ''){
			$('#password').prev().hide();
		}
})

function showLogin(){
	$('#regist').hide();
	$('#login').show();
}
function showRegist(){
	$('#login').hide();
	$('#regist').show();
}

function registerSubmit(){
	var username=$("#username").val();
	var phone=$("#phone").val();
	var password=$("#password").val();
	var repassword=$("#repassword").val();
	//alert(password+":"+repassword);
	if(username==''){
		$("#errorNtc").html("用户名不能为空").show();
		return false;
	}
	if(phone==''){
		$("#errorNtc").html("手机号码不能为空").show();
		return false;
	}
	if(password==''){
		$("#errorNtc").html("密码不能为空").show();
		return false;
	}else if(password!=repassword){
		$("#errorNtc").html("两次密码不一致").show();
		return false;
	}
	$("#registerForm").submit();
}
function submitLogin(){
	var login_username=$("#login_username").val();
	var login_password=$("#login_password").val();
	if(login_username==''){
		$("#errorLoginNtc").html("用户名不能为空").show();
		return false;
	}
	if(login_password==''){
		$("#errorLoginNtc").html("密码不能为空").show();
		return false;
	}
	$("#loginForm").submit();
}

