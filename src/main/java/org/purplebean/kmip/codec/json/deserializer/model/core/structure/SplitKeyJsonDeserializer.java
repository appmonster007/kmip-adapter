package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SplitKey;
import org.purplebean.kmip.model.core.type.KeyPartIdentifier;
import org.purplebean.kmip.model.core.type.PrimeFieldSize;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;

/**
 * JSON deserializer for {@link SplitKey}.
 */
public class SplitKeyJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<SplitKey, SplitKey.SplitKeyBuilder> {

  /**
   * Constructs a new {@link SplitKeyJsonDeserializer}.
   */
  public SplitKeyJsonDeserializer() {
    super(SplitKey.kmipTag, SplitKey.encodingType);
  }

  @Override
  protected SplitKey.SplitKeyBuilder createBuilder() {
    return SplitKey.builder();
  }

  @Override
  protected void setValue(SplitKey.SplitKeyBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.SPLIT_KEY_PARTS ->
          builder.splitKeyParts(ctxt.readValue(p, SplitKeyParts.class));
      case KmipTag.Standard.KEY_PART_IDENTIFIER ->
          builder.keyPartIdentifier(ctxt.readValue(p, KeyPartIdentifier.class));
      case KmipTag.Standard.SPLIT_KEY_THRESHOLD ->
          builder.splitKeyThreshold(ctxt.readValue(p, SplitKeyThreshold.class));
      case KmipTag.Standard.SPLIT_KEY_METHOD ->
          builder.splitKeyMethod(ctxt.readValue(p, SplitKeyMethod.class));
      case KmipTag.Standard.PRIME_FIELD_SIZE ->
          builder.primeFieldSize(ctxt.readValue(p, PrimeFieldSize.class));
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SplitKey build(SplitKey.SplitKeyBuilder builder) {
    return builder.build();
  }
}