package ua.ukrposhta.currency.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "currency")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {

    @XmlElement(name = "r030")
    private String r030;
    @XmlElement(name = "txt")
    private String txt;
    @XmlElement(name = "rate")
    private String rate;
    @XmlElement(name = "cc")
    private String cc;
    @XmlElement(name = "exchangedate")
    private String exchangedate;

}
