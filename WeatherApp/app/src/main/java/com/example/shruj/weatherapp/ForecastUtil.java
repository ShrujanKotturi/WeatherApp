package com.example.shruj.weatherapp;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shruj on 03/03/2016.
 */
public class ForecastUtil {

    static public class ForecastSAXParser extends DefaultHandler {

        ArrayList<Forecast> forecastArrayList;
        Forecast forecast;
        StringBuilder xmlInnerText;

        public List<Forecast> getForecastList() {
            return forecastArrayList;
        }

        static public List<Forecast> parseForecast(InputStream inputStream) throws IOException, SAXException {
            ForecastSAXParser parser = new ForecastSAXParser();
            Xml.parse(inputStream, Xml.Encoding.UTF_8, parser);
            return parser.getForecastList();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            xmlInnerText = new StringBuilder();
            forecastArrayList = new ArrayList<>();
        }


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals(Constants.FORECAST)) {
                forecast = new Forecast();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals(Constants.FORECAST)) {
                forecastArrayList.add(forecast);
            } else if (localName.equals(Constants.FCTTIME_PRETTY)) {
                forecast.setTime(xmlInnerText.toString().trim());
            } else if (localName.equals(Constants.TEMP)) {
                forecast.setTemperature(xmlInnerText.toString().trim());
            }

            xmlInnerText.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
        }


        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }
    }

}
