package straightforwardapps.novtwentyfive;

import android.database.Cursor;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Main_Page extends AppCompatActivity {

    //ListView spin;
    Spinner spin;
    ListView list;
    EditText txt;
    databaseboi mydb;
    TextView v, tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main__page);
        mydb = new databaseboi(this);
        spin = (Spinner) findViewById(R.id.spin);
        txt = (EditText) findViewById(R.id.text);
        list = (ListView) findViewById(R.id.list);
        v = (TextView) findViewById(R.id.view);
        tv = (TextView) findViewById(R.id.tv);

        dataread();

        //v.setText(spin.getSelectedItem().toString());

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"ITEM : "+l, Toast.LENGTH_SHORT).show();
                v.setText(spin.getItemAtPosition(i).toString());
                tv.setText(list.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }

        });
    }

    public void store(View v)
    {
        //StringBuffer fuck = new StringBuffer();

        String name = txt.getText().toString();
        if(name!="") {
            boolean result = mydb.insertData(name);

            if (result == true) {
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "FAILURE", Toast.LENGTH_SHORT).show();
            }
            dataread();
        }

        else
        {
            Toast.makeText(this, "ENTER TEXT", Toast.LENGTH_SHORT).show();
        }
    }

    public void deldel(View v)
    {
        int result = mydb.delData(tv.getText().toString());
        //Toast.makeText(this, " DELETED!", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "DELETED!", Toast.LENGTH_SHORT).show();
        dataread();
    }

    public void dataread()
    {
        Cursor res = mydb.getData();
        String[] lala = new String[res.getCount()];
        String[] lala1 = new String[res.getCount()];

        if(res!=null && res.getCount()>0)
        {
            int ok = 0;
            while (res.moveToNext())
            {
                lala[ok] = res.getString(1);
                lala1[ok] = res.getString(0);
                ok++;
            }
        }
        else
        {
            Toast.makeText(this, "Can't Read", Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter<String> z = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lala);
        spin.setAdapter(z);

        ArrayAdapter<String> z1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lala1);
        list.setAdapter(z1);
    }

}
