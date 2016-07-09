Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };
  if(fmt!==null)
  {   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
  }  
  return fmt;   
}

var FormWizard = function () {
    return {
        init: function () {
             var s1 = CreatSurface();
  start1();

  var s2 = CreatSurface();
  start2();

  var s3 = CreatSurface();
  start3();

  function start1() {
    s1.drawSurface = "canvas";
    s1.copySurface = "copySurface1";
    s1.shadeSurface = "ShadeSurface";

    s1.init();
    $("#takePictureField").on("change", function (event) {
      var tempObj = new Object();
      tempObj = s1;
      
      if (event.target.files.length == 1) {

        var reader = new FileReader();
        reader.onload = function (event) {
          try {
            var exif = new ExifReader();
            exif.load(event.target.result);
            var tags = exif.getAllTags();
            tempObj.TagO = tags;
          }
          catch (error) {
            tempObj.exif_error = true;
          }
        };

        reader.readAsArrayBuffer(event.target.files[0]);

        reader.onloadend = function () {
          var f = document.getElementById("takePictureField").files[0];
          var url = window.URL || window.webkitURL;

          var src = url.createObjectURL(f);
          
          tempObj.url = src;
          tempObj.drawInitImage();
          tempObj.dragImage();
          uploadStatus = true;
          SelectImage = true;
          runCheck = false;
        }
      }
    });
  }

  function start2() {
    s2.drawSurface = "canvas1";
    s2.copySurface = "copySurface11";
    s2.shadeSurface = "ShadeSurface1";

    s2.init();
    $("#takePictureField1").on("change", function (event) {
      var tempObj = new Object();
      tempObj = s2;
      
      if (event.target.files.length == 1) {

        var reader = new FileReader();
        reader.onload = function (event) {
          try {
            var exif = new ExifReader();
            exif.load(event.target.result);
            var tags = exif.getAllTags();
            tempObj.TagO = tags;
          }
          catch (error) {
            tempObj.exif_error = true;
          }
        };

        reader.readAsArrayBuffer(event.target.files[0]);

        reader.onloadend = function () {
          var f = document.getElementById("takePictureField1").files[0];
          var url = window.URL || window.webkitURL;

          var src = url.createObjectURL(f);
          
          tempObj.url = src;
          tempObj.drawInitImage();
          tempObj.dragImage();
          uploadStatus = true;
          SelectImage = true;
          runCheck = false;
        }
      }
    });
  }

function start3() {
    s3.drawSurface = "canvas2";
    s3.copySurface = "copySurface12";
    s3.shadeSurface = "ShadeSurface2";

    s3.init();
    $("#takePictureField2").on("change", function (event) {
      var tempObj = new Object();
      tempObj = s3;
      
      if (event.target.files.length == 1) {

        var reader = new FileReader();
        reader.onload = function (event) {
          try {
            var exif = new ExifReader();
            exif.load(event.target.result);
            var tags = exif.getAllTags();
            tempObj.TagO = tags;
          }
          catch (error) {
            tempObj.exif_error = true;
          }
        };

        reader.readAsArrayBuffer(event.target.files[0]);

        reader.onloadend = function () {
          var f = document.getElementById("takePictureField2").files[0];
          var url = window.URL || window.webkitURL;

          var src = url.createObjectURL(f);
          
          tempObj.url = src;
          tempObj.drawInitImage();
          tempObj.dragImage();
          uploadStatus = true;
          SelectImage = true;
          runCheck = false;
        }
      }
    });
  }
			var Goodsnull = location.search;
            
            var type;
            if(Goodsnull === '')
            {
                var  Goods= localStorage.getItem("@MyGoods");
                var MyGoodsdetail =JSON.parse(Goods.substr(1, Goods.length));
                var SceneName =MyGoodsdetail.SceneName;
                var tabNames = ['','旅游','医疗', '培训','装修']
                 type= $.inArray(SceneName, tabNames);  
                $('.type'+type).removeClass('hide');
                $('#GoodsName').html(' "'+MyGoodsdetail.GoodsName+'" 的') ;
            }else{
                type=-1;
            }
            console.log(type);
            var wizform1 = $('#wizForm1');
            var wizform2 = $('#wizForm2');
            var wizform3 = $('#wizForm3');
            var wizform4 = $('#wizForm4');
			var alert_success = $('.alert-success', wizform1);
            var alert_error = $('.alert-danger', wizform1);
            var formindex = 1;
            var TJindex = 1;
			/*-----------------------------------------------------------------------------------*/
			/*	Validate the form elements
			/*-----------------------------------------------------------------------------------*/
            wizform1.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
					email: {
                        required: true,
                        email: true
                    },
                    name: {
                        required: true
                    },
                    gender: {
                        required: true
                        
                    },
                    // Mobile: {
                    //     number: true
                    //     minlength: 11,
                    //     maxlength: 11
                    // },
     //                hisTourLeaveCount: {
     //                    required: true,
     //                    number: true
     //                },
					// hisLeaveCity: {
     //                    required: true
     //                },
					         idCard: {
                        required: true,
                        minlength: 16,
                        maxlength: 18
                    },
                    degree: {
                        required: true
                    },
                    marriage: {
                        required: true
                    },
                    children: {
                        required: true
                    }
                },

                invalidHandler: function (event, validator) { 
                    alert_success.hide();
                    alert_error.show();
                    
                },

                highlight: function (element) { 
                    $(element)
                        .closest('.item-input').removeClass('has-success').addClass('has-error'); 
                },

                unhighlight: function (element) { 
                    $(element)
                        .closest('.item-input').removeClass('has-error'); 
                },

                success: function (label) {

                    if (label.attr("for") == "gender") { 
                        label.closest('.item-input').removeClass('has-error').addClass('has-success');
                        label.remove(); 
                    } else { 
                        label.addClass('valid') 
                        .closest('.item-input').removeClass('has-error').addClass('has-success'); 
                    }
                }
            });

            wizform2.validate({
                doNotHideMessage: true,
                errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
                    
                    ownership: {
                        required: true
                    },
                    province: {
                        required: true
                    },
                    city: {
                        required: true
                    },
                    debitCard: {
                        required: true,
                        minlength: 16,
                        maxlength: 19
                    },
                    creditCard: {
                        required: true,
                        minlength: 16,
                        maxlength: 19
                    },
                    creditCardAmount: {
                        required: true
                    },
                    jkje: {
                        required: true
                    }
                },

                invalidHandler: function (event, validator) { 
                    alert_success.hide();
                    alert_error.show();
                },

                highlight: function (element) { 
                    $(element)
                        .closest('.item-input').removeClass('has-success').addClass('has-error'); 
                },

                unhighlight: function (element) { 
                    $(element)
                        .closest('.item-input').removeClass('has-error'); 
                },

                success: function (label) {
                    if (label.attr("for") == "gender") { 
                        label.closest('.item-input').removeClass('has-error').addClass('has-success');
                        label.remove(); 
                    } else { 
                        label.addClass('valid') 
                        .closest('.item-input').removeClass('has-error').addClass('has-success'); 
                    }
                }
            });

            wizform3.validate({
                doNotHideMessage: true,
                errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
                    companyName: {
                        required: true
                       
                    },
                    companyPhone: {
                        telnumber: true,
                        required: true,
                        minlength: 11,
                        maxlength: 13
                    },
                    joinDate: {
                        required: true
                    },
                    jobTitle: {
                        required: true
                    },
                    unitProperty: {
                        required: true
                    },
                    industry: {
                        required: true
                    },
                    monthlyIncome: {
                        required: true,
                        // minlength: 16,
                        // maxlength: 18
                    },
                    payWay: {
                        required: true
                    }
                },

                invalidHandler: function (event, validator) { 
                    alert_success.hide();
                    alert_error.show();
                },

                highlight: function (element) { 
                    $(element)
                        .closest('.item-input').removeClass('has-success').addClass('has-error'); 
                },

                unhighlight: function (element) { 
                    $(element)
                        .closest('.item-input').removeClass('has-error'); 
                },

                success: function (label) {
                    if (label.attr("for") == "gender") { 
                        label.closest('.item-input').removeClass('has-error').addClass('has-success');
                        label.remove(); 
                    } else { 
                        label.addClass('valid') 
                        .closest('.item-input').removeClass('has-error').addClass('has-success'); 
                    }
                }
            });

            wizform4.validate({
                doNotHideMessage: true,
                errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
                    workCertificateName: {
                        required: true
                    },
                    workCertificatePhone: {
                        number: true,
                        required: true,
                        minlength: 11,
                        maxlength: 11
                    },
                    familyContactName: {
                        required: true
                    },
                    familyContactPhone: {
                        number: true,
                        required: true,
                        minlength: 11,
                        maxlength: 11
                    },
                    emergencyContactName: {
                        required: true
                    },
                    emergencyContactPhone: {
                        number: true,
                        required: true,
                        minlength: 11,
                        maxlength: 11
                    }
                },

                invalidHandler: function (event, validator) { 
                    alert_success.hide();
                    alert_error.show();
                },

                highlight: function (element) { 
                    $(element)
                        .closest('.item-input').removeClass('has-success').addClass('has-error'); 
                },

                unhighlight: function (element) { 
                    $(element)
                        .closest('.item-input').removeClass('has-error'); 
                },

                success: function (label) {
                    if (label.attr("for") == "gender") { 
                        label.closest('.item-input').removeClass('has-error').addClass('has-success');
                        label.remove(); 
                    } else { 
                        label.addClass('valid') 
                        .closest('.item-input').removeClass('has-error').addClass('has-success'); 
                    }
                }
            });
            var li_list = $('.yo-SQHtab').find('a');
            $('#formWizard').find('.prevBtn').hide();
            $('#formWizard .nextBtn').click(function () {

                    switch(formindex)
                    {
                    case 1:
                       // switch(type)
                       //  {
                       //  case 1: 
                       //      if (wizform1.valid() == false) {
                       //      return false;
                       //      }
                       //   break;
                       //  case 2:
                       //      if (wizform1.valid() == false) {
                       //      return false;
                       //      }
                       //   break;
                       //  case 3: 
                       //      if (wizform1.valid() == false) {
                       //      return false;
                       //      }
                       //   break;
                       //  case 4: 
                       //      if (wizform1.valid() == false) {
                       //      return false;
                       //      }
                       //   break;
                       //  }
                      if (wizform1.valid() == false) {

                           return false;
                      } 
                     
                      localStorage.setItem('email',$("[name='email']").val());
                      localStorage.setItem('name', $("[name='name']").val());
                      localStorage.setItem('gender', $("[name='gender']").val());
                      localStorage.setItem('hisTourLeaveCount', $("[name='hisTourLeaveCount']").val());
                      localStorage.setItem('hisLeaveCity', $("[name='hisLeaveCity']").val());

                      localStorage.setItem('scyl', $("[name='scyl']").val());
                      localStorage.setItem('ylbx', $("[name='ylbx']").val());

                      localStorage.setItem('pxjl', $("[name='pxjl']").val());
                      localStorage.setItem('sqgx', $("[name='sqgx']").val());

                      localStorage.setItem('jzmj', $("[name='jzmj']").val());
                      localStorage.setItem('sfmj', $("[name='sfmj']").val());
                      localStorage.setItem('fwlx', $("[name='fwlx']").val());
                      
                      
                      localStorage.setItem('idCard', $("[name='idCard']").val());
                      localStorage.setItem('degree', $("[name='degree']").val());
                      localStorage.setItem('marriage', $("[name='marriage']").val());
                      localStorage.setItem('children', $("[name='children']").val());
                      //本地保存已填写
                      break;
                    case 2:
                      if (wizform2.valid() == false) {
                            return false;
                      }
                      localStorage.setItem('ownership',$("[name='ownership']").val());
                      localStorage.setItem('province', $("[name='province']").val());
                      localStorage.setItem('city', $("[name='city']").val());
                      localStorage.setItem('liveAddress', $("[name='liveAddress']").val());
                      
                      localStorage.setItem('debitCard', $("[name='debitCard']").val());
                      localStorage.setItem('creditCard', $("[name='creditCard']").val());
                      localStorage.setItem('creditCardAmount', $("[name='creditCardAmount']").val());
                      localStorage.setItem('jkje', $("[name='jkje']").val());
                      break;
                    case 3:
                      if (wizform3.valid() == false) {
                            return false;
                      }
                      localStorage.setItem('companyName',$("[name='companyName']").val());
                      localStorage.setItem('companyPhone', $("[name='companyPhone']").val());
                      localStorage.setItem('joinDate', $("[name='joinDate']").val());
                      localStorage.setItem('jobTitle', $("[name='jobTitle']").val());
                      localStorage.setItem('unitProperty', $("[name='unitProperty']").val());
                      localStorage.setItem('industry', $("[name='industry']").val());
                      localStorage.setItem('degree', $("[name='degree']").val());
                      localStorage.setItem('monthlyIncome', $("[name='monthlyIncome']").val());
                      localStorage.setItem('payWay', $("[name='payWay']").val());
                      break;
                    case 4:
                      if (wizform4.valid() == false) {
                            return false;
                      }
                      localStorage.setItem('workCertificateName',$("[name='workCertificateName']").val());
                      localStorage.setItem('workCertificatePhone', $("[name='workCertificatePhone']").val());
                      localStorage.setItem('familyContactName', $("[name='familyContactName']").val());
                      localStorage.setItem('familyContactPhone', $("[name='familyContactPhone']").val());
                      localStorage.setItem('emergencyContactName', $("[name='emergencyContactName']").val());
                      localStorage.setItem('emergencyContactPhone', $("[name='emergencyContactPhone']").val());
                      
                      
                      break;
                    }
                  
                $('#J-spec-axis-done').find('.L'+ (formindex-1) ).removeClass('icon-spec-point-undone').addClass('icon-spec-point-done');
                
                $('#wizForm'+formindex).find('.panel').addClass('display-none');
                    $('#wizForm'+formindex).find('.panel').addClass('display-none');
                    if(TJindex==1){  
                        formindex++;
                    }
                    $('#wizForm'+formindex).find('.panel').removeClass('display-none'); 
                    if(formindex > 1) {
                      $('#formWizard').find('.goback').hide();
                      $('#formWizard').find('.prevBtn').show();
                  }  

                if(formindex==5){
                   TJindex++;
                   if(TJindex==2){
                        $('#wizForm'+formindex-1).find('.panel').addClass('display-none');
                        $('#wizForm5').removeClass('display-none');
                        $('#wizForm5').show();
                   }else{
                        var  IDCardImageName=null,PhotoName=null;
                        if(!$("#takePictureField").val()||!$("#takePictureField1").val()){
                          // $(".copySurface1msg").html("");
                         $(".pop").show(); 
                          TJindex=2;
                          $('#wizForm6').hide();
                          $('#wizForm5').show();
                          $('#J-spec-axis-done').find('.L4').removeClass('icon-spec-point-done').addClass('icon-spec-point-undone');
                         return false;
                        }else{
                          $(".spinner").addClass("active");

                          $('#wizForm5').hide();
                          $('#wizForm6').show();
                          var formData0 = new FormData();  
                          formData0.append("file", $("#takePictureField")[0].files[0]); 
                          //formData0.append("file", s1.toImage()); 
                          //console.log(s1.toImage());
                          var formData1 = new FormData();  
                          formData1.append("file", $("#takePictureField1")[0].files[0]); 
                          //formData1.append("file", s2.toImage());

                          var formData2 = new FormData(); 
                          formData2.append("file", $("#takePictureField2")[0].files[0]);  
                          //formData2.append("file", s3.toImage()); 
                          //var urlUploadCreditKeyImg = "http://121.40.185.130:8072/api/CreditKey/UploadCreditKeyImg"; 
                          var urlUploadCreditKeyImg = "http://120.55.161.84:8064/api/CreditKey/UploadCreditKeyImg";  
                          $.ajax({  
                              url : urlUploadCreditKeyImg,  
                              type : 'POST',  
                              data : formData0,
                              dataType: 'JSON',  
                              async : false,  
                              /**   
                               * 必须false才会避开jQuery对 formdata 的默认处理   
                               * XMLHttpRequest会对 formdata 进行正确的处理   
                               */  
                              processData : false,  
                              /**   
                               *必须false才会自动加上正确的Content-Type   
                               */  
                              contentType : false, 
                              cache: false, 

                              success : function(responseStr) {  
                                                 console.log(responseStr);
                                                 IDCardImageName=responseStr.result;
                              },  
                              error : function(responseStr) {  
                                   console.log(responseStr);
                              }  
                          }); 
                          $.ajax({  
                              url : urlUploadCreditKeyImg,  
                              type : 'POST',  
                              data : formData2,
                              dataType: 'JSON',  
                              async : false,  
                              /**   
                               * 必须false才会避开jQuery对 formdata 的默认处理   
                               * XMLHttpRequest会对 formdata 进行正确的处理   
                               */  
                              processData : false,  
                              /**   
                               *必须false才会自动加上正确的Content-Type   
                               */  
                              contentType : false, 
                              cache: false, 

                              success : function(responseStr) {  
                                                 console.log(responseStr);
                                                 PhotoName=responseStr.result;
                              },  
                              error : function(responseStr) {  
                                   console.log(responseStr);
                              }  
                          });
                        }
                   }
                    for (var i = 0; i < 3; i++) {
                        if(i<TJindex)
                        {
                         jQuery(li_list[i]).addClass("item-on");
                        }
                    }
                }
                if(formindex == 5&&TJindex==3) {
                      console.log(IDCardImageName+PhotoName);
                      if(IDCardImageName===""&&PhotoName===""){
                        $(".Message").html("图片上传失败");
                        return false;
                      }
                      // var urlarr = 
                      // ['',
                      //  'http://121.40.185.130:8072/api/CreditKey/creditApply',
                      //  'http://121.40.185.130:8072/api/CreditKey/creditApplyGDYL',
                      //  'http://121.40.185.130:8072/api/CreditKey/creditApplyPX',
                      //  'http://121.40.185.130:8072/api/CreditKey/creditApplyZX',
                      //  'http://121.40.185.130:8072/api/CreditKey/creditApplyCommon']
                      var urlarr = 
                      ['','http://120.55.161.84:8064/api/CreditKey/creditApply',
                       'http://120.55.161.84:8064/api/CreditKey/creditApplyGDYL',
                       'http://120.55.161.84:8064/api/CreditKey/creditApplyPX',
                       'http://120.55.161.84:8064/api/CreditKey/creditApplyZX',
                       'http://121.40.185.130:8072/api/CreditKey/creditApplyCommon']
                      var url = type == -1 ? urlarr[4]:  urlarr[type];
                      var Key = "KoeIy12Ay~oEuN3" + new Date().Format("yyyyMMdd");
                      console.log(Key);
                      console.log(localStorage.getItem('Mobile'));
                      var data={};
                      switch(type)
                        {
                          //旅游
                        case 1: 
                          data =
                          {'checkMsg':$.md5(Key),
                          'name':$("[name='name']").val(),
                          'gender':$("[name='gender']").val(),
                          'idCard':$("[name='idCard']").val(),
                          'Mobile':localStorage.getItem('Mobile'),
                          'email':$("[name='email']").val(),
                          'degree':$("[name='degree']").val(),
                          'marriage':$("[name='marriage']").val(),
                          'children':$("[name='children']").val(),
                          'ownership':$("[name='ownership']").val(),
                          'province':$("[name='province']").val(),
                          'city':$("[name='city']").val(),
                          'liveAddress':$("[name='liveAddress']").val(),
                          'debitCard':$("[name='debitCard']").val(),
                          'creditCard':$("[name='creditCard']").val(),
                          'creditCardAmount':$("[name='creditCardAmount']").val(),
                          'companyName':$("[name='companyName']").val(),
                          'companyPhone':$("[name='companyPhone']").val(),
                          'joinDate':$("[name='joinDate']").val(),
                          'jobTitle':$("[name='jobTitle']").val(),
                          'unitProperty':$("[name='unitProperty']").val(),
                          'industry':$("[name='industry']").val(),
                          'monthlyIncome':$("[name='monthlyIncome']").val(),
                          'payWay':$("[name='payWay']").val(),
                          'goodsName' : MyGoodsdetail.GoodsName,
                          'hisTourLeaveCount':$("[name='hisTourLeaveCount']").val(),
                          'hisLeaveCity':$("[name='hisLeaveCity']").val(),
                          'travelAgency':MyGoodsdetail.MerchantName,
                          'applyMoney':$("[name='jkje']").val(),
                          'workCertificateName':$("[name='workCertificateName']").val(),
                          'workCertificatePhone':$("[name='workCertificatePhone']").val(),
                          'familyContactName':$("[name='familyContactName']").val(),
                          'familyContactPhone':$("[name='familyContactPhone']").val(),
                          'emergencyContactName':$("[name='emergencyContactName']").val(),
                          'emergencyContactPhone':$("[name='emergencyContactPhone']").val(),
                          'IDCardImageName':IDCardImageName,
                          'PhotoName':PhotoName 
                          //localStorage.getItem('IDCardImageName'),
                          //localStorage.getItem('PhotoName'),
                         }
                         break;
                        case 2:
                        //医疗
                          data ={
                          'checkMsg':$.md5(Key),
                          'name':$("[name='name']").val(),
                          'gender':$("[name='gender']").val(),
                          'idCard':$("[name='idCard']").val(),
                          'Mobile':localStorage.getItem('Mobile'),
                          'email':$("[name='email']").val(),
                          'degree':$("[name='degree']").val(),
                          'marriage':$("[name='marriage']").val(),
                          'children':$("[name='children']").val(),
                          'ownership':$("[name='ownership']").val(),
                          'province':$("[name='province']").val(),
                          'city':$("[name='city']").val(),
                          'liveAddress':$("[name='liveAddress']").val(),
                          'debitCard':$("[name='debitCard']").val(),
                          'creditCard':$("[name='creditCard']").val(),
                          'creditCardAmount':$("[name='creditCardAmount']").val(),
                          'companyName':$("[name='companyName']").val(),
                          'companyPhone':$("[name='companyPhone']").val(),
                          'joinDate':$("[name='joinDate']").val(),
                          'jobTitle':$("[name='jobTitle']").val(),
                          'unitProperty':$("[name='unitProperty']").val(),
                          'industry':$("[name='industry']").val(),
                          'monthlyIncome':$("[name='monthlyIncome']").val(),
                          'payWay':$("[name='payWay']").val(),
                          'goodsName' : MyGoodsdetail.GoodsName,
                          'isFirstTreatment':$("[name='scyl']").val(),
                          'HasMediIns':$("[name='ylbx']").val(),
                          'mediMechanism':MyGoodsdetail.MerchantName,
                          'medicalProject':MyGoodsdetail.Extended1,
                          'totalCost':MyGoodsdetail.Balance,

                          'applyMoney':$("[name='jkje']").val(),
                          'workCertificateName':$("[name='workCertificateName']").val(),
                          'workCertificatePhone':$("[name='workCertificatePhone']").val(),
                          'familyContactName':$("[name='familyContactName']").val(),
                          'familyContactPhone':$("[name='familyContactPhone']").val(),
                          'emergencyContactName':$("[name='emergencyContactName']").val(),
                          'emergencyContactPhone':$("[name='emergencyContactPhone']").val(),
                          'IDCardImageName':IDCardImageName,
                          'PhotoName':PhotoName 
                         }
                         break;
                        case 3: 
                        //培训

                          data ={
                          'checkMsg':$.md5(Key),
                          'name':$("[name='name']").val(),
                          'gender':$("[name='gender']").val(),
                          'idCard':$("[name='idCard']").val(),
                          'Mobile':localStorage.getItem('Mobile'),
                          'email':$("[name='email']").val(),
                          'degree':$("[name='degree']").val(),
                          'marriage':$("[name='marriage']").val(),
                          'children':$("[name='children']").val(),
                          'ownership':$("[name='ownership']").val(),
                          'province':$("[name='province']").val(),
                          'city':$("[name='city']").val(),
                          'liveAddress':$("[name='liveAddress']").val(),
                          'debitCard':$("[name='debitCard']").val(),
                          'creditCard':$("[name='creditCard']").val(),
                          'creditCardAmount':$("[name='creditCardAmount']").val(),
                          'companyName':$("[name='companyName']").val(),
                          'companyPhone':$("[name='companyPhone']").val(),
                          'joinDate':$("[name='joinDate']").val(),
                          'jobTitle':$("[name='jobTitle']").val(),
                          'unitProperty':$("[name='unitProperty']").val(),
                          'industry':$("[name='industry']").val(),
                          'monthlyIncome':$("[name='monthlyIncome']").val(),
                          'payWay':$("[name='payWay']").val(),
                          'goodsName' : MyGoodsdetail.GoodsName,

                          'hasTrainingExperience':$("[name='pxjl']").val(),
                          'trainRelation':$("[name='sqgx']").val(),
                          'trainingInstitution':MyGoodsdetail.MerchantName,
                          'courseName':MyGoodsdetail.Extended1,
                          'trainingType': $.inArray(MyGoodsdetail.Extended2,["","学历教育","资格认证","外语培训","电脑培训","职业培训","艺术培训","企业管理","少儿培训","出国留学"]),
                          'trainingMode':$.inArray(MyGoodsdetail.Extended3,["","课堂培训","网络培训"]),
                          'totalCost':MyGoodsdetail.Balance,
                          'totalHours':MyGoodsdetail.Extended6,
                          'courseStartDate':MyGoodsdetail.Extended4,
                          'CourseEndDate':MyGoodsdetail.Extended5,

                          'applyMoney':$("[name='jkje']").val(),
                          'workCertificateName':$("[name='workCertificateName']").val(),
                          'workCertificatePhone':$("[name='workCertificatePhone']").val(),
                          'familyContactName':$("[name='familyContactName']").val(),
                          'familyContactPhone':$("[name='familyContactPhone']").val(),
                          'emergencyContactName':$("[name='emergencyContactName']").val(),
                          'emergencyContactPhone':$("[name='emergencyContactPhone']").val(),
                          'IDCardImageName':IDCardImageName,
                          'PhotoName':PhotoName 
                         }  
                         break;
                        case 4: 
                        //装修
                          data ={
                          'checkMsg':$.md5(Key),
                          'name':$("[name='name']").val(),
                          'gender':$("[name='gender']").val(),
                          'idCard':$("[name='idCard']").val(),
                          'Mobile':localStorage.getItem('Mobile'),
                          'email':$("[name='email']").val(),
                          'degree':$("[name='degree']").val(),
                          'marriage':$("[name='marriage']").val(),
                          'children':$("[name='children']").val(),
                          'ownership':$("[name='ownership']").val(),
                          'province':$("[name='province']").val(),
                          'city':$("[name='city']").val(),
                          'liveAddress':$("[name='liveAddress']").val(),
                          'debitCard':$("[name='debitCard']").val(),
                          'creditCard':$("[name='creditCard']").val(),
                          'creditCardAmount':$("[name='creditCardAmount']").val(),
                          'companyName':$("[name='companyName']").val(),
                          'companyPhone':$("[name='companyPhone']").val(),
                          'joinDate':$("[name='joinDate']").val(),
                          'jobTitle':$("[name='jobTitle']").val(),
                          'unitProperty':$("[name='unitProperty']").val(),
                          'industry':$("[name='industry']").val(),
                          'monthlyIncome':$("[name='monthlyIncome']").val(),
                          'payWay':$("[name='payWay']").val(),
                          //'goodsName' : MyGoodsdetail.GoodsName,
                          'builtAreas':$("[name='jzmj']").val(),
                          'whetherMortgage':$("[name='sfmj']").val(),
                          'decorationCompany':MyGoodsdetail.MerchantName,

                          'decorationProvince':$("[name='province']").val(),
                          'decorationCity':$("[name='city']").val(),
                          'decorationType': $.inArray(MyGoodsdetail.Extended1,["","半包","全包"]),
                          'housingType': $("[name='fwlx']").val(),
                          'totalCost':MyGoodsdetail.Balance,


                          'applyMoney':$("[name='jkje']").val(),
                          'workCertificateName':$("[name='workCertificateName']").val(),
                          'workCertificatePhone':$("[name='workCertificatePhone']").val(),
                          'familyContactName':$("[name='familyContactName']").val(),
                          'familyContactPhone':$("[name='familyContactPhone']").val(),
                          'emergencyContactName':$("[name='emergencyContactName']").val(),
                          'emergencyContactPhone':$("[name='emergencyContactPhone']").val(),
                          'IDCardImageName':IDCardImageName,
                          'PhotoName':PhotoName 
                         } 
                         break;
                         case -1: 
                         //通用
                           data ={
                          'checkMsg':$.md5(Key),
                          'name':$("[name='name']").val(),
                          'gender':$("[name='gender']").val(),
                          'idCard':$("[name='idCard']").val(),
                          'Mobile':localStorage.getItem('Mobile'),
                          'email':$("[name='email']").val(),
                          'degree':$("[name='degree']").val(),
                          'marriage':$("[name='marriage']").val(),
                          'children':$("[name='children']").val(),
                          'ownership':$("[name='ownership']").val(),
                          'province':$("[name='province']").val(),
                          'city':$("[name='city']").val(),
                          'liveAddress':$("[name='liveAddress']").val(),
                          'debitCard':$("[name='debitCard']").val(),
                          'creditCard':$("[name='creditCard']").val(),
                          'creditCardAmount':$("[name='creditCardAmount']").val(),
                          'companyName':$("[name='companyName']").val(),
                          'companyPhone':$("[name='companyPhone']").val(),
                          'joinDate':$("[name='joinDate']").val(),
                          'jobTitle':$("[name='jobTitle']").val(),
                          'unitProperty':$("[name='unitProperty']").val(),
                          'industry':$("[name='industry']").val(),
                          'monthlyIncome':$("[name='monthlyIncome']").val(),
                          'payWay':$("[name='payWay']").val(),
                          'goodsName' : '',
                          'builtAreas':$("[name='jzmj']").val(),
                          'whetherMortgage':$("[name='sfmj']").val(),
                          'decorationCompany':'',

                          'decorationProvince':'',
                          'decorationCity':'',
                          'decorationType':'',
                          'housingType': '',
                          'totalCost':'',

                          'applyMoney':$("[name='jkje']").val(),
                          'workCertificateName':$("[name='workCertificateName']").val(),
                          'workCertificatePhone':$("[name='workCertificatePhone']").val(),
                          'familyContactName':$("[name='familyContactName']").val(),
                          'familyContactPhone':$("[name='familyContactPhone']").val(),
                          'emergencyContactName':$("[name='emergencyContactName']").val(),
                          'emergencyContactPhone':$("[name='emergencyContactPhone']").val(),
                          'IDCardImageName':IDCardImageName,
                          'PhotoName':PhotoName 
                         } 
                         break;
                        }
                        console.log(data);
                        console.log(url);

                      $.ajax({
                        url : url,
                        type: "POST",
                        data : data,
                        dataType : 'json',
                        success : function(data) {
                            console.log(data);
                            if(data.Message!=="成功"){
                            $(".Message").html(data.Message);
                            }
                            $(".Data").html(data.Data);
                            $(".spinner").removeClass("active");
                             $('#formWizard').find('.goback').hide();
                        },
                        error : function(XMLHttpRequest,textStatus, errorThrown) {
                          console.log(XMLHttpRequest);
                           $('#formWizard').find('.goback').hide();
                        }
                    }); 


                    $('#formWizard').find('.prevBtn').hide();

                    $('#formWizard').find('.nextBtn').hide();
                    $('#formWizard').find('.goback').show();
                }else{
                        $('#formWizard').find('.nextBtn').show();
                    } 
               
            })


                      
            
             $('#formWizard .prevBtn').click(function () {
                         $('#J-spec-axis-done').find('.L'+ (formindex-2) ).removeClass('icon-spec-point-done').addClass('icon-spec-point-undone');
                         if(TJindex==1){
                            $('#wizForm'+formindex).find('.panel').addClass('display-none');
                            formindex--;
                            $('#wizForm'+formindex).find('.panel').removeClass('display-none'); 
                         }else{
                            TJindex--;
                            if(TJindex==2){
                                 $('#wizForm6').hide();
                                 $('#wizForm5').show();
                                 jQuery(li_list[TJindex]).removeClass("item-on");
                            }else
                            {
                                $('#wizForm5').hide();
                                formindex--;
                                $('#wizForm'+formindex).find('.panel').removeClass('display-none');
                                 jQuery(li_list[TJindex]).removeClass("item-on");
                            }   
                         }       
                    if(formindex > 1) {
                      $('#formWizard').find('.goback').hide();
                      $('#formWizard').find('.prevBtn').show();
                    }else{
                        $('#formWizard').find('.prevBtn').hide();
                        $('#formWizard').find('.goback').show();
                    } 
                    
                })
        }
    };
}();