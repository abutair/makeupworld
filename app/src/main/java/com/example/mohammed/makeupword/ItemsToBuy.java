package com.example.mohammed.makeupword;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ItemsToBuy extends AppCompatActivity {
 //StorageReference reference;
  //  FirebaseFirestore firestore;
   private ArrayList<Integer> mImage= new ArrayList<>();
   private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_to_buy);

        Intent  i = this.getIntent();
        int s = i.getIntExtra("id",0);

        switch (s)
        { case 0 : s =0;
            FaceMakeupData();
            break;

            case 1: s=1;
                EyeMakeupData();
                break;
            case 2: s =2;
                LipsMakeupData();
                break;
            case 3: s =3;
                NailMakeupData();
                break;
             case 4: s=4;
             BrushMakeupData();
             break;

        }




    } // end OnCreate


    private void FaceMakeupData()
    {
        mImage.add(R.drawable.anti_redness_primer);
        mTitle.add("PROFESSIONAL COLOR CORRECTING PRIMERS Anti-Redness Primer");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$13.00");

        mImage.add(R.drawable.blur_foundation);
        mTitle.add("blur_foundation");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$15.00");


        mImage.add(R.drawable.concealer);
        mTitle.add("concealer");
        mDesc.add("Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$22.00");


        mImage.add(R.drawable.lock_makeup_primer);
        mTitle.add("Pro-Glow Lock Makeup Primer");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");

        mPrice.add("$10.00");


        mImage.add(R.drawable.magic_lumi_highlighting_concealer);
        mTitle.add("Magic Lumi Light Infusing Primer");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$20.0");

        mImage.add(R.drawable.magic_lumi_light_lnfusing_primer);
        mTitle.add("Magic Lumi Light Infusing Primer");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$20.00");


        mImage.add(R.drawable.pro_matte_foundation);
        mTitle.add("Pro-Matte Foundation");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$11.0");


        mImage.add(R.drawable.super_blendable_compact_makeup);
        mTitle.add("Super-Blendable Compact Makeup");
        mDesc.add("Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$13.0");

        mImage.add(R.drawable.super_blendable_multi_use_concealer);
        mTitle.add("Super-Blendable Multi-Use Concealer\n");
        mDesc.add("Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$22.0");

        initRecyclerView ();
    }


    private void EyeMakeupData()
    {
        mImage.add(R.drawable.paints_eyeliner);
        mTitle.add("Paints Eyeliner");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$8.00");

        mImage.add(R.drawable.ultimate_kohl);
        mTitle.add("ULTIMATE KOHL KAJAL EYELINER");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$5.00");


        mImage.add(R.drawable.mascara_waterproof);
        mTitle.add("SCANDALEYES MASCARA WATERPROOF");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$8.00");


        mImage.add(R.drawable.hr_supercurler);
        mTitle.add("24HR SUPERCURLER MASCARA");
        mDesc.add("Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$21.00");


        mImage.add(R.drawable.magic_lumi_highlighting_concealer);
        mTitle.add("Magic Lumi Light Infusing Primer");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$20.0");

        mImage.add(R.drawable.magnifeyes_eyeshadow);
        mTitle.add("MAGNIF’EYES EYESHADOW PALETTE");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$8.00");


        mImage.add(R.drawable.pro_matte_foundation);
        mTitle.add("Pro-Matte Foundation");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$11.0");




        mImage.add(R.drawable.super_blendable_multi_use_concealer);
        mTitle.add("TEXTURED SHADOWS PALETTE ROSE GOLD EDITION\n");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$60.0");

        initRecyclerView ();
    }


    private void LipsMakeupData() {
        mImage.add(R.drawable.lipstick_a);
        mTitle.add("COLOUR RICHE LIPSTICK Collection Exclusive Nude Lipcolor");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$14.00");

        mImage.add(R.drawable.lipstick_b);
        mTitle.add("COLOUR RICHE LIPSTICK Collection Exclusive Nude Lipcolor");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$14.00");


        mImage.add(R.drawable.lipstick_c);
        mTitle.add("Le Gloss");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$4.00");

        initRecyclerView ();

    }


    private void NailMakeupData() {
        mImage.add(R.drawable.nail_a);
        mTitle.add("LISBON WANTS MOOR OPI");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$8.00");

        mImage.add(R.drawable.nail_b);
        mTitle.add("Nail");
        mDesc.add("Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$8.00");


        mImage.add(R.drawable.nail_c);
        mTitle.add("Extraordinaire Gel-Lacque 1-2-3 Kit");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$13.00");

        initRecyclerView ();

    }


    private void BrushMakeupData() {
        mImage.add(R.drawable.brush_a);
        mTitle.add("MORPHE SET 701 - 7 PIECE ROSE SET\n");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$20.00");

        mImage.add(R.drawable.brush_b);
        mTitle.add("SET 700 - 8 PIECE CANDY APPLE RED SET");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$15.00");


        mImage.add(R.drawable.brush_c);
        mTitle.add("SET 702 - 12 PIECE EYE-CREDIBLE SET");
        mDesc.add(" Introducing a new, even longer-wearing Infallible® Advanced Never Fail Makeup now for 18 hours of uninterrupted");
        mPrice.add("$25.00");

        initRecyclerView ();

    }

    private void initRecyclerView ()
    {
        RecyclerView recyclerView = findViewById(R.id.categorey_list_item);
        CategoreyViewHolder adpater = new CategoreyViewHolder(this,mImage,mTitle, mDesc,mPrice );
        recyclerView.setAdapter(adpater);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

    }



} // end class
