package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.SubmissionDate;

public class SubmissionDateJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SubmissionDate, SubmissionDate.SubmissionDateBuilder> {

  public SubmissionDateJsonDeserializer() {
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