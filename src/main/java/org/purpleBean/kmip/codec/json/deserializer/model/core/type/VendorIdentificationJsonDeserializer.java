package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

public class VendorIdentificationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<VendorIdentification,
        VendorIdentification.VendorIdentificationBuilder> {

  public VendorIdentificationJsonDeserializer() {
    super(VendorIdentification.kmipTag, VendorIdentification.encodingType);
  }

  @Override
  protected VendorIdentification.VendorIdentificationBuilder createBuilder() {
    return VendorIdentification.builder();
  }

  @Override
  protected void setValue(VendorIdentification.VendorIdentificationBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected VendorIdentification build(VendorIdentification.VendorIdentificationBuilder builder) {
    return builder.build();
  }
}
