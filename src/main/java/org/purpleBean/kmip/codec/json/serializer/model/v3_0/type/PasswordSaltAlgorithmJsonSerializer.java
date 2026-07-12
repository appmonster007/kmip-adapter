package org.purpleBean.kmip.codec.json.serializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.v3_0.type.PasswordSaltAlgorithm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PasswordSaltAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PasswordSaltAlgorithm> {

    @Override
    public void serialize(PasswordSaltAlgorithm obj, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (obj == null) return;
        if (!obj.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", obj.getKmipTag().getDescription(), KmipContext.getSpec()));
        }
        gen.writeStartObject();
        gen.writeStringField("tag", obj.getKmipTag().getDescription());
        gen.writeStringField("type", obj.getEncodingType().getDescription());
        gen.writeFieldName("value");
        String description = PasswordSaltAlgorithm.fromValue((Integer) obj.getValue()).getDescription();
        serializers.defaultSerializeValue(description, gen);
        gen.writeEndObject();
    }
}
