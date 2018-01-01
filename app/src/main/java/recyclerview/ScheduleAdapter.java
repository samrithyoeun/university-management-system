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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Mister_Brown on 12/27/2016.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.itemViewHolder> {
        private Context context ;
        private ArrayList<Schedule> rowItem;


    public ScheduleAdapter(Context context, ArrayList<Schedule> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }


    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType ==0 ){
            view = inflater.inflate(R.layout.timetable_row,parent,false);
        }
        else{
            view = inflater.inflate(R.layout.datetime_row_layout,parent,false);
        }


        itemViewHolder viewHolder = new itemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(itemViewHolder holder, int position) {

        final int itemType = getItemViewType(position);
        if (itemType == 0) {
            holder.times.setText(rowItem.get(position).getTime());
            holder.subject.setText(rowItem.get(position).getSubject());
            holder.room.setText(rowItem.get(position).getRoom());
            holder.professor.setText(rowItem.get(position).getProfessor());

            Uri uri = Uri.parse("android.resource://"+context.getPackageName()+"/drawable/"+rowItem.get(position).getProImage());
            try{
                holder.proImage.setImageURI(uri);

            }catch (Exception e){
                e.printStackTrace();
            }

        } else {
            holder.datetime.setText(rowItem.get(position).type);
        }




    }

    @Override
    public int getItemCount() {
        return rowItem.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{

        public TextView times;
        public TextView subject;
        public TextView room;
        public TextView professor;
        public ImageView proImage;
        public TextView datetime;

        public itemViewHolder(View itemView) {
            super(itemView);

            times = (TextView)itemView.findViewById(R.id.times);
            subject = (TextView)itemView.findViewById(R.id.subject);
            room = (TextView)itemView.findViewById(R.id.room);
            professor = (TextView)itemView.findViewById(R.id.professor);
            proImage = (ImageView) itemView.findViewById(R.id.proImage);


            datetime = (TextView)itemView.findViewById(R.id.datetime);

        }




    }

    @Override
    public int getItemViewType(int position) {
        if (rowItem.get(position).type!=null){
            return 1;
        }
        else{
            return 0;
        }

    }
}

