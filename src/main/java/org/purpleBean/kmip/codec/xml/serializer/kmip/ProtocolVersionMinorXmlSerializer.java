package org.purpleBean.kmip.codec.xml.serializer.kmip;

import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorXmlSerializer() {
        super(ProtocolVersionMinor::getValue);
    }
}