package vn.edu.usth.clothesapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.clothesapp.fragment.UploadImageFragment;
import vn.edu.usth.clothesapp.fragment.WardrobeFragment;

public class PagerAdapter extends FragmentStateAdapter {
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            default:
                return new WardrobeFragment();
            case 0:
                return new UploadImageFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
