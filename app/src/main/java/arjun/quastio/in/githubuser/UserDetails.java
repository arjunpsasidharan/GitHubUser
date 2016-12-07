package arjun.quastio.in.githubuser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Api.RepoApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserDetails extends Activity{

RetrofitClient retrofitClient;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    RepoAdapter myAdapter;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetails);
        progressDialog    = new ProgressDialog(UserDetails.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("searching......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Intent myIntent=getIntent();
        username=myIntent.getStringExtra("username");
        TextView name=(TextView)findViewById(R.id.user_Name) ;
        name.setText(username+"'s Repositories");



    Retrofit retrofit=new Retrofit.Builder().baseUrl(RepoApi.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
    RepoApi repoApi=retrofit.create(RepoApi.class);
        Call<List< UserRepo>> callRepos=repoApi.getRepo(username);
        callRepos.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {

                if (response.isSuccessful()){
                    progressDialog.dismiss();
                 List  < UserRepo> userRepo=response.body();
Log.e("size",String.valueOf(userRepo.size()));

                   if (userRepo.isEmpty()){
                    Log.e("null value","empty body");}
                    for (int i=0;i<userRepo.size();i++){
                    Log.e("response",userRepo.get(i).getName());}
                    if (!userRepo.isEmpty()){
                        recyclerView=(RecyclerView)findViewById(R.id.reporeycler);
                        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        myAdapter=new RepoAdapter(getApplicationContext(),userRepo);
                        myAdapter.notifyDataSetChanged();

                        recyclerView.setAdapter(myAdapter);


                    }
                    else {


                        Toast.makeText(UserDetails.this,username+" has no repos",Toast.LENGTH_SHORT).show();
                    }



                }
                else{

                    Log.e("response erro",response.message());

                }

            }

            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {

            }
        });





    }
}
