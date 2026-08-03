package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ProcessOpResponsePayload;

/**
 * JSON deserializer for {@link ProcessOpResponsePayload}.
 */
public class ProcessOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProcessOpResponsePayload,
        ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ProcessOpResponsePayloadJsonDeserializer}.
   */
  public ProcessOpResponsePayloadJsonDeserializer() {
    super(ProcessOpResponsePayload.kmipTag, ProcessOpResponsePayload.encodingType);
  }

  @Override
  protected ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder createBuilder() {
    return ProcessOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          ctxt.readValue(p, AsynchronousCorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ProcessOpResponsePayload build(
      ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}