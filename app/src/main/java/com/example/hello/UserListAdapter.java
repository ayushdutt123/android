package com.example.hello;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.userview> {
    Context context;
    ArrayList<UserModel> Users;
    TextView username,userlastmsg,usermsgdate;
    ImageView userpic;
    public UserListAdapter(Context context1,ArrayList<UserModel> Users)
    {
        context=context1;
        this.Users=Users;
    }

    @NonNull
    @Override
    public userview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.sampleuserdesign,parent,false);
        return new userview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userview holder, int position) {
        username.setText(Users.get(position).name);
        if(Users.get(position).about==null || Users.get(position).about==""){
            userlastmsg.setText("He is too busy to update about");
        }
        else{
            userlastmsg.setText(Users.get(position).about);
        }
        Picasso.get()
                .load(Users.get(position).pic)
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .into(userpic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(context,MessengerActivity.class);
                in.putExtra("name",Users.get(position).name);
                in.putExtra("uid",Users.get(position).uid);
                in.putExtra("pic",Users.get(position).pic);
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    class userview extends RecyclerView.ViewHolder
    {

        public userview(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            userlastmsg=itemView.findViewById(R.id.userlastmsg);
            usermsgdate=itemView.findViewById(R.id.userlastmsgdate);
            userpic=itemView.findViewById(R.id.userpic);
        }
}
}