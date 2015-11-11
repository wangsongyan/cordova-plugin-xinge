package org.apache.cordova.xinge;

import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushService;

/**
 * Created by ASUS on 2015/11/4.
 */
public class XinGe extends CordovaPlugin {

    @Override
    // action 插件方法
    // args 传递过来的参数，获取方法为args.getString(数组中的位置);
    // callbackContext 回调函数
    public boolean execute(String action, JSONArray args,
                           final CallbackContext callbackContext) throws JSONException {
        Context context = this.cordova.getActivity();
        // 判断要调用的方法
        if (action.equals("registerPush")) {
            // 获取第一个参数，用户名
            String userName = args.getString(0);
            // 调用方法
            this.registerPush(context, callbackContext, userName);
            return true;
        } else if (action.equals("unregisterPush")) {
            // 反注册，取消推送
            XGPushManager.unregisterPush(context);
            return true;
        } else if(action.equals("getToken")){
            this.getToken(context,callbackContext);
            return true;
        }
        // 回调失败的函数
        callbackContext.error("该方法不存在!");
        return false;
    }

    // 推送注册
    private void registerPush(Context context,
                              final CallbackContext callbackContext, final String userName) {
        // 推送别名注册方法，可以根据别名进行推送
        XGPushManager.registerPush(context, userName, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                // 回调成功的函数
                Log.v("====>>>>>", userName);
                Log.d("TPush", "注册成功，设备token为：" + data);
                callbackContext.success();
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                // 回调失败的函数
                callbackContext.error(msg);
            }
        });
        // 在XGPushManager.registerPush(context)或其它版本的注册接口之后调用以下代码
        // 使用ApplicationContext
        // 兼容MIUI V6
        //Intent service = new Intent(context, XGPushService.class);
        //context.startService(service);
    }

    private void getToken(Context context , final CallbackContext callbackContext ){
        String token = XGPushConfig.getToken(context);
        Log.d("token------->",token);
        if(null!=token){
            callbackContext.success(token);
        }else{
            callbackContext.error("注册失败");
        }
    }

}