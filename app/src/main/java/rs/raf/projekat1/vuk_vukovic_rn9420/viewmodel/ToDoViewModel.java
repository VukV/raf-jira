package rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;

public class ToDoViewModel extends ViewModel {

    public static int counter = 0;

    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>();
    private ArrayList<Ticket> ticketList = new ArrayList<>();

    public LiveData<List<Ticket>> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket){
        counter++;
        ticket.setId(counter);

        ticketList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketList);
        tickets.setValue(listToSubmit);
    }
}
