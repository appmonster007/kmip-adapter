package org.purpleBean.kmip.codec.xml.serializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ProtocolVersionMajorXmlSerializer extends AbstractKmipXmlSerializer<ProtocolVersion.ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorXmlSerializer() {
        super(ProtocolVersion.ProtocolVersionMajor::getValue);
    }
}