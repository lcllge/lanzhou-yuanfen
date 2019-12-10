var JSONUtil = {

    parseObj: function (text) {
        if (text == null || text.length < 0) return null;
        if (typeof(text) !== 'object'){
            return JSON.parse(text);
        }else{
            return text;
        }
    },
    toJSONString: function (obj) {
        if (typeof(obj) === 'object') {
            return JSON.stringify(text);
        } else {
            return obj;
        }
    }



};