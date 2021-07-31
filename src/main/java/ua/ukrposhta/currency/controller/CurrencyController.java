package ua.ukrposhta.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;
import ua.ukrposhta.currency.model.Currency;
import ua.ukrposhta.currency.model.Exchange;
import ua.ukrposhta.currency.service.CurrencyService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@RestController
public class CurrencyController {

    private List<String> cc;
    private CurrencyService currencyService;

    @Value("${pathXmlBankCurrency}")
    String urlPath;

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = {"/", "/exchange"})
    public ModelAndView exchangeCurrent(ModelAndView modelAndView) throws ParserConfigurationException, SAXException, IOException {

        Exchange exchange = currencyService.exchangeCurrentDay();

        cc = exchange.getExchange().stream().map(Currency::getCc).collect(Collectors.toList());

        actionModelAndView(exchange, modelAndView,  cc , "");

        return modelAndView;
    }


    @PostMapping(value = "/exchange/valcode/day")
    public ModelAndView currencyByDate(@RequestParam(name = "valcode", required = false) String valcode,
                                       @RequestParam(name = "day", required = false) String day,
                                       ModelAndView modelAndView) throws ParserConfigurationException, SAXException, IOException {

        Exchange exchange = new Exchange();

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date =  LocalDate.parse(day).format(formatter1);

        if(valcode.equals("Выберите валюту !")){

            exchange = currencyService.exchangeByDate(date);

        } 
        if (!valcode.equals("Выберите валюту !") && !day.isEmpty()) {

            exchange = currencyService.currencyByDate(valcode, date);
        }

        actionModelAndView(exchange, modelAndView, cc, date);

        return modelAndView;
    }

    private void actionModelAndView(Exchange exchange, ModelAndView modelAndView, List<String> cc, String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        if(date.isEmpty())
            date = LocalDate.now().format(formatter);
        else{
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
            date = LocalDate.parse(date,formatterDate).format(formatter);
        }

        modelAndView.addObject("cc", cc);
        modelAndView.addObject("exchange", exchange.getExchange());
        modelAndView.addObject("date", date);
        modelAndView.setViewName("index");
    }
}
