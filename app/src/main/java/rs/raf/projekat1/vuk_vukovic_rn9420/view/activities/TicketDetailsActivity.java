package rs.raf.projekat1.vuk_vukovic_rn9420.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;
import rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel.ToDoViewModel;

public class TicketDetailsActivity extends AppCompatActivity {

    public static final String TICKET_KEY = "ticket";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);

        initView();
        parseTicket();
        initListeners();
    }

    private void initView(){
        loggedTimeButton = findViewById(R.id.loggedTimeButton);
        openEditButton = findViewById(R.id.openEditButton);

        titleTextView = findViewById(R.id.ticketTitleTextView);
        typeTextView = findViewById(R.id.ticketType);
        priorityTextView = findViewById(R.id.ticketPriority);
        estimationTextView = findViewById(R.id.ticketEstimation);
        descriptionTextView = findViewById(R.id.ticketDescription);

        iconImageView = findViewById(R.id.ticketDetailsImageView);
    }

    private void parseTicket(){
        Intent intent = getIntent();
        if (intent.getExtras() != null){
            this.ticket = intent.getExtras().getParcelable(TICKET_KEY);
        }

        if(ticket != null){
            titleTextView.setText(ticket.getTitle());
            typeTextView.setText(ticket.getType());
            priorityTextView.setText(ticket.getPriority());
            estimationTextView.setText(String.valueOf(ticket.getEstimation()));
            descriptionTextView.setText(ticket.getDescription());

            loggedTimeButton.setText(String.valueOf(ticket.getLoggedTime()));
            /*if (ticket.getType().equalsIgnoreCase(getString(R.string.enhancement))){
                iconImageView.setImageResource(R.drawable.ic_enhancement);
            }
            else {
                iconImageView.setImageResource(R.drawable.ic_bug);
            }*/
            iconImageView.setImageResource(R.drawable.ic_bug);
        }
    }

    private void initListeners(){
        loggedTimeButton.setOnClickListener(click -> {
            toDoViewModel.updateLoggedTime(ticket);
            loggedTimeButton.setText(String.valueOf(ticket.getLoggedTime()));
        });

        openEditButton.setOnClickListener(click -> {
            //TODO open edit activity
        });
    }
}