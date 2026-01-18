package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;
import org.purpleBean.kmip.model.core.structure.Authentication;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.RequestHeader;
import org.purpleBean.kmip.model.core.type.*;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RequestHeaderTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<RequestHeader, RequestHeader.RequestHeaderBuilder> {

    public RequestHeaderTtlvDeserializer() {
        super(RequestHeader.kmipTag);
    }

    @Override
    protected RequestHeader.RequestHeaderBuilder createBuilder() {
        return RequestHeader.builder();
    }

    @Override
    protected void setValue(RequestHeader.RequestHeaderBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION ->
                    builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
            case KmipTag.Standard.MAXIMUM_RESPONSE_SIZE ->
                    builder.maximumResponseSize(mapper.readValue(p, MaximumResponseSize.class));
            case KmipTag.Standard.ASYNCHRONOUS_INDICATOR ->
                    builder.asynchronousIndicator(mapper.readValue(p, AsynchronousIndicator.class));
            case KmipTag.Standard.ATTESTATION_CAPABLE_INDICATOR ->
                    builder.attestationCapableIndicator(mapper.readValue(p, AttestationCapableIndicator.class));
            case KmipTag.Standard.ATTESTATION_TYPE ->
                    builder.attestationType(mapper.readValue(p, AttestationType.class));
            case KmipTag.Standard.AUTHENTICATION -> builder.authentication(mapper.readValue(p, Authentication.class));
            case KmipTag.Standard.BATCH_ERROR_CONTINUATION_OPTION ->
                    builder.batchErrorContinuationOption(mapper.readValue(p, BatchErrorContinuationOption.class));
            case KmipTag.Standard.BATCH_ORDER_OPTION ->
                    builder.batchOrderOption(mapper.readValue(p, BatchOrderOption.class));
            case KmipTag.Standard.TIME_STAMP -> builder.timeStamp(mapper.readValue(p, TimeStamp.class));
            case KmipTag.Standard.BATCH_COUNT -> builder.batchCount(mapper.readValue(p, BatchCount.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RequestHeader build(RequestHeader.RequestHeaderBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return RequestHeader.encodingType;
    }
}