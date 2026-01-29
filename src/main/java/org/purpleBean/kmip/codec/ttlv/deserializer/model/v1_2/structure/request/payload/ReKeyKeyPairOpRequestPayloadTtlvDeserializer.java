package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ReKeyKeyPairOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ReKeyKeyPairOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ReKeyKeyPairOpRequestPayload, ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder> {

    public ReKeyKeyPairOpRequestPayloadTtlvDeserializer() {
        super(ReKeyKeyPairOpRequestPayload.kmipTag, ReKeyKeyPairOpRequestPayload.encodingType);
    }

    @Override
    protected ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder createBuilder() {
        return ReKeyKeyPairOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER ->
                    builder.privateKeyUniqueIdentifier(mapper.readValue(p, PrivateKeyUniqueIdentifier.class));
            case KmipTag.Standard.OFFSET -> builder.offset(mapper.readValue(p, Offset.class));
            case KmipTag.Standard.COMMON_TEMPLATE_ATTRIBUTE ->
                    builder.commonTemplateAttribute(mapper.readValue(p, CommonTemplateAttribute.class));
            case KmipTag.Standard.PRIVATE_KEY_TEMPLATE_ATTRIBUTE ->
                    builder.privateKeyTemplateAttribute(mapper.readValue(p, PrivateKeyTemplateAttribute.class));
            case KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE ->
                    builder.publicKeyTemplateAttribute(mapper.readValue(p, PublicKeyTemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ReKeyKeyPairOpRequestPayload build(ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
