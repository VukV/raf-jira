package rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Consumer;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.data.LoginData;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.TicketAction;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.TicketCallbackInfo;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Type;

public class ToDoAdapter extends ListAdapter<Ticket, ToDoAdapter.ViewHolder> {

    private final Consumer<TicketCallbackInfo> onTicketClicked;

    public ToDoAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<TicketCallbackInfo> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_todo, parent, false);
        return new ViewHolder(view, parent.getContext(), ticketInfo -> {
            Ticket ticket = getItem(ticketInfo.getPosition());
            ticketInfo.setTicket(ticket);
            onTicketClicked.accept(ticketInfo);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        holder.bind(ticket);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final Context context;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ViewHolder(@NonNull View itemView, Context context, Consumer<TicketCallbackInfo> onItemClicked) {
            super(itemView);
            this.context = context;

            initPrivileges();
            initListeners(itemView, onItemClicked);
        }

        public void bind(Ticket ticket){
            ImageView ticketImageView = itemView.findViewById(R.id.todoImageView);
            if (ticket.getType().equals(Type.ENHANCEMENT)){
                ticketImageView.setImageResource(R.drawable.ic_enhancement);
            }
            else {
                ticketImageView.setImageResource(R.drawable.ic_bug);
            }

            ((TextView)itemView.findViewById(R.id.todoTitleTextView)).setText(ticket.getTitle());
            ((TextView)itemView.findViewById(R.id.todoDescTextView)).setText(ticket.getDescription());
        }

        private void initPrivileges(){
            if (!LoginData.IS_ADMIN){
                itemView.findViewById(R.id.deleteTicketButton).setVisibility(View.GONE);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void initListeners(View view, Consumer<TicketCallbackInfo> onItemClicked){
            itemView.setOnClickListener(click -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    TicketCallbackInfo info = new TicketCallbackInfo();
                    info.setPosition(getBindingAdapterPosition());
                    info.setAction(TicketAction.OPEN_DETAILS);
                    onItemClicked.accept(info);
                }
            });

            view.findViewById(R.id.deleteTicketButton).setOnClickListener(click -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    TicketCallbackInfo info = new TicketCallbackInfo();
                    info.setPosition(getBindingAdapterPosition());
                    info.setAction(TicketAction.DELETE);
                    onItemClicked.accept(info);
                }
            });

            view.findViewById(R.id.moveToProgressButton).setOnClickListener(click -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    TicketCallbackInfo info = new TicketCallbackInfo();
                    info.setPosition(getBindingAdapterPosition());
                    info.setAction(TicketAction.MOVE_TO_PROGRESS);
                    onItemClicked.accept(info);
                }
            });
        }
    }
}
