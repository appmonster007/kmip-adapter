package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<GetOpResponsePayload, GetOpResponsePayload.GetOpResponsePayloadBuilder> {

    public GetOpResponsePayloadTtlvDeserializer() {
        super(GetOpResponsePayload.kmipTag);
    }

    @Override
    protected GetOpResponsePayload.GetOpResponsePayloadBuilder createBuilder() {
        return GetOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetOpResponsePayload.GetOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CERTIFICATE -> builder.certificate(mapper.readValue(p, Certificate.class));
            case KmipTag.Standard.SYMMETRIC_KEY -> builder.symmetricKey(mapper.readValue(p, SymmetricKey.class));
            case KmipTag.Standard.PRIVATE_KEY -> builder.privateKey(mapper.readValue(p, PrivateKey.class));
            case KmipTag.Standard.PUBLIC_KEY -> builder.publicKey(mapper.readValue(p, PublicKey.class));
            case KmipTag.Standard.SPLIT_KEY -> builder.splitKey(mapper.readValue(p, SplitKey.class));
            case KmipTag.Standard.TEMPLATE -> builder.template(mapper.readValue(p, Template.class));
            case KmipTag.Standard.SECRET_DATA -> builder.secretData(mapper.readValue(p, SecretData.class));
            case KmipTag.Standard.OPAQUE_OBJECT -> builder.opaqueObject(mapper.readValue(p, OpaqueObject.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetOpResponsePayload build(GetOpResponsePayload.GetOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return GetOpResponsePayload.encodingType;
    }
}
