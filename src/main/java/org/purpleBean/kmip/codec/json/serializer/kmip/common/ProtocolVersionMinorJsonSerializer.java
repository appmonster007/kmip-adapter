package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorJsonSerializer() {
        super(ProtocolVersionMinor::getValue);
    }
}