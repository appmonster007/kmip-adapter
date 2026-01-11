package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeXmlDeserializer extends AbstractKmipXmlDeserializer<RotateNameType, String> {

    public RotateNameTypeXmlDeserializer() {
        super(RotateNameType.kmipTag, RotateNameType.encodingType, String.class, value -> new RotateNameType(RotateNameType.fromName(value)));
    }
}