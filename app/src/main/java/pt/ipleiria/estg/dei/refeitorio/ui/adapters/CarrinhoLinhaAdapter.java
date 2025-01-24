package pt.ipleiria.estg.dei.refeitorio.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.ipleiria.estg.dei.refeitorio.data.models.LinhasSimple;
import pt.ipleiria.estg.dei.refeitorio.databinding.ItemCarrinhoLinhaBinding;
import pt.ipleiria.estg.dei.refeitorio.helpers.DateUtils;

public class CarrinhoLinhaAdapter extends RecyclerView.Adapter<CarrinhoLinhaAdapter.ViewHolder> {
    private Context context;
    private LinhasSimple[] list;

    public CarrinhoLinhaAdapter(LinhasSimple[] list ){
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        return new ViewHolder(ItemCarrinhoLinhaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LinhasSimple item = list[position];

        holder.binding.txtPrato.setText(item.pratoNome);
        holder.binding.txtDate.setText(DateUtils.formatToddMMyyyy(item.date));
        holder.binding.txtPreco.setText("3,50 €");
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCarrinhoLinhaBinding binding;
        public ViewHolder(@NonNull ItemCarrinhoLinhaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
