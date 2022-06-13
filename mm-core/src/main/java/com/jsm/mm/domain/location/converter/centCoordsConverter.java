package com.jsm.mm.domain.location.converter;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class centCoordsConverter implements AttributeConverter<Point, String> {

    @Override
    public String convertToDatabaseColumn(Point attribute) {
        return attribute.toText();
    }

    @Override
    public Point convertToEntityAttribute(String dbData) {
        try {
            return (Point) new WKTReader().read(dbData);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
