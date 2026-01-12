package org.purpleBean.kmip.codec.json.serializer.kmip;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;

public class ProtocolVersionMinorJsonSerializer extends AbstractKmipJsonSerializer<ProtocolVersion.ProtocolVersionMinor, Integer> {

    public ProtocolVersionMinorJsonSerializer() {
        super(ProtocolVersion.ProtocolVersionMinor::getValue);
    }
}