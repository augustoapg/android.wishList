package sheridan.araujope.christmaswishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mAddButton;
    private RecyclerView mWishList;
    private static final int REQUEST_CODE = 1;

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
    }
}
