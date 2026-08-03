package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;

/**
 * JSON deserializer for {@link CryptographicUsageMask}.
 */
public class CryptographicUsageMaskJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CryptographicUsageMask,
        CryptographicUsageMask.CryptographicUsageMaskBuilder> {

  /**
   * Constructs a new {@link CryptographicUsageMaskJsonDeserializer}.
   */
  public CryptographicUsageMaskJsonDeserializer() {
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
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected CryptographicUsageMask build(
      CryptographicUsageMask.CryptographicUsageMaskBuilder builder) {
    return builder.build();
  }
}
