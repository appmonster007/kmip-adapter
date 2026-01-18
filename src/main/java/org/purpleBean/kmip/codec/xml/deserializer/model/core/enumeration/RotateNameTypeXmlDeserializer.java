package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RotateNameType;

public class RotateNameTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RotateNameType, String> {

    public RotateNameTypeXmlDeserializer() {
        super(RotateNameType.kmipTag, RotateNameType.encodingType, String.class, value -> new RotateNameType(RotateNameType.fromName(value)));
    }
}