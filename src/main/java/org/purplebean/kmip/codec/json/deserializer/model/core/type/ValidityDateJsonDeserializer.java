package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ValidityDate;

/**
 * JSON deserializer for {@link ValidityDate}.
 */
public class ValidityDateJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ValidityDate, ValidityDate.ValidityDateBuilder> {

  /**
   * Constructs a new {@link ValidityDateJsonDeserializer}.
   */
  public ValidityDateJsonDeserializer() {
    super(ValidityDate.kmipTag, ValidityDate.encodingType);
  }

  @Override
  protected ValidityDate.ValidityDateBuilder createBuilder() {
    return ValidityDate.builder();
  }

  @Override
  protected void setValue(ValidityDate.ValidityDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected ValidityDate build(ValidityDate.ValidityDateBuilder builder) {
    return builder.build();
  }
}
