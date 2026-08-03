package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KeyValue;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.KeyWrappingData;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

public class KeyBlockXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<KeyBlock, KeyBlock.KeyBlockBuilder> {

  public KeyBlockXmlDeserializer() {
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