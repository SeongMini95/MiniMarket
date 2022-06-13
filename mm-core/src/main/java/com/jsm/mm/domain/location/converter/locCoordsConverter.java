package com.jsm.mm.domain.location.converter;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class locCoordsConverter implements AttributeConverter<MultiPolygon, String> {

    @Override
    public String convertToDatabaseColumn(MultiPolygon attribute) {
        return attribute.toText();
    }

    @Override
    public MultiPolygon convertToEntityAttribute(String dbData) {
        try {
            return (MultiPolygon) new WKTReader().read(dbData);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
