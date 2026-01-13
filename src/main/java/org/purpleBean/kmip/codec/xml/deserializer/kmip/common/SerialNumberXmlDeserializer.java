package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.SerialNumber;

public class SerialNumberXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SerialNumber, String> {

    public SerialNumberXmlDeserializer() {
        super(SerialNumber.kmipTag, SerialNumber.encodingType, String.class, value -> SerialNumber.builder().value(value).build());
    }
}