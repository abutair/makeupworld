package com.example.mohammed.makeupword.ShoppingCart;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mohammed.makeupword.R;

public class CardForm extends AppCompatActivity implements View.OnClickListener {

      com.braintreepayments.cardform.view.CardForm cardForm;

    Button buy ;
    AlertDialog.Builder alertBuilder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_form);

         init();
    } // end onClick



    private void init()
    {
        buy = findViewById(R.id.BuyNow);
        cardForm= findViewById(R.id.card_form);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(com.braintreepayments.cardform.view.CardForm.FIELD_REQUIRED)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .actionLabel("Purchase")
                .setup(CardForm.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (cardForm.isValid()) {
            alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setTitle("Confirm before purchase");
            alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                    "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                    "Card CVV: " + cardForm.getCvv() + "\n" +
                    "Postal code: " + cardForm.getPostalCode() + "\n" +
                    "Phone number: " + cardForm.getMobileNumber());
            alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Toast.makeText(CardForm.this, "Thank you for purchase", Toast.LENGTH_LONG).show();

                    DeleteShoppingCartItems();
                }
            });
            alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();

        } else {
            Toast.makeText(CardForm.this, "Please complete the form", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteShoppingCartItems() {


    } // end


} // end Class
