package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.GameActivity;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerMatch;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.Multiplayer;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

class MultiplayerMatchViewHolder extends RecyclerView.ViewHolder{

    View itemView;
    TextView username;
    public MultiplayerMatchViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.username = itemView.findViewById(R.id.rv_item_username);
        itemView.findViewById(R.id.item_rv).setOnClickListener(v -> jumpToGame());
    }

    private void jumpToGame() {

        String firebaseKey = username.getText().toString();

        Activity fake = (Activity)itemView.getContext();
        Intent i = new Intent(fake, Multiplayer.class);
        i.putExtra(MultiplayerGame.MULTIPLAYER_KEY, MultiplayerGame.MULTIPLAYER_TYPE_CONNECT);
        i.putExtra(MultiplayerGame.MULTIPLAYER_GAME_KEY, firebaseKey);
        fake.startActivity(i);

        //startActivity();
    }

    public void render(MultiplayerMatch mm) {
        username.setText(mm.getUserCreator());
    }
}