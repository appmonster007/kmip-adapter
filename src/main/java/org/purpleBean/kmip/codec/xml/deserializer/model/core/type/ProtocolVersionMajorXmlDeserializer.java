package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;

public class ProtocolVersionMajorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorXmlDeserializer() {
        super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType, Integer.class, value -> ProtocolVersionMajor.of(value));
    }
}