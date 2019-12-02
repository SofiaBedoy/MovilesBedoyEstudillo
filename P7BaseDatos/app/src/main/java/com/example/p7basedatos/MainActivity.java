package com.example.p7basedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    //declaración de variables miembro
    private GridView mGridView;
    public ArrayList<String> ArrayOfName=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main, ArrayOfName));
        //Creamos objetos de la clase DatabaseHelper
        DatabaseHelper db = new DatabaseHelper(this);
        //CRUD
        //creamos objetos de la clase Contact
        Contact juan = new Contact(1, "Juan", "6641111111");
        Contact maria = new Contact(2, "Maria", "6642222222");
        Contact luis = new Contact(3, "Luis", "66433333333");
        Contact ana = new Contact(4, "Ana", "6644444444");

        db.addContact(juan);
        db.addContact(maria);
        db.addContact(luis);
        db.addContact(ana);
        //Borramos un contacto
        db.deleteContact(luis);

        //Obtener todos los contactos
        List<Contact> contacts= db.getAllContacts();
        for(Contact c: contacts){
            String cont=c.getName()+"\n"+c.getPhoneNumber();
            //Escribe contactos en  cont
            ArrayOfName.add(cont);
        }
        //Regressa la cuenta de contactos
        ArrayOfName.add("Cuenta: "+db.getContactsCount());
        //Actualizar un contacto
        db.updateContact(2, "Pedro","6641231234");
        Contact consulta = db.getContact(2);
        ArrayOfName.add(consulta.getName()+"\n"+consulta.getPhoneNumber());
        ListView listView = getListView();
        listView.setTextFilterEnabled(true);
    }
}
