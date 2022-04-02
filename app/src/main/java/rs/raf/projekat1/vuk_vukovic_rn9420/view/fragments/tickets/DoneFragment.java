package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.tickets;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.TicketAction;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.TicketDetailsFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.adapter.DoneAdapter;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.adapter.InProgressAdapter;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.differ.TicketDiffer;
import rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel.DoneViewModel;

public class DoneFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText searchEditText;
    private DoneViewModel doneViewModel;
    private DoneAdapter doneAdapter;

    public DoneFragment() {
        super(R.layout.fragment_done_recycler);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doneViewModel = new ViewModelProvider(requireActivity()).get(DoneViewModel.class);

        initView(view);
        initListeners();
        initObservers();
        initRecycler();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.doneRecyclerView);
        searchEditText = view.findViewById(R.id.doneSearchEditText);
    }

    private void initListeners(){
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable editable) {
                doneViewModel.search(editable.toString());
            }
        });
    }

    private void initObservers() {
        doneViewModel.getTickets().observe(getViewLifecycleOwner(), tickets -> {
            doneAdapter.submitList(tickets);
        });
    }

    private void initRecycler() {
        doneAdapter = new DoneAdapter(new TicketDiffer(), ticketInfo -> {
            if(ticketInfo.getAction().equals(TicketAction.OPEN_DETAILS)){
                startDetailsFragment(ticketInfo.getTicket());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(doneAdapter);
    }

    private void startDetailsFragment(Ticket ticket){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragmentContainerMain, new TicketDetailsFragment(ticket));
        transaction.commit();
    }
}
