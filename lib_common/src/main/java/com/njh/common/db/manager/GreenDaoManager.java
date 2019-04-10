package com.njh.common.db.manager;

import android.content.Context;

import com.njh.common.db.dao.DaoMaster;
import com.njh.common.db.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * @author niejiahuan
 */
public class GreenDaoManager {
    private static String DB_NAME = "hx_task";
    private static MySqlLiteOpenHelper mySqlLiteOpenHelper;
    private static DaoSession mDaoSession;
    private static Database mDatabase;

    private GreenDaoManager()
    {

    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder
    {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static GreenDaoManager getInstance()
    {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    public void init(Context context)
    {
        mySqlLiteOpenHelper = new MySqlLiteOpenHelper(context,DB_NAME,null);
        mDatabase = mySqlLiteOpenHelper.getWritableDb();
        mDaoSession = new DaoMaster(mDatabase).newSession();
    }

    public DaoSession getmDaoSession()
    {
        return mDaoSession;
    }
}
