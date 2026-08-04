package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.MaskGeneratorHashingAlgorithm;

/**
 * XML deserializer for {@link MaskGeneratorHashingAlgorithm}.
 */
public class MaskGeneratorHashingAlgorithmXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<MaskGeneratorHashingAlgorithm,
        MaskGeneratorHashingAlgorithm.MaskGeneratorHashingAlgorithmBuilder> {

  /**
   * Constructs a new {@link MaskGeneratorHashingAlgorithmXmlDeserializer}.
   */
  public MaskGeneratorHashingAlgorithmXmlDeserializer() {
    super(MaskGeneratorHashingAlgorithm.kmipTag, MaskGeneratorHashingAlgorithm.encodingType);
  }

  @Override
  protected MaskGeneratorHashingAlgorithm.MaskGeneratorHashingAlgorithmBuilder createBuilder() {
    return MaskGeneratorHashingAlgorithm.builder();
  }

  @Override
  protected void setValue(
      MaskGeneratorHashingAlgorithm.MaskGeneratorHashingAlgorithmBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(MaskGeneratorHashingAlgorithm.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected MaskGeneratorHashingAlgorithm build(
      MaskGeneratorHashingAlgorithm.MaskGeneratorHashingAlgorithmBuilder builder) {
    return builder.build();
  }
}
