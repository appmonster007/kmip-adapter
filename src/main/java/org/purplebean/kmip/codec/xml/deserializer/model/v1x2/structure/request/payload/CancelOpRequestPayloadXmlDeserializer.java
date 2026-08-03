package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CancelOpRequestPayload;

/**
 * XML deserializer for {@link CancelOpRequestPayload}.
 */
public class CancelOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CancelOpRequestPayload,
        CancelOpRequestPayload.CancelOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link CancelOpRequestPayloadXmlDeserializer}.
   */
  public CancelOpRequestPayloadXmlDeserializer() {
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
