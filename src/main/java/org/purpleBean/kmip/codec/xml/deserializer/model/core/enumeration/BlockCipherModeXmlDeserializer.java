package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;

public class BlockCipherModeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<BlockCipherMode, BlockCipherMode.BlockCipherModeBuilder> {

  public BlockCipherModeXmlDeserializer() {
    super(BlockCipherMode.kmipTag, BlockCipherMode.encodingType);
  }

  @Override
  protected BlockCipherMode.BlockCipherModeBuilder createBuilder() {
    return BlockCipherMode.builder();
  }

  @Override
  protected void setValue(BlockCipherMode.BlockCipherModeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(BlockCipherMode.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected BlockCipherMode build(BlockCipherMode.BlockCipherModeBuilder builder) {
    return builder.build();
  }
}