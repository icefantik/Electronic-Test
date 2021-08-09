package com.example.electronictest;

import com.parse.xmlfile.ParseXmlFile;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.LinearLayout;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity
{
    private static ArrayList<ParseXmlFile.TestItems> setTests;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 100, 30, 0);
        XmlPullParser parser = getResources().getXml(R.xml.test);

        ParseXmlFile parseXmlFile = new ParseXmlFile();

        try {
            setTests = parseXmlFile.parsXmlFile(parser);
        } catch (IOException | XmlPullParserException error) {
            Toast.makeText(this, "" + error.toString(), Toast.LENGTH_LONG).show();
            error.printStackTrace();
        }

        //если при чтении из xml файла указано налитиче картинти
        int id = getResources().getIdentifier("yourpackagename:drawable/" + setTests.get(0).images, null, null);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(id);
        imageView.setLayoutParams(params);
        linearLayout.addView(imageView);

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
        params.setMargins(0, 0, 30, 100);

        RadioGroup radioGroup = new RadioGroup(this);

        RadioButton radioButton1 = new RadioButton(this);
        radioButton1.setText("Ответ 1.");
        radioButton1.setLayoutParams(params);
        RadioButton radioButton2 = new RadioButton(this);
        radioButton2.setText("Ответ 2.");
        radioButton2.setLayoutParams(params);
        RadioButton radioButton3 = new RadioButton(this);
        radioButton3.setText("Ответ 3.");
        radioButton3.setLayoutParams(params);

        radioGroup.addView(radioButton1);
        radioGroup.addView(radioButton2);
        radioGroup.addView(radioButton3);

        linearLayout.addView(radioGroup);
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
    enum KindCreateButton{
        RADIO_BUTTON,
        CHECKBOX
    }
}