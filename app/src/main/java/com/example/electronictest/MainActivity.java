package com.example.electronictest;

import com.parse.xmlfile.ParseXmlFile;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.LinearLayout;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    enum KindCreateButton{
        RADIO_BUTTON,
        CHECKBOX
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        XmlPullParser parser = getResources().getXml(R.xml.test);

        ParseXmlFile parseXmlFile = new ParseXmlFile();
        try {
            parseXmlFile.parsXmlFile(parser);
        } catch (Throwable t){
            Toast.makeText(this, "" + t.toString(), Toast.LENGTH_LONG).show();
        }

        createRadioButtons();
        //createCheckBoxs();

        params.setMargins(0,0,0, 0);
        Button btnToAnswer = new Button(this);
        btnToAnswer.setText("To answer");
        linearLayout.addView(btnToAnswer);
    }
    private void createRadioButtons()
    {
        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        RadioGroup radioGroup = new RadioGroup(this);
        RadioButton radioButton1 = new RadioButton(this);
        radioButton1.setText("Ответ 1.");
        RadioButton radioButton2 = new RadioButton(this);
        radioButton2.setText("Ответ 2.");
        RadioButton radioButton3 = new RadioButton(this);
        radioButton3.setText("Ответ 3.");
        params.setMargins(0, 0, 30, 100);

        radioButton1.setLayoutParams(params);
        radioButton2.setLayoutParams(params);
        radioButton3.setLayoutParams(params);

        linearLayout.addView(radioButton1);
        linearLayout.addView(radioButton2);
        linearLayout.addView(radioButton3);
    }
    private void createCheckBoxs()
    {
        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        CheckBox checkBox1 = new CheckBox(this);
        checkBox1.setText("Ответ 1.");
        CheckBox checkBox2 = new CheckBox(this);
        checkBox2.setText("Ответ 2.");
        CheckBox checkBox3 = new CheckBox(this);
        checkBox3.setText("Ответ 3.");

        params.setMargins(0, 0, 30, 100);
        checkBox1.setLayoutParams(params);
        checkBox2.setLayoutParams(params);
        checkBox3.setLayoutParams(params);

        linearLayout.addView(checkBox1);
        linearLayout.addView(checkBox2);
        linearLayout.addView(checkBox3);
    }
}