package com.funwithandroid.shoppinglist;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.funwithandroid.shoppinglist.dbhandler.dataBaseOperation;
import com.funwithandroid.shoppinglist.recyleriewdapter.RecylerViewAdapter;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  RecylerViewAdapter recylerViewAdapter;
  ArrayList<ItemObject> listviewObjectsarray;
    com.funwithandroid.shoppinglist.dbhandler.dataBaseOperation dataBaseOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        dataBaseOperation=new dataBaseOperation(this);
        listviewObjectsarray=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listviewObjectsarray= (ArrayList<ItemObject>) dataBaseOperation.getAllItem();
        recylerViewAdapter = new RecylerViewAdapter(MainActivity.this, listviewObjectsarray);
        recyclerView.setAdapter(recylerViewAdapter);
        //card click and edit click is used to edit card data
        recylerViewAdapter.setOnItemClickListener(new RecylerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemview) {
            }
            //for remove the card from recylerView
            @Override
            public void onDeleteBtnClick(int position, View itemview) {
                dataBaseOperation.deleteItemDataBase(listviewObjectsarray.get(position).getItem_name(),
                        listviewObjectsarray.get(position).getItem_quality());
                listviewObjectsarray.remove(position);
                recylerViewAdapter.notifyDataSetChanged();

            }
             // for edit the card data in recylerView
            @Override
            public void onEditBtnClick(int position, View itemview) {
                int checkint=0;
                TextView item_name=itemview.findViewById(R.id.card_list_name);
                TextView item_quality=itemview.findViewById(R.id.card_list_quantity);
                CheckBox checkBox=itemview.findViewById(R.id.checkbox);
                if(checkBox.isChecked())
                    checkint=1;
                String item_string=item_name.getText().toString().trim();
                String quality_string=item_quality.getText().toString().trim();
                alertDialogEdit(position,item_string,quality_string,checkint);
            }
            //for checkbox clicked
            @Override
            public void onCheckBoxClicked(int position, View itemview) {
               CheckBox  checkBox=itemview.findViewById(R.id.checkbox);
                TextView item_name=itemview.findViewById(R.id.card_list_name);
                TextView item_quality=itemview.findViewById(R.id.card_list_quantity);
                String item_string=item_name.getText().toString().trim();
                String quality_string=item_quality.getText().toString().trim();
               //on marked it will execute
               if(checkBox.isChecked()){
                   ItemObject itemObject = new ItemObject(item_string, quality_string,1);
                   dataBaseOperation.updateItemTable(itemObject,item_string,quality_string);
               }//on unchecked
               else{
                   ItemObject itemObject = new ItemObject(item_string, quality_string,0);
                   dataBaseOperation.updateItemTable(itemObject,item_string,quality_string);
               }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.add_item:alertDialogInput();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public  void Initilization(){
    }
    //create a  alertDialog
    public  void  alertDialogInput(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.input_sereen, null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        alertDialog.setCancelable(false);
        final EditText item_name=view.findViewById(R.id.item_name);
        final EditText item_quality=view.findViewById(R.id.item_quality);
        ImageView positivebtn=view.findViewById(R.id.add_btn);
        ImageView negativebtn=view.findViewById(R.id.cancel_btn);
        positivebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quality_string=item_quality.getText().toString().trim();
                String item_string=item_name.getText().toString().trim();
                if(quality_string.equals(""))
                    quality_string="1";
                if(!item_string.equals("")) {
                    ItemObject itemObject=new ItemObject(item_string,quality_string,0);
                    listviewObjectsarray.add(itemObject);
                    recylerViewAdapter.notifyDataSetChanged();
                    dataBaseOperation.addToDataBase(itemObject);
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(MainActivity.this, "Name cant't be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        negativebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void alertDialogEdit(final int position, final String item_string, final String quality_string, final int checkboxinteger){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.input_sereen, null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        alertDialog.setCancelable(false);
        final EditText item_name=view.findViewById(R.id.item_name);
        final EditText item_quality=view.findViewById(R.id.item_quality);
        item_name.setText(item_string);
        item_quality.setText(quality_string);
        ImageView positivebtn=view.findViewById(R.id.add_btn);
        ImageView negativebtn=view.findViewById(R.id.cancel_btn);
        positivebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quality_string_new=item_quality.getText().toString().trim();
                String item_string_new=item_name.getText().toString().trim();
                if(quality_string_new.equals(""))
                    quality_string_new="1";
                if(!item_string_new.equals("")) {
                    ItemObject itemObject = new ItemObject(item_string_new, quality_string_new,checkboxinteger);
                    listviewObjectsarray.set(position, itemObject);
                    recylerViewAdapter.notifyDataSetChanged();
                    dataBaseOperation.updateItemTable(itemObject,item_string,quality_string);
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(MainActivity.this, "Name cant't be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        negativebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
