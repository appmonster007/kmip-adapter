package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;

public class ProtocolVersionMajorXmlDeserializer extends AbstractKmipXmlDeserializer<ProtocolVersion.ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorXmlDeserializer() {
        super(ProtocolVersion.ProtocolVersionMajor.kmipTag, ProtocolVersion.ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersion.ProtocolVersionMajor.of(value));
    }
}