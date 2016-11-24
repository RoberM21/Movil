package mx.edu.itculiacan.usuarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MuestraUsuarioActivity extends AppCompatActivity {

    EditText correo,clave,nombre,apellido;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_usuario);
    }
}
