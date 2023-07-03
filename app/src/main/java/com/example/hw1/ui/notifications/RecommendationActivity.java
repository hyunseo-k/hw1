package com.example.hw1.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hw1.Cocktail2;
import com.example.hw1.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecommendationActivity extends AppCompatActivity {
    public Cocktail2 cocktail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        getSupportActionBar().setTitle("칵테일 추천");

        TextView cocktailTitle2 = (TextView) findViewById(R.id.cocktailTitle2);
        TextView cocktailMethod2 = (TextView) findViewById(R.id.cocktailMethod2);
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        ImageView cocktailImage2 = (ImageView) findViewById(R.id.cocktailImage2);
        Button buttonRandom2 = (Button) findViewById(R.id.buttonRandom2);

        buttonRandom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cocktail_api_key = getString(R.string.COCKTAIL_API_KEY);
                String dataType = "json";
                String queryUrl = "https://www.thecocktaildb.com/api/json/v1/" + cocktail_api_key + "/random.php";
                OkHttpClient client = new OkHttpClient();
                HttpUrl.Builder urlBuilder = HttpUrl.parse(queryUrl).newBuilder();

                String url = urlBuilder.build().toString();

                Request req = new Request.Builder().url(url).build();
                client.newCall(req).enqueue(new Callback(){
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        final String myResponse = response.body().string();
                        JSONObject jsonObject = null;
                        JSONArray drinks = null;
                        try {
                            jsonObject = new JSONObject(myResponse);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            drinks = jsonObject.getJSONArray("drinks");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        JSONObject finalDrinks = null;
                        try {
                            finalDrinks = drinks.getJSONObject((0));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        JSONObject finalDrinks1 = finalDrinks;
                        RecommendationActivity.this.runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                //cocktailTitle2.setText(myResponse);
                                Log.d("hello", String.valueOf(finalDrinks1));


                                Gson gson = new GsonBuilder().create();
                                final Cocktail2 data1 = gson.fromJson(String.valueOf(finalDrinks1),  Cocktail2.class);
                                        RecommendationActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                cocktailTitle2.setVisibility(View.VISIBLE);
                                                cocktailImage2.setVisibility(View.VISIBLE);
                                                cocktailMethod2.setVisibility(View.VISIBLE);
                                                textView7.setVisibility(View.VISIBLE);

                                                cocktailTitle2.setText(data1.getStrDrink());

                                                cocktailMethod2.setText(data1.getStrInstructions());

//                                                cocktailImage2.setVisibility(View.VISIBLE);
                                                if (data1.getStrDrinkThumb() == null) {
                                                    Glide.with(RecommendationActivity.this)
                                                            .load(R.drawable.cocktails)
                                                            .into(cocktailImage2);
                                                } else {
                                                    Glide.with(RecommendationActivity.this).load(data1.getStrDrinkThumb())
                                                            .into(cocktailImage2);

                                                }

//                                                try{
//                                                    String imageStr = data1.getStrImageSource();
//                                                    Log.d("yesimageStr", imageStr);
//                                                }catch (Exception e){
//                                                    cocktailImage2.setVisibility(View.VISIBLE);
//                                                }

//                                                String imageStr = data1.getStrImageSource();
//                                                Log.d("yesimageStr", imageStr);

//                                                if (imageStr == null){
//                                                    Log.d("noimageStr", imageStr);
//                                                    cocktailImage2.setVisibility(View.VISIBLE);
//                                                }
//                                                else{
//                                                    Log.d("yesimageStr", imageStr);
//                                                    Glide.with(RecommendationActivity.this).load(imageStr).into(cocktailImage2);
//                                                }
                                    }
                                });

                            }
                        });
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e){
                        e.printStackTrace();
                    }
                });




            }

        });

    }
}