package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorXmlSerializer() {
        super(ProtocolVersionMajor::getValue);
    }
}