package arjun.quastio.in.githubuser;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewholder> {
    Item[] userlist;
    Context context;


    public MyAdapter(Context context, Item[] userlist) {
        this.userlist = userlist;
        this.context = context;

    }

    @Override
    public MyAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewholder holder, final int position) {
        Item user = userlist[position];
        holder.userName.setText(user.getLogin());
        holder.score.setText(user.getScore());

        Picasso.with(context).load(user.getAvatar_url()).resize(500, 400).into(holder.userPic);
        holder.userPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item user = userlist[position];

                String userName = user.getLogin();
                String repoUrl = user.getRepos_url();

                Intent myIntent = new Intent(context, UserDetails.class);

                Log.e("Intemt put details", userName + repoUrl);
                myIntent.putExtra("username", userName);
                myIntent.putExtra("repoUrl", repoUrl);


                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return userlist.length;
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView userName, score;
        ImageView userPic;

        public MyViewholder(final View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.repo_name);
            score = (TextView) itemView.findViewById(R.id.score);
            userPic = (ImageView) itemView.findViewById(R.id.person_photo);

        }

    }

}