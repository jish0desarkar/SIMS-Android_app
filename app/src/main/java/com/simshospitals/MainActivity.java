package com.simshospitals;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    ListView listView;
    ArrayList<String> pid;
    ArrayList<String>pname;
    ArrayList<String>billamt;
    ArrayList<String>disc;
    ArrayList<detail>pending;
    ArrayAdapter arrayAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new fetchpending().execute();
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                execute();

            }
        });

    }
    public void execute()
    {
        new fetchpending().execute();
        swipeRefreshLayout.setRefreshing(false);
    }
    public void display()
    {
        mRecyclerView=(RecyclerView)findViewById(R.id.listview);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(pid,pname,billamt,disc);
        mRecyclerView.setAdapter(mAdapter);
    }

    class fetchpending extends AsyncTask<Void,Void,Void> {
         ArrayList<detail> pendingx = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                JSONArray jsonArray = JsonArrayHelper.readJsonFromUrl("http://103.249.207.47/sims/json_pending.php");
                pid =new ArrayList<>();
                pname =new ArrayList<>();
                billamt =new ArrayList<>();
                disc =new ArrayList<>();
                for (int i=0;i<jsonArray.length();i++)
                {
                  JSONObject  json= jsonArray.getJSONObject(i);
                    pendingx.add(new detail(json.getString("ipid"),
                            json.getString("patientname"),
                            json.getString("age"),
                            json.getString("agetype"),
                            json.getString("admitdatetime"),
                            json.getString("dischargedatetime"),
                            json.getString("consultant"),
                            json.getString("billamount"),
                            json.getString("discount"),
                            json.getString("authorisedby"),
                            json.getString("reason"),
                            json.getString("requestedby")));

                          pid.add(json.getString("ipid"));
                          pname.add(json.getString("patientname"));
                          billamt.add(json.getString("billamount"));
                          disc.add(json.getString("discount"));

                }
            }
            catch (Exception e)
            {
                System.err.println(e);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            pending=pendingx;
          display();

        }
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<String> patientidlist;
        private ArrayList<String> patientnamelist;
        private ArrayList<String> billamountlist;
        private ArrayList<String> discountlist;


        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView patientid;
            public TextView patientname;
            public TextView billamount;
            public TextView discount;

            public ViewHolder(View itemView) {
                super(itemView);
                patientid = itemView.findViewById(R.id.patientid);
                patientname = itemView.findViewById(R.id.patientname);
                billamount = itemView.findViewById(R.id.billamount);
                discount = itemView.findViewById(R.id.discount);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<String> patientidlist, ArrayList<String> patientnamelist,ArrayList<String> billamountlist,ArrayList<String> discountlist) {
            this.patientidlist=patientidlist;
            this.patientnamelist=patientnamelist;
            this.billamountlist=billamountlist;
            this.discountlist=discountlist;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_display_layout, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            //     new fetchlang().execute(image[position]);

            // holder.imageView.setImageBitmap(z)
            holder.patientid.setText(patientidlist.get(position));
            holder.patientname.setText(patientnamelist.get(position));
            holder.billamount.setText(billamountlist.get(position));
            holder.discount.setText(discountlist.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,approval.class);
                    HashMap<String,String> map=new HashMap<>();
                    map.put("ipid",pending.get(position).getIpid());
                    map.put("patientname",pending.get(position).getPatientname());
                    map.put("age",pending.get(position).getAge());
                    map.put("agetype",pending.get(position).getAgetype());
                    map.put("admitdatetime",pending.get(position).getAdmitdatetime());
                    map.put("dischargedatetime",pending.get(position).getDischargedatetime());
                    map.put("consultant",pending.get(position).getConsultant());
                    map.put("billamount",pending.get(position).getBillamount());
                    map.put("discount",pending.get(position).getDiscount());
                    map.put("authorisedby",pending.get(position).getAuthorisedby());
                    map.put("reason",pending.get(position).getReason());
                    map.put("requestedby",pending.get(position).getRequestedby());
        intent.putExtra("detail", map);

startActivity(intent);


                }
            });
        }

        @Override
        public int getItemCount() {
            if (patientnamelist==null)
                return 0;
            else
            return patientnamelist.size();
        }
    }
}
