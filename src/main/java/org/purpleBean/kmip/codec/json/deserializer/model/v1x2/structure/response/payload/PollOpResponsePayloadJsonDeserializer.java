package org.purpleBean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.PollOpResponsePayload;

public class PollOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PollOpResponsePayload,
        PollOpResponsePayload.PollOpResponsePayloadBuilder> {

  public PollOpResponsePayloadJsonDeserializer() {
    super(PollOpResponsePayload.kmipTag, PollOpResponsePayload.encodingType);
  }

  @Override
  protected PollOpResponsePayload.PollOpResponsePayloadBuilder createBuilder() {
    return PollOpResponsePayload.builder();
  }

  @Override
  protected void setValue(PollOpResponsePayload.PollOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          ctxt.readValue(p, AsynchronousCorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PollOpResponsePayload build(
      PollOpResponsePayload.PollOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}