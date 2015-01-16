package com.ucv.tavo.aguilas.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ucv.tavo.aguilas.R;
import com.ucv.tavo.aguilas.dto.Game;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 12/12/14.
 */
public class GameAdapter extends ArrayAdapter<Game>{

    private Activity context;
    private List<Game> gameList = new ArrayList<Game>();

    public GameAdapter(Activity context, List<Game> gameList) {
        super(context, R.layout.item_game, gameList);
        this.context = context;
        this.gameList = gameList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_game, null);

        Game game = gameList.get(position);

        if(game.getDsInning()!=null && game.getDsInning()!="") {
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.game_started);
            layout.setVisibility(View.VISIBLE);
            TextView visitorTeam = (TextView) convertView.findViewById(R.id.visitor_team);
            visitorTeam.setText(game.getVisitor_team());
            TextView visitorScore = (TextView) convertView.findViewById(R.id.visitor_score);
            visitorScore.setText(game.getVisitor_score());
            ImageView visitorIcon = (ImageView) convertView.findViewById(R.id.visitor_icon);
            visitorIcon.setImageDrawable(getTeamDrawable(game.getVisitor_team()));
            TextView homeTeam = (TextView) convertView.findViewById(R.id.home_team);
            homeTeam.setText(game.getHome_team());
            TextView homeScore = (TextView) convertView.findViewById(R.id.home_score);
            homeScore.setText(game.getHome_score());
            ImageView homeIcon = (ImageView) convertView.findViewById(R.id.home_icon);
            homeIcon.setImageDrawable(getTeamDrawable(game.getHome_team()));

            TextView statJuego = (TextView) convertView.findViewById(R.id.game_status);
            statJuego.setText(game.getStat_juego());
            TextView ining = (TextView) convertView.findViewById(R.id.game_ining);
            ining.setText(game.getDsInning());
        }else{
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.game_not_started);
            layout.setVisibility(View.VISIBLE);

            TextView visitor_team = (TextView) convertView.findViewById(R.id.visitor_team_not_started);
            visitor_team.setText(game.getVisitor_team());
            ImageView visitor_icon = (ImageView) convertView.findViewById(R.id.visitor_icon_not_started);
            visitor_icon.setImageDrawable(getTeamDrawable(game.getVisitor_team()));

            TextView home_team = (TextView) convertView.findViewById(R.id.home_team_not_started);
            home_team.setText(game.getHome_team());
            ImageView home_icon = (ImageView) convertView.findViewById(R.id.home_icon_not_started);
            home_icon.setImageDrawable(getTeamDrawable(game.getHome_team()));

            TextView stadium = (TextView) convertView.findViewById(R.id.stadium);
            stadium.setText(game.getStadium());
            TextView time = (TextView) convertView.findViewById(R.id.time);
            time.setText(game.getTime());
        }

        return convertView;
    }

    public Drawable getTeamDrawable(String team) {
        team = team.trim();
        switch (team) {
            case "AGUI":
                return getContext().getResources().getDrawable(R.drawable.ic_launcher);
            case "CARD":
                return getContext().getResources().getDrawable(R.drawable.ic_lara);
            case "MAGA":
                return getContext().getResources().getDrawable(R.drawable.ic_magallanes);
            case "CARI":
                return getContext().getResources().getDrawable(R.drawable.ic_caribes);
            case "TIBU":
                return getContext().getResources().getDrawable(R.drawable.ic_tiburones);
            case "LEON":
                return getContext().getResources().getDrawable(R.drawable.ic_leones);
            case "TIGR":
                return getContext().getResources().getDrawable(R.drawable.ic_tigres);
            case "BRAV":
                return getContext().getResources().getDrawable(R.drawable.ic_bravos);
            default:
                return getContext().getResources().getDrawable(R.drawable.ic_launcher);
        }
    }
}
