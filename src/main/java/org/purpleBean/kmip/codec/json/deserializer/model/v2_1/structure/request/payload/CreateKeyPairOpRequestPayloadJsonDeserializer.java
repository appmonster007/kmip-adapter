package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.CommonAttributes;
import org.purpleBean.kmip.model.v2_1.structure.PrivateKeyAttributes;
import org.purpleBean.kmip.model.v2_1.structure.PublicKeyAttributes;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateKeyPairOpRequestPayload;

import java.io.IOException;

public class CreateKeyPairOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CreateKeyPairOpRequestPayload, CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder> {

    public CreateKeyPairOpRequestPayloadJsonDeserializer() {
        super(CreateKeyPairOpRequestPayload.kmipTag, CreateKeyPairOpRequestPayload.encodingType);
    }

    @Override
    protected CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder createBuilder() {
        return CreateKeyPairOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.COMMON_ATTRIBUTES -> builder.commonAttributes(ctxt.readValue(p, CommonAttributes.class));
            case KmipTag.Standard.PRIVATE_KEY_ATTRIBUTES -> builder.privateKeyAttributes(ctxt.readValue(p, PrivateKeyAttributes.class));
            case KmipTag.Standard.PUBLIC_KEY_ATTRIBUTES -> builder.publicKeyAttributes(ctxt.readValue(p, PublicKeyAttributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateKeyPairOpRequestPayload build(CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}