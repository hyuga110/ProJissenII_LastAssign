package jp.ac.gifu_u.info.onishi.shiftscheduler;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import java.util.HashSet;
import java.util.Set;
public class SyncScrollView extends HorizontalScrollView {
    private static final Set<SyncScrollView> scrollViews = new HashSet<>();

    public SyncScrollView(Context context) {
        super(context);
        init();
    }

    public SyncScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SyncScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scrollViews.add(this);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        syncScroll(l);
    }

    private void syncScroll(int scrollX) {
        for (SyncScrollView scrollView : scrollViews) {
            if (scrollView != this) {
                scrollView.scrollTo(scrollX, 0);
            }
        }
    }
}
