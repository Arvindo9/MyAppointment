package com.vchmgi.myappointment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Naresh on 19-01-2017.
 */

public class SimpleListViewAdapter extends ArrayAdapter {

//    UserClass context;
//    ArrayList<RowItem> pfdatas;
//    ViewHolder holder;
//    List<RowItem> items;
       private ArrayList<RowItem> list;
    private final UserClass activity;


    //    public SimpleListViewAdapter(UserClass context, ArrayList<RowItem> pfdatas) {
//        this.context  = context;
//        this.pfdatas = pfdatas;
//
//    }
public SimpleListViewAdapter(Context context, int resource, ArrayList<RowItem> list, UserClass userClass) {
    super(context,resource);
    this.list = list;
    this.activity = userClass;
}
    public void add(RowItem object) {
        super.add(object);
        list.add(object);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //    private class ViewHolder {
//        ImageView imageView,image;
//        TextView text;
//    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        DataHolder holder;
//        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_items,parent,false);
            holder = new DataHolder();
            holder.layout = (LinearLayout) convertView.findViewById(R.id.layout_lv);
            holder.fullnameItem = (TextView) convertView.findViewById(R.id.senderName_tv);
            holder.emailItem = (TextView)convertView.findViewById(R.id.email_tv);
            holder.phonenoItem = (TextView) convertView.findViewById(R.id.phone_tv);
            holder.subItem = (TextView)convertView.findViewById(R.id.sub_tv);
            holder.msgItem = (TextView)convertView.findViewById(R.id.msg_tv);


            convertView.setTag(holder);
        } else {
            holder = (DataHolder) convertView.getTag();
        }

        final RowItem item = (RowItem) this.getItem(position);
        assert item != null;
        holder.fullnameItem.setText(item.getName());
        holder.emailItem.setText(item.getEmail());
        holder.phonenoItem.setText(item.getPhone());
        holder.subItem.setText(item.getSub());
        holder.msgItem.setText(item.getMsg());
//        holder.fullnameItem.setText(item.getFullNameId());
//        holder.emailItem.setText(item.getEmailId());
//        holder.phonenoItem.setText(item.getPhoneNoId());
//        holder.subItem.setText(item.getSubId());
//        holder.msgItem.setText(item.getMessageId());
//        holder.nameItem.setText(item.getTextId());
//        holder.subName.setText(item.getSubtextId());
//        holder.crosImg.setImageResource(item.getImageId());
//        holder.nextImage.setImageResource(item.getNextimageId());
//
//        holder.crosImg.setTag(position);
//        holder.crosImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = (Integer)v.getTag();
//                pfdatas.remove(position);
//                notifyDataSetChanged();
//            }
//        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, ReplyClass.class);
                i.putExtra("fName", item.getName());
                i.putExtra("email", item.getEmail());
                i.putExtra("phone", item.getPhone());
                i.putExtra("sub", item.getSub());
                i.putExtra("msg", item.getMsg());
//                i.putExtra("file", item.getFileData());
                activity.startActivity(i);
            }
        });

        return convertView;
    }
//    protected void sendEmail() {
//        Log.i("Send email", "");
//        String[] TO = {""};
//        String[] CC = {""};
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/plain");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_CC, CC);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
//
//        try {
//            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//            context.finish();
//            Log.i("Finished sending..", "");
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(context.getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
//        }
//    }

    private class DataHolder{
        TextView fullnameItem,emailItem,phonenoItem,subItem,msgItem;
        LinearLayout layout;
//        ImageView crosImg,nextImage;
    }
}



////        ViewHolder holder = null;
//        RowItem rowItem = getItem(position);
//
//        LayoutInflater mInflater = (LayoutInflater) context
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.listview_items, null);
//            holder = new ViewHolder();
//            text = (TextView) convertView.findViewById(R.id.senderName);
//            imageView = (ImageView) convertView.findViewById(R.id.CimageV);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                   items.remove(position);
//                    us.simpleListViewAdapter.notifyDataSetChanged();
//                }
//            });
//            holder.image =(ImageView)convertView.findViewById(R.id.NimageV);
//            convertView.setTag(holder);
//        } else
//            holder = (ViewHolder) convertView.getTag();
//
//        holder.text.setText(rowItem.getTextId());
//        holder.imageView.setImageResource(rowItem.getImageId());
//        holder.image.setImageResource(rowItem.getNextimageId());
//
//
//        return convertView;}









