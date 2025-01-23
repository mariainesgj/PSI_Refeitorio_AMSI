package pt.ipleiria.estg.dei.refeitorio.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.data.models.EmentaMenu;
import pt.ipleiria.estg.dei.refeitorio.data.models.PratoDia;
import pt.ipleiria.estg.dei.refeitorio.databinding.ItemEmentaMenuBinding;
import pt.ipleiria.estg.dei.refeitorio.helpers.DateUtils;

public class MenuEmentaAdapter extends RecyclerView.Adapter<MenuEmentaAdapter.ViewHolder>{
    private EmentaMenu[] list;

    private MenuEmentaCallback callback;
    public MenuEmentaAdapter(EmentaMenu[] list, MenuEmentaCallback callback){
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemEmentaMenuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EmentaMenu item = list[position];
        holder.binding.txtDate.setText(DateUtils.formatToddMMyyyy(item.data));

        holder.binding.txtSopa.setText(item.sopaNome) ;
        holder.binding.txtPratoPrincipal.setText(item.pratoNormalNome);
        holder.binding.txtPratoVegerariano.setText(item.pratoVegetarianoNome);

        holder.binding.btnPrincipal.setOnClickListener(event -> {
            callback.onClick(item, MenuEmentaOption.PRINCIPAL );
        });

        holder.binding.btnVegetariano.setOnClickListener(event -> {
            callback.onClick(item, MenuEmentaOption.VEGETARIANO );
        });

        holder.binding.btnNaoMarcado.setOnClickListener(event -> {
            callback.onClick(item, MenuEmentaOption.NAO_MARCADO );
        });

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEmentaMenuBinding binding;
        public ViewHolder(@NonNull ItemEmentaMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public enum MenuEmentaOption {
        PRINCIPAL, VEGETARIANO, NAO_MARCADO
    }

    public interface MenuEmentaCallback {
        public void onClick(EmentaMenu item, MenuEmentaOption option);
    }


}
