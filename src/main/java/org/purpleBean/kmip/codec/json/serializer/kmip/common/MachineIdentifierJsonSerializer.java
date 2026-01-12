package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierJsonSerializer extends AbstractKmipJsonSerializer<MachineIdentifier, String> {

    public MachineIdentifierJsonSerializer() {
        super(MachineIdentifier::getValue);
    }
}