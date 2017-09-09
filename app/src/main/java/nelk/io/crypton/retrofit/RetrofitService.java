package nelk.io.crypton.retrofit;

import nelk.io.crypton.retrofit.models.Response;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("/public/getmarketsummaries")
    Call<Response>getSummaries();
}
