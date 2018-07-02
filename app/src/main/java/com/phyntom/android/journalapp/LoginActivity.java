package com.phyntom.android.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_GET_TOKEN = 9002;

    private static final String TAG = LoginActivity.class.getName();

    private GoogleSignInClient googleSignInClient;

    private GoogleSignInOptions googleSignInOptions;

    private GoogleSignInAccount account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validateClientId();
        googleSignInClient = ((LoginApplication)getApplication()).getGoogleSignInClient(this);
        account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    /**
     *
     */
    public void getIdToken() {
        try {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_GET_TOKEN);
        }
        catch (Exception ex) {
            Log.e(TAG, "Error occurs" + ex);
        }
    }

    public void handlerLogin(View view) {
        switch (view.getId()) {
            case R.id.bt_sign_in:
                getIdToken();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GET_TOKEN) {
            // This task is said to be completed immediately , that is why we dont attach
            // and asynchronous listener
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /**
     * @param completedTask
     */
    private void handleSignInResult(@NonNull Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String idToken = account.getIdToken();
            // TODO(developer): send ID Token to server and validate
            updateUI(account);
        }
        catch (ApiException e) {
            Log.w(TAG, "handleSignInResult:error", e);
            updateUI(null);
        }
    }

    /**
     * method use to validate client ID
     */
    private void validateClientId() {
        String webClientId = getString(R.string.server_client_id);
        String suffix = getString(R.string.client_id_suffix);
        if (!webClientId.trim().endsWith(suffix)) {
            String warning = "Web Client ID supplied is invalid , please check again !!!";
            Log.w(TAG, warning);
            Toast.makeText(this, warning, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @param account
     */
    public void updateUI(@NonNull GoogleSignInAccount account) {
        if (account != null) {
            Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
            mainActivityIntent.putExtra("account", account);
            startActivity(mainActivityIntent);
        } else {
            // redesign the main screen
        }
    }
}
