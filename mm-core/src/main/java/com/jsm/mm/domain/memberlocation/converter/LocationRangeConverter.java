package com.jsm.mm.domain.memberlocation.converter;

import com.jsm.mm.domain.memberlocation.enums.LocationRange;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocationRangeConverter implements AttributeConverter<LocationRange, String> {

    @Override
    public String convertToDatabaseColumn(LocationRange attribute) {
        return attribute.getCode();
    }

    @Override
    public LocationRange convertToEntityAttribute(String dbData) {
        return LocationRange.ofCode(dbData);
    }
}
