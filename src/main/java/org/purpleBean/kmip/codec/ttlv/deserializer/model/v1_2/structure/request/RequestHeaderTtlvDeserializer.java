package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;
import org.purpleBean.kmip.model.core.structure.Authentication;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.AsynchronousIndicator;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestHeader;

public class RequestHeaderTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RequestHeader, RequestHeader.RequestHeaderBuilder> {

  public RequestHeaderTtlvDeserializer() {
    super(RequestHeader.kmipTag, RequestHeader.encodingType);
  }

  @Override
  protected RequestHeader.RequestHeaderBuilder createBuilder() {
    return RequestHeader.builder();
  }

  @Override
  protected void setValue(RequestHeader.RequestHeaderBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTOCOL_VERSION ->
          builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
      case KmipTag.Standard.MAXIMUM_RESPONSE_SIZE ->
          builder.maximumResponseSize(mapper.readValue(p, MaximumResponseSize.class));
      case KmipTag.Standard.ASYNCHRONOUS_INDICATOR ->
          builder.asynchronousIndicator(mapper.readValue(p, AsynchronousIndicator.class));
      case KmipTag.Standard.ATTESTATION_CAPABLE_INDICATOR -> builder.attestationCapableIndicator(
          mapper.readValue(p, AttestationCapableIndicator.class));
      case KmipTag.Standard.ATTESTATION_TYPE ->
          builder.attestationType(mapper.readValue(p, AttestationType.class));
      case KmipTag.Standard.AUTHENTICATION ->
          builder.authentication(mapper.readValue(p, Authentication.class));
      case KmipTag.Standard.BATCH_ERROR_CONTINUATION_OPTION -> builder.batchErrorContinuationOption(
          mapper.readValue(p, BatchErrorContinuationOption.class));
      case KmipTag.Standard.BATCH_ORDER_OPTION ->
          builder.batchOrderOption(mapper.readValue(p, BatchOrderOption.class));
      case KmipTag.Standard.TIME_STAMP -> builder.timeStamp(mapper.readValue(p, TimeStamp.class));
      case KmipTag.Standard.BATCH_COUNT ->
          builder.batchCount(mapper.readValue(p, BatchCount.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RequestHeader build(RequestHeader.RequestHeaderBuilder builder) {
    return builder.build();
  }
}