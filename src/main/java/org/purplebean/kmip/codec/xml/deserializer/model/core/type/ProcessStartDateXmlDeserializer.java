package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.ProcessStartDate;

/**
 * XML deserializer for {@link ProcessStartDate}.
 */
public class ProcessStartDateXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ProcessStartDate,
        ProcessStartDate.ProcessStartDateBuilder> {

  /**
   * Constructs a new {@link ProcessStartDateXmlDeserializer}.
   */
  public ProcessStartDateXmlDeserializer() {
    super(ProcessStartDate.kmipTag, ProcessStartDate.encodingType);
  }

  @Override
  protected ProcessStartDate.ProcessStartDateBuilder createBuilder() {
    return ProcessStartDate.builder();
  }

  @Override
  protected void setValue(ProcessStartDate.ProcessStartDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected ProcessStartDate build(ProcessStartDate.ProcessStartDateBuilder builder) {
    return builder.build();
  }
}