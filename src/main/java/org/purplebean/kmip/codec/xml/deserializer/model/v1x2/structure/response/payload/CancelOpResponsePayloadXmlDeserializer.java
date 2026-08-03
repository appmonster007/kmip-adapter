package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.CancellationResult;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CancelOpResponsePayload;

/**
 * XML deserializer for {@link CancelOpResponsePayload}.
 */
public class CancelOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CancelOpResponsePayload,
        CancelOpResponsePayload.CancelOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CancelOpResponsePayloadXmlDeserializer}.
   */
  public CancelOpResponsePayloadXmlDeserializer() {
    super(CancelOpResponsePayload.kmipTag, CancelOpResponsePayload.encodingType);
  }

  @Override
  protected CancelOpResponsePayload.CancelOpResponsePayloadBuilder createBuilder() {
    return CancelOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CancelOpResponsePayload.CancelOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          ctxt.readValue(p, AsynchronousCorrelationValue.class));
      case KmipTag.Standard.CANCELLATION_RESULT ->
          builder.cancellationResult(ctxt.readValue(p, CancellationResult.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CancelOpResponsePayload build(
      CancelOpResponsePayload.CancelOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
