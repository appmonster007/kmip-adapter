package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<GetOpRequestPayload, GetOpRequestPayload.GetOpRequestPayloadBuilder> {

    public GetOpRequestPayloadTtlvDeserializer() {
        super(GetOpRequestPayload.kmipTag, GetOpRequestPayload.encodingType);
    }

    @Override
    protected GetOpRequestPayload.GetOpRequestPayloadBuilder createBuilder() {
        return GetOpRequestPayload.builder();
    }

    @Override
    protected void setValue(GetOpRequestPayload.GetOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.KEY_FORMAT_TYPE -> builder.keyFormatType(mapper.readValue(p, KeyFormatType.class));
            case KmipTag.Standard.KEY_WRAP_TYPE -> builder.keyWrapType(mapper.readValue(p, org.purpleBean.kmip.model.core.enumeration.KeyWrapType.class));
            case KmipTag.Standard.KEY_COMPRESSION_TYPE ->
                    builder.keyCompressionType(mapper.readValue(p, KeyCompressionType.class));
            case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION ->
                    builder.keyWrappingSpecification(mapper.readValue(p, KeyWrappingSpecification.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetOpRequestPayload build(GetOpRequestPayload.GetOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
