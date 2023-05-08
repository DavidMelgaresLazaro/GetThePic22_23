package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.viewmodels.MultiplayerViewModel;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.databinding.MultiplayerBinding;

public class Multiplayer extends AppCompatActivity {

    MultiplayerViewModel multiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer);

        multiplayer = new ViewModelProvider(this).get(MultiplayerViewModel.class);
        MultiplayerBinding binding = DataBindingUtil.setContentView(this, R.layout.multiplayer);
        binding.setMultiplayerViewModel(multiplayer);
        binding.setLifecycleOwner(this);


    }
}