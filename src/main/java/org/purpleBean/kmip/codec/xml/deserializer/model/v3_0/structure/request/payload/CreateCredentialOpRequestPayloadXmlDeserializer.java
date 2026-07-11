package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateCredentialOpRequestPayload;

import java.io.IOException;

public class CreateCredentialOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CreateCredentialOpRequestPayload, CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder> {

    public CreateCredentialOpRequestPayloadXmlDeserializer() {
        super(CreateCredentialOpRequestPayload.kmipTag, CreateCredentialOpRequestPayload.encodingType);
    }

    @Override
    protected CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder createBuilder() {
        return CreateCredentialOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.CREDENTIAL_TYPE -> {
                CredentialType credentialType = ctxt.readValue(p, CredentialType.class);
                builder.credentialType(credentialType);
                ctxt.setAttribute("credentialType", credentialType.getDescription());
            }
            case KmipTag.Standard.CREDENTIAL_VALUE -> builder.credentialValue(ctxt.readValue(p, CredentialValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateCredentialOpRequestPayload build(CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}