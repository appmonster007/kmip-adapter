package org.purplebean.kmip.codec.json.serializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purplebean.kmip.model.v3x0.type.PasswordSaltAlgorithm;

public class PasswordSaltAlgorithmJsonSerializer
    extends AbstractKmipDataTypeJsonSerializer<PasswordSaltAlgorithm> {

  @Override
  public void serialize(PasswordSaltAlgorithm obj, JsonGenerator gen,
                        SerializerProvider serializers) throws IOException {
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
    String description = PasswordSaltAlgorithm
        .fromValue((Integer) obj.getValue())
        .getDescription();
    serializers.defaultSerializeValue(description, gen);
    gen.writeEndObject();
  }
}
