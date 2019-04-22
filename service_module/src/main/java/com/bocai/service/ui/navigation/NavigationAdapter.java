package com.bocai.service.ui.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bocai.service.R;
import com.bocai.service.bean.ServiceDealers;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/19
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ServiceDealers> list;

    public NavigationAdapter(Context context, List<ServiceDealers> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = inflater.inflate(R.layout.service_adapter_navigation, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationAdapter.ViewHolder holder, int position) {
        ServiceDealers item = list.get(position);
        holder.tvName.setText(item.getTitle());
        holder.tvCall.setText(item.getTelphone());
        holder.tvDetail.setText(item.getFullTitle());
        holder.tvDistance.setText(item.getDistance());
        holder.rlTop.setOnClickListener(v -> {
            if (onSelectItemListener != null) {
                onSelectItemListener.onSelectItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDetail;
        TextView tvCall;
        TextView tvDistance;
        RelativeLayout rlTop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            tvCall = itemView.findViewById(R.id.tv_call);
            tvDistance = itemView.findViewById(R.id.tv_distance);
            rlTop = itemView.findViewById(R.id.rl_top);
        }
    }

    private OnSelectItemListener onSelectItemListener;

    public void setOnSelectItemListener(OnSelectItemListener onSelectItemListener) {
        this.onSelectItemListener = onSelectItemListener;
    }

    public interface OnSelectItemListener {
        void onSelectItemClick(ServiceDealers item);
    }
}
