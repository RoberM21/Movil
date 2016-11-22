package mx.edu.itculiacan.usuarios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ccdm09 on 22/11/16.
 */

public class BaseDatosSQL extends SQLiteOpenHelper{

    //Atributos / constantes
    public static final String DATABASE_NAME = "registrousuario.db";
    public static final String TABLE_NAME = "usuarios";
    public static final String COLUMN0 = "id";
    public static final String COLUMN1 = "email";
    public static final String COLUMN2 = "clave";
    public static final String COLUMN3 = "nombre";
    public static final String COLUMN4 = "apellido";



    //constructor
    public BaseDatosSQL(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME +
                                    "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                    "email TEXT,"+
                                    "clave TEXT,"+
                                    "nombre TEXT,"+
                                    "apellido TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }
}
