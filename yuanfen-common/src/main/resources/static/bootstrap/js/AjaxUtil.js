// 基于layer的Ajax封装的数据层工具类
function AjaxPost(Url, JsonData, LodingFun, ReturnFun) {
    $.ajax({
        type: "post",
        url: Url,
        data: JsonData,
        dataType: 'json',
        async: 'false',
        beforeSend: LodingFun,
        error: function () {
            AjaxError({"Status": "Erro", "Erro": "500"});
        },
        success: ReturnFun
    });
}


//Ajax 错误返回处理
function AjaxError(e) {
    switch (e.code) {
        case "201":
            ErrorAlert("失败");
            break;
        case "404":
            WarnAlert("未找到");
            break;
        case "500":
            ErrorAlert("系统错误");
            break;
        case "403":
            WarnAlert("未授权");
            break;
        default:
            layer.msg("未知错误！");
    }
}


//错误提示弹出
function ErrorAlert(e) {
    var index = layer.alert(e , {
        icon: 5,
        time: 2000,
        offset: 'auto',
        closeBtn: 0,
        title: '错误',
        btn: [],
        anim: 5,
        shade: 0
    });
    layer.style(index, {
        color: '#777'
    });
}


//警告弹出
function WarnAlert(e) {
    var index = layer.alert(e , {
        icon: 0,
        time: 2000,
        offset: 'auto',
        closeBtn: 0,
        title: '警告',
        btn: [],
        anim: 5,
        shade: 0
    });
    layer.style(index, {
        color: '#777'
    });
}


//错误提示弹出
function SuccessAlert(e) {
    var index = layer.alert(e , {
        icon: 1,
        time: 2000,
        offset: 'auto',
        closeBtn: 0,
        title: '成功',
        btn: [],
        anim: 5,
        shade: 0
    });
    layer.style(index, {
        color: '#777'
    });
}