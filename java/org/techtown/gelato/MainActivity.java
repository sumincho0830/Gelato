package org.techtown.gelato;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
/*

!!! Issues !!!

 1) 장바구니에서 Cancel Button을 누르면 MarketFragment에 있는 뷰의 장바구니 토글 버튼도 상태가 바뀌도록 연결하기.

*/
public class MainActivity extends AppCompatActivity implements OnPostListener {
    Toolbar toolbar;
    TabLayout tabs;
    ViewPager viewPager;
    public static MarketFragment marketFragment;
    AddItemFragment addItemFragment;
    public static CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //================== ToolBar =================
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar(); //ActionBar레이아웃을 동적으로 활용하려면 액션바를 설정해주어야 한다
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
        }

        //================== TabLayout & ViewPager ==================
        tabs = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        marketFragment = new MarketFragment();
        addItemFragment = new AddItemFragment();
        cartFragment = new CartFragment();

        adapter.addFragment(marketFragment,"Market");
        adapter.addFragment(addItemFragment, "Post");
        adapter.addFragment(cartFragment, "Cart");

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.black));
        tabs.setTabTextColors(getResources().getColor(R.color.black), getResources().getColor(R.color.black));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_searchbar);

        if(searchItem != null){
            LinearLayout searchBar = (LinearLayout) searchItem.getActionView();
            EditText searchTab = searchBar.findViewById(R.id.searchTab_text);
            ImageButton searchButton = searchBar.findViewById(R.id.search_button);

            //============== 검색 기능 구현 ==============
            if(searchTab != null){
                searchTab.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        Toast.makeText(getApplicationContext(), searchTab.getText()+ "검색됨", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }

//            searchTab.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                     //나중에 기능 구현하기
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });

        }

        return true;
    }

    public static void setCartButtonState(Item item) {
        if(marketFragment != null){
            marketFragment.setCartButtonState(item);
        }
    }

    //MarketFragment에 상품 포스트
    @Override
    public void postItem(Item item) {
        marketFragment.postItemOnMarket(item);
    }

    public static void addToCart(Item item) {
        cartFragment.addItemToCart(item);
    }


    public static void removeFromCart(int position) {
        cartFragment.removeItemFromCart(position);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        ArrayList<String> titileList = new ArrayList<>();

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            titileList.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titileList.get(position);
        }
    }



}