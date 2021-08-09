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
                parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                if (parser.next() == XmlPullParser.TEXT) {
                    testItemsElems.question = parser.getText();
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
            }
            else if (parser.getName().contains("answer")) {
                parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                if (parser.next() == XmlPullParser.TEXT) {
                    answers.add(parser.getText());
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
            }
            else if (parser.getName().equals("count")) {
                parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                if (parser.next() == XmlPullParser.TEXT) {
                    testItemsElems.CountQuestions = Integer.parseInt(parser.getText());
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
            }
            else if (parser.getName().equals("answNumber")) {
                parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                if (parser.next() == XmlPullParser.TEXT) {
                    testItemsElems.NumAnswer = Integer.parseInt(parser.getText());
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
            }
            else if (parser.getName().equals("kindButton")) {
                parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                if (parser.next() == XmlPullParser.TEXT) {
                    testItemsElems.KindAnswer = Integer.parseInt(parser.getText());
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
            }
            else if (parser.getName().equals("image")) {
                parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                if (parser.next() == XmlPullParser.TEXT) {
                    testItemsElems.images = parser.getText();
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
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
