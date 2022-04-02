package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.tickets;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.TicketAction;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.TicketDetailsFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.adapter.InProgressAdapter;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.adapter.ToDoAdapter;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.differ.TicketDiffer;
import rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel.DoneViewModel;
import rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel.InProgressViewModel;
import rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel.ToDoViewModel;

public class InProgressFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText searchEditText;

    private InProgressViewModel inProgressViewModel;
    private InProgressAdapter inProgressAdapter;

    private ToDoViewModel toDoViewModel;
    private DoneViewModel doneViewModel;

    public InProgressFragment() {
        super(R.layout.fragment_inprogress_recycler);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toDoViewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        inProgressViewModel = new ViewModelProvider(requireActivity()).get(InProgressViewModel.class);
        doneViewModel = new ViewModelProvider(requireActivity()).get(DoneViewModel.class);

        initView(view);
        initListeners();
        initObservers();
        initRecycler();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.inProgressRecyclerView);
        searchEditText = view.findViewById(R.id.inProgressSearchEditText);
    }

    private void initListeners(){
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //TODO SEARCH
            }
        });
    }

    private void initObservers() {
        inProgressViewModel.getTickets().observe(getViewLifecycleOwner(), tickets -> {
            inProgressAdapter.submitList(tickets);
        });
    }

    private void initRecycler() {
        inProgressAdapter = new InProgressAdapter(new TicketDiffer(), ticketInfo -> {
            if(ticketInfo.getAction().equals(TicketAction.OPEN_DETAILS)){
                startDetailsFragment(ticketInfo.getTicket());
            }
            else if(ticketInfo.getAction().equals(TicketAction.MOVE_TO_DONE)){
                inProgressViewModel.removeTicket(ticketInfo.getTicket());
                //TODO ADD TO DONE
            }
            else {
                inProgressViewModel.removeTicket(ticketInfo.getTicket());
                toDoViewModel.addTicket(ticketInfo.getTicket());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(inProgressAdapter);
    }

    private void startDetailsFragment(Ticket ticket){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragmentContainerMain, new TicketDetailsFragment(ticket));
        transaction.commit();
    }
}
