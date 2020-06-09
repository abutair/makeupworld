package com.example.mohammed.makeupword.Register;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mohammed.makeupword.MainActivity;
import com.example.mohammed.makeupword.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;


public class SetupActivity extends AppCompatActivity {

    private Uri mainImageUrl = null;
    private CircleImageView SetupImage ;
    private  boolean isChanged  = false;
    private EditText SetupName;
    private Button SetupButton ;
    private StorageReference storageReference ;
    private FirebaseAuth firebaseAuth ;

    private ProgressBar progressBar ;
    private FirebaseFirestore firebaseFirestore;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        Toolbar setupToolbar = findViewById(R.id.setup_toolbar);
        setSupportActionBar(setupToolbar);
        getSupportActionBar().setTitle(" Account Setup ");
        SetupName = findViewById(R.id.setup_name);
        SetupButton = findViewById(R.id.setup_button);
        SetupImage =    findViewById(R.id.setup_image);
        progressBar = findViewById(R.id.setup_prograss);
        progressBar.setVisibility(View.VISIBLE);
        storageReference= FirebaseStorage.getInstance().getReference();
        firebaseAuth =FirebaseAuth.getInstance() ;
        firebaseFirestore = FirebaseFirestore.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid() ;
        progressBar.setVisibility(View.VISIBLE);
        SetupButton.setEnabled(false);

        firebaseFirestore.collection("Users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful())
                {
                    if (task.getResult().exists())
                    {

                        String name = task.getResult().getString("name");
                        String image= task.getResult().getString("image");
                        SetupName.setText(name);
                        mainImageUrl = Uri.parse(image);
                        Log.d("hayneee","ana hoan , image: "+image);
                        RequestOptions placeholderRequset = new RequestOptions() ;
                        placeholderRequset.placeholder(R.drawable.dark_default);


                        Glide.with(SetupActivity.this ).setDefaultRequestOptions(placeholderRequset).load(image).into(SetupImage);



                    }



                }
                else
                {
                    String error = task.getException().getMessage() ;
                    Toast.makeText(SetupActivity.this ,"Error " +error ,Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
                SetupButton.setEnabled(true);


            }

        });

        SetupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ContextCompat.checkSelfPermission(SetupActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)

                    {
                        ActivityCompat.requestPermissions(SetupActivity.this , new String [] {Manifest.permission.READ_EXTERNAL_STORAGE},1);

                    }
                    else
                    {
                        BringImagePicker();
                    }


                }
                else
                {
                    BringImagePicker();
                }

            }
        });  // end onClick Listener

        SetupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = SetupName.getText().toString();
                if (!TextUtils.isEmpty(userName) && mainImageUrl != null) {

                    progressBar.setVisibility(View.VISIBLE);

                    if (isChanged) {


                        userId = firebaseAuth.getCurrentUser().getUid();

                        final StorageReference image_path = storageReference.child("profile_Images").child(userId + ".jpg");
                        UploadTask uploadTask=image_path.putFile(mainImageUrl);
                        Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }

                                // Continue with the task to get the download URL
                                return image_path.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.isSuccessful()){
                                    mainImageUrl=task.getResult();
                                }
                                storeFireStore(userName);
                            }
                        });

                    }
                    else
                    {
                        storeFireStore(userName);
                    }
                }

            }
        }); // end ClickListener



    } // end OnCreate

    private void storeFireStore(String userName) {

        Map<String,String> userMsp = new HashMap<>();
        userMsp.put("name",userName);
        userMsp.put("image",mainImageUrl.toString());


        firebaseFirestore.collection("Users").document(userId).set(userMsp).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(SetupActivity.this ,"Task Successful "  ,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SetupActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }

                else
                {
                    String error = task.getException().getMessage() ;
                    Toast.makeText(SetupActivity.this ,"Error " +error ,Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void BringImagePicker() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .start(SetupActivity.this);
    } // end Method


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mainImageUrl = result.getUri();
                SetupImage.setImageURI(mainImageUrl);


                isChanged = true;

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }

} // end Class