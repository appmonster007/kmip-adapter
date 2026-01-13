package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<NameType, String> {

    public NameTypeXmlDeserializer() {
        super(NameType.kmipTag, NameType.encodingType, String.class, value -> new NameType(NameType.fromName(value)));
    }
}