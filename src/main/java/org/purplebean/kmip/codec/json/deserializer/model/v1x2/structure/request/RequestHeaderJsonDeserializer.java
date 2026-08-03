package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.enumeration.BatchErrorContinuationOption;
import org.purplebean.kmip.model.core.structure.Authentication;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.AsynchronousIndicator;
import org.purplebean.kmip.model.core.type.AttestationCapableIndicator;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.BatchOrderOption;
import org.purplebean.kmip.model.core.type.MaximumResponseSize;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v1x2.structure.request.RequestHeader;

/**
 * JSON deserializer for {@link RequestHeader}.
 */
public class RequestHeaderJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RequestHeader, RequestHeader.RequestHeaderBuilder> {

  /**
   * Constructs a new {@link RequestHeaderJsonDeserializer}.
   */
  public RequestHeaderJsonDeserializer() {
    super(RequestHeader.kmipTag, RequestHeader.encodingType);
  }

  @Override
  protected RequestHeader.RequestHeaderBuilder createBuilder() {
    return RequestHeader.builder();
  }

  @Override
  protected void setValue(RequestHeader.RequestHeaderBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTOCOL_VERSION ->
          builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
      case KmipTag.Standard.MAXIMUM_RESPONSE_SIZE ->
          builder.maximumResponseSize(ctxt.readValue(p, MaximumResponseSize.class));
      case KmipTag.Standard.ASYNCHRONOUS_INDICATOR ->
          builder.asynchronousIndicator(ctxt.readValue(p, AsynchronousIndicator.class));
      case KmipTag.Standard.ATTESTATION_CAPABLE_INDICATOR ->
          builder.attestationCapableIndicator(ctxt.readValue(p, AttestationCapableIndicator.class));
      case KmipTag.Standard.ATTESTATION_TYPE ->
          builder.attestationType(ctxt.readValue(p, AttestationType.class));
      case KmipTag.Standard.AUTHENTICATION ->
          builder.authentication(ctxt.readValue(p, Authentication.class));
      case KmipTag.Standard.BATCH_ERROR_CONTINUATION_OPTION -> builder.batchErrorContinuationOption(
          ctxt.readValue(p, BatchErrorContinuationOption.class));
      case KmipTag.Standard.BATCH_ORDER_OPTION ->
          builder.batchOrderOption(ctxt.readValue(p, BatchOrderOption.class));
      case KmipTag.Standard.TIME_STAMP -> builder.timeStamp(ctxt.readValue(p, TimeStamp.class));
      case KmipTag.Standard.BATCH_COUNT -> builder.batchCount(ctxt.readValue(p, BatchCount.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RequestHeader build(RequestHeader.RequestHeaderBuilder builder) {
    return builder.build();
  }
}
