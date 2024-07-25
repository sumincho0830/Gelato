package org.techtown.gelato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.gelato.Item;
import org.techtown.gelato.ItemAdapter;
import org.techtown.gelato.OnItemClickListener;
import org.techtown.gelato.R;

public class MarketFragment extends Fragment {
    RecyclerView recyclerView;
    ItemAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_market, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

//        adapter.addItem(postItem);
//        adapter.notifyDataSetChanged();


        //장바구니 추가 기능 구현하기
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ItemAdapter.ViewHolder holder, View view, int position) {

            }
        });
        //메서드 (Item item){ adapter.addItem(item); } 이렇게 나오고, adapter안에서는 다시 item으로 정보 세팅


        return root;
    }

    public void postItemOnMarket(Item item){
        adapter.addItem(item);
    }

    public void setCartButtonState(Item item){
        adapter.setCartButtonState(item);
    }
}
