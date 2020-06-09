package com.example.mohammed.makeupword;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FalshDeadlAdapter  extends RecyclerView.Adapter<FalshDeadlAdapter.FalshDealsViewHolder> {

    private Context mCtx;

    //we are storing all the products in a list
    private List<FlashDeadProudct> productList;


    public FalshDeadlAdapter(Context mCtx, List<FlashDeadProudct> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @NonNull
    @Override
    public FalshDealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.flash_deals_items, null);
        return new FalshDealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FalshDealsViewHolder holder, final int position) {
        FlashDeadProudct product = productList.get(position);

        holder.textViewDiscount.setText(product.getDiscount());
        holder.textViewPrice.setText(product.getPrice());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));


        /// on Click listsner
        holder.ParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, "" + position,
                        Toast.LENGTH_SHORT).show();            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();

    }

    class FalshDealsViewHolder extends RecyclerView.ViewHolder {

        TextView  textViewDiscount, textViewPrice;
        ImageView imageView;
       LinearLayout ParentLayout ;

       CheckBox checkBox ;

        public FalshDealsViewHolder(View itemView) {
            super(itemView);

            textViewDiscount = itemView.findViewById(R.id.redBackground);
            textViewPrice = itemView.findViewById(R.id.price_flash_deal);
            imageView = itemView.findViewById(R.id.flash_deals_image);
           ParentLayout = itemView.findViewById(R.id.parent_layout);
            checkBox=itemView.findViewById(R.id.checkbox);
        }
    } // end FalshDealsViewHolder Class


} // end Class
