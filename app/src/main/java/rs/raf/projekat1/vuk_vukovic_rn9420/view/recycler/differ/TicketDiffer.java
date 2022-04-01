package rs.raf.projekat1.vuk_vukovic_rn9420.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.vuk_vukovic_rn9420.model.Ticket;

public class TicketDiffer extends DiffUtil.ItemCallback<Ticket> {

    @Override
    public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getTitle().equals(newItem.getTitle())
                && oldItem.getDescription().equals(newItem.getDescription())
                && oldItem.getPriority().equals(newItem.getPriority())
                && oldItem.getType().equals(newItem.getType())
                && oldItem.getEstimation() == newItem.getEstimation()
                && oldItem.getLoggedTime() == newItem.getLoggedTime();
    }
}
