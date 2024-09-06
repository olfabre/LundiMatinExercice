package com.olivierfabre.lundimatin.api;

import com.olivierfabre.lundimatin.models.ClientResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ClientService {
    @GET("clients/")
    Call<ClientResponse> getClients(
            @Header("Authorization") String authHeader
            //@Header("Accept") String acceptHeader
    );

}
