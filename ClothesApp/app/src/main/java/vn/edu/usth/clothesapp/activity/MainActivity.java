package vn.edu.usth.clothesapp.activity;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.clothesapp.ApiService.RetrofitClient;
import vn.edu.usth.clothesapp.db.ClothingItem;
import vn.edu.usth.clothesapp.ApiService.ServiceApi;
import vn.edu.usth.clothesapp.R;
import vn.edu.usth.clothesapp.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {
//    ViewPager2 viewPager2;
    BottomNavigationView bottomNavigationView;
    WebView webView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceApi serviceApi = RetrofitClient.getClient().create(ServiceApi.class);

        serviceApi.getClothingItems().enqueue(new Callback<List<ClothingItem>>() {
            @Override
            public void onResponse(Call<List<ClothingItem>> call, Response<List<ClothingItem>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<ClothingItem> clothingItems = response.body();
                    for (ClothingItem item : clothingItems){
                        Log.d(TAG, "Item Name" + item.getItemName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ClothingItem>> call, Throwable t) {
                Log.e(TAG, "Error fetching data: " + t.getMessage());
            }
        });


        // Thêm một item quần áo mới
        ClothingItem newItem = new ClothingItem("C002", "u002", "Blue T-Shirt", "Top", "Casual",
                "Blue", "Cotton", "M", "Adidas", "Winter",
                "Everyday", "http://example.com/clothing.jpg",
                "http://example.com/model.glb");
        serviceApi.addClothingItem(newItem).enqueue(new Callback<ClothingItem>() {
            @Override
            public void onResponse(Call<ClothingItem> call, Response<ClothingItem> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Added Item Name: " + response.body().getItemName());
                }
            }

            @Override
            public void onFailure(Call<ClothingItem> call, Throwable t) {
                Log.e(TAG, "Error adding item: " + t.getMessage());
            }
        });


        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        // Load local HTML file from assets
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/test.html");
        // viewPager2 = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        PagerAdapter adapter = new PagerAdapter(this);
       /* viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(3);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.wardrobe).setChecked(true);
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.upload_image).setChecked(true);
                        break;
                }
            }
        });
*/
        /*bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.wardrobe) {
                    viewPager2.setCurrentItem(0, true);
                } else if (itemId == R.id.upload_image) {
                    viewPager2.setCurrentItem(1, true);
                }
                return true;
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.setting_button) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}