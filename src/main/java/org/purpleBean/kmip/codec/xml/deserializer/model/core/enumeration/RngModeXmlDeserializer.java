package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RngMode;

public class RngModeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RngMode, String> {

    public RngModeXmlDeserializer() {
        super(RngMode.kmipTag, RngMode.encodingType, String.class, value -> RngMode.fromName(value).inst());
    }
}