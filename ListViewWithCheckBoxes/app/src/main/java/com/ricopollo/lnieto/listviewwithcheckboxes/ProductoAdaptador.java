package com.ricopollo.lnieto.listviewwithcheckboxes;

import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lnieto on 29/10/2016.
 */

class Producto
{
    private String pcCodPro;
    private String pcNombre;
    private Boolean plEscogido;

    public Producto(String tcCodPro, String tcNombre, Boolean tlValor)
    {
        super();
        this.pcCodPro = tcCodPro;
        this.pcNombre = tcNombre;
        this.plEscogido = tlValor;
    }

    public String getPcCodPro() {
        return pcCodPro;
    }

    public void setPcCodPro(String pcCodPro) {
        this.pcCodPro = pcCodPro;
    }

    public String getPcNombre() {
        return pcNombre;
    }

    public void setPcNombre(String pcNombre) {
        this.pcNombre = pcNombre;
    }

    public Boolean getPlEscogido() {
        return plEscogido;
    }

    public void setPlEscogido(Boolean plEscogido) {
        this.plEscogido = plEscogido;
    }
}

public class ProductoAdaptador extends ArrayAdapter<Producto> {

    private List<Producto> laProductos;
    private Context loContexto;

    public ProductoAdaptador(List<Producto> laProductos, Context loContexto){
        super(loContexto, R.layout.activity_main, laProductos);
        this.laProductos = laProductos;
        this.loContexto = loContexto;
    }

    private static class ProductoHolder
    {
        public TextView productoCodigo;
        public TextView productoNombre;
        public CheckBox productoEscogido;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vista = convertView;
        ProductoHolder holder = new ProductoHolder();

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) loContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.activity_main, null);

            holder.productoCodigo = (TextView) vista.findViewById(R.id.dist);
            holder.productoNombre = (TextView) vista.findViewById(R.id.name);
            holder.productoEscogido = (CheckBox) vista.findViewById(R.id.chk_box);

            holder.productoEscogido.setOnCheckedChangeListener((MainActivity) loContexto);
        }
        else
        {
            holder = (ProductoHolder) vista.getTag();
        }

        Producto prod = laProductos.get(position);
        holder.productoCodigo.setText((prod.getPcCodPro()));
        holder.productoNombre.setText(prod.getPcNombre());
        holder.productoEscogido.setChecked(prod.getPlEscogido());
        holder.productoEscogido.setTag(prod);

        return vista;
        //return super.getView(position, convertView, parent);
    }
}
