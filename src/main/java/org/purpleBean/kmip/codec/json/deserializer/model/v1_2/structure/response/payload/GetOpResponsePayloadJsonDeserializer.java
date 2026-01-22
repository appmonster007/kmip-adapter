package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetOpResponsePayload;

import java.io.IOException;

public class GetOpResponsePayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<GetOpResponsePayload, GetOpResponsePayload.GetOpResponsePayloadBuilder> {

    public GetOpResponsePayloadJsonDeserializer() {
        super(GetOpResponsePayload.kmipTag, GetOpResponsePayload.encodingType);
    }

    @Override
    protected GetOpResponsePayload.GetOpResponsePayloadBuilder createBuilder() {
        return GetOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetOpResponsePayload.GetOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CERTIFICATE -> builder.certificate(ctxt.readValue(p, Certificate.class));
            case KmipTag.Standard.SYMMETRIC_KEY -> builder.symmetricKey(ctxt.readValue(p, SymmetricKey.class));
            case KmipTag.Standard.PRIVATE_KEY -> builder.privateKey(ctxt.readValue(p, PrivateKey.class));
            case KmipTag.Standard.PUBLIC_KEY -> builder.publicKey(ctxt.readValue(p, PublicKey.class));
            case KmipTag.Standard.SPLIT_KEY -> builder.splitKey(ctxt.readValue(p, SplitKey.class));
            case KmipTag.Standard.TEMPLATE -> builder.template(ctxt.readValue(p, Template.class));
            case KmipTag.Standard.SECRET_DATA -> builder.secretData(ctxt.readValue(p, SecretData.class));
            case KmipTag.Standard.OPAQUE_OBJECT -> builder.opaqueObject(ctxt.readValue(p, OpaqueObject.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetOpResponsePayload build(GetOpResponsePayload.GetOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
