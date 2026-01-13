package org.purpleBean.kmip.codec.json.serializer.kmip;

import org.purpleBean.kmip.common.ProtocolVersionMajor;

public class ProtocolVersionMajorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorJsonSerializer() {
        super(ProtocolVersionMajor::getValue);
    }
}