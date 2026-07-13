package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.CreateKeyPairOpResponsePayload;

import java.io.IOException;

public class CreateKeyPairOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CreateKeyPairOpResponsePayload, CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder> {

    public CreateKeyPairOpResponsePayloadJsonDeserializer() {
        super(CreateKeyPairOpResponsePayload.kmipTag, CreateKeyPairOpResponsePayload.encodingType);
    }

    @Override
    protected CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder createBuilder() {
        return CreateKeyPairOpResponsePayload.builder();
    }

    @Override
    protected void setValue(CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER -> builder.privateKeyUniqueIdentifier(ctxt.readValue(p, PrivateKeyUniqueIdentifier.class));
            case KmipTag.Standard.PUBLIC_KEY_UNIQUE_IDENTIFIER -> builder.publicKeyUniqueIdentifier(ctxt.readValue(p, PublicKeyUniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateKeyPairOpResponsePayload build(CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
