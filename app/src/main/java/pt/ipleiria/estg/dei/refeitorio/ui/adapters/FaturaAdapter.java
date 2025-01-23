package pt.ipleiria.estg.dei.refeitorio.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.ipleiria.estg.dei.refeitorio.data.models.Fatura;
import pt.ipleiria.estg.dei.refeitorio.data.models.Linhas;
import pt.ipleiria.estg.dei.refeitorio.databinding.ItemFaturaBinding;
import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.databinding.ItemFaturaLinhaBinding;
import pt.ipleiria.estg.dei.refeitorio.helpers.DateUtils;

public class FaturaAdapter extends RecyclerView.Adapter<FaturaAdapter.ViewHolder> {
    private Fatura[] list;

    private Context context;

    public FaturaAdapter(Fatura[] list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(ItemFaturaBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fatura item = list[position];
        holder.binding.txtNfatura.setText(String.valueOf(item.id));

        String formattedDate = item.data.substring(0, 10); // Date formatter
        holder.binding.txtDate.setText(DateUtils.formatToddMMyyyy(formattedDate));

        holder.binding.txtIliquido.setText(context.getString(R.string.total_liquido, item.totalIliquido));
        holder.binding.txtIva.setText(context.getString(R.string.total_iva, item.totalIVA));
        holder.binding.txtTotal.setText(context.getString(R.string.total, item.totalDoc));

        for (Linhas linha : item.getLinhas()) {
            ItemFaturaLinhaBinding linhaBinding = ItemFaturaLinhaBinding.inflate(LayoutInflater.from(context));
            linhaBinding.txtNsenha.setText(String.valueOf(linha.getSenhaId()));
            linhaBinding.txtQuantidade.setText(String.valueOf(linha.getQuantidade()));
            linhaBinding.txtPreco.setText(String.valueOf(linha.getPreco()));
            linhaBinding.txtTaxa.setText(String.valueOf(linha.getTaxaIva()));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            linhaBinding.contentPanel.setLayoutParams(lp);
            holder.binding.linhasContent.addView(linhaBinding.contentPanel);
        }

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
