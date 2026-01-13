package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CryptographicLength;

public class CryptographicLengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CryptographicLength, Integer> {

    public CryptographicLengthXmlDeserializer() {
        super(CryptographicLength.kmipTag, CryptographicLength.encodingType, Integer.class, value -> CryptographicLength.builder().value(value).build());
    }
}