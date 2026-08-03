package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.DerivationData;

/**
 * XML deserializer for {@link DerivationData}.
 */
public class DerivationDataXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DerivationData, DerivationData.DerivationDataBuilder> {

  /**
   * Constructs a new {@link DerivationDataXmlDeserializer}.
   */
  public DerivationDataXmlDeserializer() {
    super(DerivationData.kmipTag, DerivationData.encodingType);
  }

  @Override
  protected DerivationData.DerivationDataBuilder createBuilder() {
    return DerivationData.builder();
  }

  @Override
  protected void setValue(DerivationData.DerivationDataBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected DerivationData build(DerivationData.DerivationDataBuilder builder) {
    return builder.build();
  }
}