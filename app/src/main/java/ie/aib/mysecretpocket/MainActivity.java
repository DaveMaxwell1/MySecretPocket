package ie.aib.mysecretpocket;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView goalsView;
    private RecyclerView transactionsView;
    private TransactionsAdapter transactionsAdapter;
    private GoalsAdapter goalsAdapter;
    private List<Transaction> transactionList;
    private List<Goal> goalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        // Setup Goals
        goalsView = (RecyclerView) findViewById(R.id.goals_view);
        goalList = new ArrayList<>();
        goalsAdapter = new GoalsAdapter(this, goalList);

        RecyclerView.LayoutManager goalLayoutManager = new GridLayoutManager(this, 1);
        goalsView.setLayoutManager(goalLayoutManager);
        goalsView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        goalsView.setItemAnimator(new DefaultItemAnimator());
        goalsView.setAdapter(goalsAdapter);

        prepareGoals();

        // Setup Transaction list
        transactionsView = (RecyclerView) findViewById(R.id.transactions_view);

        transactionList = new ArrayList<>();
        transactionsAdapter = new TransactionsAdapter(this, transactionList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        transactionsView.setLayoutManager(mLayoutManager);
        transactionsView.setAdapter(transactionsAdapter);

        prepareTransactions();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }

    /**
     * Adding some sample goals for testing
     * Modify below to add new goals
     */
    private void prepareGoals() {
        Goal a = new Goal("Holiday in Brazil", 500.00, 100);
        goalList.add(a);

        Goal b = new Goal("New Computer", 900.00, 100);
        goalList.add(b);

        goalsAdapter.notifyDataSetChanged();
    }

    /**
     * Adding a few transactions for testing
     * Modify below to add more transactions
     */
    private void prepareTransactions() {

        Date date = new Date();

        Transaction a = new Transaction("Shop 1", date, 123.00 );
        transactionList.add(a);

        Transaction b = new Transaction("Shop 2", date, 132.11 );
        transactionList.add(b);

        Transaction c = new Transaction("Shop 3", date, 400.55 );
        transactionList.add(c);

        transactionsAdapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
