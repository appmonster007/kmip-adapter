package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.Modulus;

/**
 * JSON deserializer for {@link Modulus}.
 */
public class ModulusJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Modulus, Modulus.ModulusBuilder> {

  /**
   * Constructs a new {@link ModulusJsonDeserializer}.
   */
  public ModulusJsonDeserializer() {
    super(Modulus.kmipTag, Modulus.encodingType);
  }

  @Override
  protected Modulus.ModulusBuilder createBuilder() {
    return Modulus.builder();
  }

  @Override
  protected void setValue(Modulus.ModulusBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected Modulus build(Modulus.ModulusBuilder builder) {
    return builder.build();
  }
}
