package vn.edu.usth.clothesapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("ProfileFragment", "onCreateView: ProfileFragment is being created");
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        view.findViewById(R.id.back_button1).setOnClickListener(v -> {
            if (getActivity() != null) {
                ((AppCompatActivity) getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
}
