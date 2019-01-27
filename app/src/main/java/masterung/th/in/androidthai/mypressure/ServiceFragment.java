package masterung.th.in.androidthai.mypressure;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment {


    public ServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create RecyclerView
        createRecyclerView();

    }   // Main Method

    private void createRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {

            GetAllDataThread getAllDataThread = new GetAllDataThread(getActivity());
            MyConstant myConstant = new MyConstant();
            getAllDataThread.execute(myConstant.getUrlGetAllData());
            String json = getAllDataThread.get();
            Log.d("27JanV1", "json of Service ==> " + json);

            ArrayList<String> nameStringArrayList = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStringArrayList.add(jsonObject.getString("Name"));
            }

            Log.d("27JanV2", "nameStringArrayList ==> " + nameStringArrayList.toString());
            ServiceAdapter serviceAdapter = new ServiceAdapter(getActivity(), nameStringArrayList);
            recyclerView.setAdapter(serviceAdapter);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service, container, false);
    }

}
