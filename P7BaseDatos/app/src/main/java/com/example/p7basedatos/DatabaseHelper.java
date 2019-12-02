package com.example.p7basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    //Version de base de datos
    private static final int DATABASE_VERSION=1;
    //Nombre de base de datos
    private static final String DATABASE_NAME ="contactsManager";
    //Nombre de la tabla contactos
    private static final String TABLE_CONTACTS="contacts";
    //Nombre de las colunas de la tabla contactos
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PH_NO="phone_number";
    //Constructor
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Creando tabla
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_CONTACTS_TABLE=
                "CREATE TABLE "+TABLE_CONTACTS+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"
                +KEY_PH_NO+" TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);

        //CREATE TABLE contacts(id INTEGER PRIMARY KEY, name TEXT, phone_number TEXT)
    }
    //Actualización de la Base de Datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Borrar tablas si existen
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
        onCreate(db);
    }

    //CRUD (Create, Read, Update, Delete)
    //Agregar contacto

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());//Nombre del contacto
        values.put(KEY_PH_NO, contact.getPhoneNumber());//Teléfono del contacto
        //Insertar filas
        db.insert(TABLE_CONTACTS, null, values);
        db.close();//cerrar las conexiones de la base de datos
    }
    //Obtener contacto
    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CONTACTS,new String[]{KEY_ID,
                KEY_NAME, KEY_PH_NO}, KEY_ID+"=?", new String[]{String.valueOf(id)
        }, null, null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        Contact contact = new Contact(Integer.parseInt(
                cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));
        return contact;
    }
    //Obtener todos los contactos
    public List<Contact> getAllContacts(){
        ArrayList<Contact> allContacts=new ArrayList<Contact>();
        //Consulta que selecciona todos
        String selectQuery="SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Contact contact=new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                allContacts.add(contact);
                //Agregar contactos a la lista
            }while(cursor.moveToNext());
        }
        return allContacts;
    }

    //Actualizar contacto
    public void updateContact(int id, String name, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PH_NO, phone);
        //Actualizar fila
        db.update(TABLE_CONTACTS, values, KEY_ID+"=?",
                new String[]{String.valueOf(id)});

    }

    //Borrar un contacto
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});
    }

    //Obtiene la cuenta de contactos
    public int getContactsCount(){
        String countQuery="SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }
}