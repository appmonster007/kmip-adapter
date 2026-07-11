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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetDefaultsOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SetDefaultsOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SetDefaultsOpResponsePayload, SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder> {

    public SetDefaultsOpResponsePayloadTtlvDeserializer() {
        super(SetDefaultsOpResponsePayload.kmipTag, SetDefaultsOpResponsePayload.encodingType);
    }

    @Override
    protected SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder createBuilder() {
        return SetDefaultsOpResponsePayload.builder();
    }

    @Override
    protected void setValue(SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected SetDefaultsOpResponsePayload build(SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}