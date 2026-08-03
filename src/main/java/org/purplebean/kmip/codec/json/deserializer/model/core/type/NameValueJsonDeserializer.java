package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.NameValue;

/**
 * JSON deserializer for {@link NameValue}.
 */
public class NameValueJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<NameValue, NameValue.NameValueBuilder> {

  /**
   * Constructs a new {@link NameValueJsonDeserializer}.
   */
  public NameValueJsonDeserializer() {
    super(NameValue.kmipTag, NameValue.encodingType);
  }

  @Override
  protected NameValue.NameValueBuilder createBuilder() {
    return NameValue.builder();
  }

  @Override
  protected void setValue(NameValue.NameValueBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected NameValue build(NameValue.NameValueBuilder builder) {
    return builder.build();
  }
}
