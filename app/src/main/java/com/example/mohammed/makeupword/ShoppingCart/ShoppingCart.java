package com.example.mohammed.makeupword.ShoppingCart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.mohammed.makeupword.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShoppingCart extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String>mTitle= new ArrayList<>();
    private ArrayList<Integer>mImageUrls = new ArrayList<>();
private ArrayList<String>mDesc= new ArrayList<>();
private ArrayList<String>mPrice = new ArrayList<>();
private FirebaseFirestore firebaseFirestore ;
private FirebaseAuth firebaseAuth ;

  private   ShoppingCartAdapter adpater = new ShoppingCartAdapter(this,mTitle,mImageUrls,mDesc,mPrice );

    private String userId;

    private Button PlaceOrder;

    private CheckBox checkBox ;

    private String [] listIteams ;

    boolean [] checkedItemas  ;

    private  int Sum=0 ;

    ArrayList<Integer> mUserIteams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        firebaseAuth =FirebaseAuth.getInstance() ;

        firebaseFirestore = FirebaseFirestore.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();

        PlaceOrder = findViewById(R.id.Place_Order);


        checkBox = findViewById(R.id.checkbox);



        CartData();


     checkedItemas= new boolean[mTitle.size()];




            PlaceOrder.setOnClickListener(this);

    }  // end onCreate



    private void CartData()
    {
        firebaseFirestore.collection("Shopping Cart").whereEqualTo("User Id",userId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

           if (task.isSuccessful())
           {
               for (QueryDocumentSnapshot doc :task.getResult() )
               {
                   mTitle.add(doc.getData().get("Title").toString());
                 mDesc.add(doc.getData().get("Descrption").toString());
                  mPrice.add(doc.getData().get("Price").toString());
                  mImageUrls.add(Integer.parseInt(doc.getData().get("image").toString()));


                   initRecyclerView ();
               }

           }
           else
           {

               String e = task.getException().getMessage();
               Toast.makeText(ShoppingCart.this, e, Toast.LENGTH_LONG).show();
           }
            }
        });
    }




    private void initRecyclerView ()
    {
        RecyclerView recyclerView = findViewById(R.id.Shopping_list_item);

        recyclerView.setAdapter(adpater);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

    }


    @Override
    public void onClick(View view) {

        String str ;
        int a  ;
        double b ;

        try {
            for ( int i=0;i<adpater.checkedIteams.size();i++)
            {
                if(adpater.checkedIteams.get(i).isChecked())
                { a= mPrice.get(i).length();

                    str = mPrice.get(i).substring(1,a-1);

                   b= Double.parseDouble(str);
                     Sum += b;
                }
            }


            if(Sum>0) {
                Toast.makeText(this, "The Total is :" + Sum, Toast.LENGTH_LONG).show();

                Intent i = new Intent(this, CardForm.class);
                startActivity(i);

            }
            else
            {
                Toast.makeText(this, "Select Items" , Toast.LENGTH_LONG).show();

            }
        }
        catch (Exception e)
        {
          Toast.makeText(this,"Error :"+e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }




} // end Class
