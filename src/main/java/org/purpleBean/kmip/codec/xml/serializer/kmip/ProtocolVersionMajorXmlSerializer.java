package org.purpleBean.kmip.codec.xml.serializer.kmip;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorXmlSerializer extends AbstractKmipXmlSerializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorXmlSerializer() {
        super(ProtocolVersionMajor::getValue);
    }
}