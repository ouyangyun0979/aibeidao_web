<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>U活 | 移动应用基本接口</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, minimal-ui" />
<link rel="stylesheet" href="<%=basePath%>css/style.css">
<style type="text/css">
.ali-main-product-other-box{
	width: 960px; 
    margin:0 auto;
}
.ali-main-product-other-cell{
  display: block;
  padding: 0 10px 0 10px;
  height: auto;
  border: 1px solid #e5e5e5;
  text-decoration: none;
  font-size: 13px;
  color: #666;
  cursor: pointer;
  margin-left: 10px;
  text-align: center;
}
.ali-main-product-other-cell:hover {
  border: 1px solid #09c;
  color: #00a2ca;
}
.ali-main-num{ 
  float: left;
  width: 42%;
} 
.ali-main-product-other-cell h4 {
  font-size: 28px;
  margin-top: 40px;
  font-weight: bold;
}
</style>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript">
	/*$(function() {
		$.ajax({
			type : "POST",
			url : "api/user/save",
			dataType : "json",
			async : false,
			cache : false,
			data : {
				test : "testyy"
			},
			success : function(data, textStatus, jqXHR) {
				//alert(JSON.stringify(data));
				//$("#content").html(JSON.stringify(data));
			}
		});
	})*/
</script>
</head>
<body>
	<div class="ali-main-product-other-box">
			<a href="jsp/api.jsp" class="ali-main-num ali-main-product-other-cell">
				<h4>Api1.0</h4>
				<p>常规下单接单支付接口。</p> 
			</a>
			<a href="jsp/api2.0.jsp" class="ali-main-num ali-main-product-other-cell">
				<h4>Api2.0</h4>
				<p>鲜花和活动下单服务接口。</p>
			</a>
			<div style="clear: both;"></div>
		</div>
		<div class="preview-inner" id="content">
			<h1>一 错误码定义</h1>
			<table>
				<thead>
					<tr>
						<th width="20%">返回码</th>
						<th width="40%">错误信息</th>
						<th width="40%">说明</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="20%">0</td>
						<td width="40%">成功</td>
						<td width="40%"></td>
					</tr>
					<tr>
						<td>1</td>
						<td>常规错误</td>
						<td></td>
					</tr>
					<tr>
						<td>200</td>
						<td>正常逻辑提示</td>
						<td></td>
					</tr>
					<tr>
						<td>101</td>
						<td>未授权</td>
						<td>需登录</td>
					</tr>
					<tr>
						<td>102</td>
						<td>没有操作权限</td>
						<td>未登录或者登录已经超时，需要重新登录</td>
					</tr>
					<tr>
						<td>103</td>
						<td>用户不存在</td>
						<td></td>
					</tr>
					<tr>
						<td>104</td>
						<td>时间戳不在有效范围</td>
						<td></td>
					</tr>
					<tr>
						<td>105</td>
						<td>登录验证失败</td>
						<td></td>
					</tr>
					<tr>
						<td>110</td>
						<td>登录超时</td>
						<td></td>
					</tr>
					<tr>
						<td>201</td>
						<td>缺少必填参数</td>
						<td></td>
					</tr>
					<tr>
						<td>202</td>
						<td>参数格式错误</td>
						<td></td>
					</tr>
					<tr>
						<td>203</td>
						<td>对象不存在</td>
						<td></td>
					</tr>
					<tr>
						<td>204</td>
						<td>参数取值错误</td>
						<td></td>
					</tr>
					<tr>
						<td>301</td>
						<td>角色不正确</td>
						<td></td>
					</tr>
					<tr>
						<td>302</td>
						<td>订单状态不符合当前操作</td>
						<td></td>
					</tr>
					<tr>
						<td>300</td>
						<td>订单不存在</td>
						<td></td>
					</tr>
					<tr>
						<td>303</td>
						<td>订单操作人员不一致</td>
						<td></td>
					</tr>
					<tr>
						<td>503</td>
						<td>该时间段已经预约满，请预约其他时间.</td>
						<td></td>
					</tr>
					<tr>
						<td>999</td>
						<td>其它错误</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<p>
				<strong>范例</strong>
			</p>
			<pre>{
  "success": false,                            // 成功标志
  "error_code": 1,                             // 错误码
  "error_desc": "error description",           // 错误描述 
  "error_url": "/api/account/login",           // 错误URL 
}
		</pre>
			<h1>API概述</h1>
			<h1>请求格式</h1>
			<h1>GET</h1>
			<p>GET请求时参数必须放在URL中，如：</p>
			<pre>&gt; GET /api/exampledata/?device=1 HTTP/1.1
&gt; Host: 203.195.166.239:8888
&gt; Accept: */*
		</pre>
			<h1>POST</h1>
			<p>POST命令参数可放在URL，也可放在BODY中</p>
			<pre>&gt; POST /api/account/login HTTP/1.1
&gt; Host: 203.195.166.239:8888
&gt; Accept: */*
&gt; Content-Length: 29
&gt; Content-Type: application/x-www-form-urlencoded
&gt; key1=value1&amp;key2=value2
		</pre>
			<h1>分页参数</h1>
			<p>当返回对象列表时，可以对列表进行分页</p>
			<table>
				<tbody>
					<tr>
						<th>名称</th>
						<th>必须</th>
						<th>类型</th>
						<th>说明</th>
					</tr>
					<tr>
						<td>page</td>
						<td>否</td>
						<td>int</td>
						<td>页数1~N，缺省1</td>
					</tr>
					<tr>
						<td>rows_per_page</td>
						<td>否</td>
						<td>int</td>
						<td>每页行数，缺省20</td>
					</tr>
				</tbody>
			</table>
			<h2>对象嵌套参数</h2>
			<p>返回对象中有引用类型（Reference）的属性时，可以将被引用的对象嵌套到返回中。</p>
			<ul>
				<li>原属性保留不主为，依然存在，</li>
				<li>新增一个以 $
					开头的属性，形如"$[attr_name]",其中attr_name为原属性名，其内容是完整的json对象。</li>
			</ul>
			<table>
				<tbody>
					<tr>
						<th>名称</th>
						<th>必须</th>
						<th>类型</th>
						<th>说明</th>
					</tr>
					<tr>
						<td>depth</td>
						<td>否</td>
						<td>int</td>
						<td>嵌套层数(1-4)，缺省1</td>
					</tr>
				</tbody>
			</table>
			<h1>响应格式</h1>
			<p>返回内容统一是json格式。</p>
			<h1>返回单个对象</h1>
			<strong>范例</strong>
			<pre>{
  "success": true,                            // 成功标志
  "obj_name": "obj name",                     // 对象名
  "obj": {"key1":"val1", "key2":"val2", ...}  // 返回的对象内容
}
		</pre>
			<h1>返回多个对象</h1>
			<strong>范例</strong>
			<pre>{
  "success": true,                            // 成功标志
  "obj_name": "obj name",                     // 对象名
  "objs": [{obj1}, {obj2}, ...]               // 对象列表
  "page": {                                   // 分页信息
      "rows_per_page": intval                 // 每页行数
      "total": intval,                        // 总数
      "page_count": intval,                   // 分页数
      "page_current": intval,                 // 当前页号 
  }
}
		</pre>
			<h1>返回失败</h1>
			<p>
				<strong>范例</strong>
			</p>
			<pre>{ 
  "success": false,                            // 成功标志
  "error_code": 1,                             // 错误码
  "error_desc": "error description",           // 错误描述 
  "error_url": "/api/account/login",           // 错误URL 
}
		</pre>
		</div>
</body>
</html>