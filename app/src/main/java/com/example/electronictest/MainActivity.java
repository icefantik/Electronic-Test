package com.example.electronictest;

import com.parse.xmlfile.ParseXmlFile;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.parse.xmlfile.ParseXmlFile;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity
{
    private static int indexSetTests = 0;
    private ArrayList<CheckBox> arrayCheckBoxs = new ArrayList<CheckBox>();
    private ArrayList<RadioButton> arrayRadioButton = new ArrayList<RadioButton>();
    private static ArrayList<ParseXmlFile.TestItems> setTests;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT),
                paramsTextView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT),
                paramsButton = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsButton.setMargins(0,0,0, 0);
        paramsImage.setMargins(0, 100, 30, 0);
        paramsTextView.setMargins(5, 0, 0, 50);

        XmlPullParser parser = getResources().getXml(R.xml.test);
        ParseXmlFile parseXmlFile = new ParseXmlFile();

        try {
            setTests = parseXmlFile.parsXmlFile(parser);
        } catch (IOException | XmlPullParserException error) {
            Toast.makeText(this, "" + error.toString(), Toast.LENGTH_LONG).show();
            error.printStackTrace();
        }

        ImageView imageView = new ImageView(this);
        //если при чтении из xml файла указано налитиче картинти
        TextView textViewQuestion = new TextView(this);

        Button btnToAnswer = new Button(this);
        btnToAnswer.setLayoutParams(paramsButton);
        btnToAnswer.setText("To answer");

        btnToAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        textViewQuestion.setText(setTests.get(indexSetTests).question);
        textViewQuestion.setLayoutParams(paramsTextView);
        linearLayout.addView(textViewQuestion);
        /**
         if (setTests.get(i).images != null) {
         int id = getResources().getIdentifier("yourpackagename:drawable/" + setTests.get(i).images, null, null);
         imageView.setImageResource(id);
                imageView.setLayoutParams(paramsImage);
         linearLayout.addView(imageView);
         }*/
        if (ParseXmlFile.KindCreateButton.RADIOBUTTON.ordinal() == setTests.get(indexSetTests).KindAnswer) {
            createRadioButtons(setTests.get(indexSetTests).answers);
        }
        else if (ParseXmlFile.KindCreateButton.CHECKBOX.ordinal() == setTests.get(indexSetTests).KindAnswer) {
            createCheckBoxs(setTests.get(indexSetTests).answers);
        }
        linearLayout.addView(btnToAnswer);
    }
    private void createRadioButtons(ArrayList<String> answers)
    {
        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 30, 100);

        RadioGroup radioGroup = new RadioGroup(this);
        RadioButton radioButton = new RadioButton(this);
        for (int i = 0; i < answers.size(); ++i) {
            radioButton.setText(answers.get(i));
            radioButton.setLayoutParams(params);
            arrayRadioButton.add(radioButton);
            radioGroup.addView(radioButton);
            radioButton = new RadioButton(this);
        }
        linearLayout.addView(radioGroup);
    }
    private void createCheckBoxs(ArrayList<String> answers)
    {
        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 30, 100);
        CheckBox checkBox = new CheckBox(this);
        for (int i = 0; i < answers.size(); ++i) {
            checkBox.setText(answers.get(i));
            checkBox.setLayoutParams(params);
            arrayCheckBoxs.add(checkBox);
            linearLayout.addView(checkBox);
            checkBox = new CheckBox(this);
        }
    }
}