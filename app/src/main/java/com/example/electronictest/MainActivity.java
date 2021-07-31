package com.example.electronictest;

import com.parse.xmlfile.ParseXmlFile;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.LinearLayout;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        XmlPullParser parser = getResources().getXml(R.xml.test);

        ParseXmlFile parseXmlFile = new ParseXmlFile();
        parseXmlFile.parsXmlFile(parser);

        createRadioButtons();

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

        params.setMargins(0, 0, 30, 300);
        radioGroup.setLayoutParams(params);

        radioGroup.addView(radioButton1);
        radioGroup.addView(radioButton2);
        radioGroup.addView(radioButton3);

        linearLayout.addView(radioGroup);
    }
    private void createCheckBoxs(LinearLayout linearLayout, LinearLayout.LayoutParams params)
    {
        //CheckBoxView checkBoxView = new CheckBoxView(this);
        CheckBox checkBox1 = new CheckBox(this);
        checkBox1.setText("Ответ 1.");
        CheckBox checkBox2 = new CheckBox(this);
        checkBox2.setText("Ответ 2.");
        CheckBox checkBox3 = new CheckBox(this);
        checkBox3.setText("Ответ 3.");


    }
}