package com.olivierfabre.lundimatin;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import android.util.Log;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // Log des détails de la requête
        Log.d("Request", "URL: " + request.url());
        Log.d("Request", "Headers: " + request.headers());
        Log.d("Request", "Method: " + request.method());

        Response response = chain.proceed(request);

        // Log des détails de la réponse
        Log.d("Response", "Code: " + response.code());
        Log.d("Response", "Body: " + response.body().string());

        return response;
    }
}
