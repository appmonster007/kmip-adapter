package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.CancellationResult;

/**
 * XML deserializer for {@link CancellationResult}.
 */
public class CancellationResultXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CancellationResult,
        CancellationResult.CancellationResultBuilder> {

  /**
   * Constructs a new {@link CancellationResultXmlDeserializer}.
   */
  public CancellationResultXmlDeserializer() {
    super(CancellationResult.kmipTag, CancellationResult.encodingType);
  }

  @Override
  protected CancellationResult.CancellationResultBuilder createBuilder() {
    return CancellationResult.builder();
  }

  @Override
  protected void setValue(CancellationResult.CancellationResultBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(CancellationResult.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected CancellationResult build(CancellationResult.CancellationResultBuilder builder) {
    return builder.build();
  }
}