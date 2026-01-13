package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorXmlDeserializer extends AbstractKmipXmlDeserializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorXmlDeserializer() {
        super(ProtocolVersionMinor.kmipTag, ProtocolVersionMinor.encodingType, Integer.class, value -> ProtocolVersionMinor.of(value));
    }
}