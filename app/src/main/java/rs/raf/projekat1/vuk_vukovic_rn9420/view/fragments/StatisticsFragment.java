package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;

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

    public StatisticsFragment() {
        super(R.layout.fragment_statistics);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
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
}
