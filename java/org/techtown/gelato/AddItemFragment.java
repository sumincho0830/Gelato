package org.techtown.gelato;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddItemFragment extends Fragment {
    String[] itemNames = {"pastry", "cake","lemon","oranges","pasta","picnic set", "raspberry","fig salad"};
    int[] itemImages = {R.drawable.pastry, R.drawable.cake, R.drawable.lemon, R.drawable.oranges,
            R.drawable.pasta, R.drawable.picnic, R.drawable.raspberry, R.drawable.salad};
    int selectedImgRes;
    EditText nameInput;
    EditText detailInput;
    EditText priceInput;
    ImageView itemImage;
    Button postButton;
    OnPostListener onPostListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnPostListener){
            onPostListener = (OnPostListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add, container, false);

        nameInput = root.findViewById(R.id.itemName_input);
        priceInput = root.findViewById(R.id.itemPrice_input);
        detailInput = root.findViewById(R.id.itemDetail_input);
        itemImage = root.findViewById(R.id.itemImage);
        postButton = root.findViewById(R.id.post_button);

//=================== 버튼 클릭 시 Item 인스턴스 생성 및 저장 ======================
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //이 버튼을 눌렸을 때 MainActivity에서 지정한 메서드가 실행되어야 한다.


                // 기능 1: 상품 아이템을 MarketFragment로 넘겨주기
                String name = nameInput.getText().toString();
                String price = priceInput.getText().toString();
                String detail = detailInput.getText().toString();
                int imgRes = selectedImgRes;

                if(name.isEmpty()){
                    showToast("상품명을 입력해주세요");
                }else if(detail.isEmpty()){
                    showToast("상품 설명을 입력해주세요");
                }else if(price.isEmpty()){
                    showToast("가격을 입력해주세요");
                }else{
                    //받은 정보로 새로운 아이템을 생성해서 저장
                    onPostListener.postItem(new Item(name, price, selectedImgRes,detail));
                    //여기서 리스너 승계...?
                }

                //기능 2: 버튼 텍스트 색 변경
            }
        });

// ======================== Spinner 설정 =========================
        Spinner spinner = root.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
            android.R.layout.simple_spinner_item, itemNames){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setTextColor(getResources().getColor(R.color.black));

                return view;

            }
        };


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //뷰 모듈? 위젯?

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedImgRes = itemImages[position]; //이걸 넘겨줘야 하는데...어디로?
                itemImage.setImageResource(selectedImgRes);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    public void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
