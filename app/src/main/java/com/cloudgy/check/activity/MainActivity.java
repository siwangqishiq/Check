package com.cloudgy.check.activity;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PowerManager;

import com.cloudgy.check.R;
import com.cloudgy.check.core.NetStateManager;

public class MainActivity extends BaseActivity {

    private NetStateManager.IOnNetChange mNetChange = new NetStateManager.IOnNetChange(){
        @Override
        public void onNetStateChange(NetworkInfo activeNetInfo) {
//            String s = activeNetInfo==null?"无网络可用":activeNetInfo.getTypeName();
//            System.out.println("之前状态 = "+NetStateManager.getInstance().getState()+"state = "+s);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetStateManager.getInstance().addNetChangeListener(mNetChange);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStateManager.getInstance().removeNetChangeListener(mNetChange);
    }
}//end class
