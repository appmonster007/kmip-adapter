package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

public class UnwrapModeXmlDeserializer extends AbstractKmipXmlDeserializer<UnwrapMode, String> {

    public UnwrapModeXmlDeserializer() {
        super(UnwrapMode.kmipTag, UnwrapMode.encodingType, String.class, value -> new UnwrapMode(UnwrapMode.fromName(value)));
    }
}