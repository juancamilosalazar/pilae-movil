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
import co.com.pilae.pilae.entidades.Equipo;

public class EquipoAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<Equipo> equiposOut;
    private List<Equipo> equiposin;


    public EquipoAdapter(Context context, List<Equipo> equipos){
        equiposOut = equipos;
        equiposin  = equipos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return equiposOut.size();
    }

    @Override
    public Equipo getItem(int position) {
        return equiposOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.equipo_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.txtNombreEquipo.setText(equiposOut.get(position).getNombre());
        holder.txtDeporteEquipo.setText(equiposOut.get(position).getDeporte());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.nombreEquipo)
        TextView txtNombreEquipo;
        @BindView(R.id.deporteEquipo)
        TextView txtDeporteEquipo;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
