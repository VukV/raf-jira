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

public class ToDoAdapter extends ListAdapter<Ticket, ToDoAdapter.ViewHolder> {

    private final Consumer<Ticket> onTicketClicked;

    public ToDoAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_todo, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
            Ticket ticket = getItem(position);
            onTicketClicked.accept(ticket);
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
        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.accept(getBindingAdapterPosition());
                }
            });
        }

        public void bind(Ticket ticket){
            ImageView ticketImageView = itemView.findViewById(R.id.ticketImageView);
            if (ticket.getType().equalsIgnoreCase(context.getString(R.string.enhancement))){
                ticketImageView.setImageResource(R.drawable.ic_enhancement);
            }
            else {
                ticketImageView.setImageResource(R.drawable.ic_bug);
            }

            ((TextView)itemView.findViewById(R.id.ticketTitleTextView)).setText(ticket.getTitle());
            ((TextView)itemView.findViewById(R.id.ticketDescTextView)).setText(ticket.getDescription());
        }
    }
}
