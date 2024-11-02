package vn.edu.usth.clothesapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import vn.edu.usth.clothesapp.R;
import vn.edu.usth.clothesapp.activity.MainActivity;
import vn.edu.usth.clothesapp.activity.SettingActivity;

public class LanguageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("LanguageFragment", "onCreateView: LanguageFragment is being created");

        View view = inflater.inflate(R.layout.fragment_language, container, false);

        Spinner mySpinner = view.findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);

//        view.findViewById(R.id.back_button3).setOnClickListener(v -> {
//            if (getActivity() != null) {
//                ((AppCompatActivity) getActivity()).getSupportFragmentManager().popBackStack();
//
//                SettingActivity activity = (SettingActivity) getActivity();
//                activity.showSettingsButtons();
//            }
//        });
        ImageButton closelanguage = view.findViewById(R.id.close_language_button);
        closelanguage.setOnClickListener(new View.OnClickListener() {
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
