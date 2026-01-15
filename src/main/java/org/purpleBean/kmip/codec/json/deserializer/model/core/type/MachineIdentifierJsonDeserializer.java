package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.MachineIdentifier;

public class MachineIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MachineIdentifier, String> {

    public MachineIdentifierJsonDeserializer() {
        super(MachineIdentifier.kmipTag, MachineIdentifier.encodingType, String.class, value -> MachineIdentifier.builder().value(value).build());
    }
}