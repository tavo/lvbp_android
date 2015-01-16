package com.ucv.tavo.aguilas.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ucv.tavo.aguilas.R;
import com.ucv.tavo.aguilas.dto.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 27/12/14.
 */
public class PositionAdapter extends ArrayAdapter<Position> {
    private Activity context;
    private List<Position> positionList = new ArrayList<Position>();

    public PositionAdapter(Activity context, List<Position> positionList) {
        super(context, R.layout.item_position, positionList);
        this.context = context;
        this.positionList = positionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_position, null);

        Position pos = positionList.get(position);

        TextView team = (TextView) convertView.findViewById(R.id.position_team);
        team.setText(pos.getTeam());
        TextView playedGames = (TextView) convertView.findViewById(R.id.position_played_games);
        playedGames.setText(pos.getGames_played());
        TextView gamesWon = (TextView) convertView.findViewById(R.id.position_games_won);
        gamesWon.setText(pos.getGames_won());
        TextView gamesLost = (TextView) convertView.findViewById(R.id.position_games_lost);
        gamesLost.setText(pos.getGames_lost());
        TextView avg = (TextView) convertView.findViewById(R.id.position_avg);
        avg.setText(pos.getAvg());
        TextView dif = (TextView) convertView.findViewById(R.id.position_dif);
        dif.setText(pos.getDif());


        return convertView;
    }
}
