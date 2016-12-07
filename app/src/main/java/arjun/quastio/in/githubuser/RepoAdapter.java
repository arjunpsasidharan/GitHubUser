package arjun.quastio.in.githubuser;

import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;



import java.util.List;

/**
 * Created by arjun on 07/12/16.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MyViewholder> {
    List<UserRepo> userrepo;
    Context context;



    public RepoAdapter(Context context, List<UserRepo> userrepo) {
        this.userrepo = userrepo;
        this.context = context;

    }


    @Override
    public RepoAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.repoview, parent, false);

        return new RepoAdapter.MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoAdapter.MyViewholder holder,  int position) {
        UserRepo repo = userrepo.get(position);

        holder.reponame.setText(repo.getName());


    }

    @Override
    public int getItemCount() {
        return userrepo.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView reponame;

        public MyViewholder(final View itemView) {
            super(itemView);

            reponame = (TextView) itemView.findViewById(R.id.repositoryName);


        }
    }
}