package project3310.financemenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class liveStock {
    String symbol;
    double last;
    String name;
    java.util.Date curDate;
    HttpResponse httpResponse;
    // the base url of the web service
    static String baseUrl = "http://www.webservicex.net/stockquote.asmx/GetQuote?symbol=";

    public liveStock(String symbol) {
        super();
        this.symbol = symbol;
    }

    public void callService() {
        HttpClient client = new DefaultHttpClient();
        System.out.println(baseUrl + symbol);
        HttpGet httpget = new HttpGet(baseUrl + symbol);
        int responseCode = 0;
        String message;
        String response;
        try {
            httpResponse = client.execute(httpget);
            System.out.println("get executed");
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();
            readresponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readresponse() {
        HttpEntity entity = httpResponse.getEntity();
        try {
            if (entity != null) {
                InputStream instream = entity.getContent();
                // read the stream
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(instream));
                String innerXml = new String();
                XmlPullParser parser = Xml.newPullParser();
                // auto-detect the encoding from the stream
                parser.setInput(instream, "UTF-8");
                int eventType = parser.getEventType();
                boolean done = false;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String name = null;
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            System.out.println("start document");
                            break;
                        case XmlPullParser.START_TAG:
                            name = parser.getName();
                            innerXml=parser.nextText();
                            break;
                        case XmlPullParser.END_TAG:
                            System.out.println("End tag");
                            break;
                    }
                    eventType = parser.next();
                }
                parser=Xml.newPullParser();
                parser.setInput(new StringReader(innerXml));
                System.out.println(innerXml);
                eventType=parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String name = null;
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:

                            break;
                        case XmlPullParser.START_TAG:
                            name = parser.getName();
                            if (name.equalsIgnoreCase("last")) {
                                this.setLast(Double.parseDouble(parser.nextText()));
                            } else if (name.equalsIgnoreCase("date")) {
                                Date x = new Date(parser.nextText());
                                this.setCurDate(x);
                            } else if (name.equalsIgnoreCase("name")) {
                                this.setName((parser.nextText()));
                            }
                            break;
                        case XmlPullParser.END_TAG:

                            break;
                    }
                    eventType = parser.next();

                }
                instream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.Date getCurDate() {
        return curDate;
    }

    public void setCurDate(java.util.Date curDate) {
        this.curDate = curDate;
    }

}
