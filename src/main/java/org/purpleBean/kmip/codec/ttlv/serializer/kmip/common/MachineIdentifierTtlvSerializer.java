package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<MachineIdentifier, String> {

    public MachineIdentifierTtlvSerializer() {
        super(MachineIdentifier::getValue);
    }
}