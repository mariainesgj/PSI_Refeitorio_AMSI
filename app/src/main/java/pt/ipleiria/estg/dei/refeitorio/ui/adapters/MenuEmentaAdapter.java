package pt.ipleiria.estg.dei.refeitorio.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import pt.ipleiria.estg.dei.refeitorio.R;
import pt.ipleiria.estg.dei.refeitorio.data.models.EmentaMenu;
import pt.ipleiria.estg.dei.refeitorio.data.models.PratoDia;
import pt.ipleiria.estg.dei.refeitorio.databinding.ItemEmentaMenuBinding;
import pt.ipleiria.estg.dei.refeitorio.helpers.DateUtils;

public class MenuEmentaAdapter extends RecyclerView.Adapter<MenuEmentaAdapter.ViewHolder>{
    private EmentaMenu[] list;

    private Context context;

    private MenuEmentaCallback callback;
    public MenuEmentaAdapter(EmentaMenu[] list, MenuEmentaCallback callback){
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(ItemEmentaMenuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EmentaMenu item = list[position];
        holder.binding.txtDate.setText(DateUtils.formatToddMMyyyy(item.data));

        holder.binding.txtSopa.setText(item.sopaNome) ;
        holder.binding.txtPratoPrincipal.setText(item.pratoNormalNome);
        holder.binding.txtPratoVegetariano.setText(item.pratoVegetarianoNome);

        holder.binding.btnPrincipal.setStrokeColor(AppCompatResources.getColorStateList(context, android.R.color.transparent));
        holder.binding.btnVegetariano.setStrokeColor(AppCompatResources.getColorStateList(context, android.R.color.transparent));
        holder.binding.btnNaoMarcado.setStrokeColor(AppCompatResources.getColorStateList(context, android.R.color.transparent));

        holder.binding.labelPratoPrincipal.setTextColor(AppCompatResources.getColorStateList(context, android.R.color.white));
        holder.binding.txtPratoPrincipal.setTextColor(AppCompatResources.getColorStateList(context, android.R.color.white));

        holder.binding.labelPratoVegetariano.setTextColor(AppCompatResources.getColorStateList(context, android.R.color.white));
        holder.binding.txtPratoVegetariano.setTextColor(AppCompatResources.getColorStateList(context, android.R.color.white));

        holder.binding.actions.setVisibility(View.VISIBLE);
        holder.binding.txtEditTip.setVisibility(View.GONE);

        if(item.linhaCarrinhoNormalId != null){
            holder.binding.btnPrincipal.setStrokeColor(AppCompatResources.getColorStateList(context, R.color.lightAzul));

            holder.binding.labelPratoVegetariano.setTextColor(AppCompatResources.getColorStateList(context, R.color.azul_md));
            holder.binding.txtPratoVegetariano.setTextColor(AppCompatResources.getColorStateList(context, R.color.azul_md));
        }

        if(item.linhaCarrinhoVegetarianoId != null){
            holder.binding.btnVegetariano.setStrokeColor(AppCompatResources.getColorStateList(context, R.color.lightAzul));

            holder.binding.labelPratoPrincipal.setTextColor(AppCompatResources.getColorStateList(context, R.color.azul_md));
            holder.binding.txtPratoPrincipal.setTextColor(AppCompatResources.getColorStateList(context, R.color.azul_md));
        }

        if(item.linhaCarrinhoVegetarianoId == null && item.linhaCarrinhoNormalId == null){
            holder.binding.btnNaoMarcado.setStrokeColor(AppCompatResources.getColorStateList(context, R.color.lightAzul));
        }

        if(item.senhaId != null){
            holder.binding.actions.setVisibility(View.GONE);
            holder.binding.txtEditTip.setVisibility(View.VISIBLE);
        }

        holder.binding.btnPrincipal.setOnClickListener(event -> {
            if(item.linhaCarrinhoNormalId == null){
                callback.onClick(item, MenuEmentaOption.PRINCIPAL );
            }
        });

        holder.binding.btnVegetariano.setOnClickListener(event -> {
            if(item.linhaCarrinhoVegetarianoId == null){
                callback.onClick(item, MenuEmentaOption.VEGETARIANO );
            }

        });

        holder.binding.btnNaoMarcado.setOnClickListener(event -> {
            if(item.linhaCarrinhoVegetarianoId != null || item.linhaCarrinhoNormalId != null){
                callback.onClick(item, MenuEmentaOption.NAO_MARCADO );
            }

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
