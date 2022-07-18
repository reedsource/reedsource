//根据不同格式的参数获取页面中的DOM对象
// 1.根据标记的id属性来获取单个的DOM对象,参数的格式:"#id值"
// 2.根据标记的class属性来获取多个DOM对象,也就是一个数组对象; 参数的格式:".class值"
// 3.根据标记名称来获取多个DOM对象,也就是一个数组对象 ;参数的格式:"标记名称"


//本js分为3种情况,当计入数据为#name时,.id时,直接是内容值时
function $(param) {
    // 创建一个管理对象,来管理一个或多个DOM对象(通过管理对象来隐藏数组的实质)
    let obj;

    // 创建一个数组对象,用来保存DOM对象
    let arr = [];

    // 根据标记的id属性来获取单个的DOM对象,参数的格式:"#id值",例如:"#name"
    if (param.indexOf("#") === 0) {
        // 切分出标记的id值,获取数据1开始的所有值,相当于把#去掉了
        const id = param.substr(1);
        // 通过id属性获取DOM对象
        const domObj = document.getElementById(id);
        // 把单个的DOM对象保存到数组中
        arr.push(domObj);
    }
        // 根据标记的class属性来获取多个DOM对象,也就是一个数组对象; 参数的格式:".class值"
    //开始的第一个的格式为.
    else if (param.indexOf(".") === 0) {
        // 切分出标记的class值
        const className = param.substr(1);
        // 根据标记的class属性获取数组
        // 把多个dom对象保存到数组中
        arr = document.getElementsByClassName(className);
    }
    // 根据标记名称来获取多个DOM对象,也就是一个数组对象 ;参数的格式:"标记名称"
    else {
         // 根据标记名称来获取数组
        arr = document.getElementsByTagName(param); // 把多个dom对象保存到数组中
    }

    // 把管理对和数组对象关联起来,管理对象实际上就是一个数组对象
    obj = arr;

    // 给管理对象上面扩充属性/方法.来管理这些DOM对象
    // 给管理对象扩充length属性
    obj.length = arr.length;
    // 给管理对象扩充val()方法,获取DOM对象的value值
    obj.val = function () {
        let str = "";

        for (let i = 0; i < arr.length; i++) {
            
            if (i !== arr.length - 1) {
                str += arr[i].value + ","
            } else {
                str += arr[i].value
            }
        }

        //上部分代码的方法2
        // var str = "";
        // for (var i = 0; i < arr.length; i++) {
        //     str += arr[i].value + ","
        // }
        // 去除末尾的逗号,取字符串0到长度-1位的字符
        // str = str.substring(0, str.length - 1);
        // return str;


        return str;
    };
    // 把管理对象返回给调用者
    return obj;
}