package pt.ipleiria.estg.dei.refeitorio.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.ipleiria.estg.dei.refeitorio.data.models.Fatura;
import pt.ipleiria.estg.dei.refeitorio.databinding.ItemFaturaBinding;

public class FaturaAdapter extends RecyclerView.Adapter<FaturaAdapter.ViewHolder> {
    private Fatura[] list;
    public FaturaAdapter(Fatura[] list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(ItemFaturaBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fatura item = list[position];
        holder.binding.txtNfatura.setText(String.valueOf(item.id));
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    static class ViewHolder extends  RecyclerView.ViewHolder {
        private ItemFaturaBinding binding;
        public ViewHolder(ItemFaturaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
