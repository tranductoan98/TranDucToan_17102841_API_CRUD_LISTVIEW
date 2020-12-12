package com.example.api_crud_mock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnClick, btEdit, btDelete;
    TextView tvDisplay;
    EditText Fname;
    EditText Lname;
    EditText Gender;
    EditText Salary;
    Button btAdd;
    int index;
    ListView lvUsers;
    CustomUsersAdapter adt;
    ArrayList<User> arrayList;

    String url = "https://5fd0db34b485ea0016eed851.mockapi.io/user/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvUsers =  findViewById(R.id.lvUsers);
        arrayList = new ArrayList<>();
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        btnClick = (Button) findViewById(R.id.btnClick);
        btAdd = findViewById(R.id.btAdd);
        btEdit = findViewById(R.id.btEdit);
        btDelete = findViewById(R.id.btDel);

        Fname = findViewById(R.id.txtFeName);
        Lname = findViewById(R.id.txtLastName);
        Gender = findViewById(R.id.txtGender);
        Salary = findViewById(R.id.txtSalary);

        adt = new CustomUsersAdapter(this, R.layout.item_users, arrayList);
        lvUsers.setAdapter(adt);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetArrayJson(url);
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostApi(url);
                lvUsers.removeAllViewsInLayout();
                arrayList.clear();
                Toast.makeText(MainActivity.this, "Vui lòng lấy lại danh sách!", Toast.LENGTH_SHORT).show();
            }
        });
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = arrayList.get(i);
                index = Integer.parseInt(user.getId());

            }
         });
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PutApi(url,index);
                lvUsers.removeAllViewsInLayout();
                arrayList.clear();
                Toast.makeText(MainActivity.this, "Vui lòng lấy lại danh sách!", Toast.LENGTH_SHORT).show();
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteApi(url,index);
                lvUsers.removeAllViewsInLayout();
                arrayList.clear();
                GetArrayJson(url);

            }
        });

    }
    private void GetArrayJson(final String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        String txtid = object.getString("id").toString();
                        String txtLname = object.getString("LASTNAME").toString();
                        String txtFname = object.getString("FIRSTNAME").toString();
                        String txtG = object.getString("GENDER").toString();
                        String txtS = object.getString("SALARY").toString();
                        User user = new User(txtid, txtFname, txtLname, txtG, txtS);
                        arrayList.add(user);
                        tvDisplay.setText(arrayList.get(i).getFIRSTNAME());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                String txFname =  Fname.getText().toString();
                String txLname = Lname.getText().toString();
                String txG = Gender.getText().toString();
                String txS = Salary.getText().toString();
                params.put("FIRSTNAME", txFname);
                params.put("LASTNAME", txLname);
                params.put("GENDER", txG);
                params.put("SALARY", txS);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void PutApi(String url, int i){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT,
                url + '/' + i, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                String txFname =  Fname.getText().toString();
                String txLname = Lname.getText().toString();
                String txG = Gender.getText().toString();
                String txS = Salary.getText().toString();
                params.put("FIRSTNAME", txFname);
                params.put("LASTNAME", txLname);
                params.put("GENDER", txG);
                params.put("SALARY", txS);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void DeleteApi(String url, int i){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + i, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
