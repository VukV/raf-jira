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
import androidx.fragment.app.FragmentTransaction;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Priority;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Type;

public class EditFragment extends Fragment {

    private Ticket ticket;

    private Spinner prioritySpinner;
    private Spinner typeSpinner;
    private EditText estEditText;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button saveButton;

    public EditFragment(Ticket ticket) {
        super(R.layout.fragment_edit);
        this.ticket = ticket;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        handleTicket();
        initListeners();
    }

    private void initView(View view){
        prioritySpinner = view.findViewById(R.id.editTicketPrioritySpinner);
        typeSpinner = view.findViewById(R.id.editTicketTypeSpinner);

        estEditText = view.findViewById(R.id.editTicketEstEditText);
        titleEditText = view.findViewById(R.id.editTicketTitleEditText);
        descriptionEditText = view.findViewById(R.id.editTicketDescEditText);

        initSpinners();
        saveButton = view.findViewById(R.id.saveTicketButton);
    }

    private void initSpinners(){
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.ticket_types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.ticket_priorities, android.R.layout.simple_spinner_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);
    }

    private void handleTicket(){
        if (ticket != null){
            titleEditText.setText(ticket.getTitle());
            estEditText.setText(String.valueOf(ticket.getEstimation()));
            descriptionEditText.setText(ticket.getDescription());

            setTypeSpinner();
            setPrioritySpinner();
        }
    }

    private void setTypeSpinner(){
        if(ticket.getType().equals(Type.ENHANCEMENT)){
            typeSpinner.setSelection(0);
        }
        else {
            typeSpinner.setSelection(1);
        }
    }

    private void setPrioritySpinner(){
        switch (ticket.getPriority()){
            case HIGHEST: prioritySpinner.setSelection(0); break;
            case HIGH: prioritySpinner.setSelection(1); break;
            case MEDIUM: prioritySpinner.setSelection(2); break;
            case LOW: prioritySpinner.setSelection(3); break;
            default: prioritySpinner.setSelection(4);
        }
    }

    private void initListeners(){
        saveButton.setOnClickListener(click -> {
            if (checkUserInput()){
                saveTicket();
                getActivity().onBackPressed();
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

    private void saveTicket(){
        ticket.setTitle(titleEditText.getText().toString());
        ticket.setDescription(descriptionEditText.getText().toString());
        ticket.setEstimation(Integer.parseInt(estEditText.getText().toString()));
        ticket.setType(getType());
        ticket.setPriority(getPriority());
    }

    private Type getType(){
        if(typeSpinner.getSelectedItem().toString().equalsIgnoreCase(getString(R.string.enhancement))){
            return Type.ENHANCEMENT;
        }
        else {
            return Type.BUG;
        }
    }

    private Priority getPriority(){
        String priority = prioritySpinner.getSelectedItem().toString();

        if (priority.equalsIgnoreCase(getString(R.string.lowest))){
            return Priority.LOWEST;
        }
        else if (priority.equalsIgnoreCase(getString(R.string.low))){
            return Priority.LOW;
        }
        else if (priority.equalsIgnoreCase(getString(R.string.medium))){
            return Priority.MEDIUM;
        }
        else if (priority.equalsIgnoreCase(getString(R.string.high))){
            return Priority.HIGH;
        }
        else {
            return Priority.HIGHEST;
        }
    }

}
