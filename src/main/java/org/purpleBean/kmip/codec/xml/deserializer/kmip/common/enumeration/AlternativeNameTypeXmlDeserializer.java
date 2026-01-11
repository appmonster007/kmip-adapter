package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;

public class AlternativeNameTypeXmlDeserializer extends AbstractKmipXmlDeserializer<AlternativeNameType, String> {

    public AlternativeNameTypeXmlDeserializer() {
        super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType, String.class, value -> new AlternativeNameType(AlternativeNameType.fromName(value)));
    }
}