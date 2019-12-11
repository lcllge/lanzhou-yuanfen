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
    $("#send").on('click', function () {
        $("input").css({"border": "1px solid #474d5b", "box-shadow": "none"});
        var $username = $("input[name='username']"), $email = $("input[name='email']");
        var username = $username.val(), email = $email.val();
        /*if (!username) {
            WarnAlert("请输入用户名 !!");
            $username.css({
                "border": "1px solid #f70404",
                "box-shadow": "0px 5px 10px #fd000c33"
            });
            return false;
        }*/
        if (!email) {
            WarnAlert("请输入邮箱 !!");
            $email.css({
                "border": "1px solid #f70404",
                "box-shadow": "0px 5px 10px #fd000c33"
            });
            return false;
        }
        var url = ctxPath + "sendEmail", json = {email: email};
        AjaxPost(url, json, function () {
        }, function (result) {
            result = JSONUtil.parseObj(result);
            if (parseInt(result.code) === 200) {
                SuccessAlert("发送成功 !!");
            } else {
                WarnAlert(result.message);
            }
        });


    });


    // login-form
    $("#sign").on('click', function () {
        $("input").css({"border": "1px solid #474d5b", "box-shadow": "none"});
        var $username = $("input[name='username']"), $email = $("input[name='email']"),
            $emailCode = $("input[name='emailCode']");
        var username = $username.val(), email = $email.val(), emailCode = $emailCode.val();
        /*if (!username) {
            WarnAlert("请输入用户名 !!");
            $username.css({
                "border": "1px solid #f70404",
                "box-shadow": "0px 5px 10px #fd000c33"
            });
            return false;
        }*/
        if (!email) {
            WarnAlert("请输入邮箱 !!");
            $email.css({
                "border": "1px solid #f70404",
                "box-shadow": "0px 5px 10px #fd000c33"
            });
            return false;
        }
        if (!emailCode) {
            WarnAlert("请输入验证码 !!");
            $emailCode.css({
                "border": "1px solid #f70404",
                "box-shadow": "0px 5px 10px #fd000c33"
            });
            return false;
        }
        var url = ctxPath + "emailVerify", json = {email: email, eCode: emailCode};
        AjaxPost(url, json, function () {
        }, function (result) {
            result = JSONUtil.parseObj(result);
            if (parseInt(result.code) === 200) {
                window.location.href = ctxPath + "home";
            } else {
                WarnAlert(result.message);
            }
        });
    });


});
	