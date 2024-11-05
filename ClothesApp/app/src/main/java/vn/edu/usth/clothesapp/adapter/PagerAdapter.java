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
            case 0:
                return new UploadImageFragment();
            case 1:
                return new WardrobeFragment();
            default:
                return new WardrobeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
