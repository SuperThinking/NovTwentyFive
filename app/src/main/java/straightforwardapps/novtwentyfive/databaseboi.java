package straightforwardapps.novtwentyfive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vishal on 26-11-2017.
 */

public class databaseboi extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "firstdb";
    public static final String TABLE_NAME = "firsttable";

    public static final String col_1 = "DATA";

    public databaseboi(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String data)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues op = new ContentValues();
        op.put(col_1, data);
        long result = db.insert(TABLE_NAME, null, op);
        db.close();

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * from "+TABLE_NAME,null);
        return res;
    }

    public int delData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(TABLE_NAME, "ID=?", new String[]{id});

        return result;
    }
}
