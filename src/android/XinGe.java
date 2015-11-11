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
    // action �������
    // args ���ݹ����Ĳ�������ȡ����Ϊargs.getString(�����е�λ��);
    // callbackContext �ص�����
    public boolean execute(String action, JSONArray args,
                           final CallbackContext callbackContext) throws JSONException {
        Context context = this.cordova.getActivity();
        // �ж�Ҫ���õķ���
        if (action.equals("registerPush")) {
            // ��ȡ��һ���������û���
            String userName = args.getString(0);
            // ���÷���
            this.registerPush(context, callbackContext, userName);
            return true;
        } else if (action.equals("unregisterPush")) {
            // ��ע�ᣬȡ������
            XGPushManager.unregisterPush(context);
            return true;
        } else if(action.equals("getToken")){
            this.getToken(context,callbackContext);
            return true;
        }
        // �ص�ʧ�ܵĺ���
        callbackContext.error("�÷���������!");
        return false;
    }

    // ����ע��
    private void registerPush(Context context,
                              final CallbackContext callbackContext, final String userName) {
        // ���ͱ���ע�᷽�������Ը��ݱ�����������
        XGPushManager.registerPush(context, userName, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                // �ص��ɹ��ĺ���
                Log.v("====>>>>>", userName);
                Log.d("TPush", "ע��ɹ����豸tokenΪ��" + data);
                callbackContext.success();
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                // �ص�ʧ�ܵĺ���
                callbackContext.error(msg);
            }
        });
        // ��XGPushManager.registerPush(context)�������汾��ע��ӿ�֮��������´���
        // ʹ��ApplicationContext
        // ����MIUI V6
        //Intent service = new Intent(context, XGPushService.class);
        //context.startService(service);
    }

    private void getToken(Context context , final CallbackContext callbackContext ){
        String token = XGPushConfig.getToken(context);
        Log.d("token------->",token);
        if(null!=token){
            callbackContext.success(token);
        }else{
            callbackContext.error("ע��ʧ��");
        }
    }

}