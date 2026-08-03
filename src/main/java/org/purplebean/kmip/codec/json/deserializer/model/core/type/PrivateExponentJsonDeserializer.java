package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.PrivateExponent;

/**
 * JSON deserializer for {@link PrivateExponent}.
 */
public class PrivateExponentJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PrivateExponent, PrivateExponent.PrivateExponentBuilder> {

  /**
   * Constructs a new {@link PrivateExponentJsonDeserializer}.
   */
  public PrivateExponentJsonDeserializer() {
    super(PrivateExponent.kmipTag, PrivateExponent.encodingType);
  }

  @Override
  protected PrivateExponent.PrivateExponentBuilder createBuilder() {
    return PrivateExponent.builder();
  }

  @Override
  protected void setValue(PrivateExponent.PrivateExponentBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected PrivateExponent build(PrivateExponent.PrivateExponentBuilder builder) {
    return builder.build();
  }
}
