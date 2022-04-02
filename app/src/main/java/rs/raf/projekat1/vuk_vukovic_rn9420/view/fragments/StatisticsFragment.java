package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel.InProgressViewModel;
import rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel.ToDoViewModel;

public class StatisticsFragment extends Fragment {

    private TextView toDoTotalTextView;
    private TextView toDoEnhancementsTextView;
    private TextView toDoBugsTextView;

    private TextView inProgressTotalTextView;
    private TextView inProgressEnhancementsTextView;
    private TextView inProgressBugsTextView;

    private TextView doneTotalTextView;
    private TextView doneEnhancementsTextView;
    private TextView doneBugsTextView;

    private ToDoViewModel toDoViewModel;
    private InProgressViewModel inProgressViewModel;

    public StatisticsFragment() {
        super(R.layout.fragment_statistics);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toDoViewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        inProgressViewModel = new ViewModelProvider(requireActivity()).get(InProgressViewModel.class);

        initView(view);
        initObservers();
    }

    private void initView(View view){
        toDoTotalTextView = view.findViewById(R.id.todoTotal);
        toDoEnhancementsTextView = view.findViewById(R.id.todoEnhancements);
        toDoBugsTextView = view.findViewById(R.id.todoBugs);

        inProgressTotalTextView = view.findViewById(R.id.inProgressTotal);
        inProgressEnhancementsTextView = view.findViewById(R.id.inProgressEnhancements);
        inProgressBugsTextView = view.findViewById(R.id.inProgressBugs);

        doneTotalTextView = view.findViewById(R.id.doneTotal);
        doneEnhancementsTextView = view.findViewById(R.id.doneEnhancements);
        doneBugsTextView = view.findViewById(R.id.doneBugs);
    }

    private void initObservers(){
        toDoViewModel.getTicketCount().observe(getViewLifecycleOwner(), ticketCount -> {
            toDoTotalTextView.setText(String.valueOf(ticketCount));
        });

        toDoViewModel.getBugCount().observe(getViewLifecycleOwner(), bugCount -> {
            toDoBugsTextView.setText(String.valueOf(bugCount));
        });

        toDoViewModel.getEnhancementCount().observe(getViewLifecycleOwner(), enhCount -> {
            toDoEnhancementsTextView.setText(String.valueOf(enhCount));
        });

        inProgressViewModel.getTicketCount().observe(getViewLifecycleOwner(), ticketCount -> {
            inProgressTotalTextView.setText(String.valueOf(ticketCount));
        });

        inProgressViewModel.getEnhancementCount().observe(getViewLifecycleOwner(), ticketCount -> {
            inProgressEnhancementsTextView.setText(String.valueOf(ticketCount));
        });

        inProgressViewModel.getBugCount().observe(getViewLifecycleOwner(), ticketCount -> {
            inProgressBugsTextView.setText(String.valueOf(ticketCount));
        });

        //TODO OSTALI VIEWMODELI I OBSERVERI
    }
}
