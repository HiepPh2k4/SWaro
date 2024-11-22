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

public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";
    private static final String REGISTER_URL = "http://10.0.2.2:5000/register";
    private final OkHttpClient client = new OkHttpClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        EditText useridEditText = view.findViewById(R.id.username_edit_text); // Sửa username thành userid
        EditText passwordEditText = view.findViewById(R.id.password_edit_text);
        EditText confirmPasswordEditText = view.findViewById(R.id.confirm_password_edit_text);
        Button registerButton = view.findViewById(R.id.register_submit_button);

        registerButton.setOnClickListener(v -> {
            String userid = useridEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (!userid.isEmpty() && !password.isEmpty() && password.equals(confirmPassword)) {
                registerUser(userid, password);
            } else {
                Toast.makeText(getActivity(), "Please check your entries", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void registerUser(String userid, String password) {
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
                .url(REGISTER_URL)
                .post(requestBody)
                .build();

        // Thực hiện API call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "API Call Failed: " + e.getMessage());
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getActivity(), "Registration failed. Try again.", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getActivity(), "Registration successful!", Toast.LENGTH_SHORT).show());
                } else {
                    String errorMessage = response.body() != null ? response.body().string() : "Unknown error";
                    Log.e(TAG, "API Response Error: " + errorMessage);
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getActivity(), "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}
