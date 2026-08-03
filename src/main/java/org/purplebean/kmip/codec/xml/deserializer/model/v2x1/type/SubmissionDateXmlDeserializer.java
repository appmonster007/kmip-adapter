package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.SubmissionDate;

/**
 * XML deserializer for {@link SubmissionDate}.
 */
public class SubmissionDateXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SubmissionDate, SubmissionDate.SubmissionDateBuilder> {

  /**
   * Constructs a new {@link SubmissionDateXmlDeserializer}.
   */
  public SubmissionDateXmlDeserializer() {
    super(SubmissionDate.kmipTag, SubmissionDate.encodingType);
  }

  @Override
  protected SubmissionDate.SubmissionDateBuilder createBuilder() {
    return SubmissionDate.builder();
  }

  @Override
  protected void setValue(SubmissionDate.SubmissionDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected SubmissionDate build(SubmissionDate.SubmissionDateBuilder builder) {
    return builder.build();
  }
}