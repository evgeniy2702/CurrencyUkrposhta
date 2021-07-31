package ua.ukrposhta.currency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import ua.ukrposhta.currency.handlers.MySaxHandler;
import ua.ukrposhta.currency.model.Exchange;
import ua.ukrposhta.currency.parser.MySaxParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


@Service
public class CurrencyService {


    @Value("${pathXmlBankCurrency}")
    String urlPath;

    private MySaxParser mySaxParser;
    private MySaxHandler mySaxHandler;

    @Autowired
    public void setMySaxHandler(MySaxHandler mySaxHandler) {
        this.mySaxHandler = mySaxHandler;
    }

    @Autowired
    public void setMySaxParser(MySaxParser mySaxParser) {
        this.mySaxParser = mySaxParser;
    }


    public Exchange exchangeCurrentDay() throws IOException, SAXException, ParserConfigurationException {

        Exchange exchange = mySaxParser.getObjectExchangeFromXML(urlPath,mySaxHandler);

        return exchange;
    }

    public Exchange exchangeByDate(String date) throws IOException, SAXException, ParserConfigurationException {

        Exchange exchange = mySaxParser.getObjectExchangeFromXML(urlPath + "?date=" + date,mySaxHandler);

        return exchange;
    }

    public Exchange currencyByDate(String valcode, String day) throws IOException, SAXException, ParserConfigurationException {

        Exchange exchange = mySaxParser
                .getObjectExchangeFromXML(urlPath + "?valcode=" + valcode +"&date=" + day,mySaxHandler);

        return exchange;
    }
}
