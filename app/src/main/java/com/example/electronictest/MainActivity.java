package com.example.electronictest;

import com.parse.xmlfile.ParseXmlFile;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.view.Gravity;
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
    private static int lengthSetTests = 0;
    private static int countCorrectAnswer = 0;
    private static int countNotCorrectAnswer = 0;
    private ArrayList<CheckBox> arrayCheckBoxs;// = new ArrayList<CheckBox>();
    private ArrayList<RadioButton> arrayRadioButton;// = new ArrayList<RadioButton>();
    private static ArrayList<ParseXmlFile.TestItems> setTests;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (indexSetTests == 0)
        {
            XmlPullParser parser = getResources().getXml(R.xml.test);
            ParseXmlFile parseXmlFile = new ParseXmlFile();
            try {
                setTests = parseXmlFile.parsXmlFile(parser);
                lengthSetTests = setTests.size();
            } catch (IOException | XmlPullParserException error) {
                Toast.makeText(this, "" + error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        if (indexSetTests >= 0 && lengthSetTests > indexSetTests) {
            Button btnToAnswer = new Button(this);
            ImageView imageView = new ImageView(this);
            TextView textViewQuestion = new TextView(this);

            LinearLayout linearLayout = findViewById(R.id.rootContainer);
            LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT),
                    paramsTextView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT),
                    paramsButton = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsButton.setMargins(0, 0, 0, 0);
            paramsImage.setMargins(0, 100, 30, 0);
            paramsTextView.setMargins(5, 0, 0, 50);

            //если при чтении из xml файла указано налитиче картинти
            btnToAnswer.setLayoutParams(paramsButton);
            //btnToAnswer.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL); //Gravity.CENTER_HORIZONTAL
            btnToAnswer.setText("To answer");

            btnToAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent();
                    overridePendingTransition(0, 0);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();

                    if (ParseXmlFile.KindCreateButton.RADIOBUTTON.ordinal() == setTests.get(indexSetTests).KindAnswer) {
                        if (arrayRadioButton.get(setTests.get(indexSetTests).NumAnswer).isChecked()) {
                            ++countCorrectAnswer;
                        } else {
                            ++countNotCorrectAnswer;
                        }
                    } else if (ParseXmlFile.KindCreateButton.CHECKBOX.ordinal() == setTests.get(indexSetTests).KindAnswer) {
                        if (arrayCheckBoxs.get(setTests.get(indexSetTests).NumAnswer).isChecked()) {
                            ++countCorrectAnswer;
                        } else {
                            ++countNotCorrectAnswer;
                        }
                    }

                    ++indexSetTests;
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
            } else if (ParseXmlFile.KindCreateButton.CHECKBOX.ordinal() == setTests.get(indexSetTests).KindAnswer) {
                createCheckBoxs(setTests.get(indexSetTests).answers);
            }
            linearLayout.addView(btnToAnswer);
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.rootContainer);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 0);

            TextView correctAnswer = new TextView(this);
            correctAnswer.setText("Count correct answer: " + countCorrectAnswer);
            correctAnswer.setLayoutParams(layoutParams);
            correctAnswer.setTextSize(25);
            correctAnswer.setGravity(Gravity.CENTER_HORIZONTAL);

            layoutParams.setMargins(0, 0, 0, 500);

            TextView notCorrectAnswer = new TextView(this);
            notCorrectAnswer.setText("Count not correct answer: " + countNotCorrectAnswer);
            notCorrectAnswer.setLayoutParams(layoutParams);
            notCorrectAnswer.setTextSize(25);
            notCorrectAnswer.setGravity(Gravity.CENTER_HORIZONTAL);

            linearLayout.addView(correctAnswer);
            linearLayout.addView(notCorrectAnswer);
        }
    }

    private void createRadioButtons(ArrayList<String> answers)
    {
        arrayRadioButton = new ArrayList<RadioButton>();
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
        arrayCheckBoxs = new ArrayList<CheckBox>();
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