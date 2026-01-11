package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeXmlDeserializer extends AbstractKmipXmlDeserializer<NameType, String> {

    public NameTypeXmlDeserializer() {
        super(NameType.kmipTag, NameType.encodingType, String.class, value -> new NameType(NameType.fromName(value)));
    }
}