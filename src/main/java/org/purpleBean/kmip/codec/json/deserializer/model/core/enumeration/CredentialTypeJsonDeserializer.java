package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

import java.io.IOException;

public class CredentialTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CredentialType, CredentialType.CredentialTypeBuilder> {

    public CredentialTypeJsonDeserializer() {
        super(CredentialType.kmipTag, CredentialType.encodingType);
    }

    @Override
    protected CredentialType.CredentialTypeBuilder createBuilder() {
        return CredentialType.builder();
    }

    @Override
    protected void setValue(CredentialType.CredentialTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(CredentialType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected CredentialType build(CredentialType.CredentialTypeBuilder builder) {
        return builder.build();
    }
}
