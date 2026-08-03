package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KeyValue;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyCompressionType;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.KeyWrappingData;
import org.purplebean.kmip.model.core.type.CryptographicLength;

/**
 * JSON deserializer for {@link KeyBlock}.
 */
public class KeyBlockJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<KeyBlock, KeyBlock.KeyBlockBuilder> {

  /**
   * Constructs a new {@link KeyBlockJsonDeserializer}.
   */
  public KeyBlockJsonDeserializer() {
    super(KeyBlock.kmipTag, KeyBlock.encodingType);
  }

  @Override
  protected KeyBlock.KeyBlockBuilder createBuilder() {
    return KeyBlock.builder();
  }

  @Override
  protected void setValue(KeyBlock.KeyBlockBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_FORMAT_TYPE -> {
        KeyFormatType keyFormatType = ctxt.readValue(p, KeyFormatType.class);
        builder.keyFormatType(keyFormatType);
        ctxt.setAttribute("keyFormatType", keyFormatType.getDescription());
      }
      case KmipTag.Standard.KEY_COMPRESSION_TYPE ->
          builder.keyCompressionType(ctxt.readValue(p, KeyCompressionType.class));
      case KmipTag.Standard.KEY_VALUE ->
          builder.keyValue((KeyValue) ctxt.readValue(p, KmipDataType.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
          builder.cryptographicAlgorithm(ctxt.readValue(p, CryptographicAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_LENGTH ->
          builder.cryptographicLength(ctxt.readValue(p, CryptographicLength.class));
      case KmipTag.Standard.KEY_WRAPPING_DATA ->
          builder.keyWrappingData(ctxt.readValue(p, KeyWrappingData.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected KeyBlock build(KeyBlock.KeyBlockBuilder builder) {
    return builder.build();
  }
}
