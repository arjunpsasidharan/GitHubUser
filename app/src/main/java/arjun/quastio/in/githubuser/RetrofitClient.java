package arjun.quastio.in.githubuser;


import Api.GitApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private String baseUrl="https://api.github.com";
    private GitApi gitApi;
    public RetrofitClient(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        gitApi=retrofit.create(GitApi.class);


    }
    public GitApi getGitApi(){

        return gitApi;

    }
}
