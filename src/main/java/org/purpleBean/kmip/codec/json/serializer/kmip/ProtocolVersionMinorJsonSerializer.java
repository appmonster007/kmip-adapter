package org.purpleBean.kmip.codec.json.serializer.kmip;

import org.purpleBean.kmip.common.ProtocolVersionMinor;

public class ProtocolVersionMinorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorJsonSerializer() {
        super(ProtocolVersionMinor::getValue);
    }
}