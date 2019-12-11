$(document).ready(function () {
    "use strict";
    var checkedFiled = {
        username: "用户名",
        password: "密码",
        email: "邮箱",
        rePassword: "重复密码"
    };

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
    $("#signOut").on('click', function () {
        var data = {};
        var t = $('#register').serializeArray(), unfail = false;
        $("input").css({"border": "1px solid #474d5b", "box-shadow": "none"});
        $.each(t, function () {
            if (this.value == null || this.value === '') {
                // alert(this.name);
                // box-shadow: 0px 5px 10px rgba(45, 50, 64, .2);
                $("input[name='" + this.name + "']").css({
                    "border": "1px solid #f70404",
                    "box-shadow": "0px 5px 10px #fd000c33"
                });
                WarnAlert("请输入" + checkedFiled[this.name] + "!");
                unfail = true;
                return false;
            }
            data[this.name] = this.value;
        });
        if (unfail) {
            return false;
        }
        var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        var isok = reg.test(data.email);
        if (!isok) {
            WarnAlert("请输入正确的邮箱地址 !");
            $("input[name='email']").css({
                "border": "1px solid #f70404",
                "box-shadow": "0px 5px 10px #fd000c33"
            });
            return false;
        }
        if (data.password !== data.rePassword) {
            WarnAlert("两次输入的密码不一致 !");
            $("input[name='rePassword']").val('').css({
                "border": "1px solid #f70404",
                "box-shadow": "0px 5px 10px #fd000c33"
            });
            return false;
        }
        console.log("校验完成 !");
        var url = ctxPath + "registerUser";
        AjaxPost(url, data, function () {
        }, function (result) {
            result = JSONUtil.parseObj(result);
            if (parseInt(result.code) === 200) {
                layer.alert('注册成功 !!', {
                    skin: 'layui-layer-molv' //样式类名
                    ,closeBtn: 0,
                    icon: 1
                }, function(){
                    window.location.href = ctxPath + "/loginPage";
                });

            } else {
                ErrorAlert(result.message);
            }
        });


    });


});