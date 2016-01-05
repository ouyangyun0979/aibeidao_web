<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>爱贝岛 | 登录</title>
	<link href="css/login.css" rel="stylesheet" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
</head>
<body>
	<div class="login-bg">
		<div class="logo"><a href="javascriprt:void(0);"><img src="images/logo_big.png" /></a></div>
		<div class="login-c">
        	<!--登陆-->
            <div class="login-con" id="login">
                <form id="loginForm" action="app/login/submit" method="post">
                    <div class="login-form" id="login-form">
                        <div class="row">
                            <h3 class="title">登录</h3>
                            <span id="errorLoginNtc" class="tips error"></span>
                        </div>
                        <div class="row">
                            <label class="placeholder">邮箱/手机号码</label><input type="text" name="login_username" id="login_username" class="userTxt" value="" /><i class="icon-user"></i>
                        </div>
                        <div class="row">
                            <label class="placeholder">密码</label><input type="password" id="login_password" name="login_password" class="pwdTxt" value="" /><i class="icon-pwd"></i>
                        </div>
                        <div class="row">
                            <input type="button" class="subBtn" onclick="submitLogin();" value="立即登录" />
                        </div>
                        <div class="row">
                            <a href="javascript:void(0);" onclick="showRegist()" class="fr">免费注册</a>
                            <a href="javascriprt:void(0);" class="fl">忘记密码？</a>
                        </div>
                    </div>
                </form>
                <div class="other-login">
                    <h3>其他方式</h3>
                    <ul>
                        <li><a href="#"><i class="i-qq"></i><span>QQ登录</span></a></li>
                        <li><a href="#"><i class="i-wx"></i><span>微信登录</span></a></li>
                        <li><a href="#"><i class="i-wb"></i><span>微博登录</span></a></li>
                    </ul>
                </div>
            </div>
            <!--注册-->
            <div class="login-con" id="regist" style="display:none;">
                <form id="registerForm" action="app/register/submit" method="post">
                    <div class="login-form" id="login-form">
                        <div class="row">
                            <h3 class="title">注册</h3>
                            <span id="errorNtc" class="tips error"></span>
                        </div>
                        <div class="row">
                            <label class="placeholder">用户名</label><input type="text" name="username" id="username" class="userTxt" value="" /><i class="icon-user"></i>
                        </div>
                        <div class="row">
                            <label class="placeholder">手机号</label><input type="text" id="phone" name="phone" class="pwdTxt" value="" /><i class="icon-phone"></i>
                        </div>
                        <div class="row">
                            <label class="placeholder">手机验证码</label><input type="text" id="verifycode" name="verifycode" class="codeTxt" value="" /><a href="javascript:void(0);" class="codeBtn">获取验证码</a>
                        </div>
                        <div class="row">
                            <label class="placeholder">密码</label><input type="password" id="password" name="password" class="pwdTxt" value="" /><i class="icon-pwd"></i>
                        </div>
                        <div class="row">
                            <label class="placeholder">确认密码</label><input type="password" id="repassword" class="pwdTxt" value="" /><i class="icon-pwd"></i>
                        </div>
                        <div class="row">
                            <input type="button" class="subBtn" onclick="registerSubmit();" value="立即注册" />
                        </div>
                        <div class="row">
                            <a href="javascript:void(0);" onclick="showLogin()" class="fr">立即登录</a>
                            <div class="checkbox active fl">同意<a href="javascriprt:void(0);">《淘吧用户协议》</a></div>
                        </div>
                    </div>
                </form>
            </div>
			<div class="foot-txt">copyright&copy;2015  爱贝岛  版权所有</div>
		</div>
	</div>
    <img src="images/demo/login_bg.jpg" class="bg-img" />
    <jsp:include page="js.jsp" />
    <script type="text/javascript" src="js/login.js"></script>
    <script type="text/javascript">
    	var errorMsg="${errorMsg}";
    	var errortype="${errortype}";
   		if(errortype=='1'){
   			showRegist();
   			if(errorMsg!=''){
   				$("#errorNtc").html(errorMsg).show();
   			}
   		}else{
   			showLogin();
   			if(errorMsg!=''){
   				$("#errorLoginNtc").html(errorMsg).show();
   			}
   		}
    </script>
</body>
</html>