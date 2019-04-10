package com.njh.common.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.njh.common.db.dao.DaoMaster;
import com.njh.common.db.dao.UserModelDao;

import org.greenrobot.greendao.database.Database;

/**
 * @author niejiahuan
 */
public class MySqlLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySqlLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        /**
         * 添加数据库表之后将操作对象填入该方法中，避免出现数据库版本升级之后数据丢失问题
         */
        Log.i("greendao", "oldVersion:" + oldVersion + ",newVersion" + newVersion);
        MigrationHelper.getInstance().migrate(db, UserModelDao.class);
    }
}