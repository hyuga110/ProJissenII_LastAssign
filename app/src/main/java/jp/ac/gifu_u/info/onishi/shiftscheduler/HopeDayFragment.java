package jp.ac.gifu_u.info.onishi.shiftscheduler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HopeDayFragment extends Fragment {

    private static final String ARG_DAY = "day";

    public static HopeDayFragment newInstance(String day) {
        HopeDayFragment fragment = new HopeDayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());

        String day = getArguments() != null ? getArguments().getString(ARG_DAY) : "不明";
        tv.setText(getString(R.string.hope_table_title, day));

        tv.setTextSize(20f);
        tv.setPadding(40, 100, 40, 100);
        return tv;
    }
}
