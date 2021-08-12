package com.parse.xmlfile;

import com.example.electronictest.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.parse.xmlfile.ParseXmlFile;

import java.io.IOException;
import java.util.ArrayList;

public class ParseXmlFile
{
    public ArrayList <TestItems> parsXmlFile(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        ArrayList<String> answers = new ArrayList<String>();
        TestItems testItemsElems = new TestItems();
        ArrayList <TestItems> listTestItem = new ArrayList<TestItems>();
        while (parser.next() != XmlPullParser.END_DOCUMENT)
        {
            int a = parser.getEventType();
            String nameTage = parser.getName();
            if (parser.getEventType() != XmlPullParser.START_TAG && parser.getEventType() != XmlPullParser.END_TAG) {
                continue;
            }
            if (parser.getName().equals("question")) {
                testItemsElems.question = readText(parser);
            }
            else if (parser.getName().contains("answer")) {
                answers.add(readText(parser));
            }
            else if (parser.getName().equals("count")) {
                testItemsElems.CountQuestions = Integer.parseInt(readText(parser));
            }
            else if (parser.getName().equals("answNumber")) {
                testItemsElems.NumAnswer = Integer.parseInt(readText(parser));
            }
            else if (parser.getName().equals("kindButton")) {
                testItemsElems.KindAnswer = Integer.parseInt(readText(parser));
            }
            else if (parser.getName().equals("image")) {
                testItemsElems.images = readText(parser);
            }
            else if (parser.getEventType() != XmlPullParser.START_TAG && parser.next() != XmlPullParser.END_DOCUMENT) {
                testItemsElems.answers = answers;
                listTestItem.add(testItemsElems);
                testItemsElems = new TestItems();
                answers = new ArrayList<String>();
                String nameTage2 = parser.getName();
            }
        }
        return listTestItem;
    }
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        String text = null;
        parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
        if (parser.next() == XmlPullParser.TEXT) {
            text = parser.getText();
            parser.nextTag();
        }
        parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
        return text;
    }

    public class TestItems
    {
        public String question;
        public ArrayList<String> answers;
        public int NumAnswer;
        public int CountQuestions;
        public int KindAnswer;
        public String images;
    }
}
