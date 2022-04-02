package rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.adapter;

import android.content.Context;
import android.os.Build;
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
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.TicketAction;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.TicketCallbackInfo;
import rs.raf.projekat1.vuk_vukovic_rn9420.model.Type;

public class InProgressAdapter extends ListAdapter<Ticket, InProgressAdapter.InProgressViewHolder> {

    private final Consumer<TicketCallbackInfo> onTicketClicked;

    public InProgressAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<TicketCallbackInfo> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public InProgressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_in_progress, parent, false);
        return new InProgressViewHolder(view, parent.getContext(), ticketInfo -> {
            Ticket ticket = getItem(ticketInfo.getPosition());
            ticketInfo.setTicket(ticket);
            onTicketClicked.accept(ticketInfo);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull InProgressViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        holder.bind(ticket);
    }

    public static class InProgressViewHolder extends RecyclerView.ViewHolder{

        private final Context context;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public InProgressViewHolder(@NonNull View itemView, Context context, Consumer<TicketCallbackInfo> onItemClicked) {
            super(itemView);
            this.context = context;

            initListeners(itemView, onItemClicked);
        }

        public void bind(Ticket ticket){
            ImageView ticketImageView = itemView.findViewById(R.id.progressImageView);
            if (ticket.getType().equals(Type.ENHANCEMENT)){
                ticketImageView.setImageResource(R.drawable.ic_enhancement);
            }
            else {
                ticketImageView.setImageResource(R.drawable.ic_bug);
            }

            ((TextView)itemView.findViewById(R.id.progressTitleTextView)).setText(ticket.getTitle());
            ((TextView)itemView.findViewById(R.id.progressDescTextView)).setText(ticket.getDescription());
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

            view.findViewById(R.id.backToTodoButton).setOnClickListener(click -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    TicketCallbackInfo info = new TicketCallbackInfo();
                    info.setPosition(getBindingAdapterPosition());
                    info.setAction(TicketAction.MOVE_TO_TODO);
                    onItemClicked.accept(info);
                }
            });

            view.findViewById(R.id.moveToDoneButton).setOnClickListener(click -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    TicketCallbackInfo info = new TicketCallbackInfo();
                    info.setPosition(getBindingAdapterPosition());
                    info.setAction(TicketAction.MOVE_TO_DONE);
                    onItemClicked.accept(info);
                }
            });
        }

    }
}
