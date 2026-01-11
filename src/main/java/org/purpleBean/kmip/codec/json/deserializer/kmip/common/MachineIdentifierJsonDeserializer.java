package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierJsonDeserializer extends AbstractKmipJsonDeserializer<MachineIdentifier, String> {

    public MachineIdentifierJsonDeserializer() {
        super(MachineIdentifier.kmipTag, MachineIdentifier.encodingType, String.class, value -> MachineIdentifier.builder().value(value).build());
    }
}