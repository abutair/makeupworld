package com.example.mohammed.makeupword.ShoppingCart;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mohammed.makeupword.R;

import java.util.ArrayList;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

   private ArrayList<String > title = new ArrayList<>();
   private ArrayList<Integer> Image = new ArrayList<>();
   private ArrayList<String> desc = new ArrayList<>();
   private  ArrayList<String> price = new ArrayList<>();
   private Context mContext ;
   public ArrayList<CheckBox> checkedIteams = new ArrayList<>();

   ShoppingCartModel [] iteams;


    public ShoppingCartAdapter(Context mContext,ArrayList<String> title, ArrayList<Integer> image, ArrayList<String> desc, ArrayList<String> price ) {
        this.title = title;
        Image = image;
        this.desc = desc;
        this.price = price;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_item,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(Image.get(position))
                .into(holder.CartImage);

        holder.Title.setText(title.get(position));
        holder.Desc.setText(desc.get(position));
        holder.Price.setText(price.get(position));
        checkedIteams.add(holder.checkBox);


    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView Title ,Desc , Price;
           private ImageView CartImage;
           CheckBox checkBox ;

        public ViewHolder(View itemView) {
            super(itemView);


            Title = itemView.findViewById(R.id.Cart_title);
            Desc = itemView.findViewById(R.id.Cart_desc);
            Price = itemView.findViewById(R.id.Cart_price);
            CartImage = itemView.findViewById(R.id.Cart_image);
            checkBox = itemView.findViewById(R.id.checkbox);


        }


    } // end view holder


} // end Class
