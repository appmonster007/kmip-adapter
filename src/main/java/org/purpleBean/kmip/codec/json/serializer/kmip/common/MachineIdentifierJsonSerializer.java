package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MachineIdentifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MachineIdentifierJsonSerializer extends KmipDataTypeJsonSerializer<MachineIdentifier> {

    @Override
    public void serialize(MachineIdentifier machineIdentifier, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (machineIdentifier == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!machineIdentifier.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", machineIdentifier.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(machineIdentifier.getKmipTag());
        gen.writeStringField("type", machineIdentifier.getEncodingType().getDescription());
        gen.writeObjectField("value", machineIdentifier.getValue());
        gen.writeEndObject();
    }
}