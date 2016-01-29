package com.irving.networkrequests;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;
import models.Evento;
import models.EventoDeLista;
import models.ServerEventListResponse;


public class MainActivity extends AppCompatActivity {
    private final String dominio ="https://bugzilla.mozilla.org";
    private TextView tvHTML;
    private TextView mTVuId;
    private TextView mTVpId;
    private TextView mTVpTitle;
    private EditText mEtURL;

    private ServerEventListResponse selrListaEventos;

    public Evento mEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHTML = (TextView) findViewById(R.id.tvShow);
        mTVuId = (TextView) findViewById(R.id.tvID);
        mTVpId = (TextView) findViewById(R.id.tvPostID);
        mTVpTitle = (TextView) findViewById(R.id.tvTitulo);
        mEtURL = (EditText) findViewById(R.id.tvEditURL);

        selrListaEventos = new ServerEventListResponse();
    }

    public void onClick(View view){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://132.248.108.7/cec/Controladores/listaEventos.php?getLista", new TextHttpResponseHandler() {
            String resp;
            EventoDeLista mEventoDL;
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                throwable.printStackTrace(System.out);
                resp = "Error " + statusCode;
                tvHTML.setText(resp);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                selrListaEventos = ServerEventListResponse.parseJSON(responseString);
                mEventoDL = selrListaEventos.getEventList().get(0);
                resp = "Nombre: 6 " + mEventoDL.getName() + "\nFecha de inicio: " + mEventoDL.getStartDate();
                tvHTML.setText(resp);
            }
        });
        /*
        AsyncHttpClient client = new AsyncHttpClient();
        String dominioMod = dominio +mEtURL.getText().toString() + "rest/bug/35?include_fields=summary,status,resolution";
        System.out.println(dominioMod);
        client.get(dominioMod, new AsyncHttpResponseHandler() {
            String resp;
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"}
                resp = new String(response);
                resp = "> " + statusCode + "\n" + resp;
                Log.i(">>> HTML ", resp);
                System.out.println(resp);
                tvHTML.setText(resp);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                e.printStackTrace(System.out);
                resp = "Error " + statusCode;
                tvHTML.setText(resp);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });*/
    }
    //TODO Hacer el modelo de Evento y probar con el dummie


    public void getPostInfo(View view) throws JSONException {
        String dominioMod = mEtURL.getText().toString() + "posts/1";
        PublicRestClient.get(dominioMod, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    int userID = response.getInt("userId");
                    mTVuId.setText(String.valueOf(userID));
                    int postID = response.getInt("id");
                    mTVpId.setText(String.valueOf(postID));
                    String title = response.getString("title");
                    mTVpTitle.setText(title);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                Log.i(">>> JsonArray", "Recibió JsonArray ??!!");
            }

            @Override
            public void onFailure(int statusCode, Header[] heades, Throwable e, JSONObject response) {
                mTVpTitle.setText("Error " + statusCode);
                e.printStackTrace();
            }
        });
    }


    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
