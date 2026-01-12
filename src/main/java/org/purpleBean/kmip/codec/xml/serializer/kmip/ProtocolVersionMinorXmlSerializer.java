package org.purpleBean.kmip.codec.xml.serializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ProtocolVersionMinorXmlSerializer extends AbstractKmipXmlSerializer<ProtocolVersion.ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorXmlSerializer() {
        super(ProtocolVersion.ProtocolVersionMinor::getValue);
    }
}