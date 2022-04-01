package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.tickets;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.adapter.ToDoAdapter;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.differ.TicketDiffer;
import rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel.ToDoViewModel;

public class ToDoFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText searchEditText;

    private ToDoViewModel toDoViewModel;
    private ToDoAdapter toDoAdapter;

    public ToDoFragment() {
        super(R.layout.fragment_ticket_recycler);

        //toDoViewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        //TODO - zasto crash?

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toDoViewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);

        initView(view);
        initListeners();
        initObservers();
        initRecycler();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.ticketRecyclerView);
        searchEditText = view.findViewById(R.id.ticketSearchEditText);
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

            }
        });
    }

    private void initObservers() {
        toDoViewModel.getTickets().observe(getViewLifecycleOwner(), cars -> {
            toDoAdapter.submitList(cars);
        });
    }

    private void initRecycler() {
        toDoAdapter = new ToDoAdapter(new TicketDiffer(), ticket -> {
            //TODO details activity
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(toDoAdapter);
    }
}
