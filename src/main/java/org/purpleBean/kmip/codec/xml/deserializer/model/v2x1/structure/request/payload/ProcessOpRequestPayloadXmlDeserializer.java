package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.ProcessOpRequestPayload;

public class ProcessOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ProcessOpRequestPayload,
        ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder> {

  public ProcessOpRequestPayloadXmlDeserializer() {
    super(ProcessOpRequestPayload.kmipTag, ProcessOpRequestPayload.encodingType);
  }

  @Override
  protected ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder createBuilder() {
    return ProcessOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder builder,
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
  protected ProcessOpRequestPayload build(
      ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}