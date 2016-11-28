package mx.edu.itculiacan.usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by computadoradelmaestro on 22/11/16.
 */

public class BaseDatosSQL extends SQLiteOpenHelper{

    //Atributos / Constantes
    public static final String DATABASE_NAME = "registrousuarios.db";
    public static final String TABLE_NAME    = "usuarios";
    public static final String COLUMN0       = "id";
    public static final String COLUMN1       = "email";
    public static final String COLUMN2       = "clave";
    public static final String COLUMN3       = "nombre";
    public static final String COLUMN4       = "apellido";

    //Constructor
    public BaseDatosSQL(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    //Validación de usuarios
    public Cursor validaUsuario(String email,String clave){
        //Abrimos la BD
        SQLiteDatabase bd = this.getWritableDatabase();
        String[] parametros = {email,clave};
        String consulta = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN1+" = ? AND "+COLUMN2+" = ?";
        //EJECUTO LA CONSULTA
        Cursor cursor = bd.rawQuery(consulta,parametros);
        return cursor;
    }


    public boolean inserta_datos(String email,String clave,String nombre,String apellido){
        boolean respuesta = true;
        //Abrir la Base de datos
        SQLiteDatabase bd = this.getWritableDatabase(); //Abre o crea si no existe
        ContentValues contenido = new ContentValues();
        contenido.put(COLUMN1,email);
        contenido.put(COLUMN2,clave);
        contenido.put(COLUMN3,nombre);
        contenido.put(COLUMN4,apellido);
        //Si algo salío mal
        if (bd.insert(TABLE_NAME,null,contenido) == -1)
        {
            respuesta = false;
        }
        return respuesta;
    }

    public int eliminaUsuario(String email) {
        //conectar a la bd
        SQLiteDatabase bd = this.getWritableDatabase();
        String datos[] = {email};
        return bd.delete(TABLE_NAME, COLUMN1 + " =?", datos);
    }

    public boolean editar_usuario(String email,String clave,String nombre,String apellido)
    {
        boolean respuesta = false;
        SQLiteDatabase bd = this.getWritableDatabase(); //Conectamos a la BD
        ContentValues contenido = new ContentValues();
        contenido.put("email",email);
        contenido.put("clave",clave);
        contenido.put("nombre",nombre);
        contenido.put("apellido",apellido);
        String datos[] = {email};
        //Update: Cantidad de registros actualizados
        //Si no se actualiza ninguno, devuelve 0(cero).
        if (bd.update(TABLE_NAME, contenido,"email = ?",datos) > 0){
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME +
                              "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                              "email TEXT,"+
                              "clave TEXT,"+
                              "nombre TEXT,"+
                              "apellido TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }
}









