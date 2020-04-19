package com.founderr.founderr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.AppsRecycleViewRowHolder> {


    private final List<Category> catList;
    List<Magazine> magazineList;
    Context context;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;
    public HomeRecyclerAdapter(Context context,List<Category> catList,List<Magazine> magazineList) {
        this.catList = catList;
        this.magazineList = magazineList;
        this.context = context;
    }

    @NonNull
    @Override
    public AppsRecycleViewRowHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        final View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cat_list, viewGroup, false);

        return new AppsRecycleViewRowHolder(v);
    }

    @Override
    public void onBindViewHolder(final AppsRecycleViewRowHolder appListRowHolder, int i) {
        final Magazine magazine = magazineList.get(i);

        for (Category cat :
                catList) {
            if(cat.getRID().equals(magazine.getCategoryRID())){
                appListRowHolder.tag.setText(cat.getCategoyrName());
            }
        }

        switch (magazine.getCategoryRID()){
            case 1:
                appListRowHolder.tag.setBackgroundColor(context.getResources().getColor(R.color.color_sky));
                break;
            case 2:
                appListRowHolder.tag.setBackgroundColor(context.getResources().getColor(R.color.color_red));
                break;
            case 3:
                appListRowHolder.tag.setBackgroundColor(context.getResources().getColor(R.color.color_green));
                break;
                default:
                    appListRowHolder.tag.setBackgroundColor(context.getResources().getColor(R.color.color_yellow));
                    break;

        }

        Picasso.with(context)
                .load(magazine.getThumbImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(appListRowHolder.thumbnail);

        appListRowHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("MAGAZINE",magazine);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return magazineList == null ? 0 : magazineList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class AppsRecycleViewRowHolder extends RecyclerView.ViewHolder {

        private final ImageView thumbnail;
        private final TextView tag;

        private AppsRecycleViewRowHolder(View view) {
            super(view);
            this.thumbnail = view.findViewById(R.id.installed_app_icon);
            this.tag = view.findViewById(R.id.tag_text);
        }
    }
}
