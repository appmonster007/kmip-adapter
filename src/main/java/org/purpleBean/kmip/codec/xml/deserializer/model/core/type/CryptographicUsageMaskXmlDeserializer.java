package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;

public class CryptographicUsageMaskXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CryptographicUsageMask,
        CryptographicUsageMask.CryptographicUsageMaskBuilder> {

  public CryptographicUsageMaskXmlDeserializer() {
    super(CryptographicUsageMask.kmipTag, CryptographicUsageMask.encodingType);
  }

  @Override
  protected CryptographicUsageMask.CryptographicUsageMaskBuilder createBuilder() {
    return CryptographicUsageMask.builder();
  }

  @Override
  protected void setValue(CryptographicUsageMask.CryptographicUsageMaskBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    String maskString = ctxt.readValue(p, String.class);
    CryptographicUsageMask mask = CryptographicUsageMask.fromMaskString(maskString);
    builder.value(mask.getValue());
  }

  @Override
  protected CryptographicUsageMask build(
      CryptographicUsageMask.CryptographicUsageMaskBuilder builder) {
    return builder.build();
  }
}