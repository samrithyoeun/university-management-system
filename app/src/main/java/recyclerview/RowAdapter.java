package recyclerview;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.fluffy.samrith.university_managment_system.R;

import java.util.ArrayList;

/**
 * Created by Mister_Brown on 12/27/2016.
 */

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.itemViewHolder> {
        private Context context ;
        private ArrayList<RowItem> rowItem;
        private RowListener rowListener;

    public RowAdapter(Context context, ArrayList<RowItem> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }

    public void setOnClick(RowListener rowListener){
        this.rowListener = rowListener;
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.feature_row,parent,false);
        itemViewHolder viewHolder = new itemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(itemViewHolder holder, int position) {


            holder.txtName.setText(rowItem.get(position).getName());

            Uri uri = Uri.parse("android.resource://"+context.getPackageName()+"/drawable/"+rowItem.get(position).getImage());
            try{
                holder.logo.setImageURI(uri);

            }catch (Exception e){
                e.printStackTrace();
            }



    }

    @Override
    public int getItemCount() {
        return rowItem.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{
        public ImageView logo;
        public TextView txtName;

        public itemViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.rowName);
            logo = (ImageView) itemView.findViewById(R.id.rowImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int id = rowItem.get(getAdapterPosition()).getId();
                    if(rowListener != null)
                        rowListener.onRowClick(rowItem.get(getAdapterPosition()));
                }
            });




        }




    }
}

