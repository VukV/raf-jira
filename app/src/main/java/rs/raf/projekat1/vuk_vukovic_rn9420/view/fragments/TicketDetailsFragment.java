package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.data.LoginData;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Priority;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.State;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Type;
import rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel.ToDoViewModel;

public class TicketDetailsFragment extends Fragment {

    private Ticket ticket;
    private ToDoViewModel toDoViewModel;

    private Button loggedTimeButton;
    private Button openEditButton;
    private TextView titleTextView;
    private TextView typeTextView;
    private TextView priorityTextView;
    private TextView estimationTextView;
    private TextView descriptionTextView;
    private ImageView iconImageView;

    public TicketDetailsFragment(Ticket ticket) {
        super(R.layout.fragment_ticket_details);
        this.ticket = ticket;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toDoViewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);

        initView(view);
        handleTicket();
        initListeners();
    }

    private void initView(View view){
        loggedTimeButton = view.findViewById(R.id.loggedTimeButton);
        openEditButton = view.findViewById(R.id.openEditButton);

        titleTextView = view.findViewById(R.id.ticketTitleTextView);
        typeTextView = view.findViewById(R.id.ticketType);
        priorityTextView = view.findViewById(R.id.ticketPriority);
        estimationTextView = view.findViewById(R.id.ticketEstimation);
        descriptionTextView = view.findViewById(R.id.ticketDescription);

        iconImageView = view.findViewById(R.id.ticketDetailsImageView);

        checkPrivileges();
    }

    private void handleTicket(){
        if (ticket != null){
            titleTextView.setText(ticket.getTitle());
            estimationTextView.setText(String.valueOf(ticket.getEstimation()));
            descriptionTextView.setText(ticket.getDescription());

            setTypeText();
            setPriorityText();

            loggedTimeButton.setText(String.valueOf(ticket.getLoggedTime()));
            if (ticket.getType().equals(Type.ENHANCEMENT)){
                iconImageView.setImageResource(R.drawable.ic_enhancement);
            }
            else {
                iconImageView.setImageResource(R.drawable.ic_bug);
            }
        }
    }

    private void setTypeText(){
        if(ticket.getType().equals(Type.ENHANCEMENT)){
            typeTextView.setText(R.string.enhancement);
        }
        else {
            typeTextView.setText(R.string.bug);
        }
    }

    private void setPriorityText(){
        switch (ticket.getPriority()){
            case LOWEST: priorityTextView.setText(R.string.lowest); break;
            case LOW: priorityTextView.setText(R.string.low); break;
            case MEDIUM: priorityTextView.setText(R.string.medium); break;
            case HIGH: priorityTextView.setText(R.string.high); break;
            default: priorityTextView.setText(R.string.highest);
        }
    }

    private void initListeners(){
        loggedTimeButton.setOnClickListener(click -> {
            //toDoViewModel.updateLoggedTime(ticket);
            ticket.setLoggedTime(ticket.getLoggedTime() + 1);
            loggedTimeButton.setText(String.valueOf(ticket.getLoggedTime()));
        });

        openEditButton.setOnClickListener(click -> {
            //TODO open edit fragment
        });
    }

    private void checkPrivileges(){
        if (!LoginData.IS_ADMIN){
            openEditButton.setVisibility(View.INVISIBLE);
        }
        if(ticket.getState().equals(State.DONE)){
            openEditButton.setVisibility(View.INVISIBLE);
        }
    }
}
