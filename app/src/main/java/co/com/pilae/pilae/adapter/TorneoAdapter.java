package co.com.pilae.pilae.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.entidades.Torneo;

public class TorneoAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<Torneo> torneossOut;
    private List<Torneo> torneosIn;


    public TorneoAdapter(Context context, List<Torneo> torneos){
        torneossOut = torneos;
        torneosIn = torneos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return torneossOut.size();
    }

    @Override
    public Torneo getItem(int position) {
        return torneossOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TorneoAdapter.ViewHolder holder;
        if (convertView != null) {
            holder = (TorneoAdapter.ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.torneo_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.txtNombreTorneo.setText(torneossOut.get(position).getNombre());
        holder.txtDeporteTorneo.setText(torneossOut.get(position).getDeporte());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.nombreTorneo)
        TextView txtNombreTorneo;
        @BindView(R.id.deporteTroneo)
        TextView txtDeporteTorneo;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
