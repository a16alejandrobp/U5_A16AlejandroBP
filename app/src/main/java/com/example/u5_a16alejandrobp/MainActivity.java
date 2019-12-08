package com.example.u5_a16alejandrobp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_CAMARA = 1;
    TextView tv;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView)findViewById(R.id.tv);
        registerForContextMenu(tv);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        // Comprobamos se o menú contextual se lanzou sobre a etiqueta ou sobre
        // a lista
        if (v.getId() == R.id.tv){
            inflater.inflate(R.menu.menu_overflow, menu);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.action_camera) {
            Intent intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intento, REQUEST_CODE_CAMARA);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            // Ítems premidos sobre o TextView
            // Lanza un Toast coa opción do menú contextual que se seleccinou
            case R.id.tvItem1:
                Intent intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intento, REQUEST_CODE_CAMARA);
                return true;

            case R.id.tvItem2:
                finish();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_CODE_CAMARA) {
            if (resultCode == RESULT_OK) {

                if (data == null) {
                    Toast.makeText(this, "NON HAI IMAXE", Toast.LENGTH_LONG).show();
                    return;
                }


                iv.setImageBitmap((Bitmap) data.getExtras().get("data"));

            } else if (resultCode == RESULT_CANCELED) {
                // Foto cancelada
            } else {
                // Fallo na captura da foto.
            }
        }

    }
}
