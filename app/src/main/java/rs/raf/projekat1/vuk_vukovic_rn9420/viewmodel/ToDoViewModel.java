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

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Type;

public class ToDoViewModel extends ViewModel {

    public static int counter = 0;

    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>();
    private final MutableLiveData<Integer> ticketCount = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> enhancementCount = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> bugCount = new MutableLiveData<>(0);
    private ArrayList<Ticket> ticketList = new ArrayList<>();

    public LiveData<List<Ticket>> getTickets() {
        return tickets;
    }

    public LiveData<Integer> getTicketCount(){
        return ticketCount;
    }

    public LiveData<Integer> getEnhancementCount(){
        return enhancementCount;
    }

    public LiveData<Integer> getBugCount(){
        return bugCount;
    }

    public void addTicket(Ticket ticket){
        counter++;
        ticket.setId(counter);

        ticketList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketList);
        tickets.setValue(listToSubmit);

        incrementCount(ticket);
    }

    private void incrementCount(Ticket ticket){
        ticketCount.setValue(ticketCount.getValue() + 1);

        if(ticket.getType().equals(Type.ENHANCEMENT)){
            enhancementCount.setValue(enhancementCount.getValue() + 1);
        }
        else {
            bugCount.setValue(bugCount.getValue() + 1);
        }
    }

    public void removeTicket(Ticket ticket){
        ticketList.remove(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketList);
        tickets.setValue(listToSubmit);

        decrementCount(ticket);
    }

    private void decrementCount(Ticket ticket){
        ticketCount.setValue(ticketCount.getValue() - 1);

        if(ticket.getType().equals(Type.ENHANCEMENT)){
            enhancementCount.setValue(enhancementCount.getValue() - 1);
        }
        else {
            bugCount.setValue(bugCount.getValue() - 1);
        }
    }

    public void updateLoggedTime(Ticket ticket){
        ticket.setLoggedTime(ticket.getLoggedTime() + 1);

        //ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketList);
        //tickets.setValue(listToSubmit);
    }
}
