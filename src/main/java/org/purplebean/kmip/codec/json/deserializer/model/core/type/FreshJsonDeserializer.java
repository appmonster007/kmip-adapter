package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.Fresh;

/**
 * JSON deserializer for {@link Fresh}.
 */
public class FreshJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Fresh, Fresh.FreshBuilder> {

  /**
   * Constructs a new {@link FreshJsonDeserializer}.
   */
  public FreshJsonDeserializer() {
    super(Fresh.kmipTag, Fresh.encodingType);
  }

  @Override
  protected Fresh.FreshBuilder createBuilder() {
    return Fresh.builder();
  }

  @Override
  protected void setValue(Fresh.FreshBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected Fresh build(Fresh.FreshBuilder builder) {
    return builder.build();
  }
}
