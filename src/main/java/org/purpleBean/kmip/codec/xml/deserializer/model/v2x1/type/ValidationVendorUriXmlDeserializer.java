package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.ValidationVendorUri;

public class ValidationVendorUriXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidationVendorUri,
        ValidationVendorUri.ValidationVendorUriBuilder> {

  public ValidationVendorUriXmlDeserializer() {
    super(ValidationVendorUri.kmipTag, ValidationVendorUri.encodingType);
  }

  @Override
  protected ValidationVendorUri.ValidationVendorUriBuilder createBuilder() {
    return ValidationVendorUri.builder();
  }

  @Override
  protected void setValue(ValidationVendorUri.ValidationVendorUriBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ValidationVendorUri build(ValidationVendorUri.ValidationVendorUriBuilder builder) {
    return builder.build();
  }
}