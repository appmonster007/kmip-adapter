package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CancelOpRequestPayload;

public class CancelOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CancelOpRequestPayload,
        CancelOpRequestPayload.CancelOpRequestPayloadBuilder> {

  public CancelOpRequestPayloadJsonDeserializer() {
    super(CancelOpRequestPayload.kmipTag, CancelOpRequestPayload.encodingType);
  }

  @Override
  protected CancelOpRequestPayload.CancelOpRequestPayloadBuilder createBuilder() {
    return CancelOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CancelOpRequestPayload.CancelOpRequestPayloadBuilder builder, String tag,
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
  protected CancelOpRequestPayload build(
      CancelOpRequestPayload.CancelOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
