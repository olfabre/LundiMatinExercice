package com.olivierfabre.lundimatin.api;




import com.olivierfabre.lundimatin.models.AuthRequest;
import com.olivierfabre.lundimatin.models.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;




public interface AuthService {
    @Headers({
            "Content-Type: application/json",
            "Accept: application/api.rest-v1+json"
    })
    @POST("auth") // endpoint
    Call<AuthResponse> authenticate(@Body AuthRequest authRequest);
}