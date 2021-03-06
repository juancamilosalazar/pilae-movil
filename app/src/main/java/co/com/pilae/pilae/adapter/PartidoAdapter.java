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
import co.com.pilae.pilae.entidades.Partido;
import co.com.pilae.pilae.persistencia.room.DataBaseHelper;

public class PartidoAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<Partido> partidossOut;
    private List<Partido> partidosIn;
    DataBaseHelper dataBaseHelper;

    public PartidoAdapter(Context context, List<Partido> partidos, DataBaseHelper db){
        partidossOut = partidos;
        partidosIn = partidos;
        inflater = LayoutInflater.from(context);
        dataBaseHelper= db;
    }

    @Override
    public int getCount() {
        return partidossOut.size();
    }

    @Override
    public Partido getItem(int position) {
        return partidossOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PartidoAdapter.ViewHolder holder;
        if (convertView != null) {
            holder = (PartidoAdapter.ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.partido_item_layout, parent, false);
            holder = new PartidoAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }
        Equipo equipoLocal = getEquipo(partidossOut.get(position).getEquipoLocal().toString());
        Equipo equipoVisitante =getEquipo(partidossOut.get(position).getEquipoVisitante().toString());

        holder.equipoLocalPartido.setText(equipoLocal.getNombre());
        holder.equipoVisitantePartido.setText(equipoVisitante.getNombre());
        holder.idaVuelta.setText(partidossOut.get(position).getIdaVuelta());
        holder.fechaPartido.setText(partidossOut.get(position).getFecha());

        return convertView;
    }

    private Equipo getEquipo(String id) {
        return dataBaseHelper.getEquipoDAO().findByIdEquipo(id);
    }

    class ViewHolder {
        @BindView(R.id.equipoLocalPartido)
        TextView equipoLocalPartido;
        @BindView(R.id.equipoVisitantePartido)
        TextView equipoVisitantePartido;
        @BindView(R.id.idaVuelta)
        TextView idaVuelta;
        @BindView(R.id.fechaPartido)
        TextView fechaPartido;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
