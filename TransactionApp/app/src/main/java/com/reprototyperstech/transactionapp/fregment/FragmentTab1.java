package com.reprototyperstech.transactionapp.fregment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reprototyperstech.transactionapp.pojo.PojoFragment;
import com.reprototyperstech.transactionapp.R;
import com.reprototyperstech.transactionapp.adapter.listItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentTab1 extends Fragment {

    RecyclerView viewRecycle;

    public static List<PojoFragment> lst_transaction = new ArrayList<>();

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment1, container, false);

        view = inflater.inflate(R.layout.fragment1, container, false);
        viewRecycle = view.findViewById(R.id.viewRecycle);


        initialzeValue();
        listItemAdapter adapter = new listItemAdapter(lst_transaction, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        viewRecycle.setLayoutManager(layoutManager);
        viewRecycle.setAdapter(adapter);

        return view;
    }

    void initialzeValue() {
        lst_transaction.clear();
        lst_transaction.add(new PojoFragment("Success", "100.00", "23-08-2022 08:00:00", "Amount paid for Recharge payment"));
        lst_transaction.add(new PojoFragment("Pending", "110.00", "25-09-2022 11:04:00", "Amount paid for Government bill payment"));
        lst_transaction.add(new PojoFragment("Failed", "110.00", "22-09-2022 11:04:00", "Amount paid for Recharge payment"));
        lst_transaction.add(new PojoFragment("Pending", "200.00", "28-09-2022 11:04:00", "Amount paid for Government bill payment"));
        lst_transaction.add(new PojoFragment("Success", "500.00", "30-09-2022 11:04:00", "Amount paid for Recharge payment"));
        lst_transaction.add(new PojoFragment("Failed", "800.00", "01-09-2022 11:04:00", "Amount paid for Government bill payment"));
        lst_transaction.add(new PojoFragment("Success", "100.00", "23-08-2022 08:00:00", "Amount paid for Recharge payment"));
        lst_transaction.add(new PojoFragment("Pending", "110.00", "25-09-2022 11:04:00", "Amount paid for Government bill payment"));
        lst_transaction.add(new PojoFragment("Failed", "110.00", "22-09-2022 11:04:00", "Amount paid for Recharge payment"));
        lst_transaction.add(new PojoFragment("Pending", "200.00", "28-09-2022 11:04:00", "Amount paid for Government bill payment"));
        lst_transaction.add(new PojoFragment("Success", "500.00", "30-09-2022 11:04:00", "Amount paid for Recharge payment"));
        lst_transaction.add(new PojoFragment("Failed", "800.00", "01-09-2022 11:04:00", "Amount paid for Government bill payment"));

    }
}