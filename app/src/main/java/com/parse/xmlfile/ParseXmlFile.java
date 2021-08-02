package com.parse.xmlfile;

import com.example.electronictest.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import com.parse.xmlfile.ParseXmlFile;
import java.util.ArrayList;

public class ParseXmlFile {
    ArrayList<String> list = new ArrayList<String>();
    int NumAnser;
    public void parsXmlFile(XmlPullParser parser) throws org.xmlpull.v1.XmlPullParserException
    {
        try {
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT)
            {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("TestSet")){
                    list.add(parser.getAttributeValue(0));
                    list.add(parser.getAttributeValue(1));
                    list.add(parser.getAttributeValue(2));
                    list.add(parser.getAttributeValue(3));
                    parser.next();
                    if (parser.getName().equals("numAnswer"))
                    {
                        //parser.get;
                    }
                }
                parser.next();
            }
        }
        catch (Throwable t)
        {
            //Toast.makeText(this, "" + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
