package com.example.ensai.todo;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ensai on 13/05/16.
 */
public class ElementAdapter extends BaseAdapter{


    List<Element> liste = new ArrayList<Element>();
    Context context ;

    public ElementAdapter(Context context, List<Element> liste) {
        this.context = context;
        this.liste = liste;
    }

    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public Object getItem(int position) {
        return liste.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Element element = (Element) getItem(position);
        View view =  LayoutInflater.from(context).inflate(R.layout.item_element, parent, false);
        TextView nom = (TextView) view.findViewById(R.id.item_nom);
        TextView commentaire = (TextView) view.findViewById(R.id.item_com);
        ImageView type = (ImageView) view.findViewById(R.id.item_type);

        nom.setText(element.getNom());
        commentaire.setText(element.getCommentaire());

        if (element.getType().equals("Mémo")) {type.setImageResource(R.drawable.ic_assignment_memo);}
        if (element.getType().equals("Rendez-Vous")) {type.setImageResource(R.drawable.ic_people_rdv);}
        if (element.getType().equals("Anniversaire")) {type.setImageResource(R.drawable.ic_action_anniv);}
        if (element.getType().equals("Echéance")) {type.setImageResource(R.drawable.ic_today_echeance);}

        /*switch (element.getType()) {
            case "Mémo" :
                type.setImageResource(R.drawable.ic_assignment_memo);
                break;
            case "Rendez-Vous" :
                type.setImageResource(R.drawable.ic_people_rdv);
                break;
            case "Anniversaire" :
                type.setImageResource(R.drawable.ic_action_anniv);
                break;
            case "Echéance" :
                type.setImageResource(R.drawable.ic_today_echeance);
                break;
            //default et autre
        }*/

        return view;
    }

}
