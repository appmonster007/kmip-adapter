package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.response.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.response.payload.ObliterateOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ObliterateOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObliterateOpResponsePayload, ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder> {

    public ObliterateOpResponsePayloadTtlvDeserializer() {
        super(ObliterateOpResponsePayload.kmipTag, ObliterateOpResponsePayload.encodingType);
    }

    @Override
    protected ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder createBuilder() {
        return ObliterateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected ObliterateOpResponsePayload build(ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}