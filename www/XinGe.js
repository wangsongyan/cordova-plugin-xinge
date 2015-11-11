
var cordova = require('cordova');

var Tencent = function () {
    //success 注册成功执行方法
    //fail 注册失败执行方法
    //推送注册
    Tencent.prototype.registerPush = function (success, fail, userName) {
        //'XinGe'对应我们在java文件中定义的类名
        //registerPush对应我们在这个类中调用的自定义方法
        //userName是我们客户端传递给这个方法的参数，是个string字段
        cordova.exec(success, fail, 'XinGe', 'registerPush', [userName])
    }
    //推送反注册
    Tencent.prototype.unregisterPush = function () {
        cordova.exec(null, null, 'XinGe', 'unregisterPush', [])
    }
    
  //获取Token
    Tencent.prototype.getToken = function (success, fail) {
        cordova.exec(success, fail, 'XinGe', 'getToken', [])
    }
    
}
var tencent = new Tencent();

module.exports = tencent;
