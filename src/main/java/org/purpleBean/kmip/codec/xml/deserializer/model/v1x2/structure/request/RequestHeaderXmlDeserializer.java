package org.purpleBean.kmip.codec.xml.deserializer.model.v1x2.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
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
import org.purpleBean.kmip.model.v1x2.structure.request.RequestHeader;

public class RequestHeaderXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<RequestHeader, RequestHeader.RequestHeaderBuilder> {

  public RequestHeaderXmlDeserializer() {
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