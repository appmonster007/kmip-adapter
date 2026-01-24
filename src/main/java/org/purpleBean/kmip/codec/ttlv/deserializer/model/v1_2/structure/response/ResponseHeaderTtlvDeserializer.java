package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseHeader;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ResponseHeaderTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<ResponseHeader, ResponseHeader.ResponseHeaderBuilder> {

    public ResponseHeaderTtlvDeserializer() {
        super(ResponseHeader.kmipTag);
    }

    @Override
    protected ResponseHeader.ResponseHeaderBuilder createBuilder() {
        return ResponseHeader.builder();
    }

    @Override
    protected void setValue(ResponseHeader.ResponseHeaderBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION ->
                    builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
            case KmipTag.Standard.TIME_STAMP -> builder.timeStamp(mapper.readValue(p, TimeStamp.class));
            case KmipTag.Standard.NONCE -> builder.nonce(mapper.readValue(p, Nonce.class));
            case KmipTag.Standard.ATTESTATION_TYPE ->
                    builder.attestationType(mapper.readValue(p, AttestationType.class));
            case KmipTag.Standard.BATCH_COUNT -> builder.batchCount(mapper.readValue(p, BatchCount.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ResponseHeader build(ResponseHeader.ResponseHeaderBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return ResponseHeader.encodingType;
    }
}
