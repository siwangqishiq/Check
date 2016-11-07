package com.cloudgy.check.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cloudgy.check.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 网络状态管理器
 * Created by panyi on 16/11/7.
 */
public class NetStateManager {

    private static NetStateManager mInstance = new NetStateManager();

    public static NetStateManager getInstance() {
        return mInstance;
    }

    public enum NetState{
        NONE,
        MOBILE,
        WIFI,
        UNKONOW
    }

    private NetState state = NetState.UNKONOW;

    private NetStateManager() {
        ConnectivityManager cm =
                (ConnectivityManager) MyApplication.getInstance().getApplicationContext().
                        getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        state = convert(activeNetwork);
    }

    private NetState convert(NetworkInfo info){
        if(info == null)
            return NetState.NONE;

        if(info.getType() == ConnectivityManager.TYPE_WIFI
                || info.getType() == ConnectivityManager.TYPE_VPN){
            return NetState.WIFI;
        }else if(info.getType() == ConnectivityManager.TYPE_MOBILE
                || info.getType() == ConnectivityManager.TYPE_MOBILE_DUN){
            return NetState.MOBILE;
        }else{
            return NetState.UNKONOW;
        }
    }

    /**
     * 监听网络情况改变
     */
    public interface IOnNetChange {
        /**
         * 当前可用的网络   为null时标示无网络可用
         *
         * @param activeNetInfo
         */
        void onNetStateChange(NetworkInfo activeNetInfo);
    }

    private List<IOnNetChange> list = new ArrayList<IOnNetChange>(3);


    public boolean addNetChangeListener(IOnNetChange action) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == action){
                return false;
            }
        }//end for i

        list.add(action);
        return true;
    }

    public void removeNetChangeListener(IOnNetChange action){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == action){
                list.remove(i);
                break;
            }
        }//end for i
    }

    public void netStateChange(NetworkInfo activeInfo){
        for(IOnNetChange lis:list){
            lis.onNetStateChange(activeInfo);
        }//end for each
        state = convert(activeInfo);

        //System.out.println("状态 = "+state);
    }

    public NetState getState() {
        return state;
    }
}//end class
