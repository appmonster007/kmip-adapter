package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;

public class UnwrapModeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<UnwrapMode, String> {

    public UnwrapModeXmlDeserializer() {
        super(UnwrapMode.kmipTag, UnwrapMode.encodingType, String.class, value -> UnwrapMode.fromName(value).inst());
    }
}