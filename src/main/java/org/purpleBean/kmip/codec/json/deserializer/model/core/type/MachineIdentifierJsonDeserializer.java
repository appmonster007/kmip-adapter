package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.MachineIdentifier;

import java.io.IOException;

public class MachineIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MachineIdentifier, MachineIdentifier.MachineIdentifierBuilder> {

    public MachineIdentifierJsonDeserializer() {
        super(MachineIdentifier.kmipTag, MachineIdentifier.encodingType);
    }

    @Override
    protected MachineIdentifier.MachineIdentifierBuilder createBuilder() {
        return MachineIdentifier.builder();
    }

    @Override
    protected void setValue(MachineIdentifier.MachineIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected MachineIdentifier build(MachineIdentifier.MachineIdentifierBuilder builder) {
        return builder.build();
    }
}
