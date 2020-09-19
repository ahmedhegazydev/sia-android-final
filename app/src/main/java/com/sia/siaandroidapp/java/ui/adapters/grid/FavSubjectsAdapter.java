//package com.sia.siaandroidapp.java.ui.adapters.grid;
//
//import android.content.Context;
//import android.content.pm.ResolveInfo;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//
//import com.sia.siaandroidapp.R;
//import com.sia.siaandroidapp.java.model.Subject;
//import com.sia.siaandroidapp.java.ui.activities.MainActivity;
//
//import java.util.List;
//
//public class FavSubjectsAdapter extends BaseAdapter {
//
//    private Context context;
//    private List<Subject> subjectList;
//
//    public FavSubjectsAdapter(Context context, List<Subject> subjectList) {
//        this.context = context;
//        this.subjectList = subjectList;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View v = convertView;
//        LayoutInflater inflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        v = inflater.inflate(R.layout.grid_item_fav_subject, null);
//
//
//        Subject subject = subjectList.get(position);
//
//        checkableLayout.tvSubjectTitle.setText(subject.getTitle());
//        checkableLayout.ivSubjectImage.setImageResource(subject.getImage());
//
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                subject.setChecked(!subject.isChecked());
//            }
//        });
//
//        if (subject.isChecked()){
//            checkableLayout.tvSubjectTitle.setText();
//        }else {
//
//        }
//
//        return v;
//    }
//
//    public final int getCount() {
//        return subjectList.size();
//    }
//
//    public final Object getItem(int position) {
//        return subjectList.get(position);
//    }
//
//    public final long getItemId(int position) {
//        return position;
//    }
//
//}
