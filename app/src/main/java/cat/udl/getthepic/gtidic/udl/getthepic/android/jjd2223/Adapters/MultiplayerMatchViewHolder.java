package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.MultiplayMatchItemListener;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.GameActivity;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerGame;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.MultiplayerMatch;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views.Multiplayer;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

class MultiplayerMatchViewHolder extends RecyclerView.ViewHolder{

    private MultiplayMatchItemListener listener;
    View itemView;
    TextView userName;
    TextView userEmail;
    TextView matchKey;
    public MultiplayerMatchViewHolder(@NonNull View itemView, MultiplayMatchItemListener listener) {
        super(itemView);
        this.itemView = itemView;
        this.userName = itemView.findViewById(R.id.rv_item_username);
        this.userEmail = itemView.findViewById(R.id.rv_item_useremail);
        this.matchKey = itemView.findViewById(R.id.rv_item_matchkey);
        this.listener = listener;
    }

    private void jumpToGame() {

        String firebaseKey = userName.getText().toString();

        Activity fake = (Activity)itemView.getContext();
        Intent i = new Intent(fake, Multiplayer.class);
        i.putExtra(MultiplayerGame.MULTIPLAYER_KEY, MultiplayerGame.MULTIPLAYER_TYPE_CONNECT);
        i.putExtra(MultiplayerGame.MULTIPLAYER_GAME_KEY, firebaseKey);
        fake.startActivity(i);

        //startActivity();
    }

    public void render(MultiplayerMatch mm) {
        itemView.findViewById(R.id.item_rv).setOnClickListener(v -> listener.onMatchClick(mm));

        userName.setText(mm.getUserName());
        userEmail.setText(mm.getUserEmail());
        matchKey.setText(mm.getMatchKey());
    }
}