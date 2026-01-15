package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

public class ProtocolVersionMinorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorXmlSerializer() {
        super(ProtocolVersionMinor::getValue);
    }
}