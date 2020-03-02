package com.example.airport.flightoperations.model.request;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This class represents a flight operation feed for a XML file
 *
 * e.g.
 * <?xml version='1.0' encoding='UTF-8'?>
 * <FlightOperations>
 * 	<FlightOperation>
 * 		<Number>AX-223</Number>
 * 		<DepartureTime>2020-02-29T07:11</DepartureTime>
 * 		<ArrivalTime>2020-02-29T08:39</ArrivalTime>
 * 		<Origin>SJO</Origin>
 * 		<Destination>LIR</Destination>
 * 		<Airline>BOV</Airline>
 * 		<Gate>3</Gate>
 * 		<Status>ON_TIME</Status>
 * 	</FlightOperation>
 * 	<FlightOperation>
 * 		<Number>IB-777</Number>
 * 		<DepartureTime>2020-02-29T07:11</DepartureTime>
 * 		<ArrivalTime>2020-02-29T14:30</ArrivalTime>
 * 		<Origin>LSL</Origin>
 * 		<Destination>DRK</Destination>
 * 		<Airline>CSX</Airline>
 * 		<Gate>1</Gate>
 * 		<Status>DELAYED</Status>
 * 	</FlightOperation>
 * </FlightOperations>
 */
@Data
@XmlRootElement(name = "FlightOperations")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightOperationsXMLRequestWrapper {

    @XmlElement(name="FlightOperation")
    private List<FlightOperationsXMLRequest> flightOperations;

}
