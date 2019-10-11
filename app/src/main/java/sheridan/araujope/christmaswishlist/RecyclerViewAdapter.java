package sheridan.araujope.christmaswishlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Bitmap> mImages = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDescriptions = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<Bitmap> mImages,
                               ArrayList<String> mNames, ArrayList<String> mDescriptions) {
        this.mImages = mImages;
        this.mNames = mNames;
        this.mDescriptions = mDescriptions;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.mImage.setImageBitmap(mImages.get(position));
        holder.mName.setText(mNames.get(position));
        holder.mDesc.setText(mDescriptions.get(position));
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mName;
        private TextView mDesc;
        private RelativeLayout mParentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.imgList);
            mName = itemView.findViewById(R.id.txtListName);
            mDesc = itemView.findViewById(R.id.txtListDesc);
            mParentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
