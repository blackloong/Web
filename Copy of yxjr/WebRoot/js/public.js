$(document).ready(function(){

	// top-account-hover
	$(".top-account").hover(function(){
		$(this).addClass("top-account-hover")
		$(".account-down").stop(false,true).slideDown(300)
	},function(){
		$(this).removeClass("top-account-hover")
		$(".account-down").stop(true,false).slideUp(100)
	});

	// banner-nav-cont
	$(".banner-tab li").hover(function(){
	 	$(".cont-list").show()
	 	$(this).addClass("tabcss").siblings().removeClass("tabcss");
	  	$(".cont-list .cont").eq($(this).index()).stop(true,false).fadeIn(400).siblings().hide();
	 },function(){
	 	$(".cont-list").hide()
	 });
	  $(".cont-list").hover(function(){
	 	$(this).show();

	 },function(){
	 	$(this).hide();
	 	$(".banner-tab li").removeClass("tabcss");
	 })

	 $(".banner-nav-cont").hover(function(){
		$(this).addClass("tabcss").siblings()
	 },function(){
	 	$(".banner-tab li").removeClass("tabcss");
	 	$(".cont-list").hide();
	 })

	// wx-ewm-cion
	$(".top-wx .weix").hover(function(){
		$(this).find(".wx-ewm-cion").stop(false,true).fadeIn(400);
	},function(){
		$(this).find(".wx-ewm-cion").stop(true,false).fadeOut(200);
	});
	$(".ft-wx").hover(function(){
		$(this).find(".wx-ewm-cion").stop(false,true).fadeIn(400);
	},function(){
		$(this).find(".wx-ewm-cion").stop(true,false).fadeOut(200);
	});

	// index-cont-tab
	$(".index-tab li").hover(function(){
		$(this).addClass("current").siblings().removeClass("current")
		var _index=$(this);
		$(this).parents(".index-wrap-l").next(".index-wrap-r").children().eq(_index.index()).stop(false,true).show().siblings().stop(true,false).hide();
	});
	$(".shop-table tr:odd").css("background","#fbfbfb")
	$(".shop-table tr").hover(function(){
		$(this).css("background","#ededed")
		$(".shop-table tr:first-child").css("background","")
		$(this).find(".orange").css({"background":"#f18d00","color":"white"})
	},function(){
		$(this).css("background","")
		$(".shop-table tr:odd").css("background","#fbfbfb")
		$(this).find(".orange").css({"background":"","color":"#f18d00"})
	});

	// 股民在线
	$(".investor-list:last-child").css("border-right","0");

	// 天使众筹
	$(".index-wrap-r-4 .index-wrap-cont a:last-child").css("border-right","0");

	// 交易实况
	// $(".index-deal-con:gt(0)").hide();
	// $(".index-deal-con:eq(0)").show();
	$(".index-deal-tab li").hover(function(){
		$(this).addClass("current").siblings().removeClass("current");
		$(".index-deal-con").eq($(this).index()).show().siblings().hide();
		var aaaa=$(".index-deal-con").eq($(this).index());
		// alert(aaaa.index())
		 // if(aaaa==aaaa.index()){}
	},function(){
		// clearInterval(scrollTimer);
	});

	/*交易实况列表滚动*/
	// alert("测试")
	var $dcthis = $("#index-deal-con");
	var scrollTimer;
	var dcHeight=$dcthis.height();
	var dcliHeight=$("#index-deal-con li").size()*$("#index-deal-con li:first").height();
	// alert(dcliHeight)
	if(dcliHeight>dcHeight){
		$dcthis.hover(function() {
	clearInterval(scrollTimer);
	}, function() {
	scrollTimer = setInterval(function() {
	scrollNews($dcthis);
	}, 2000);
	}).trigger("mouseleave");
	}else{
		clearInterval(scrollTimer);
	}

	function scrollNews(obj) {
	var $self = $("#index-deal-con").find("ul");
	var lineHeight = $self.find("li:first").height();
	$self.animate({
	"marginTop": -lineHeight + "px"
	}, 600, function() {
	$self.css({
	marginTop: 0
	}).find("li:first").appendTo($self);
	})
	}

	var $dcthis2 = $("#index-deal-con2");
	var scrollTimer2;
	var dcHeight=$dcthis2.height();
	var dcliHeight=$("#index-deal-con2 li").size()*$("#index-deal-con2 li:first").height();
	// alert(dcliHeight)
	if(dcliHeight>dcHeight){
		$dcthis2.hover(function() {
	clearInterval(scrollTimer2);
	}, function() {
	scrollTimer2 = setInterval(function() {
	scrollNews2($dcthis2);
	}, 2000);
	}).trigger("mouseleave");
	}else{
		clearInterval(scrollTimer2);
	}

	function scrollNews2(obj) {
	var $self2 = $("#index-deal-con2").find("ul");
	var lineHeight = $self2.find("li:first").height();
	$self2.animate({
	"marginTop": -lineHeight + "px"
	}, 600, function() {
	$self2.css({
	marginTop: 0
	}).find("li:first").appendTo($self2);
	})
	}

	var $dcthis3 = $("#index-deal-con3");
	var scrollTimer3;
	var dcHeight=$dcthis3.height();
	var dcliHeight=$("#index-deal-con3 li").size()*$("#index-deal-con3 li:first").height();
	// alert(dcliHeight)
	if(dcliHeight>dcHeight){
		$dcthis3.hover(function() {
	clearInterval(scrollTimer3);
	}, function() {
	scrollTimer3 = setInterval(function() {
	scrollNews3($dcthis3);
	}, 2000);
	}).trigger("mouseleave");
	}else{
		clearInterval(scrollTimer3);
	}

	function scrollNews3(obj) {
	var $self3 = $("#index-deal-con3").find("ul");
	var lineHeight = $self3.find("li:first").height();
	$self3.animate({
	"marginTop": -lineHeight + "px"
	}, 600, function() {
	$self3.css({
	marginTop: 0
	}).find("li:first").appendTo($self3);
	})
	}


	var $dcthis4 = $("#index-deal-con4");
	var scrollTimer4;
	var dcHeight=$dcthis4.height();
	var dcliHeight=$("#index-deal-con4 li").size()*$("#index-deal-con4 li:first").height();
	// alert(dcliHeight)
	if(dcliHeight>dcHeight){
		$dcthis4.hover(function() {
	clearInterval(scrollTimer4);
	}, function() {
	scrollTimer4 = setInterval(function() {
	scrollNews4($dcthis4);
	}, 2000);
	}).trigger("mouseleave");
	}else{
		clearInterval(scrollTimer4);
	}

	function scrollNews4(obj) {
	var $self4 = $("#index-deal-con4").find("ul");
	var lineHeight = $self4.find("li:first").height();
	$self4.animate({
	"marginTop": -lineHeight + "px"
	}, 600, function() {
	$self4.css({
	marginTop: 0
	}).find("li:first").appendTo($self4);
	})
	}

	// 最新公告/新闻动态
	$(".index-news-title li").hover(function(){
		$(this).addClass("current").siblings().removeClass("current")
		$(".index-news-cont ul").eq($(this).index()).show().siblings().hide()
	});

	// 友情链接
	$(".index-link li:gt(20)").hide();
	$(".index-link li:last()").show()
	$(".index-link-down").click(function(){
		$(this).hide();
		$(".index-link li").show();
		$(".index-link-up").show();
	});
	$(".index-link-up").click(function(){
		$(this).hide();
		$(".index-link li:gt(20)").hide();
		$(".index-link-down").show();
		$(".index-link li:last()").css("background","none").show()
	});

	// p2p js
	$(".nav-down").hover(function(){
		$(this).children("a").addClass("current");
		$(this).children(".nav-down-wrap").stop(false,true).slideDown(400)
	},function(){
		$(this).children(".nav-down-wrap").hide()
		$(this).children("a").removeClass("current");
	});

	$(".account-sub-nav").click(function(){
		$(this).addClass("current").children("dd").slideDown();

		$(this).siblings().removeClass("current").children("dd").slideUp();
	});
//	$(".account-sub-nav:eq(0)").addClass("current").children("dd").show();


	$(".alter").click(function(){
		var safety_mod_1= $(this).parents(".safety-bag").next(".safety-mod").children(".safety-mod-1");
		$(this).parents(".safety-bag").siblings(".safety-mod").children().slideUp(150);
		if(safety_mod_1.is(":hidden")){
		safety_mod_1.slideDown(400)
	}else{safety_mod_1.slideUp(150)}
	})
	$(".find").click(function(){
	var safety_mod_2= $(this).parents(".safety-bag").next(".safety-mod").children(".safety-mod-2");
	$(this).parents(".safety-bag").siblings(".safety-mod").children().slideUp(150);
	if(safety_mod_2.is(":hidden")){
		safety_mod_2.slideDown(400)
		}else{safety_mod_2.slideUp(150)}
	})

	// $(".popup").left()=$(".popup").outerWidth()/2;
	// var top = $('.popup').position().top;
	// var top=$(".popup").outerHeight()/2;
	// var px = $('.popup').css('marginTop')-top;
	// $(".popup").css("margin-top"-"top")
	// alert(top)

	$(".close").click(function(){
		$(".popup").fadeOut(300)
		$(".mask").fadeOut(300)
	});
	$(".binding-btn,.loan-calc-icon,.head-btn").click(function(){
			$(".popup").fadeIn(300)
			$(".mask").fadeIn(300)
		});

	// tab-cont
	$(".tab-cont li").hover(function(){
		$(this).addClass("current").siblings().removeClass("current");
		$(".cont-tab").eq($(this).index()).show().siblings().hide();
	});
		$(".infor-table tr:gt(0)").css("border-top","1px solid #ebebeb")
		$(".infor-email").click(function(){
			var infor_tr=$(this).parents("tr").next(".infor-tr");
			$(this).parents("tr").next(".infor-tr").siblings(".infor-tr").hide();
			if(infor_tr.is(":hidden")){
				infor_tr.show().css("border-top","0").addClass("infor-tr-2");
			}else{
				infor_tr.hide()
			}
		});

		$(".claim-table tr:odd,.loan-calc-tible tr:odd").css("background","#f9f9f9")
		$(".nwbie ul li:last,.type-area dl:last").css("border-bottom","0")
		$(".borrow-money-list dl").hover(function(){
			$(this).css("border","1px solid #ca0202").siblings().css("border","1px solid #e7e7e7");
			$(this).find(".red-radius-btn").css("background","#ca0202")
		},function(){
			$(this).css("border","1px solid #e7e7e7");
			$(this).find(".red-radius-btn").css("background","")
		})
		$(".money-product-ul").hover(function(){
				$(this).css("border","1px solid #fece8a").siblings().css("border","")
		},function(){
			$(this).css("border","")
		});

		$(".money-table-list tr").css("border-bottom","solid 1px #ebebeb");
		$(".money-table-list tr:first-child").css("border-bottom","");
		$(".mg-seek-ul li:first-child").css("border-top","0");

		// 查询
	$(".mg-seek-up").hide();
		$(".mg-seek-ul").find(".mg-seek-btn2 a:gt(8)").hide();
		$(".mg-seek-down").click(function(){
			$(this).hide().siblings(".mg-seek-up").show();
			$(this).parents(".mg-seek-ul").find(".mg-seek-btn2 a").show();
		});
		$(".mg-seek-up").click(function(){
			$(this).hide().siblings(".mg-seek-down").show();
			$(this).parents(".mg-seek-ul").find(".mg-seek-btn2 a:gt(8)").hide();
			$
		});

		// head-pic
		$(".head-pic").hover(function(){
				$(".head-btn").fadeIn(300);
		},function(){
			$(".head-btn").fadeOut(300);
		});

		// placement-l-title ul li:hover
		$(".placement-tab li").click(function(){
				$(this).addClass("cur").siblings().removeClass("cur");
				_this=$(this);
				$(this).parents(".placement-title").next().find(".placement-tab-cont").eq(_this.index()).show().siblings().hide();
		})

	$(".type-more-btn").click(function(){
		$(this).nextAll(".type-area-child").slideDown(400);
	});
	$(".js-up-btn").click(function(){
		$(this).parents(".type-area-child").slideUp(300);
	});
	$(".t-a-tab li").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
		$(this).parent(".t-a-tab").next(".t-a-cont").find(".t-a-deta").eq($(this).index()).show().siblings(".t-a-deta").hide();
	});
	$(".trust-h-prod").find("ul li:odd").css("background","#f9f9f9")
});
