package org.techtown.gelato;

import org.techtown.gelato.Item;

public interface OnCartAddRemoveListener {
    public void addToCart(Item item);

    public void removeFromCart(int position);
}
