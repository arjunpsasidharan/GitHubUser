package Api;

import java.util.List;

import arjun.quastio.in.githubuser.UserRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public   interface RepoApi{
      String baseUrl="https://api.github.com";
  @GET("/users/{user}/repos")
  Call<List<UserRepo>> getRepo(@Path("user") String user);

}
