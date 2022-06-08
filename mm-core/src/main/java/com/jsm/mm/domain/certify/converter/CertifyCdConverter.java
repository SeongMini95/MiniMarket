package com.jsm.mm.domain.certify.converter;

import com.jsm.mm.domain.certify.enums.CertifyCd;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CertifyCdConverter implements AttributeConverter<CertifyCd, String> {

    @Override
    public String convertToDatabaseColumn(CertifyCd attribute) {
        return attribute.getCode();
    }

    @Override
    public CertifyCd convertToEntityAttribute(String dbData) {
        return CertifyCd.ofCode(dbData);
    }
}
