package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ResultReason;

/**
 * XML deserializer for {@link ResultReason}.
 */
public class ResultReasonXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ResultReason, ResultReason.ResultReasonBuilder> {

  /**
   * Constructs a new {@link ResultReasonXmlDeserializer}.
   */
  public ResultReasonXmlDeserializer() {
    super(ResultReason.kmipTag, ResultReason.encodingType);
  }

  @Override
  protected ResultReason.ResultReasonBuilder createBuilder() {
    return ResultReason.builder();
  }

  @Override
  protected void setValue(ResultReason.ResultReasonBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ResultReason.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ResultReason build(ResultReason.ResultReasonBuilder builder) {
    return builder.build();
  }
}