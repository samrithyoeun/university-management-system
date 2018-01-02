package recyclerview;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.fluffy.samrith.university_managment_system.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mister_Brown on 12/27/2016.
 */

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.itemViewHolder> implements Filterable{
        private Context context ;
        private List<RowItem> rowItem;
        private List<RowItem> listFiltered;
        private RowListener rowListener;
        
        

    public RowAdapter(Context context, ArrayList<RowItem> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
        listFiltered = rowItem;
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


            holder.txtName.setText(listFiltered.get(position).getName());

            Uri uri = Uri.parse("android.resource://"+context.getPackageName()+"/drawable/"+listFiltered.get(position).getImage());
            try{
                holder.logo.setImageURI(uri);

            }catch (Exception e){
                e.printStackTrace();
            }



    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                     listFiltered= rowItem;
                } else {
                    List<RowItem> filteredList = new ArrayList<>();
                    for(int i =0;i<rowItem.size();i++){
                        if (rowItem.get(i).getName().toLowerCase().contains(charString)){

                            filteredList.add(rowItem.get(i));
                            Log.d("professor",rowItem.get(i).getName());
                            Log.d("professors",charString);
                            Log.d("professorss",rowItem.get(i).getName());
                        }
                    }
                    listFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                CreateToast.show(context, listFiltered.size()+" -- FilterResult");
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (ArrayList<RowItem>) filterResults.values;
                CreateToast.show(context, listFiltered.size()+" -- ListFiltered");
                notifyDataSetChanged();
            }
        };
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

                    int id = listFiltered.get(getAdapterPosition()).getId();
                    if(listFiltered != null)
                        rowListener.onRowClick(listFiltered.get(getAdapterPosition()));
                }
            });
        }
        
        
        
    }
    
    
    
}

