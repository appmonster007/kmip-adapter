package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MachineIdentifier, String> {

    public MachineIdentifierTtlvDeserializer() {
        super(MachineIdentifier.kmipTag, MachineIdentifier.encodingType, String.class, value -> MachineIdentifier.builder().value(value).build());
    }
}