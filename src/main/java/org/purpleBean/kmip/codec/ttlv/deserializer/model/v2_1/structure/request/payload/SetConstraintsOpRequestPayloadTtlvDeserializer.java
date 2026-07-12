package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetConstraintsOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v2_1.structure.Constraints;

public class SetConstraintsOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SetConstraintsOpRequestPayload, SetConstraintsOpRequestPayload.SetConstraintsOpRequestPayloadBuilder> {

    public SetConstraintsOpRequestPayloadTtlvDeserializer() {
        super(SetConstraintsOpRequestPayload.kmipTag, SetConstraintsOpRequestPayload.encodingType);
    }

    @Override
    protected SetConstraintsOpRequestPayload.SetConstraintsOpRequestPayloadBuilder createBuilder() {
        return SetConstraintsOpRequestPayload.builder();
    }

    @Override
    protected void setValue(SetConstraintsOpRequestPayload.SetConstraintsOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CONSTRAINTS -> builder.constraints(mapper.readValue(p, Constraints.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SetConstraintsOpRequestPayload build(SetConstraintsOpRequestPayload.SetConstraintsOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}