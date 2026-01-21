package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CertifyOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertifyOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CertifyOpResponsePayload, CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder> {

    public CertifyOpResponsePayloadTtlvDeserializer() {
        super(CertifyOpResponsePayload.kmipTag);
    }

    @Override
    protected CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder createBuilder() {
        return CertifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertifyOpResponsePayload build(CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CertifyOpResponsePayload.encodingType;
    }
}