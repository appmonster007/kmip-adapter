package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AlternativeNameType, String> {

    public AlternativeNameTypeXmlDeserializer() {
        super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType, String.class, value -> new AlternativeNameType(AlternativeNameType.fromName(value)));
    }
}