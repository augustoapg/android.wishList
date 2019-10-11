package sheridan.araujope.christmaswishlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mAddButton;
    private RecyclerView mWishList;
    private RecyclerViewAdapter mAdapter;
    private static final int REQUEST_CODE = 1;

    private ArrayList<Bitmap> mImages = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDescriptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWishList = findViewById(R.id.wishList);

        mAddButton = findViewById(R.id.btnAdd);
        mAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WishGift.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        mAdapter = new RecyclerViewAdapter(this, mImages, mNames,
                mDescriptions);
        mWishList.setAdapter(mAdapter);
        mWishList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String description = data.getStringExtra("description");
                Bitmap image = (Bitmap)data.getParcelableExtra("image");

                Log.d(TAG, "onActivityResult: " + name + " - " + description);

                mNames.add(name);
                mDescriptions.add(description);
                mImages.add(image);

                mAdapter.notifyItemInserted(mNames.size() - 1);
            }
        }
    }
}
