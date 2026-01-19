package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.CreateKeyPairOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CreateKeyPairOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CreateKeyPairOpRequestPayload, CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder> {

    public CreateKeyPairOpRequestPayloadTtlvDeserializer() {
        super(CreateKeyPairOpRequestPayload.kmipTag);
    }

    @Override
    protected CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder createBuilder() {
        return CreateKeyPairOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
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
    protected CreateKeyPairOpRequestPayload build(CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CreateKeyPairOpRequestPayload.encodingType;
    }
}