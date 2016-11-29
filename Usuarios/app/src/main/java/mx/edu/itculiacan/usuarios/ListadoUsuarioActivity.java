package mx.edu.itculiacan.usuarios;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static mx.edu.itculiacan.usuarios.R.id.lstListado;

public class ListadoUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuario);
        //Llenar el ListView
        BaseDatosSQL bd = new BaseDatosSQL(this);
        Cursor datos = bd.listado_usuarios();
        ListView listaUsuarios = (ListView) findViewById(R.id.lstListado);
        ArrayList<String> arrayDatos = new ArrayList<String>();
        if (datos.moveToFirst()){
            //recorre todos los registros del cursor
            for (int i=0;i<datos.getCount();i++){
                arrayDatos.add(datos.getString(3)+"("+datos.getString(1)+")");
                datos.moveToNext();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayDatos);
        listaUsuarios.setAdapter(adapter);
    }
}
