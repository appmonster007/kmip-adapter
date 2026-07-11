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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.Pkcs11OpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Pkcs11OpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Pkcs11OpResponsePayload, Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder> {

    public Pkcs11OpResponsePayloadTtlvDeserializer() {
        super(Pkcs11OpResponsePayload.kmipTag, Pkcs11OpResponsePayload.encodingType);
    }

    @Override
    protected Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder createBuilder() {
        return Pkcs11OpResponsePayload.builder();
    }

    @Override
    protected void setValue(Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.PKCS_11_RETURN_CODE -> builder.pkcs11ReturnCode(mapper.readValue(p, Pkcs11ReturnCode.class));
            case KmipTag.Standard.PKCS_11_OUTPUT_PARAMETERS -> builder.pkcs11OutputParameters(mapper.readValue(p, Pkcs11OutputParameters.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Pkcs11OpResponsePayload build(Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder builder) {
        return builder.build();
    }
}