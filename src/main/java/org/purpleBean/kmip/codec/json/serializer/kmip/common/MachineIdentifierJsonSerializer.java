package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MachineIdentifier, String> {

    public MachineIdentifierJsonSerializer() {
        super(MachineIdentifier::getValue);
    }
}