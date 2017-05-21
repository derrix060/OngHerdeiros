package com.example.mario.ongproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by mario on 05/05/17.
 */

public class DonateFragment  extends Fragment {

    ImageView imgDonate;
    SeekBar seekDonate;
    TextView valueDonate;
    String donateCurrency;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.donate_new_view, container, false);


        Button btnDonate = (Button) v.findViewById(R.id.btnDonate);
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donate();
            }
        });

        imgDonate = (ImageView) v.findViewById(R.id.imgChild);
        seekDonate = (SeekBar) v.findViewById(R.id.seekDonate);
        valueDonate = (TextView) v.findViewById(R.id.txtDonateValue);
        donateCurrency = getText(R.string.currency).toString();

        seekDonate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeSeekBarValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return v;
    }

    private void donate(){
        Uri uri = Uri.parse("https://mario-apra.tk/mercado_pago");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void changeSeekBarValue(Integer newValue){
        valueDonate.setText(donateCurrency + newValue.toString());

        
    }
}
