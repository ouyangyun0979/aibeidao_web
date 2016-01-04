<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>U活 | 移动应用基本接口</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, minimal-ui" />
<link rel="stylesheet" type="text/css" href="css/swiper.min.css">
</head> 
<body>
	<div style="height: 300px;" class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><img src="images/web/1.jpg" width="100%" /></div>
            <div class="swiper-slide"><img src="images/web/2.jpg" width="100%" /></div>
            <div class="swiper-slide"><img src="images/web/3.jpg" width="100%" /></div>
            <div class="swiper-slide"><img src="images/web/4.jpg" width="100%" /></div>
            <div class="swiper-slide"><img src="images/web/5.jpg" width="100%" /></div>
            <div class="swiper-slide"><img src="images/web/4.jpg" width="100%" /></div>
            <div class="swiper-slide"><img src="images/web/3.jpg" width="100%" /></div>
            <div class="swiper-slide"><img src="images/web/5.jpg" width="100%" /></div>
            <div class="swiper-slide"><img src="images/web/1.jpg" width="100%" /></div>
            <div class="swiper-slide"><img src="images/web/2.jpg" width="100%" /></div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
        <!-- Add Arrows -->
        <div class="swiper-button-next"></div>
        <div class="swiper-button-prev"></div>
    </div>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/swiper.min.js"></script>
<script type="text/javascript">
var swiper = new Swiper('.swiper-container', {
    pagination: '.swiper-pagination',
    nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',
    paginationClickable: true,
    spaceBetween: 20,
    centeredSlides: true,
    autoplay: 2500,
    autoplayDisableOnInteraction: false
});
</script>
</body>
</html>