package com.rajeshrayalasolutions.nearbyresturants.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajeshrayalasolutions.nearbyresturants.Modal.PlacesDetails_Modal;
import com.rajeshrayalasolutions.nearbyresturants.PersistanceForData;
import com.rajeshrayalasolutions.nearbyresturants.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


/**
 * Created by Rajesh Rayala
 */

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder> {

    private ArrayList<PlacesDetails_Modal> storeModels;
    private Context context;
    private static final int TYPE_LIST=1;
    OnItemClickListener onItemClickListener;

    public RestaurantListAdapter(Context context , ArrayList<PlacesDetails_Modal> storeModels, String current_address)
    {

        this.context = context;
        this.storeModels = storeModels;
    }


    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_listitem, parent, false);

        return new MyViewHolder(itemView,viewType);


    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {


        return TYPE_LIST;


    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        if(holder.view_type == TYPE_LIST)
        {
            holder.res_name.setText(storeModels.get(holder.getAdapterPosition()).name);
            if(storeModels.get(position).getPhotourl()!=null && !TextUtils.isEmpty(storeModels.get(position).getPhotourl())){
                Picasso.with(context).load(storeModels.get(holder.getAdapterPosition() ).photourl)
                        .placeholder(R.drawable.placeholder).resize(100, 100).into(holder.res_image, new Callback() {
                    @Override
                    public void onSuccess() {
                        byte[] byteArray= imageViewToByte(holder.res_image);

                        PlacesDetails_Modal placesDetails_modal = PersistanceForData.getInstance().getPlacesDetails_modals().get(position);
                        placesDetails_modal.setImageFromBb(byteArray);

                        ArrayList<PlacesDetails_Modal> data = PersistanceForData.getInstance().getPlacesDetails_modals();
                        data.set(position,placesDetails_modal);

                        PersistanceForData.getInstance().setPlacesDetails_modals(data);


                    }

                    @Override
                    public void onError() {

                    }
                });

            }else{
                holder.res_image.setImageBitmap(getImage(storeModels.get(position).getImageFromBb()));
            }



            holder.res_address.setText(storeModels.get(holder.getAdapterPosition()).address);
            holder.card_view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null)
                    onItemClickListener.onItemClick(v,position);
                }
            });

            if(storeModels.get(holder.getAdapterPosition()).phone_no == null)
            {
                holder.res_phone.setText("N/A");
            }
            else  holder.res_phone.setText(storeModels.get(holder.getAdapterPosition()).phone_no);

            holder.res_rating.setText(String.valueOf(storeModels.get(holder.getAdapterPosition() ).rating));

            holder.res_distance.setText(storeModels.get(holder.getAdapterPosition()).distance);

            Log.i("details on adapter", storeModels.get(holder.getAdapterPosition()).name + "  " +
                    storeModels.get(holder.getAdapterPosition()).address +
                    "  " +  storeModels.get(holder.getAdapterPosition()).distance);
        }


    }

    @Override
    public int getItemCount() {

        return  storeModels.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView res_name;
        TextView res_rating;
        TextView res_address;
        TextView res_phone;
        TextView res_distance;
        TextView current_location;
        ImageView res_image,location_image;
        CardView card_view;

        int view_type;

        public MyViewHolder(final View itemView, final int viewType) {
            super(itemView);

            if(viewType == TYPE_LIST) {

                view_type=1;
                this.res_name = (TextView) itemView.findViewById(R.id.name);
                this.res_rating = (TextView) itemView.findViewById(R.id.rating);
                this.res_address = (TextView) itemView.findViewById(R.id.address);
                this.res_phone = (TextView) itemView.findViewById(R.id.phone);
                this.res_distance = (TextView) itemView.findViewById(R.id.distance);
                this.res_image = (ImageView) itemView.findViewById(R.id.res_image);
                this.card_view=(CardView)itemView.findViewById(R.id.card_view);
            }


        }

    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }
}
