package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CryptographicLength;

public class CryptographicLengthXmlDeserializer extends AbstractKmipXmlDeserializer<CryptographicLength, Integer> {

    public CryptographicLengthXmlDeserializer() {
        super(CryptographicLength.kmipTag, CryptographicLength.encodingType, Integer.class, value -> CryptographicLength.builder().value(value).build());
    }
}