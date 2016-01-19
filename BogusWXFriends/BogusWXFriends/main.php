<?PHP
$appid = 'wxde7a1c3c9b803601';
$appsecret = 'd4624c36b6795d1d99dcf0547af5443d';
require_once "wxsdk.php";
$wxsdk = new CWXSDK($appid, $appsecret);
$user_info = $wxsdk->GetUserInfo();
?>

<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="css/css.css">
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="js/jquery.min.js"></script>
	<script src="js/my_jssdk.js"></script>
    <title>我的明星朋友圈</title>
</head>
<body>
<!--<div id="actionSheet_wrap">
    <div class="weui_mask_transition" id="mask" style="display: none;"></div>
    <div class="weui_actionsheet" id="weui_actionsheet">
        <div class="weui_actionsheet_menu">
            <div class="weui_actionsheet_cell title">选择下面👇进入你的朋友圈</div>           
            <div class="weui_actionsheet_cell play_pyq" data-scene="1">我的明星朋友圈</div>
        </div>
        <div class="weui_actionsheet_action">
            <div class="weui_actionsheet_cell" id="actionsheet_cancel">取消</div>
        </div>
    </div>
</div>-->
<div style="margin:0 auto;display:none;">
    <img class="data-avt">
</div>
<header id="header">
    <img id="bg" src="img/bg.jpg">
    <p id="user-name" class="data-name"></p>
    <img id="avt" class="data-avt">
</header>
<div  id="main">
    <div id="list">
		<ul>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt data-avt">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">
                            <span class="data-name"></span>
                        </p>
                        <div class="post">
                            <p> 我来到，我看见，我征服! </p>
                            <p>
                               <img class="list-img" src="img/xk.jpg" />
                            </p>
                        </div>
                        <p class="time">刚刚 </p>
                        <img class="c-icon" src="img/c.png" />
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like">
                            <img src="img/l.png">
                                 Bruce Willis，刘烨，谢霆锋，黄晓明，胡歌，霍建华，Angelababy
                        </div>
                        <div class="cmt-list">
                            <p><span>刘烨:</span> 沙发 </p>
                            <p> <span>皮尔斯布鲁斯南:</span>OMG！</p>
                            <p> <span>王思聪:</span> 这……，我能玩起不？</p>
                            <p> <span>范冰冰:</span>  下次带我一起飞~<img class="bq" src="img/bq2.png"></p>
                            <p> <span>林志玲:</span> <img class="bq" src="img/bq2.png"></p>
                            <p> <span>倪妮:</span> <img class="bq" src="img/bq2.png"></p>
                            <p> <span>李晨</span>回复<span>范冰冰:</span> <img class="bq" src="img/bq3.png"></p>
						    <p> <span>NASA:</span>随时欢迎~</p>
						    <p> <span class="data-name"></span>回复<span>NASA:</span>下次带朋友一起去~</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="img/sz.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">
                            施建祥
                        </p>
                        <div class="post">
                            <p>
						        作为《敢死队4》的中方总制片人，对这部好莱坞大片将加大投资力度，
						        加紧融入中国元素，并挑选优秀的中方演员参演，中国梦是每一个中国人的使命，
						        希望更多的青年演员走向世界，走进好莱坞！如果你有“星梦”，
						        我就帮你“成真”！@<span class="data-name ad-link"></span>来试试吧，我很看好你，快扫码报名！
                            </p>
                            <img class="list-img" src="img/qr.jpg" />
                        </div>
                        <p class="time">2分钟前</p>
                        <img class="c-icon" src="img/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like">
                            <img src="img/l.png">
                            东虹桥张蕾，Bruce Willis，Adrien Brody，张艺谋，吴秀波，刘晓庆，崔永元，刘烨，甄子丹，黄晓明，Angelababy，范冰冰、谢霆锋，泰森，张晋，叶伟信，任达华，范伟，马苏，冯远征，宋承宪，熊黛林，谭耀文，黄百鸣，钟镇涛，李克勤，汪明荃，唐季礼，吕良伟，黄圣依
                        </div>
                        <div class="cmt-list">
                           <p><span class="data-name"></span> <span>:</span>多谢施主席，可以和史泰龙飙戏嘛？</p>
                           <p><span>施建祥</span>回复<span class="data-name"></span><span>:</span>年轻人只要有梦想，我就给你“星梦成真”的机会，你要是拿到冠军，让你和Stallone、Schwarzenegger一起搭档《敢死队4》。</p>
					       <p><span>Obama:</span>Mr施，上次来我家吃饭的时候，我提的带我女儿拍戏的那个事……</p>
					       <p><span>范冰冰:</span>施总太霸气，美国总统都来了……并且整个娱乐圈都来点赞……</p>
					       <p><span>泰森:</span>因为你让我走进中国，走进叶问，我认定你就是我的大哥！</p>
					       <p><span>谢霆锋:</span>施总霸气……</p>
					       <p><span>谢天华:</span>施总威武……</p>
					       <p><span>段奕宏:</span>膜拜……</p>
					       <p><span>刘强东:</span>我家奶茶妹妹您看能给安排下么……</p>
					       <p><span>许家印:</span>施总对足球感兴趣么……</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="img/zlt.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">
                            林志玲
                        </p>
					    <div class="post">
						    <p style="overflow: inherit;">
                              说好了陪人家走金马奖红毯，<img id="inline" src="img/bq4.png">你人在哪里？
                            </p>
                            <img class="list-img" src="img/zl.jpg">
                            <img class="data-avt list-img">
                        </div>
                        <p class="time">
                            2分钟前
                        </p>
                        <img class="c-icon" src="img/c.png" </div>
                    <div class="r">
                    </div>
                    <div class="cmt-wrap">
                        <div class="like">
                            <img src="img/l.png">
                           桂纶镁，邓超，张艾嘉，范伟，黄子佼，贾樟柯，林嘉欣，舒淇，陈赫
                        </div>
                        <div class="cmt-list">
						    <p><span>王大锤:</span>下次我陪你吧女神</p>
						    <p><span>王祖蓝:</span>下次我陪你吧女神</p>
						    <p><span>陈赫:</span>下次我陪你吧女神</p>
						    <p><span>黄海波:</span>等我回国我陪你吧女神</p>
						    <p><span class="data-name"></span><span>:</span>下次~</p>
						    <p><span>林志玲</span>回复<span class="data-name"></span><span>:</span>就酱说定哦~<img class="bq" src="img/bq5.png"></p>
						    <p><span>柳岩</span>回复<span class="data-name"></span><span>:</span>哼！</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="img/bbt.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">
                            范冰冰
                        </p>
					    <div class="post">
						    <p style="overflow: inherit;">
                              360°无死角美美哒~@<span class="data-name ad-link"></span><img id="inline" src="img/bq3.png">你人在哪里？
                            </p>
                            <img class="list-img" src="img/bb.jpg">
                        </div>
                        <p class="time">
                            2分钟前
                        </p>
                        <img class="c-icon" src="img/c.png" />
                    </div>
                    <div class="r">
                    </div>
                    <div class="cmt-wrap">
                        <div class="like">
                            <img src="img/l.png" />
                           谢霆锋，Bruce Willis，霍建华，泰森，鹿晗，宋承宪，陈伟霆，希安拉博夫，詹姆斯麦卡沃伊，黄晓明，Angelababy
                        </div>
                        <div class="cmt-list">
						    <p><span>刘烨:</span>沙发，城会玩啊</p>
						    <p><span>李晨:</span>我也有黑牛粉！<img class="bq" src="img/bq6.png"></p>
						    <p><span>甄子丹:</span>到时候带他们来看叶问3，我送票@<span class="data-name ad-link"></span></p>
						    <p><span class="data-name"></span><span>:</span>晨哥吃醋了</p>
						    <p><span>范冰冰</span>回复<span class="data-name"></span><span>:</span>他敢！让他背着陈赫跪指压板！</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="img/dq.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="ads">
                            推广
                            <img src="img/ads.png">
                        </p>
                        <p class="po-name">
                            东虹桥金融
                        </p>
                        <div class="post">
                           “星梦成真”演员征选！
					       <br>
						    【参赛要求】
						    <br>
						    1.年龄5——35岁；
						    <br>
						    2.有志于从事影视演出行业；
						    <br>
						    3.授权主办方在比赛过程中使用姓名权、肖像权及其他个人权益；
						    <br>
						    4.填写符合报名要求的信息并上传照片。
						    <br>
						    长按识别二维码抓住走进好莱坞机会！
                            <img src="img/qr.jpg" />
						    <br>
						    <p>
                                <a  id="xq"class="ad-link" href="http://m.dhqjr.com/heroine/index">
                                    查看详情
                                    <img src="img/link.png" />
                                </a>
                            </p>
                        </div>
                        <p class="time">
                            45分钟前
                        </p>
                        <img class="c-icon" src="img/c.png" />
                    </div>
                    <div class="r">
                    </div>
                    <div class="cmt-wrap">
                        <div class="like">
                            <img src="img/l.png" />
                           施建祥，黄晓明，曾志伟，任达华，谢天华，钟镇涛，吕良伟，刘晓庆，胡兵，谢霆锋，黄海冰，鲁斯威利斯，刘烨，泰森，宋承宪，陈伟霆，林志玲，倪妮，希安拉博夫，詹姆斯麦卡沃伊，皮尔斯布鲁斯南，黄晓明，卡雅斯考达里奥，本杰明沃克，威廉赫特，瑞切尔格里菲斯，范伟，段奕宏，桂纶镁
                        </div>
                        <div class="cmt-list">
						    <p><span>范冰冰:</span>张董@<span>东虹桥张蕾</span>，我除了颜值无敌，更是演技派啊，让我也当导师！</p>
						    <p><span>曾志伟:</span>报名回炉~</p>
						    <p><span>刘烨:</span>给诺一报名~竟然有火华社抢不到的沙发……</p>
						    <p><span>安娜</span>回复<span>刘烨:</span>Nina也要一起报上</p>
						    <p><span>东虹桥张蕾:</span>爱表演的小鲜肉们，快来实现演员梦，与明星飙戏，参演好莱坞电影大片！~</p>
						    <p><span>黄晓明:</span>来和我拍大片哦<img class="bq" src="img/bq7.png"></p>
						    <p><span>Angelababy:</span>我要天天去探班</p>
                        </div>
                    </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="img/tft.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">
                            谢霆锋
                        </p>
                        <div class="post">
                            <p>
                              等你回家吃饭
                            </p>
                            <p>
                                <img class="list-img" src="img/tf.jpg">
                            </p>
                        </div>
                        <p class="time">
                            50分钟前
                        </p>
                        <img class="c-icon" src="img/c.png">
                    </div>
                    <div class="r">
                    </div>
                    <div class="cmt-wrap">
                        <div class="like">
                            <img src="img/l.png">
						
                            <!--<b class="data-name"></b>-->
						    Bruce Willis，Adrien Brody，曾志伟，任达华，范冰冰,<b class="data-name"></b>
                        </div>
                        <div class="cmt-list">
						    <p><span>王菲:</span>么么哒</p>
						    <p><span>陈奕迅:</span>红果果秀恩爱</p>
						    <p><span>张卫健:</span>啧啧，女神有口福了</p>
						    <p><span>李亚鹏:</span>她爱吃辣的</p>
						    <p><span>苏永康:</span>楼上破坏队形</p>
						    <p><span>谢霆锋:</span>@<span class="data-name ad-link"></span>做了你爱吃的菜哦</p>
						    <p><span>新东方烹饪学校:</span>饭菜烧的好,女神回家早</p>
						    <p><span class="data-name"></span><span>:</span>我和菲姐唱完K就回去</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="img/abt.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">
                            Angelababy
                        </p>
                        <p class="post">
                           HOHO！周末跑男家族又有重量级嘉宾哦！欢迎亲爱的~
                            <img class="data-avt">
                        </p>
                        <p class="time">
                            52分钟前
                        </p>
                        <img class="c-icon" src="img/c.png">
                    </div>
                    <div class="r">
                    </div>
                    <div class="cmt-wrap">
                        <div class="like">
                            <img src="img/l.png">
                            <b class="data-name">
                            </b>
							    ,鹿晗，邓超，郑恺，陈赫，李晨，范冰冰
                        </div>
                        <div class="cmt-list">
					    <p><span class="data-name"></span><span>:</span>baby，导演不是说保密么</p>
					    <p><span>Angelababy</span>回复<span class="data-name"></span><span>:</span>人家实在是忍不住不喊出来~</p>
                        <p><span>黄晓明:</span>咳咳 注意分寸</p>
                        <p><span>王祖蓝:</span>吓死宝宝了，baby你好有胆量呢~</p>
                        <p><span>王宝强</span>回复<span>王祖蓝:</span>我没被吓到啊？~</p>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
	
    </div>
	
	
		<div class="shopping" id="shopping-box">
            <div class="container">
                <button onclick="showActionSheet();" class="btn btn-success btn-lg btn-block">我也要玩</button>
            </div>
        </div>
		
	<a id="gg" href="http://m.dhqjr.com/heroine/index" ><img src="img/gg.jpg"></a>
    <div id="share">
        <p>（此朋友圈纯属虚构）</p>
        <div style="padding:15px;padding-bottom:20px">
            <button id="gotoplay" onclick="showActionSheet();" class="btn btn-success btn-lg btn-block">我也要玩</button>
            <br>
        </div>

    </div>
    <div id="noguide" class="hide"></div>
</div>

<script>
    function gotoplay(scene) {
        var gourl = "http://web.bjxmeng.com/pengyou/";
       /* if (Math.random()>0.5) {
            gourl = 'http://web.bjxmeng.com/pengyou/img/q3.jpg';
        }
		*/
        location.href=gourl;
    };
    function safetostring(str) {
        return String(str).replace(/&amp;/g, '&').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&quot;/g, '"').replace(/&#39;/g, "'"); 
	}
	var nickname="东融君";
	var headimgurl="http://web.bjxmeng.com/pengyou/img/dq.jpg";
	var uid="001";
	var img="http://web.bjxmeng.com/pengyou/img/dq.jpg";
	nickname = "<?PHP echo $user_info->nickname; ?>";
	headimgurl = "<?PHP echo $user_info->headimgurl; ?>";
	uid = "<?PHP echo $user_info->openid; ?>";
	img = headimgurl;
	$(function(){
		
		$(".loading").hide();	  
		$("#header").show();	  
		$("#main").show();	
		
		var tit="OMG！明星都在我朋友圈！";
		var doc="万万没想到，朋友圈里竟然有辣么多明星，猛料频频哦！";
		var link="http://web.bjxmeng.com/pengyou/?uid="+uid;		
	//	var img="http://web.bjxmeng.com/pengyou/img/dq.jpg";
		// img
		//alert(link);
		JSSDK.init(tit,doc,link,img);
		
		
    setTimeout(function(){
        $(".data-name").text(safetostring(nickname));
        $(".data-avt").attr("src",headimgurl);
        var cw = $('.list-img').width();
        $('.list-img').css({'height':cw+'px'});
		alert(nickname);	
    },20);
	
		
	})
	
    $(window).resize(function() {
        var cw = $('.list-img').width();
        $('.list-img').css({'height':cw+'px'});
    });

    $(document.body).show();
    function hideActionSheet(weuiActionsheet, mask) {
        weuiActionsheet.removeClass('weui_actionsheet_toggle');
        mask.removeClass('weui_fade_toggle');
        weuiActionsheet.on('transitionend', function () {
            mask.hide();
        }).on('webkitTransitionEnd', function () {
            mask.hide();
        })
    }
    function showActionSheet() {
        var mask = $('#mask');
        var weuiActionsheet = $('#weui_actionsheet');
        weuiActionsheet.addClass('weui_actionsheet_toggle');
        mask.show().addClass('weui_fade_toggle').click(function () {
            hideActionSheet(weuiActionsheet, mask);
        });
        $('#actionsheet_cancel').click(function () {
            hideActionSheet(weuiActionsheet, mask);
        });
        weuiActionsheet.unbind('transitionend').unbind('webkitTransitionEnd');
    }
    $('#list').on('click', function () {
     //   showActionSheet();
    });
    $('img').on('click', function () {
   //     showActionSheet();
    });

    $('.play_pyq').on('click', function () {
        var scene = $(this).data("scene");
        gotoplay(scene);
    });
	
	
	$('body').on('click', function (e) {

		var tar=e.target||window.event.srcElement;
		var tid=$(tar).attr("id")
		var tcl=$(tar).attr("class")
		
		if(tid!="gotoplay" &&tid!="actionsheet_cancel" &&tid!="xq" && tcl!="weui_actionsheet_cell play_pyq" ){
	
			if ($("#shopping-box").is(":visible")) {
				$("#shopping-box").slideUp();
			} else {
				$("#shopping-box").hide();
				$("#shopping-box").slideDown();
			}
		}
    });
    $(window).scroll(function () {
        //   $("#shopping-box").hide();
        $("#shopping-box").slideUp();
    })
	
</script>

</body>
</html>