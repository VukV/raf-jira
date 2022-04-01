package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;

public class NewFragment extends Fragment {

    private Spinner prioritySpinner;
    private Spinner typeSpinner;
    private EditText estEditText;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button newTicketButton;

    public NewFragment() {
        super(R.layout.fragment_new);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initListeners();
    }

    private void initView(View view){
        prioritySpinner = view.findViewById(R.id.ticketPrioritySpinner);
        typeSpinner = view.findViewById(R.id.ticketTypeSpinner);
        estEditText = view.findViewById(R.id.ticketEstEditText);
        titleEditText = view.findViewById(R.id.ticketTitleEditText);
        descriptionEditText = view.findViewById(R.id.ticketDescEditText);
        newTicketButton = view.findViewById(R.id.newTicketButton);

        initSpinners();
    }

    private void initSpinners(){
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.ticket_types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.ticket_priorities, android.R.layout.simple_spinner_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);
    }

    private void initListeners(){
        newTicketButton.setOnClickListener(click -> {
            if (checkUserInput()){
                //TODO ADD NEW TICKET
            }
        });
    }

    private boolean checkUserInput(){
        String title = titleEditText.getText().toString();
        if (title.equals("")){
            Toast.makeText(getActivity(), getString(R.string.title_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        String desc = descriptionEditText.getText().toString();
        if (desc.equals("")){
            Toast.makeText(getActivity(), getString(R.string.desc_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        String est = estEditText.getText().toString();
        if (est.equals("")){
            Toast.makeText(getActivity(), getString(R.string.est_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
