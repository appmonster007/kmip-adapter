package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ProtectionStorageMask;

import java.io.IOException;

public class ProtectionStorageMaskJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProtectionStorageMask, ProtectionStorageMask.ProtectionStorageMaskBuilder> {

    public ProtectionStorageMaskJsonDeserializer() {
        super(ProtectionStorageMask.kmipTag, ProtectionStorageMask.encodingType);
    }

    @Override
    protected ProtectionStorageMask.ProtectionStorageMaskBuilder createBuilder() {
        return ProtectionStorageMask.builder();
    }

    @Override
    protected void setValue(ProtectionStorageMask.ProtectionStorageMaskBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        String maskString = ctxt.readValue(p, String.class);
        ProtectionStorageMask mask = ProtectionStorageMask.fromMaskString(maskString);
        builder.value(mask.getValue());
    }

    @Override
    protected ProtectionStorageMask build(ProtectionStorageMask.ProtectionStorageMaskBuilder builder) {
        return builder.build();
    }
}