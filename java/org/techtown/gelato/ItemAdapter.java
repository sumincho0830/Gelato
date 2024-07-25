package org.techtown.gelato;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements OnItemClickListener{

    ArrayList<Item> items = new ArrayList<>();

    OnItemClickListener listener; //아직 기능 없음
    OnCartAddRemoveListener onCartAddRemoveListener;
    public static boolean toggleState;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Item item){
        items.add(item);
        notifyDataSetChanged();
    }
    public void setItems(ArrayList<Item> items){
        this.items = items;
    }

    public void setItem(int position, Item item){
        items.set(position, item);
    }
    public Item getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position); //setOnItemClickListener로 설정된 그 메서드에 인자 넘겨주기
        }
    }

    public void setCartButtonState(Item item){
        //n번째
        items.get(item.marketPosition).isAddedToCart = item.isAddedToCart;
        notifyDataSetChanged(); //이 위치에 있는 아이템이 변경되었음을 알림
    }

    static public class ViewHolder extends RecyclerView.ViewHolder{
        Item item;
        TextView itemName;
        TextView detail;
        TextView price;
        ImageView itemImage;
        ToggleButton addToCartButton;
        boolean isToggled = false;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener adapterListener) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName_label);
            detail = itemView.findViewById(R.id.detail_text);
            price = itemView.findViewById(R.id.price_text);
            addToCartButton = itemView.findViewById(R.id.addToCart_button);
            itemImage = itemView.findViewById(R.id.itemImage);

            //이게..맞나...?
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();//현재 생성된 ViewHolder의 adapter내에서의 번호?
                    if(adapterListener != null){
                        adapterListener.onItemClick(ViewHolder.this, view, position); //아직 기능 없음
                    }
                }
            });

            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(item.isAddedToCart == false){ //카트에 추가가 안되어있다면
                        item.isAddedToCart = true;
                        MainActivity.addToCart(item);
                        addToCartButton.setChecked(true);

                    }else{
                        item.isAddedToCart = false;
                        MainActivity.removeFromCart(item.getCartPosition());
                        addToCartButton.setChecked(false);
                    }
                }
            });

        }

        public void setItem(Item item){
            this.item = item;
            itemName.setText(item.getName());
            detail.setText(item.getDetail());
            price.setText("₩ " + item.getPrice());
            itemImage.setImageResource(item.getImageRes());
            item.marketPosition = getAdapterPosition();
            addToCartButton.setChecked(item.isAddedToCart);
        }

    }
}
