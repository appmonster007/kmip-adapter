package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;

public class ProtocolVersionMinorXmlDeserializer extends AbstractKmipXmlDeserializer<ProtocolVersion.ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorXmlDeserializer() {
        super(ProtocolVersion.ProtocolVersionMinor.kmipTag, ProtocolVersion.ProtocolVersionMinor.encodingType, Integer.class, value -> ProtocolVersion.ProtocolVersionMinor.of(value));
    }
}