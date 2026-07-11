package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ExportOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class ExportOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ExportOpRequestPayload, ExportOpRequestPayload.ExportOpRequestPayloadBuilder> {

    public ExportOpRequestPayloadTtlvDeserializer() {
        super(ExportOpRequestPayload.kmipTag, ExportOpRequestPayload.encodingType);
    }

    @Override
    protected ExportOpRequestPayload.ExportOpRequestPayloadBuilder createBuilder() {
        return ExportOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ExportOpRequestPayload.ExportOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION -> builder.keyWrappingSpecification(mapper.readValue(p, KeyWrappingSpecification.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ExportOpRequestPayload build(ExportOpRequestPayload.ExportOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}