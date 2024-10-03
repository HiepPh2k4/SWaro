package vn.edu.usth.clothesapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThemeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.d("ThemeFragment", "onCreateView: ThemeFragment is being created");

        View view = inflater.inflate(R.layout.fragment_theme, container, false);

        view.findViewById(R.id.back_button2).setOnClickListener(v -> {
            if (getActivity() != null) {
                ((AppCompatActivity) getActivity()).getSupportFragmentManager().popBackStack();

                SettingActivity activity = (SettingActivity) getActivity();
                activity.showSettingsButtons();
            }
        });
        return view;
    }
}