package arjun.quastio.in.githubuser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Api.GitApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static arjun.quastio.in.githubuser.R.id.query;

public class MainActivity extends AppCompatActivity {
TextView querytext;

    Item[] items;

    RecyclerView myRecycler;
    MyAdapter myAdapter;
    ImageButton searchButton;
    ProgressDialog progressDialog;
    RetrofitClient retrofitClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         searchButton=(ImageButton)findViewById(R.id.searchButton);
        querytext=(TextView)findViewById(query);
        querytext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager=(InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(querytext.getWindowToken(),0);

                String query=querytext.getText().toString();

    progressDialog    = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("searching......");
                progressDialog.setCancelable(false);
                progressDialog.show();
                retrofitClient=new RetrofitClient();

                GitApi gitApi=retrofitClient.getGitApi();
//                Retrofit retrofit=new Retrofit.Builder().baseUrl(RepoApi.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
//                GitApi gitApi=retrofit.create(GitApi.class);
//
                Call<GitModel> callUSers = gitApi.getUsers(query);
                //asynchronous call
                callUSers.enqueue(new Callback<GitModel>() {
                    @Override
                    public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                        progressDialog.dismiss();

                       if (response.isSuccessful()){

                           final GitModel users=response.body();
                         if( Integer.parseInt( users.getTotal_count())!=0)
                         {

                           items=users.getItems();
                           myRecycler=(RecyclerView)findViewById(R.id.reycler);
                           RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
                           myRecycler.setLayoutManager(layoutManager);
                           myRecycler.setItemAnimator(new DefaultItemAnimator());

                           myAdapter=new MyAdapter(getApplicationContext(),items);
                           myAdapter.notifyDataSetChanged();
//
                           myRecycler.setAdapter(myAdapter);

                        for(int i=0;i<items.length;i++) {
                            Log.e("adad", items[i].getLogin());
                        }}

                           else {

                             Toast.makeText(MainActivity.this,"No user Found",Toast.LENGTH_SHORT).show();
                         }

                       }else {
                           Log.e("response", String.valueOf(response.code())+"------------"+response.message());
                       }
                    }

                    @Override
                    public void onFailure(Call<GitModel> call, Throwable t) {
                        Log.e("failure response","failed"+t.getMessage());


                    }

                });





            }
        });

    }
}
