package ru.devegang.myvolleyapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String testUrl = "http://date.jsontest.com/"; //url, из которого мы будем брать JSON-объект
    RequestQueue mRequestQueue; // очередь запросов
    TextView textView2,textView3,textView4; // текстовые поля для отображения данных
    String date, time;
    long milisec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);

        mRequestQueue = Volley.newRequestQueue(this);
    }

    public void onGetjsonButtonClick (View view) {
        Toast.makeText(this, "Сейчас все будет", Toast.LENGTH_SHORT).show();
        getJson(testUrl);
    }

    private void getJson(String url) {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, //GET - API-запрос для получение данных
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //JSONObject weather = response.getJSONObject("main"),wind = response.getJSONObject("wind"); //получаем JSON-обьекты main и wind (в фигурных скобках - объекты, в квадратных - массивы (JSONArray).
                    date = response.getString("date");
                    time = response.getString("time");
                    milisec = response.getLong("milliseconds_since_epoch");
                    //temp = weather.getDouble("temp");
                    //windSpeed = wind.getDouble("speed");
                    // присваеваем переменным соответствующие значения из API
                    setValues(); // создадим метод setValues для присваивания значений переменным
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() { // в случае возникновеня ошибки
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request); // добавляем запрос в очередь
    }

    private void setValues() {
        textView2.setText("Current date: " + date);
        textView3.setText("milliseconds_since_epoch " + milisec);
        textView4.setText("Current time: " + time);

    }

}