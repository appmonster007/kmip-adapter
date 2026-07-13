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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CertifyOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertifyOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertifyOpRequestPayload, CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder> {

    public CertifyOpRequestPayloadTtlvDeserializer() {
        super(CertifyOpRequestPayload.kmipTag, CertifyOpRequestPayload.encodingType);
    }

    @Override
    protected CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder createBuilder() {
        return CertifyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CERTIFICATE_REQUEST_TYPE -> builder.certificateRequestType(mapper.readValue(p, CertificateRequestType.class));
            case KmipTag.Standard.CERTIFICATE_REQUEST -> builder.certificateRequest(mapper.readValue(p, CertificateRequest.class));
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertifyOpRequestPayload build(CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}