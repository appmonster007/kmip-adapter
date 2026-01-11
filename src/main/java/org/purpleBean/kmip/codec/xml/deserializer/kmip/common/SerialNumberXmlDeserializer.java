package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.SerialNumber;

public class SerialNumberXmlDeserializer extends AbstractKmipXmlDeserializer<SerialNumber, String> {

    public SerialNumberXmlDeserializer() {
        super(SerialNumber.kmipTag, SerialNumber.encodingType, String.class, value -> SerialNumber.builder().value(value).build());
    }
}