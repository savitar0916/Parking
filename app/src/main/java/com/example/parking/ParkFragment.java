package com.example.parking;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



public class ParkFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeLayout;
    ListView listView;
    String url="http://163.17.131.134/department/Parking/wsParkingPermit/wsParkingSpace.asmx/fnParkingSpace";
    private View View;
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    public String[][] d = new String[3][3];
    public ProgressBar pBar;
    boolean flag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View = inflater.inflate(R.layout.fragment_park, container, false);
        // UI元件的取得及設定
        pBar=View.findViewById(R.id.pBar);
        flag=pBar.isIndeterminate();
        listView=View.findViewById(R.id.listview);
        mSwipeLayout=(SwipeRefreshLayout)View.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        if(!flag)
        {
            pBar.setMax(100);
            pBar.setProgress(10);
            pBar.setSecondaryProgress(20);
        }
        getData(url);
        return View;
    }

    private void getData(String url) {
        RequestQueue mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String data) {
                        pBar.incrementSecondaryProgressBy(20);
                        pBar.incrementProgressBy(10);
                        Log.d("dataNote",data);
                        ParseXML(data);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("volleyError",volleyError.toString());
                    }
                });
        mQueue.add(stringRequest);
    }
    private void ParseXML(String data) {
        pBar.incrementSecondaryProgressBy(20);
        pBar.incrementProgressBy(10);
        try{
            builder = factory.newDocumentBuilder();
            Document dt=builder.parse(new InputSource(new StringReader(data)));
            dt.getDocumentElement().normalize();
            pBar.incrementProgressBy(10);
            //XML檔第一層
            NodeList nDS = dt.getElementsByTagName("DataSet");
            Element eleDS = (Element)nDS.item(0);
            pBar.incrementSecondaryProgressBy(20);
            pBar.incrementProgressBy(10);
            //XML檔第二層
            NodeList nddm = eleDS.getElementsByTagName("diffgr:diffgram");
            Element eleddm =(Element)nddm.item(0);
            pBar.incrementProgressBy(10);
            //XML檔第三層
            NodeList nPark =eleddm.getElementsByTagName("Park");
            Element elePark=(Element)nPark.item(0);
            pBar.incrementSecondaryProgressBy(20);
            pBar.incrementProgressBy(10);
            //XML檔第四層
            NodeList nParkingSpace =elePark.getElementsByTagName("ParkingSpace");
            //參考範例資料dsLPR
            int PSLen = nParkingSpace.getLength();
            pBar.incrementProgressBy(10);
            pBar.incrementSecondaryProgressBy(20);
            ParkQuantity []parkquantities=new ParkQuantity[PSLen];
            for(int i =0; i<PSLen;i++)
            {
                Element eleParkingSpace =(Element)nParkingSpace.item(i);
                ParkQuantity parkquantity = new ParkQuantity();
                //XML我要的資料
                //Area
                NodeList nArea =eleParkingSpace.getElementsByTagName("Area");
                Element eleArea = (Element)nArea.item(0);
                String Area =eleArea.getChildNodes().item(0).getNodeValue();


                //Quota
                NodeList nQuota =eleParkingSpace.getElementsByTagName("Quota");
                Element eleQuota = (Element)nQuota.item(0);
                String Quota =eleQuota.getChildNodes().item(0).getNodeValue();

                d[i][0]=String.valueOf(i+1);
                d[i][1]=Area;
                d[i][2]=Quota;
                //讀每一筆
                parkquantities[i]=parkquantity;
            }
            pBar.incrementProgressBy(10);
            LayoutInflater inflater =(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            pBar.incrementProgressBy(10);
            ViewAdapter adapter=new ViewAdapter(d,inflater);
            pBar.incrementProgressBy(10);
            pBar.setVisibility(android.view.View.GONE);
            listView.setAdapter(adapter);
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(url);
                mSwipeLayout.setRefreshing(false);
            }
        }, 3000);
    }
}
