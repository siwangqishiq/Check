package com.cloudgy.check;

/**
 * Created by panyi on 2016/11/7.
 */
public class Account {
    private static Account mInstance;

    private static Object mLock = new Object();

    private Account(){
    }

    public Account getInstance(){
        synchronized (mLock){
            if(mInstance==null){
                mInstance = new Account();
            }
        }
        return mInstance;
    }

    public void initAccount(){

    }

    private String name;
    private String token;
}//end class
