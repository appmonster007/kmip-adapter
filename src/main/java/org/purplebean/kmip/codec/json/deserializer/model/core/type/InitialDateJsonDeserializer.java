package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.InitialDate;

/**
 * JSON deserializer for {@link InitialDate}.
 */
public class InitialDateJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<InitialDate, InitialDate.InitialDateBuilder> {

  /**
   * Constructs a new {@link InitialDateJsonDeserializer}.
   */
  public InitialDateJsonDeserializer() {
    super(InitialDate.kmipTag, InitialDate.encodingType);
  }

  @Override
  protected InitialDate.InitialDateBuilder createBuilder() {
    return InitialDate.builder();
  }

  @Override
  protected void setValue(InitialDate.InitialDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected InitialDate build(InitialDate.InitialDateBuilder builder) {
    return builder.build();
  }
}
