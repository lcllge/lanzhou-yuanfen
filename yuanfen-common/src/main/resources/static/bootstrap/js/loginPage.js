$(document).ready(function () {
    "use strict";

    /*==================================
    * Author        : "ThemeSine"
    * Template Name :  HTML Template
    * Version       : 1.0
    ==================================== */


    /*=========== TABLE OF CONTENTS ===========
    1. Scroll To Top
    ======================================*/

    // 1. Scroll To Top 
    $(window).on('scroll', function () {
        if ($(this).scrollTop() > 600) {
            $('.return-to-top').fadeIn();
        } else {
            $('.return-to-top').fadeOut();
        }
    });
    $('.return-to-top').on('click', function () {
        $('html, body').animate({
            scrollTop: 0
        }, 1500);
        return false;
    });


    // login-form
    $("#sign").on('click', function () {
        var $username = $("input[name='username']"), $password = $("input[name='password']");
        var username = $username.val(),password = $password.val();
        if(!username){
            WarnAlert("请输入用户名 !");
            $username.css({
                "border": "1px solid #f70404",
                "box-shadow": "0px 5px 10px #fd000c33"
            });
            return false;
        }
        if(!password){
            WarnAlert("请输入密码 !");
            $password.css({
                "border": "1px solid #f70404",
                "box-shadow": "0px 5px 10px #fd000c33"
            });
            return false;
        }
        var url = ctxPath + "login", json = {username: username, password: password};
        AjaxPost(url, json, function () {
        }, function (result) {
            result = JSONUtil.parseObj(result);
            if (parseInt(result.code) === 200) {
                window.location.href = ctxPath + "home";
            }else{
                layer.msg('用户登入异常, 请检查用户名或密码 !');
            }
        });

    });


});
	