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
import co.com.pilae.pilae.entidades.Partido;
import co.com.pilae.pilae.entidades.TablaPosicion;

public class TablaPosicionAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<TablaPosicion> tablaPosicionOut;
    private List<TablaPosicion> yablaPosicionIn;


    public TablaPosicionAdapter(Context context, List<TablaPosicion> tablaPosicions){
        tablaPosicionOut = tablaPosicions;
        yablaPosicionIn = tablaPosicions;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tablaPosicionOut.size();
    }

    @Override
    public TablaPosicion getItem(int position) {
        return tablaPosicionOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TablaPosicionAdapter.ViewHolder holder;
        if (convertView != null) {
            holder = (TablaPosicionAdapter.ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.tabla_posiciones_item_layout, parent, false);
            holder = new TablaPosicionAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.equipoPosicion.setText(tablaPosicionOut.get(position).getEquipo().toString());
        holder.pj.setText(String.valueOf(tablaPosicionOut.get(position).getPartidosJugados()));
        holder.pg.setText(String.valueOf(tablaPosicionOut.get(position).getPartidosGanados()));
        holder.pp.setText(String.valueOf(tablaPosicionOut.get(position).getPartidosPerdidos()));
        holder.pe.setText(String.valueOf(tablaPosicionOut.get(position).getPartidosEmpatados()));
        holder.gf.setText(String.valueOf(tablaPosicionOut.get(position).getGolesFavor()));
        holder.ge.setText(String.valueOf(tablaPosicionOut.get(position).getGolesContra()));
        holder.gd.setText(String.valueOf(tablaPosicionOut.get(position).getGolesDiferencia()));
        holder.pts.setText(String.valueOf(tablaPosicionOut.get(position).getPuntos()));
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.equipoPosicion)
        TextView equipoPosicion;
        @BindView(R.id.pj)
        TextView pj;
        @BindView(R.id.pg)
        TextView pg;
        @BindView(R.id.pp)
        TextView pp;
        @BindView(R.id.pe)
        TextView pe;
        @BindView(R.id.gf)
        TextView gf;
        @BindView(R.id.ge)
        TextView ge;
        @BindView(R.id.gd)
        TextView gd;
        @BindView(R.id.pts)
        TextView pts;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
