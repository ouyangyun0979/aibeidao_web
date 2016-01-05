var REG_MAIL = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
var REG_MPHONE = /^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/;
var REG_PASSWORD = /^(\w){6,20}$/;
var REG_USERNAME = /^[\s\u4e00-\u9fa5_a-zA-Z0-9_]{1,20}$/;
var REG_USERNAME1 = /^[\sa-zA-Z0-9_]{1,20}$/;
var REG_ADDRESSNAME = /^[\s\u4e00-\u9fa5_a-zA-Z0-9_]{2,25}$/;
var REG_PHONE_NUM = /^([0-9]){11}$/;
var REG_NUMBER=/^[0-9]{1,10}(.[0-9]{1,3})?$/;
var REG_IDCARD=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
var REG_INTNUMBER=/^[0-9]{1,10}?$/;
var REG_VERSIONNO=/^\d+(\.\d+)?(\.\d+)?$/;
var REG_BANK_ACCOUNT=/^\d+$/;
var basePath=$("base").attr("href");

$(function(){
	//大图轮播
	$('.slide-banners').owlCarousel({
		autoplay:true,
		loop:true,
		margin:0,
		dots: true,
		nav: true,
		autoplayTimeout:5000,
		smartSpeed:500,
		lazyLoad:true,
		items:1,
		mouseDrag:true,
		slideBy:1
	});	
	//大图轮播
	$('.g-slide').owlCarousel({
		autoplay:true,
		loop:true,
		margin:0,
		dots: true,
		nav: false,
		autoplayTimeout:5000,
		smartSpeed:500,
		lazyLoad:true,
		items:1,
		mouseDrag:true,
		slideBy:1
	});	
	//品牌轮播
	$('.brand-slide').owlCarousel({
		autoplay:true,
		loop:true,
		margin:0,
		dots: false,
		nav: false,
		autoplayTimeout:3000,
		smartSpeed:500,
		lazyLoad:true,
		items:8,
		mouseDrag:true,
		slideBy:1
	});	
});
//顶部左侧菜单
silderbar("#ul-cont li", "#menuCon .sub-pannel");
/*
 * 鍒ゆ柇IE鐗堟湰鏂规硶
 * return   myBrowser()=="IE7" myBrowser()=="IE8"
 *
 */
function myBrowser(ie) {
		var userAgent = navigator.userAgent;
		var isOpera = userAgent.indexOf("Opera") > -1; 
		var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; 
		var isFF = userAgent.indexOf("Firefox") > -1; 
		var isSafari = userAgent.indexOf("Safari") > -1; 

		if (isIE) {
			var IE5 = IE55 = IE6 = IE7 = IE8 = false;
			var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
			reIE.test(userAgent);
			var fIEVersion = parseFloat(RegExp["$1"]);

			IE55 = fIEVersion == 5.5;
			IE6 = fIEVersion == 6.0;
			IE7 = fIEVersion == 7.0;
			IE8 = fIEVersion == 8.0;
			IE9 = fIEVersion == 9.0;
			if (ie) {
				return "IE"
			}
			if (IE55) {
				return "IE55";
			}
			if (IE6) {
				return "IE6";
			}
			if (IE7) {
				return "IE7";
			}
			if (IE8) {
				return "IE8";
			}
			if (IE9) {
				return "IE9";
			}
		} //isIE end

		if (isFF) {
			return "FF";
		}
		if (isOpera) {
			return "Opera";
		}

	} //myBrowser() end
//if IE8 IE7 
function iFie(json) {
	var ie = myBrowser()
	if (ie == "IE7" || ie == "IE8") {
		if (ie == "IE8" && json["ie8"]) {
			return json["ie8"]();
		}
		if (ie == "IE7" && json["ie7"]) {
			return json["ie7"]();
		}
		if (json["ie78"]) {
			return json["ie78"]();
		}
	}
}

//头部左侧菜单效果
function silderbar(obj, box) {
	navOpen(obj, box);
	$(obj).hover(barfn1, barfn2);
	var fn1time, fn2time, ietest = false

	function barfn1(i) {
		var This = i ? $(obj).eq($(this).index()) : this;
		var px1, px2;
		if ($(box).eq($(this).index()).css("display") != "none") {
			clearTimeout(fn2time);
			px1 = 47, px2 = 47, ietest = true;

		} else {
			px1 = 39, px2 = 47;
		}
		fn1time = setTimeout(function() {
			$(box).eq($(This).index()).show().stop()
				.animate({
					opacity: 0.96,
					filter: "alpha(opacity=96)",
					left: "180px"
				}, 200);
			$(obj).removeClass("nav-selected nav-hover");
			$(This).addClass("nav-selected nav-hover");
			IE($(This), px1, px2, ietest);
		}, 5)
	}

	function barfn2(i) {
		var This = i ? $(obj).eq($(this).index()) : this;
		fn2time = setTimeout(function() {
			$(This).removeClass("nav-hover");
			$(box).eq($(This).index()).stop()
				.animate({
					opacity: 0.5,
					filter: "alpha(opacity=50)",
					left: "170px"
				}, 0, function() {
					$(this).hide();
				});
			IE($(This), 47, 39);
		}, 5)
	}
	$(box).hover(function() {
		clearTimeout(fn2time)
	}, barfn2)

	function IE(t, px1, px2, ietest) {
		if (myBrowser(1)) {
			t.find("i").css("width", px1).stop().animate({
				width: px2
			}, 100);
		}
		iFie({
			ie78: function() {
				px1 == 39 || ietest ? t.find("span").addClass("ie") : t.find("span").removeClass("ie")
			}
		})
	}
}

function navOpen(obj, box) {
	var opentime1, opentime2;
	if ($(".navMain").attr("stop") == "true") {
		$(".navMain .open-m").hover(function() {
			openfn1(obj)
		}, function() {
			openfn2(obj)
		});
		$(box).hover(function() {
			openfn1(obj)
		}, function() {
			openfn2(obj)
		});
	}

	function openfn1(obj) {
		$(obj).parent().show().stop().animate({
			height: 480
		}, 150);
		$(".navMain .open-menu-btn span").removeClass().addClass("up");
	}

	function openfn2(obj) {
		$(obj).parent().stop().animate({
			height: 0
		}, 150, function() {
			$(this).hide();
			$(".navMain .open-menu-btn span").removeClass().addClass("down");
		});
	}
}

//让弹出窗口显示在body中央
function showPopbox(popbox_id){
	var popbox = $('#' + popbox_id);
	//return false;
	var new_left = ($(document).width() - popbox.width())/2;
	var new_top = $(document).scrollTop() + $(window).height()/2 - popbox.height()/2 ;
	popbox.css({"top": new_top + 'px'});
	popbox.css({"margin-left":-popbox.width()/2+'px',"left":"50%"});
	if($("#cover").length <= 0){
		$("body").append('<div class="cover" id="cover" style="display:none;"><div class="cover-main"></div></div>');
	}
	setTimeout(function(){
		popbox.css({"display":"block","opacity":"0","top":new_top-60+"px"}).animate({"opacity":"1","top":new_top+"px"},200);
		$("#cover").fadeIn(200);
	},100);
	
	
}


function closePopbox(popbox_id){
	var popbox = $('#' + popbox_id);
	var new_top = $(document).scrollTop() + $(window).height()/2 - popbox.height()/2 ;
	popbox.animate({"opacity":"0","top":new_top-60+"px"},200,function(){
		popbox.css({"display":"none"});
		
	});
	if($("#cover").length > 0){
		$("#cover").animate({"opacity":"0"},200,function(){
			$("#cover").remove();
			
		});
	}
	
}
