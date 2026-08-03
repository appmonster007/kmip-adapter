package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.InvocationFieldLength;

/**
 * XML deserializer for {@link InvocationFieldLength}.
 */
public class InvocationFieldLengthXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<InvocationFieldLength,
        InvocationFieldLength.InvocationFieldLengthBuilder> {

  /**
   * Constructs a new {@link InvocationFieldLengthXmlDeserializer}.
   */
  public InvocationFieldLengthXmlDeserializer() {
    super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType);
  }

  @Override
  protected InvocationFieldLength.InvocationFieldLengthBuilder createBuilder() {
    return InvocationFieldLength.builder();
  }

  @Override
  protected void setValue(InvocationFieldLength.InvocationFieldLengthBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected InvocationFieldLength build(
      InvocationFieldLength.InvocationFieldLengthBuilder builder) {
    return builder.build();
  }
}