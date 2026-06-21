package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.Pkcs11OpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Pkcs11OpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Pkcs11OpRequestPayload, Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder> {

    public Pkcs11OpRequestPayloadTtlvDeserializer() {
        super(Pkcs11OpRequestPayload.kmipTag, Pkcs11OpRequestPayload.encodingType);
    }

    @Override
    protected Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder createBuilder() {
        return Pkcs11OpRequestPayload.builder();
    }

    @Override
    protected void setValue(Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // TODO: Implement setting values on the builder based on the tag
        // KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        // switch (nodeTag) {
        //     case KmipTag.Standard.FIELD_1 -> builder.field1(mapper.readValue(p, Field1.class));
        //     case KmipTag.Standard.FIELD_2 -> builder.field2(mapper.readValue(p, Field2.class));
        //     default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        // }
    }

    @Override
    protected Pkcs11OpRequestPayload build(Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder builder) {
        return builder.build();
    }
}