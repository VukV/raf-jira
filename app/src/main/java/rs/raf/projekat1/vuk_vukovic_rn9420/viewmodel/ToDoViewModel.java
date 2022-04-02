package rs.raf.projekat1.vuk_vukovic_rn9420.viewmodel;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;

public class ToDoViewModel extends ViewModel {

    public static int counter = 0;

    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>();
    private final MutableLiveData<Integer> ticketCount = new MutableLiveData<>(0);
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

        ticketCount.setValue(ticketCount.getValue() + 1);
    }

    public void updateLoggedTime(Ticket ticket){
        ticket.setLoggedTime(ticket.getLoggedTime() + 1);

        //ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketList);
        //tickets.setValue(listToSubmit);
    }
}
