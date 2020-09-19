package com.sia.siaandroidapp.java.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sia.siaandroidapp.R;

public class NavPanelListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OnItemClickListener mListener;
    private Context context;

    private String[] menus;
    private int[] menuImg = {

            R.drawable.ic_list_alt_24px,
            R.drawable.ic_markunread_24px,
            R.drawable.ic_notification,
//            R.drawable.ic_question,
//            R.drawable.ic_settings
    };
    private int[] tvColors = {

            R.color.colorWhite,
            R.color.colorWhite,
            R.color.colorWhite,
//            R.color.colorGreen,
//            R.color.colorGreen,
    };

    public NavPanelListAdapter(Context con) {
        this.context = con;
        inflater = LayoutInflater.from(con);
        menus = new String[]{con.getString(R.string.menu_boards),
                con.getString(R.string.menu_inbox),
                con.getString(R.string.menu_notifications),
//                con.getString(R.string.contact_us),
//                con.getString(R.string.settings)
        };
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getCount() {
        return menus.length;
    }

    @Override
    public Object getItem(int i) {
        return menus.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertview == null) {

            convertview = inflater.inflate(R.layout.nav_list_template, null);
            holder = new ViewHolder();
            holder.imgMenu = (ImageView) convertview.findViewById(R.id.menu_img);
            holder.txtMenu = (TextView) convertview.findViewById(R.id.mene_id);
            holder.linearLayout = (LinearLayout) convertview.findViewById(R.id.linearLayout);
            convertview.setTag(holder);

        } else {
            holder = (ViewHolder) convertview.getTag();
        }

        holder.txtMenu.setText(menus[i]);
        holder.imgMenu.setImageResource(menuImg[i]);
        holder.txtMenu.setTextColor(context.getResources().getColor(tvColors[i]));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(i);
                }
            }
        });
        return convertview;
    }

    public class ViewHolder {

        ImageView imgMenu;
        TextView txtMenu;
        LinearLayout linearLayout;

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}