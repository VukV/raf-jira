package rs.raf.projekat1.vuk_vukovic_rn9420.model;

public class TicketCallbackInfo {

    private Ticket ticket;
    private int position;
    private TicketAction action;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public TicketAction getAction() {
        return action;
    }

    public void setAction(TicketAction action) {
        this.action = action;
    }
}
