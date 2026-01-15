package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

public class ProtocolVersionMinorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorJsonSerializer() {
        super(ProtocolVersionMinor::getValue);
    }
}