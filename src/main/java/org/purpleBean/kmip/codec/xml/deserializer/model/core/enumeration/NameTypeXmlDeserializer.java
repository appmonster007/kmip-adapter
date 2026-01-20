package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.NameType;

public class NameTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<NameType, String> {

    public NameTypeXmlDeserializer() {
        super(NameType.kmipTag, NameType.encodingType, String.class, value -> NameType.fromName(value).inst());
    }
}