package vn.edu.usth.clothesapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.clothesapp.R;
import vn.edu.usth.clothesapp.activity.MainActivity;
import vn.edu.usth.clothesapp.activity.SettingActivity;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("ProfileFragment", "onCreateView: ProfileFragment is being created");
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageButton closeprofile = view.findViewById(R.id.close_profile_button);
        closeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity when the back button is clicked
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
