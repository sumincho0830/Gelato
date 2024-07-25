package org.techtown.gelato;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> implements OnSelectCartItemListener {
    public static ArrayList<Item> cart = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cart_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = cart.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public void addItem(Item item){
        cart.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        cart.remove(position);
        notifyDataSetChanged(); //아직 잘 모르니까 일단 이걸로 쓰자
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        Item item;
        ImageView itemImage;
        CheckBox orderCheckBox;
        TextView itemName;
        TextView itemDetail;
        TextView itemPrice;
        Button plusButton;
        Button minusButton;
        TextView numOfItems;
        ImageButton cancelButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderCheckBox = itemView.findViewById(R.id.placeOrder_checkbox);
            itemName = itemView.findViewById(R.id.cartItemName_text);
            itemDetail = itemView.findViewById(R.id.cartItemDetail_text);
            itemPrice = itemView.findViewById(R.id.cartItemPrice_text);
            plusButton = itemView.findViewById(R.id.plus_button);
            minusButton = itemView.findViewById(R.id.minus_button);
            numOfItems = itemView.findViewById(R.id.numOfItem_text);
            cancelButton = itemView.findViewById(R.id.cancelOrder_button);
            itemImage = itemView.findViewById(R.id.itemImage);

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num =  Integer.parseInt(numOfItems.getText().toString());
                    num += 1;

                    numOfItems.setText(String.valueOf(num));
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num =  Integer.parseInt(numOfItems.getText().toString());
                    if(num > 0){
                        num -= 1;
                        numOfItems.setText(String.valueOf(num));
                    }
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(item.isAddedToCart == true){
                        item.isAddedToCart = false; //cart를 false로 바꾸고
                        MainActivity.setCartButtonState(item); //cart와 안에 저장된 marketposition을 넘겨준다
                        MainActivity.removeFromCart(item.getCartPosition()); //cartposition의 객체를 없애고
                    }
                }
            });
        }

        public void setItem(Item item){
            this.item = item;
            item.setCartPosition(getAdapterPosition());
            itemName.setText(item.getName());
            itemDetail.setText(item.getDetail());
            itemPrice.setText(item.getPrice());
            numOfItems.setText("1");
            itemImage.setImageResource(item.getImageRes());
        }
    }

}
