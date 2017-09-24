package nelk.io.crypton.helpers;

import android.support.v4.widget.SwipeRefreshLayout;

import nelk.io.crypton.models.app.User;
import nelk.io.crypton.recyclerview.BalanceAdapter;

import static nelk.io.crypton.helpers.MainActivityHelper.initializeBalanceView;


public class SwipeRefresher {
    private SwipeRefreshLayout swipeRefreshLayout;

    public User refreshItems(SwipeRefreshLayout onRefreshListener, User mUser, BalanceAdapter mBalanceAdapter) {
        this.swipeRefreshLayout = onRefreshListener;
        mUser.getPortfolios().clear();
        mUser = initializeBalanceView(mUser, mBalanceAdapter);
        mBalanceAdapter.notifyDataSetChanged();
        onItemsLoadComplete();
        return mUser;
    }

    private void onItemsLoadComplete() {
        this.swipeRefreshLayout.setRefreshing(false);
    }
}
