package com.example.mohammed.makeupword;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mohammed.makeupword.ShoppingCart.ShoppingCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoreyViewHolder extends RecyclerView.Adapter<CategoreyViewHolder.ViewHolder>  {
private ArrayList<Integer> mImages= new ArrayList<>();
private ArrayList<String> Title = new ArrayList<>();
private ArrayList<String> Desc = new ArrayList<>();
private ArrayList<String> Price = new ArrayList<>();
private Context mContext;
    private StorageReference storageReference ;
    private FirebaseAuth firebaseAuth ;
    private FirebaseFirestore firebaseFirestore;

    private String userId;

    public CategoreyViewHolder(Context context ,ArrayList<Integer> mImages, ArrayList<String> title, ArrayList<String> desc, ArrayList<String> price) {
        this.mContext = context;
        this.mImages = mImages;
        Title = title;
        Desc = desc;
        Price = price;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list__item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.imageView);
        holder.Title.setText(Title.get(position));

        holder.Price.setText(Price.get(position));
        holder.desc.setText(Desc.get(position));
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser() ;
                firebaseFirestore = FirebaseFirestore.getInstance();
                storageReference= FirebaseStorage.getInstance().getReference();
                firebaseAuth =FirebaseAuth.getInstance() ;

                if (currentUser!= null) {
                    userId = firebaseAuth.getCurrentUser().getUid();
                    String image =mImages.get(position).toString();
                    Map<String,String> userMsp = new HashMap<>();
                    userMsp.put("Title",Title.get(position));
                    userMsp.put("image",image);
                    userMsp.put("Price",Price.get(position));
                    userMsp.put("User Id",userId);
                    userMsp.put("Descrption",Desc.get(position));

                    firebaseFirestore.collection("Shopping Cart").add(userMsp).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(mContext," Added to your shopping cart ",Toast.LENGTH_LONG).show();
                                Intent i= new Intent(mContext,ShoppingCart.class);
                                mContext.startActivity(i);

                            }

                            else
                            {
                                String e = task.getException().getMessage();
                                Toast.makeText(mContext, e, Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(mContext,"Current User is null "  ,Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return Title.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView Title ;
        TextView desc ;
        TextView Price ;
        Button b;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.categorey_image);
            Title = itemView.findViewById(R.id.categorey_list_title);
            desc = itemView.findViewById(R.id.categorey_list_desc);
            Price= itemView.findViewById(R.id.categorey_list_price);
            b = itemView.findViewById(R.id.AddToCart);
        }
    }





} // end Class
