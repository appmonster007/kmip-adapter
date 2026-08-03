package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;

/**
 * XML deserializer for {@link RevocationReasonCode}.
 */
public class RevocationReasonCodeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RevocationReasonCode,
        RevocationReasonCode.RevocationReasonCodeBuilder> {

  /**
   * Constructs a new {@link RevocationReasonCodeXmlDeserializer}.
   */
  public RevocationReasonCodeXmlDeserializer() {
    super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType);
  }

  @Override
  protected RevocationReasonCode.RevocationReasonCodeBuilder createBuilder() {
    return RevocationReasonCode.builder();
  }

  @Override
  protected void setValue(RevocationReasonCode.RevocationReasonCodeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(RevocationReasonCode.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected RevocationReasonCode build(RevocationReasonCode.RevocationReasonCodeBuilder builder) {
    return builder.build();
  }
}