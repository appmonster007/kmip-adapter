package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.RequestCount;

/**
 * XML deserializer for {@link RequestCount}.
 */
public class RequestCountXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<RequestCount, RequestCount.RequestCountBuilder> {

  /**
   * Constructs a new {@link RequestCountXmlDeserializer}.
   */
  public RequestCountXmlDeserializer() {
    super(RequestCount.kmipTag, RequestCount.encodingType);
  }

  @Override
  protected RequestCount.RequestCountBuilder createBuilder() {
    return RequestCount.builder();
  }

  @Override
  protected void setValue(RequestCount.RequestCountBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected RequestCount build(RequestCount.RequestCountBuilder builder) {
    return builder.build();
  }
}
