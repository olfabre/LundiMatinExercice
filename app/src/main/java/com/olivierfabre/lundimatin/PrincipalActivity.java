package com.olivierfabre.lundimatin;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.olivierfabre.lundimatin.ClientAdapter;
import com.olivierfabre.lundimatin.R;
import com.olivierfabre.lundimatin.api.AuthService;
import com.olivierfabre.lundimatin.api.ClientService;
import com.olivierfabre.lundimatin.models.AuthRequest;
import com.olivierfabre.lundimatin.models.AuthResponse;
import com.olivierfabre.lundimatin.models.Client;
import com.olivierfabre.lundimatin.models.ClientResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;

public class PrincipalActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private AuthService authService;
    private ClientService clientService;
    private RecyclerView recyclerView;
    private ClientAdapter adapter;
    private List<Client> clientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Initialisation de Retrofit avec intercepteur
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://evaluation-technique.lundimatin.biz/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Création des services AuthService et ClientService
        authService = retrofit.create(AuthService.class);
        clientService = retrofit.create(ClientService.class);

        // Initialisation du RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClientAdapter(clientList);
        recyclerView.setAdapter(adapter);

        // Appel à l'API pour s'authentifier et récupérer le token
        authenticateAndFetchClients();
    }

    // Méthode pour s'authentifier et récupérer la liste des clients
    private void authenticateAndFetchClients() {
        AuthRequest authRequest = new AuthRequest("test_api", "api123456");

        Call<AuthResponse> call = authService.authenticate(authRequest);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    String token = authResponse.getDatas().getToken();
                    Log.d("AuthSuccess", "Token: " + token);

                    // Utilisez le token pour récupérer la liste des clients
                    fetchClients(token);
                } else {
                    Log.e("AuthError", "Erreur: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("AuthFailure", "Erreur: " + t.getMessage());
            }
        });
    }

    // Méthode pour récupérer la liste des clients
    private void fetchClients(String authToken) {
       // Call<ClientResponse> call = clientService.getClients("Bearer " + authToken, "application/api.rest-v1+json");
        Call<ClientResponse> call = clientService.getClients("Bearer " + authToken);
                call.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                int httpCode = response.code(); // Obtenir le code HTTP
                //Log.d("ClientResponseCode", "Code HTTP: " + httpCode);


                Log.d("ClientRequest", "URL: " + response.raw().request().url());
                Log.d("ClientRequest", "Headers: " + response.raw().request().headers());
                Log.d("ClientRequest", "Code: " + response.code());

                if (response.isSuccessful()) {
                    ClientResponse clientResponse = response.body();
                    if (clientResponse != null) {
                        List<Client> clients = clientResponse.getDatas();
                        List<String> warnings = clientResponse.getWarnings();

                        if (warnings != null && !warnings.isEmpty()) {
                            for (String warning : warnings) {
                                Log.w("ClientWarning", "Warning: " + warning);
                            }
                        }

                        adapter.setClientList(clients);
                    }
                } else {
                    Log.e("ClientError", "Erreur: " + response.message());
                    Log.e("ClientError", "Code HTTP: " + response.code());
                    Log.e("ClientError", "Body: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                Log.e("ClientFailure", "Erreur: " + t.getMessage());
            }
        });
    }

}
