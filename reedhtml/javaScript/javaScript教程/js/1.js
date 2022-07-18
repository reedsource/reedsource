// 当点击提交按钮的时候,执行表单校验
// 表单校验的规则:
// 1.用户名,密码,确认密码不能为空
// 2.用户名,密码,确认密码的长度不能小于6位
// 3.密码,确认密码必须一致(可以使用 != 判断两个值是否不相等)
// 如果校验成功则返回true,如果校验失败则返回false
// 校验标的思路:获取到页面中的输入框标记对象,然后判断标记对象的value属性是否有效
function checkForm() {
    // 为了获取到页面中的标记对象,给标记对象添加id属性
    var nameInput = document.getElementById("uname");

    // 页面中的标记具有什么样的属性,则标记对象上面就有什么样的属性;例如标记中有个value属性,则标记对象上面也有个value属性
    if (nameInput.value === "" || nameInput.value === "请输入用户名") {
        window.alert("用户名不能为空"); // window.alert()弹出一个提示信息对话框
        nameInput.focus(); // 让输入标记获取到焦点,便于用户输入数据
        return false; // 如果校验失败则返回false
    }
    // 用户在输入框中输入的数据"张三",是一个类似于Java中String的数据,该数据上面有个length属性
    else if (nameInput.value.length < 6) {
        alert("用户名长度不能小于6为"); // window可以忽略
        nameInput.focus(); // 让输入标记获取到焦点,便于用户输入数据
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 在下面继续检查密码和确认密码
    var pwdInput = document.getElementById("pwd");
    if (pwdInput.value === "") {
        alert("密码不能为空");
        pwdInput.focus();
        return false;
    } else if (pwdInput.value.length < 6) {
        alert("密码长度不能小于6位");
        pwdInput.focus();
        return false;
    }

    var repwdInput = document.getElementById("repwd");
    if (pwdInput.value != repwdInput.value) {
        alert("密码和确认秘密必须要一致");
        repwdInput.focus();
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 如果校验成功,则返回true
    return true;
}

/**
 * 清空输入框中的提示信息
 */
function clearValue() {
    var nameInput = document.getElementById("uname");
    if (nameInput.value === "请输入用户名") {
        nameInput.value = "";
    }
}
