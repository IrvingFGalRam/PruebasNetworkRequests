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
import models.Evento.*;
import models.EventoDeLista;
import models.ServerEventListResponse;
import models.ServerEventResponse;


public class MainActivity extends AppCompatActivity {
    private final String dominio ="https://bugzilla.mozilla.org";
    private TextView tvHTML;
    private TextView mTVuId;
    private TextView mTVpId;
    private TextView mTVpTitle;
    private EditText mEtURL;

    private ServerEventListResponse selrListaEventos;
    private ServerEventResponse serEvent;

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
        serEvent = new ServerEventResponse();
    }

    public void onClick(View view){
        AsyncHttpClient client = new AsyncHttpClient();
        String link;
        switch(view.getId()) {
            case R.id.bListaEvento:
                link = "http://132.248.108.7/cec/Controladores/listaEventos.php?getLista=Enero";
                client.get(link, new TextHttpResponseHandler() {
                    String resp;
                    EventoDeLista respEventoDL;
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        throwable.printStackTrace(System.out);
                        resp = "Error " + statusCode;
                        tvHTML.setText(resp);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        selrListaEventos = ServerEventListResponse.parseJSON(responseString);
                        respEventoDL = selrListaEventos.getEventList().get(0);
                        resp = "Nombre:eweg " + respEventoDL.getName() + "\nFecha de inicio: " + respEventoDL.getStartDate();
                        tvHTML.setText(resp);
                    }
                });
                break;
            case R.id.bEvento:
                link = "http://132.248.108.7/cec/Controladores/datosEvento.php?getEvento=123456";
                client.get(link, new TextHttpResponseHandler() {
                    String resp;
                    Evento respEvento;
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        throwable.printStackTrace(System.out);
                        resp = "Error " + statusCode;
                        tvHTML.setText(resp);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        serEvent = ServerEventResponse.parseJSON(responseString);
                        respEvento = serEvent.getEvent();
                        resp = "Nombre: " + respEvento.getName() + "\nID: " + respEvento.getId() +
                                "\nnum Orgs: " + respEvento.getOrganizerList().size() + "\t num temas: " +
                                respEvento.getSubjectList().size() + "\n1ra act: " + respEvento.getDayProgramList().
                                get(0).getActivityList().get(0).getName();
                        tvHTML.setText(resp);
                    }
                });
                break;
        }
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
