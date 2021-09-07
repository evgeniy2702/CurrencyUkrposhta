package ua.ukrposhta.currency.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.xml.sax.SAXException;
import ua.ukrposhta.currency.handlers.MySaxHandler;
import ua.ukrposhta.currency.model.Exchange;
import ua.ukrposhta.currency.parser.MySaxParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyByDayServiceTest {

    @Value("${pathXmlBankCurrency}")
    String urlPath;

    @lombok.Setter
    private MySaxParser mySaxParser;
    @lombok.Setter
    private MySaxHandler mySaxHandler;

    @Test
    void currencyByDate() throws IOException, SAXException, ParserConfigurationException {
        String valcode = "USD";
        String day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        Exchange exchange = mySaxParser
                .getObjectExchangeFromXML(urlPath + "?valcode=" + valcode +"&date=" + day,mySaxHandler);

    }
}