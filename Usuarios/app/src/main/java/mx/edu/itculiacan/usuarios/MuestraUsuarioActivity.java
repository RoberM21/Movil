package mx.edu.itculiacan.usuarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MuestraUsuarioActivity extends AppCompatActivity {

    EditText correo, clave, nombre, apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_usuario);
        //MOSTRAR LOS DATOS DEL USUARIO
        correo = (EditText) findViewById(R.id.txtCorreoMuestra);
        clave = (EditText) findViewById(R.id.txtClaveMuestra);
        nombre = (EditText) findViewById(R.id.txtNombreMuestra);
        apellido = (EditText) findViewById(R.id.txtApellidoMuestra);
        Intent intento = getIntent(); //CAPTURA DATOS DEL INTENT ANTERIOR
        Bundle paquete = intento.getExtras(); //RECOGER EL PAQUETE DEL INTENT
        String datos[];

        if (!paquete.isEmpty())
        {
            datos = paquete.getStringArray("datos");
            correo.setText(datos[1]);
            clave.setText(datos[2]);
            nombre.setText(datos[3]);
            apellido.setText(datos[4]);
        }
        else {
            Toast.makeText(this, "NO SE PUDO LEER INFORMACION", Toast.LENGTH_SHORT).show();


        }

    }

    public void eliminarUsuario(View view) {
        correo = (EditText) findViewById(R.id.txtCorreoMuestra);
        BaseDatosSQL bd = new BaseDatosSQL(this);
        if (bd.eliminaUsuario(correo.getText().toString()) > 0) {
            Toast.makeText(this, "USUARIO ELIMINADO", Toast.LENGTH_SHORT).show();
            correo.setText("");
            clave.setText("");
            nombre.setText("");
            apellido.setText("");
        }
        else {
            Toast.makeText(this, "NO SE PUDO ELIMINAR", Toast.LENGTH_SHORT).show();
        }

    }

    public void editarUsuario(View view) {
        BaseDatosSQL bd = new BaseDatosSQL(this);
        correo = (EditText) findViewById(R.id.txtCorreoMuestra);
        clave = (EditText) findViewById(R.id.txtClaveMuestra);
        nombre = (EditText) findViewById(R.id.txtNombreMuestra);
        apellido = (EditText) findViewById(R.id.txtApellidoMuestra);
        if (bd.editar_usuario(correo.getText().toString(),clave.getText().toString(),nombre.getText().toString(),apellido.getText().toString())){
            Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"No se pudieron actualizar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void ListadoUsuarios(View view) {
        Intent intento = new Intent(this,ListadoUsuarioActivity.class);
        startActivity(intento);
    }
}
