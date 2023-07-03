package com.example.hw1.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hw1.Cocktail;
import com.example.hw1.R;
import com.example.hw1.databinding.FragmentNotificationsBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        TextView cocktailTitle = (TextView) root.findViewById(R.id.cocktailTitle);
        ImageView cocktailImage = (ImageView) root.findViewById(R.id.cocktailImage);
        TextView cocktailMethod = (TextView) root.findViewById(R.id.cocktailMethod);
        RecyclerView cocktailIngreds = (RecyclerView) root.findViewById(R.id.cocktailIngreds);
        EditText cocktailSearch = (EditText) root.findViewById(R.id.cocktailSearch);
        Button buttonSearch = (Button) root.findViewById(R.id.buttonSearch);
        Button buttonRandom = (Button) root.findViewById(R.id.buttonRandom);

        ArrayList<Cocktail> cocktails = new ArrayList<Cocktail>();


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼이 눌렸으니 API 호출을 날리자.
                String searchQuery = cocktailSearch.getText().toString();
                String cocktailAPIKey = getString(R.string.COCKTAIL_API_KEY);
                String queryURL = "https://www.thecocktaildb.com/api/json/v1/" + cocktailAPIKey + "/search.php?s=" + searchQuery;

                OkHttpClient client = new OkHttpClient();

                //HttpUrl.Builder urlBuilder = HttpUrl.parse(queryURL).newBuilder();
                //urlBuilder.addQueryParameter("page", "2");

                Request req = new Request.Builder().url(queryURL).build();

                client.newCall(req).enqueue(new Callback() {

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        final String myResponse = response.body().string();

                        JSONArray drinks;
                        JSONObject jsonObject = null;
                        Gson gson = new GsonBuilder().create();
                        JSONObject firstDrink = null;

                        try {
                            jsonObject = new JSONObject(myResponse);
                        } catch (JSONException e){
                            Log.d("hello", "jsonObject 얻기 실패");
                            throw new RuntimeException(e);
                        }

//                        if (!jsonObject.has("drinks")) {
//                            Toast noResultToast = Toast.makeText(getContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT);
//                            noResultToast.show();
//                            Log.d("hello", "검색 결과가 없어서 return합니다~");
//                            return;
//                        }

                        try {
                            drinks = jsonObject.getJSONArray("drinks");
                        } catch (JSONException e){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // 여기에 코드를 입력하세요
                                    Toast noResultToast = Toast.makeText(getContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT);
                                    noResultToast.show();
                                    Log.d("hello", "검색 결과가 없어서 return합니다~");
                                }
                            });

                            return;
                        }

                        try {
                            firstDrink = drinks.getJSONObject(0);
                        } catch (JSONException e) {
                            Log.d("hello", "firstDrink");
                            throw new RuntimeException(e);
                        }


                        if (getActivity() != null) {
                            JSONObject finalFirstDrink = firstDrink;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // 여기에 코드를 입력하세요
                                    Log.d("hello", String.valueOf(finalFirstDrink));

                                    Cocktail resultCocktail = gson.fromJson(String.valueOf(finalFirstDrink), Cocktail.class);

                                    // cocktailTitle
                                    cocktailTitle.setText(resultCocktail.getStrDrink());
                                    cocktailTitle.setVisibility(View.VISIBLE);

                                    // cocktailImage
                                    cocktailImage.setVisibility(View.VISIBLE);
                                    if (resultCocktail.getStrDrinkThumb() == null) {
                                        Glide.with(getContext())
                                                .load(R.drawable.cocktails)
                                                .into(cocktailImage);
                                    } else {
                                        Glide.with(getContext())
                                                .load(resultCocktail.getStrDrinkThumb())
                                                .into(cocktailImage);
                                    }

                                    // cocktailMethod
                                    cocktailMethod.setVisibility(View.VISIBLE);
                                    cocktailMethod.setText(resultCocktail.getStrInstructions());

                                    // cocktailIngreds
                                    if (resultCocktail.getStrIngredients().size() != 0) {
                                        cocktailIngreds.setVisibility(View.VISIBLE);
                                        Toast ingredsToast = Toast.makeText(getContext(), resultCocktail.getStrIngredients().toString(), Toast.LENGTH_SHORT);
                                        ingredsToast.show();
                                    }


                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }
                });

//                String searchQuery = cocktailSearch.getText().toString();
//                String cocktailAPIKey = getString(R.string.COCKTAIL_API_KEY);
//                String queryURL = "https://www.thecocktaildb.com/api/json/v1/" + cocktailAPIKey + "/search.php?s=" + searchQuery;
//
//                //Toast toast = Toast.makeText(getContext(), "러니ㅓ일알", Toast.LENGTH_SHORT);
//                //toast.show();
//
//
//                try {
//                    URL url = new URL(queryURL);
//
//                    InputStream is = url.openStream();
//                    InputStreamReader isr = new InputStreamReader(is);
//                    BufferedReader reader = new BufferedReader(isr);
//
//                    StringBuffer buffer = new StringBuffer();
//                    String line = reader.readLine();
//                    while (line != null) {
//                        buffer.append(line + "\n");
//                        line = reader.readLine();
//                    }
//
//                    // 데이터 파싱하기
//                    String jsonString = buffer.toString();
//                    JSONObject jsonObject = new JSONObject(jsonString);
//                    JSONArray drinksArray = jsonObject.getJSONArray("drinks");
//
//                    for (int i = 0; i < drinksArray.length(); i++) {
//                        JSONObject cocktail = drinksArray.getJSONObject(i);
//                        String strDrink = cocktail.getString("strDrink");
//                        String strImageSource = cocktail.getString("strImageSource");
//                        String strInstructions = cocktail.getString("strInstructions");
//                        String[] strIngredients = new String[15];
//                        String[] strMeasures = new String[15];
//
//                        int index = 1;
//                        while (true) { // strIngredients 채우기
//                            String nextIngredient = cocktail.getString("strIngredient" + Integer.toString(index));
//                            if (nextIngredient == null || index > 15) {
//                                break;
//                            }
//                            strIngredients[index - 1] = nextIngredient;
//                            index++;
//                        }
//                        while (true) { // strMeasures 채우기
//                            String nextIngredient = cocktail.getString("strIngredient" + Integer.toString(index));
//                            if (nextIngredient == null || index > 15) {
//                                break;
//                            }
//                            strIngredients[index - 1] = nextIngredient;
//                            index++;
//                        }
//
//                        cocktails.add(new Cocktail(strDrink, strImageSource, strInstructions, strIngredients, strMeasures));
//                        Log.d("cocktailSearchResult", String.valueOf(cocktails));
//                        Toast toast2 = Toast.makeText(getContext(), "wpeosfsdaffsdfsdf", Toast.LENGTH_SHORT);
//                        toast2.show();
//                    }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}