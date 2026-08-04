package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.MaskGeneratorHashingAlgorithm;

/**
 * JSON deserializer for {@link MaskGeneratorHashingAlgorithm}.
 */
public class MaskGeneratorHashingAlgorithmJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MaskGeneratorHashingAlgorithm,
        MaskGeneratorHashingAlgorithm.MaskGeneratorHashingAlgorithmBuilder> {

  /**
   * Constructs a new {@link MaskGeneratorHashingAlgorithmJsonDeserializer}.
   */
  public MaskGeneratorHashingAlgorithmJsonDeserializer() {
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
