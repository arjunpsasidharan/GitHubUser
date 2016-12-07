package Api;

import arjun.quastio.in.githubuser.GitModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by arjunp on 03/12/16.
 */

public interface GitApi {

    @GET("/search/users")
    Call<GitModel> getUsers(@Query("q") String query);

}
