package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetConstraintsOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SetConstraintsOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SetConstraintsOpResponsePayload, SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder> {

    public SetConstraintsOpResponsePayloadTtlvDeserializer() {
        super(SetConstraintsOpResponsePayload.kmipTag, SetConstraintsOpResponsePayload.encodingType);
    }

    @Override
    protected SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder createBuilder() {
        return SetConstraintsOpResponsePayload.builder();
    }

    @Override
    protected void setValue(SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected SetConstraintsOpResponsePayload build(SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}