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
        TestItems testItemsElems = new TestItems();
        ArrayList <TestItems> listTestItem = new ArrayList<TestItems>();
        try {
            int indexListItem = 0;
            String tmp = null;
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT)
            {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("TestSet"))
                {
                    parser.next();
                    if (parser.getName().contains("question")) {
                        parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                        if (parser.next() == XmlPullParser.TEXT) {
                            String s = parser.getText();
                            //testItemsElems.questions.add("");
                            parser.nextTag();
                        }
                        parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
                        parser.next();
                    }
                    else if (parser.getName().equals("coundQuestions")) {
                        parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                        if (parser.next() == XmlPullParser.TEXT) {
                            testItemsElems.CountQuestions = Integer.parseInt(parser.getText());
                            parser.nextTag();
                        }
                        parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
                        //parser.next();
                    }
                    else if (parser.getName().equals("numAnswer")) {
                        parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                        if (parser.next() == XmlPullParser.TEXT) {
                            testItemsElems.NumAnswer = Integer.parseInt(parser.getText());
                            parser.nextTag();
                        }
                        parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
                        //parser.next();
                    }
                    else if (parser.getName().equals("kindButton")) {
                        parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
                        if (parser.next() == XmlPullParser.TEXT) {
                            testItemsElems.KindAnswer = Integer.parseInt(parser.getText());
                            parser.nextTag();
                        }
                        parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
                        //parser.next();
                    }
                }
                parser.next();
            }
        }
        catch (Throwable t)
        {
            //Toast.makeText(this, "" + t.toString(), Toast.LENGTH_LONG).show();
        }
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
