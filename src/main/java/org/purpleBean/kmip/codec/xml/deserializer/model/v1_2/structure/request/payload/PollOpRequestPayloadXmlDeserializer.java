package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.PollOpRequestPayload;

public class PollOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PollOpRequestPayload,
        PollOpRequestPayload.PollOpRequestPayloadBuilder> {

  public PollOpRequestPayloadXmlDeserializer() {
    super(PollOpRequestPayload.kmipTag, PollOpRequestPayload.encodingType);
  }

  @Override
  protected PollOpRequestPayload.PollOpRequestPayloadBuilder createBuilder() {
    return PollOpRequestPayload.builder();
  }

  @Override
  protected void setValue(PollOpRequestPayload.PollOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);

    if (nodeTag.equals(KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE)) {
      builder.asynchronousCorrelationValue(ctxt.readValue(p, AsynchronousCorrelationValue.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PollOpRequestPayload build(PollOpRequestPayload.PollOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
