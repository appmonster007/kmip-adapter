package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.MachineIdentifier;

public class MachineIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MachineIdentifier, String> {

    public MachineIdentifierJsonSerializer() {
        super(MachineIdentifier::getValue);
    }
}