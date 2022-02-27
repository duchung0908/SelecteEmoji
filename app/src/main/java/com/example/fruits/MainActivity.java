package com.example.fruits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView grd;
    int max = 2;
    TextView result;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grd = findViewById(R.id.gridView);
        result = findViewById(R.id.result);
        /*test = findViewById(R.id.mainItem);
        test.setText("hello");*/

        List<Integer> emojiList = Arrays.asList(
                8986, 0x1F603, 0x1F605, 0x1F60D, 0x1F60F,
                0x1F618, 0x1F621, 0x1F625, 0x1F628, 0x1F62D,
                0x1F637, 0x1F61D, 0x1F616, 0x1F609, 0x1F60B,
                0x1F635, 0x1F633, 0x1F624, 0x1F61C, 0x1F60A);
        List data = new ArrayList();
        for (int i = 0; i < emojiList.size(); i++) {
            data.add(new String(Character.toChars(emojiList.get(i))));
        }
        EmojiAdapter a = new EmojiAdapter(getApplicationContext(), R.layout.emoji, data);
        grd.setAdapter(a);
        List temp = new ArrayList(data);
        final String[] selectedE = {getRandomEmoji(temp)};
        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView v = (TextView) view;
                if(v.getText().equals(selectedE[0])){
                    temp.remove(selectedE[0]);
                    v.setText("");
                }
                else {
                    max --;
                    if(max==0){
                        result.setText("Bạn thua");
                        finish();}
                }
                if(temp.size()==0)
                    result.setText("Bạn thắng");
                selectedE[0] = getRandomEmoji(temp);
            }
        });
    }

    private String getRandomEmoji(List listE) {
        Random rnd = new Random();
        String a = listE.get(rnd.nextInt(listE.size())).toString();
        TextView main = findViewById(R.id.main);
        main.setText(a);
        return a;
    }
}