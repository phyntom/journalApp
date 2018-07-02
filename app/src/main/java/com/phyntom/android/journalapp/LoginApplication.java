package com.phyntom.android.journalapp;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class LoginApplication extends Application {

    public  AppCompatActivity activity;

    private  GoogleSignInClient googleSignInClient;

    private GoogleSignInOptions googleSignInOptions;

    private  GoogleSignInAccount account;

    private static Object LOCK = new Object();

    public  GoogleSignInClient getGoogleSignInClient(Context context) {
        if (googleSignInClient == null) {
            googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    //.requestIdToken(getString(R.string.server_client_id))
                    .requestEmail().build();
            googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
        }
        return googleSignInClient;
    }


}
