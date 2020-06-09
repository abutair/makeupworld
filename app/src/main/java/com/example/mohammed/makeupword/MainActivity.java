package com.example.mohammed.makeupword;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mohammed.makeupword.CategoryGridView.CustomGridViewActivity;
import com.example.mohammed.makeupword.Register.SetupActivity;
import com.example.mohammed.makeupword.Register.loginActivity;
import com.example.mohammed.makeupword.ShoppingCart.ShoppingCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private FirebaseAuth mAuth ;

private String userId;
private FirebaseFirestore firebaseFirestore;

private FlipperLayout flipperLayout ;

     CircleImageView userImage ;
    GridView androidGridView;
  int[] gridViewImageId = { R.drawable.face_icon , R.drawable.eye_icon , R.drawable.lips_icon
            ,R.drawable.nail_polish_icon , R.drawable.prush_icon};


 private    List<FlashDeadProudct> productList;

  private   RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
         firebaseFirestore = FirebaseFirestore.getInstance();
        flipperLayout  = findViewById(R.id.fliper_view);



        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setImageAds();

        recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false));

        productList = new ArrayList<>();

        category();

    } // end onCreate


    private  void category()
    {
        //adding some items to our list
        productList.add(
                new FlashDeadProudct(
                        1,
                        "-10%",
                        "20$",
                        R.drawable.a
                ));

        productList.add(
                new FlashDeadProudct(
                        2,
                        "-15 %",
                        "30$",
                        R.drawable.b
                ));

        productList.add(
                new FlashDeadProudct(
                        3,
                        " - 25%",
                        "20$",
                        R.drawable.c));

        productList.add(
                new FlashDeadProudct(
                        3,
                        " - 10%",
                        "20$",
                        R.drawable.d));

        FalshDeadlAdapter adapter = new FalshDeadlAdapter(this, productList);

        recyclerView.setAdapter(adapter);
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(MainActivity.this, gridViewImageId);
        androidGridView=findViewById(R.id.GridView);
        androidGridView.setAdapter(adapterViewAndroid);

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(MainActivity.this,ItemsToBuy.class);


                i.putExtra("id",position);
                startActivity(i);

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.action_logout_btn :

                logout();
                return true;
            case  R.id.action_settings_btn :
                Intent a = new Intent(getApplicationContext(),SetupActivity.class);
                startActivity(a);




            default: return false ;

        }


    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        Intent i ;
        int id = item.getItemId();

        if (id == R.id.home) {
            i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();

        }
        else if (id == R.id.myAccount) {
            i = new Intent(MainActivity.this,SetupActivity.class);
            startActivity(i);

        }
        else if (id == R.id.ShopingCart) {

            i = new Intent(MainActivity.this, ShoppingCart.class);
           startActivity(i);
        }

        else
            if (id==R.id.logout_nav)
            {
                logout();
            }


        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser() ;

        if (currentUser == null )
        {
            Intent i = new Intent(getApplicationContext(),loginActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            updateNaveHeader();
        }


    } // end method


    private void logout() {
        mAuth.signOut();
        sendTologin();

    }


    private void sendTologin() {
        Intent i = new Intent(MainActivity.this,loginActivity.class);
        startActivity(i);
        finish();
    }


    public void setImageAds()
    {
        int imageAds []   = {R.drawable.ad_one,R.drawable.ad_two,R.drawable.ad_there
        ,R.drawable.ad_four , R.drawable.ad_five , R.drawable.ad_six};
        for (int i=0 ; i<imageAds.length ;i++ )
        {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageDrawable(imageAds[i]);
            flipperLayout.addFlipperView(view);

        }
    }  // end method




    private  void updateNaveHeader()
    {

        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);

        final TextView username=headerView.findViewById(R.id.username_nav);
      userImage = headerView.findViewById(R.id.userImage_nav);
         try {
            userId = mAuth.getCurrentUser().getUid();
            firebaseFirestore.collection("Users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful())
                    {

                            try{
                                String name= task.getResult().getString("name") ;
                                String image= task.getResult().getString("image");
                                RequestOptions placeholderRequset = new RequestOptions() ;
                                placeholderRequset.placeholder(R.drawable.dark_default);


                                Glide.with(MainActivity.this ).setDefaultRequestOptions(placeholderRequset).load(image).into(userImage);

                                username.setText(name);





                            }catch (Exception e)
                            {
                                Toast.makeText(MainActivity.this,"Error :-"+e.getMessage(),Toast.LENGTH_LONG).show();

                            }



                    }
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

        }








    }




} // end Class
