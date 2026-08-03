package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.J;

/**
 * JSON deserializer for {@link J}.
 */
public class JJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<J, J.JBuilder> {

  /**
   * Constructs a new {@link JJsonDeserializer}.
   */
  public JJsonDeserializer() {
    super(J.kmipTag, J.encodingType);
  }

  @Override
  protected J.JBuilder createBuilder() {
    return J.builder();
  }

  @Override
  protected void setValue(J.JBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected J build(J.JBuilder builder) {
    return builder.build();
  }
}
