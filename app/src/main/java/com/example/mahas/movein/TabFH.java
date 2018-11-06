package com.example.mahas.movein;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFH.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFH#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFH extends Fragment implements RVAdapter.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    //private ArrayList<String> urlList=new ArrayList<>();
    //private ArrayList<String> headList;
    private String mParam1;
    private String mParam2;
    private RequestQueue requestQueue;
    private OnFragmentInteractionListener mListener;
    private Context context;
    private List<ListItems> listItems;
    NukeSSLCerts nukeSSLCerts=new NukeSSLCerts();
    SharedPreferences sharedPreferences;
    String userid;
    public TabFH() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFH.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFH newInstance(String param1, String param2) {
        TabFH fragment = new TabFH();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        sharedPreferences=getActivity().getSharedPreferences(Login.MyPREFERENCES,Context.MODE_PRIVATE);
        nukeSSLCerts.nuke();
        listItems = new ArrayList<>();
        userid=sharedPreferences.getString("id",null);
        //headList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        Log.w("mayank","asdasdas");
        View rootView = inflater.inflate(R.layout.fragment_tab_fh, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        requestQueue = Volley.newRequestQueue(getActivity());

        sendRequest();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void sendRequest()
    {

        StringRequest stringRequest= new StringRequest(com.android.volley.Request.Method.POST, Constants.URL_IMAGE, new Response.Listener<String>()
        {

            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonObject= new JSONObject(response);
                    if(jsonObject.getString("error")=="false")
                    {
                        JSONArray url=jsonObject.getJSONArray("urlarr");
                        JSONArray likear=jsonObject.getJSONArray("likedarr");
                        process(url,likear);
                    }
                    else
                    {

                        Log.w("Err ",jsonObject.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //ListItems ls= new ListItems("heading"+(k+1),error.getMessage());
                //listItems.add(ls);
                //Picasso.get().load(url).into(img);
                //Log.w("Err ",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> m = new HashMap();
                m.put("tablename","fandh");
                m.put("userid",userid);
                return m;
            }
        };

        requestQueue.add(stringRequest);

    }
    public void process(JSONArray url,JSONArray likear) throws JSONException {
        int i=0,j=0;
        listItems.clear();
        //urlList.clear();
        Log.w("arr",likear.toString());
        while (!url.isNull(i))
        {
            boolean liked=false;
            j=0;
            while (!likear.isNull(j))
            {
                Log.w("arr",likear.getJSONObject(j).toString());
                if(Integer.parseInt(url.getJSONObject(i).getString("id"))==Integer.parseInt(likear.getJSONObject(j).getString("liked")))
                {
                    Log.d("ifb","inside if");
                    liked=true;
                    break;
                }
                j++;
            }
            ListItems ls = new ListItems(url.getJSONObject(i).getString("head") , url.getJSONObject(i).getString("rent"),url.getJSONObject(i).getString("bhk"),url.getJSONObject(i).getString("address"),url.getJSONObject(i).getString("rcview"),"fandh",url.getJSONObject(i).getString("overv1"),url.getJSONObject(i).getString("overv2"),url.getJSONObject(i).getString("over3"),url.getJSONObject(i).getString("overv4"),url.getJSONObject(i).getString("contact") ,url.getJSONObject(i).getString("url"),url.getJSONObject(i).getString("id"),liked);
            listItems.add(ls);
            i++;
        }
        /*for (i=0;i<urlList.size();i++)
        {
            ListItems ls = new ListItems(headList.get(i) , "LoremIpsum", urlList.get(i));
            listItems.add(ls);
        }*/
        Log.w("Tabfh",":" + listItems.toString());
        adapter = new RVAdapter(listItems, getContext());
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(ListItems item) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}