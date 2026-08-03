package org.purpleBean.kmip.codec.json.serializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.v3_0.type.OtpDigest;

public class OtpDigestJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OtpDigest> {

  @Override
  public void serialize(OtpDigest obj, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    if (obj == null) {
      return;
    }
    if (!obj.isSupported()) {
      throw new UnsupportedEncodingException(
          String.format("%s is not supported for KMIP spec %s", obj
              .getKmipTag()
              .getDescription(), KmipContext.getSpec()));
    }
    gen.writeStartObject();
    gen.writeStringField("tag", obj
        .getKmipTag()
        .getDescription());
    gen.writeStringField("type", obj
        .getEncodingType()
        .getDescription());
    gen.writeFieldName("value");
    String description = OtpDigest
        .fromValue((Integer) obj.getValue())
        .getDescription();
    serializers.defaultSerializeValue(description, gen);
    gen.writeEndObject();
  }
}
