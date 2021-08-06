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
        ArrayList<String> questions = new ArrayList<String>();
        TestItems testItemsElems = new TestItems();
        ArrayList <TestItems> listTestItem = new ArrayList<TestItems>();
        while (parser.next() != XmlPullParser.END_DOCUMENT)
        {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String nameTage = parser.getName();
            if (parser.getName().contains("question")) {
                parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                if (parser.next() == XmlPullParser.TEXT) {
                    questions.add(parser.getText());
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
            }
            else if (parser.getName().equals("coundQuestions")) {
                parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                if (parser.next() == XmlPullParser.TEXT) {
                    testItemsElems.CountQuestions = Integer.parseInt(parser.getText());
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
            }
            else if (parser.getName().equals("numAnswer")) {
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
            else if (parser.next() != XmlPullParser.END_DOCUMENT) {
                testItemsElems.questions = questions;
                listTestItem.add(testItemsElems);
                testItemsElems = new TestItems();
                questions = new ArrayList<String>();
                String nameTage2 = parser.getName();
            }
        }
        testItemsElems.questions = questions;
        listTestItem.add(testItemsElems);
        return listTestItem;
    }

    public class TestItems
    {
        public ArrayList<String> questions;
        public int NumAnswer;
        public int CountQuestions;
        public int KindAnswer;
    }
}
