package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorXmlDeserializer extends AbstractKmipXmlDeserializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorXmlDeserializer() {
        super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersionMajor.of(value));
    }
}