package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3_0.structure.CredentialInformation;

import java.io.IOException;

public class CredentialInformationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CredentialInformation, CredentialInformation.CredentialInformationBuilder> {

    public CredentialInformationJsonDeserializer() {
        super(CredentialInformation.kmipTag, CredentialInformation.encodingType);
    }

    @Override
    protected CredentialInformation.CredentialInformationBuilder createBuilder() {
        return CredentialInformation.builder();
    }

    @Override
    protected void setValue(CredentialInformation.CredentialInformationBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        if (nodeTag == CredentialType.kmipTag.getValue()) {
            builder.credentialType(ctxt.readValue(p, CredentialType.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CredentialInformation build(CredentialInformation.CredentialInformationBuilder builder) {
        return builder.build();
    }
}