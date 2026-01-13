package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

public class UnwrapModeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<UnwrapMode, String> {

    public UnwrapModeXmlDeserializer() {
        super(UnwrapMode.kmipTag, UnwrapMode.encodingType, String.class, value -> new UnwrapMode(UnwrapMode.fromName(value)));
    }
}