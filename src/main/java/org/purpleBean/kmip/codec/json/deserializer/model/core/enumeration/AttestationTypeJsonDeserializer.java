package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;

import java.io.IOException;

public class AttestationTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttestationType, AttestationType.AttestationTypeBuilder> {

    public AttestationTypeJsonDeserializer() {
        super(AttestationType.kmipTag, AttestationType.encodingType);
    }

    @Override
    protected AttestationType.AttestationTypeBuilder createBuilder() {
        return AttestationType.builder();
    }

    @Override
    protected void setValue(AttestationType.AttestationTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(AttestationType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected AttestationType build(AttestationType.AttestationTypeBuilder builder) {
        return builder.build();
    }
}
