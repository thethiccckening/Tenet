package com.example.testapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Cursor checker;
    public ArrayList<Conversations> mConverse;

    public ChatAdapter(ArrayList<Conversations> c){
        mConverse = c;
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView messageText;
        public TextView contactName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.message_);
            contactName = (TextView) itemView.findViewById(R.id.____label);

        }
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView;

        contactView = inflater.inflate(R.layout.chat_conversation, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        Conversations convo = mConverse.get(position);
        TextView textView = holder.messageText;
        textView.setText(convo.lastconvo);
        TextView ConvoName = holder.contactName;
        ConvoName.setText(convo.convoName);
    }



    @Override
    public int getItemCount() {
        return mConverse.size();
    }

}
