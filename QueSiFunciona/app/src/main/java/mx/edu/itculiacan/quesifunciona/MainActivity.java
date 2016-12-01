package mx.edu.itculiacan.quesifunciona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    // Declaramos el bjeto listvoew
    ListView listaPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaPersonas = (ListView) findViewById(R.id.lstPersonas);
        //Ejecutar obtieneDatos();
        obtieneDatos();
    }
    public void obtieneDatos(){
        AsyncHttpClient cliente = new AsyncHttpClient();
        String url = "https://randomuser.me/api/";
        RequestParams parametros = new RequestParams();
        parametros.put("results",20); //20 personas de la API
        cliente.get(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    //Cargar la lista de personas
                    cargalista(obtieneDatosJSON(new String(responseBody)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void cargalista(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listaPersonas.setAdapter(adapter);
    }
    public ArrayList<String>obtieneDatosJSON(String response){
        ArrayList<String> listado = new ArrayList<String>();
        try{
            JSONObject objetoJSON = new JSONObject(response);
            JSONArray arregloResultados = objetoJSON.getJSONArray("results");
            String texto;
            for (int i=0;i<arregloResultados.length();i++){
                JSONObject objetoNombre = new JSONObject(arregloResultados.getJSONObject(i).getString("name"));
                texto = objetoNombre.getString("first")+" "+objetoNombre.getString("last");
                listado.add(texto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listado;
    }

}
