package mx.edu.itculiacan.usuarios;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroUsuariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void registroUsuario(View view) {
        //Extraemos los datos de la vista
        EditText email    = (EditText) findViewById(R.id.txtCorreo);
        EditText clave    = (EditText) findViewById(R.id.txtClave);
        EditText nombre   = (EditText) findViewById(R.id.txtNombre);
        EditText apellido = (EditText) findViewById(R.id.txtApellido);
        //Creamos un objeto de nuestra clase base de datos
        BaseDatosSQL bd = new BaseDatosSQL(this);
        if(bd.inserta_datos(email.getText().toString(),clave.getText().toString(),nombre.getText().toString(),apellido.getText().toString()))
        {
            Toast.makeText(this, "Usuario Registrado con éxito", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Usuario no Registrado", Toast.LENGTH_SHORT).show();
        }
    }
}













