package jp.ac.gifu_u.info.onishi.shiftscheduler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HopePagerAdapter extends FragmentStateAdapter {
    private final String[] days;

    public HopePagerAdapter(FragmentActivity fa, String[] days) {
        super(fa);
        this.days = days;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return HopeDayFragment.newInstance(days[position]);
    }

    @Override
    public int getItemCount() {
        return days.length;
    }
}
