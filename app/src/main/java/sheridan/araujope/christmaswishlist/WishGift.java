package sheridan.araujope.christmaswishlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class WishGift extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private Button mSaveButton;
    private EditText mName;
    private EditText mDescription;
    private ImageView mImage;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_gift);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSaveButton = findViewById(R.id.btnSave);
        mName = findViewById(R.id.txtName);
        mDescription = findViewById(R.id.txtDescription);
        mImage = findViewById(R.id.imgPicture);
        mSaveButton = findViewById(R.id.btnSave);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        
        addImageClickHandler();
    }

    private void addImageClickHandler() {
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            mImage.setImageBitmap(imageBitmap);
        }
    }

    protected void save() {
        String name = mName.getText().toString();
        String description = mDescription.getText().toString();

        Log.i("test", name + description);

        if (name.isEmpty() || description.isEmpty()) {
            Toast toast = Toast.makeText(this, "Please enter a name and a description",
                    Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Intent homeIntent = new Intent();
            homeIntent.putExtra("name", name);
            homeIntent.putExtra("description", description);
            homeIntent.putExtra("image", imageBitmap);
            setResult(RESULT_OK, homeIntent);
            finish();
        }
    }
}
