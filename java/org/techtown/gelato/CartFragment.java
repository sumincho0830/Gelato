package org.techtown.gelato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.gelato.CartItemAdapter;
import org.techtown.gelato.R;
import org.techtown.gelato.Item;

public class CartFragment extends Fragment {

    public static CartItemAdapter adapter;
    RecyclerView recyclerView;
    Button payButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_carts, container, false);

        recyclerView = root.findViewById(R.id.cartRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CartItemAdapter();
        recyclerView.setAdapter(adapter);

        payButton = root.findViewById(R.id.payButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "결제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    public void addItemToCart(Item item){
        adapter.addItem(item);
    }
    public void removeItemFromCart(int position){
        adapter.removeItem(position);
    }
}
