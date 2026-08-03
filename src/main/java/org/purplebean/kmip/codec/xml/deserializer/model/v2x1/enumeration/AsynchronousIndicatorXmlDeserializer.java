package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.AsynchronousIndicator;

/**
 * XML deserializer for {@link AsynchronousIndicator}.
 */
public class AsynchronousIndicatorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AsynchronousIndicator,
        AsynchronousIndicator.AsynchronousIndicatorBuilder> {

  /**
   * Constructs a new {@link AsynchronousIndicatorXmlDeserializer}.
   */
  public AsynchronousIndicatorXmlDeserializer() {
    super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType);
  }

  @Override
  protected AsynchronousIndicator.AsynchronousIndicatorBuilder createBuilder() {
    return AsynchronousIndicator.builder();
  }

  @Override
  protected void setValue(AsynchronousIndicator.AsynchronousIndicatorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(AsynchronousIndicator.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected AsynchronousIndicator build(
      AsynchronousIndicator.AsynchronousIndicatorBuilder builder) {
    return builder.build();
  }
}