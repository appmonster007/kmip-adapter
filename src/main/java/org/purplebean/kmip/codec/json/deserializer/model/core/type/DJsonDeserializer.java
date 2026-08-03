package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.D;

/**
 * JSON deserializer for {@link D}.
 */
public class DJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<D, D.DBuilder> {

  /**
   * Constructs a new {@link DJsonDeserializer}.
   */
  public DJsonDeserializer() {
    super(D.kmipTag, D.encodingType);
  }

  @Override
  protected D.DBuilder createBuilder() {
    return D.builder();
  }

  @Override
  protected void setValue(D.DBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected D build(D.DBuilder builder) {
    return builder.build();
  }
}
