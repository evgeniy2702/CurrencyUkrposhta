package ua.ukrposhta.currency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement(name = "exchange" )
@XmlAccessorType(XmlAccessType.FIELD)
public class Exchange {

    @XmlElement(name = "currency")
    private List<Currency> exchange;

}
