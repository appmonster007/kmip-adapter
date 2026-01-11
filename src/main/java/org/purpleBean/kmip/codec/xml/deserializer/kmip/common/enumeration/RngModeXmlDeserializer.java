package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.RngMode;

public class RngModeXmlDeserializer extends AbstractKmipXmlDeserializer<RngMode, String> {

    public RngModeXmlDeserializer() {
        super(RngMode.kmipTag, RngMode.encodingType, String.class, value -> new RngMode(RngMode.fromName(value)));
    }
}