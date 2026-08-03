package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.ProtectionStorageMask;

public class ProtectionStorageMaskXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ProtectionStorageMask,
        ProtectionStorageMask.ProtectionStorageMaskBuilder> {

  public ProtectionStorageMaskXmlDeserializer() {
    super(ProtectionStorageMask.kmipTag, ProtectionStorageMask.encodingType);
  }

  @Override
  protected ProtectionStorageMask.ProtectionStorageMaskBuilder createBuilder() {
    return ProtectionStorageMask.builder();
  }

  @Override
  protected void setValue(ProtectionStorageMask.ProtectionStorageMaskBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    String maskString = ctxt.readValue(p, String.class);
    ProtectionStorageMask mask = ProtectionStorageMask.fromMaskString(maskString);
    builder.value(mask.getValue());
  }

  @Override
  protected ProtectionStorageMask build(
      ProtectionStorageMask.ProtectionStorageMaskBuilder builder) {
    return builder.build();
  }
}