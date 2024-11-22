package vn.edu.usth.clothesapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import vn.edu.usth.clothesapp.R;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";
    private static final String LOGIN_URL = "http://10.0.2.2:5000/login";
    private final OkHttpClient client = new OkHttpClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText useridEditText = view.findViewById(R.id.username_edit_text); // Thay đổi cho phù hợp với Userid
        EditText passwordEditText = view.findViewById(R.id.password_edit_text);
        Button loginButton = view.findViewById(R.id.login_submit_button);

        loginButton.setOnClickListener(v -> {
            String userid = useridEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (!userid.isEmpty() && !password.isEmpty()) {
                loginUser(userid, password);
            } else {
                Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void loginUser(String userid, String password) {
        // Tạo JSON object cho dữ liệu gửi lên server
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Userid", userid);
            jsonObject.put("PasswordHash", password);
        } catch (JSONException e) {
            Log.e(TAG, "JSON Exception: " + e.getMessage());
            return;
        }

        // Tạo RequestBody cho POST request
        RequestBody requestBody = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        // Tạo Request
        Request request = new Request.Builder()
                .url(LOGIN_URL)
                .post(requestBody)
                .build();

        // Thực hiện API call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "API Call Failed: " + e.getMessage());
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getActivity(), "Login failed. Please try again.", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getActivity(), "Login successful!", Toast.LENGTH_SHORT).show());
                } else {
                    String errorMessage = response.body() != null ? response.body().string() : "Unknown error";
                    Log.e(TAG, "API Response Error: " + errorMessage);
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getActivity(), "Login failed: Wrong Userid or Password" , Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}
