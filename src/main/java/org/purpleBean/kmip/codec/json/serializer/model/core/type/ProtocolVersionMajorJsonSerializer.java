package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;

public class ProtocolVersionMajorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProtocolVersionMajor, Integer> {

    public ProtocolVersionMajorJsonSerializer() {
        super(ProtocolVersionMajor::getValue);
    }
}