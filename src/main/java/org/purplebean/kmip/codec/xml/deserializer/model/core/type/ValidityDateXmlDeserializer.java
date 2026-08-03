package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.ValidityDate;

/**
 * XML deserializer for {@link ValidityDate}.
 */
public class ValidityDateXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ValidityDate, ValidityDate.ValidityDateBuilder> {

  /**
   * Constructs a new {@link ValidityDateXmlDeserializer}.
   */
  public ValidityDateXmlDeserializer() {
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