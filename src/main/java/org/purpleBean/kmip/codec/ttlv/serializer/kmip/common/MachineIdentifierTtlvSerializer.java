package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MachineIdentifier, String> {

    public MachineIdentifierTtlvSerializer() {
        super(MachineIdentifier::getValue);
    }
}