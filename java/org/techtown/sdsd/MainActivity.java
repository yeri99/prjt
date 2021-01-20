package org.techtown.sdsd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView address_view;
    String gu = "";
    String dong = "";
    public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address_view = findViewById(R.id.textLocation);

        ImageButton button = findViewById(R.id.imageButton);

    }

    public void onClickLoc(View V){
        Intent intent = new Intent(this, LocActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            Toast.makeText(getApplicationContext(), "위치를 설정했습니다.", Toast.LENGTH_LONG).show();
        }
        if(resultCode == RESULT_OK){
            int count = 0;
            String address = data.getStringExtra("addText");
            for (int i = 0; i < address.length(); i++){
                char ch = address.charAt(i);
                if(Character.isWhitespace(ch) && count == 0){
                    address = address.substring(i+1);
                    count++;
                }
                else if(Character.isWhitespace(ch) && count == 1){ count++; }
                else if(count == 2){
                    if(!Character.isWhitespace(ch)){
                        gu += ch;
                    }
                    else{ count++; }
                }
                else if(count == 3){
                    if(!Character.isWhitespace(ch)){
                        dong += ch;
                    }
                    else{break;}
                }
            }
            address_view.setText(address);
        }
    }

}