package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.TransparentRsaPrivateKey;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class TransparentRsaPrivateKeyJsonSerializer extends KmipDataTypeJsonSerializer<TransparentRsaPrivateKey> {

    @Override
    public void serialize(TransparentRsaPrivateKey transparentRsaPrivateKey, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (transparentRsaPrivateKey == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!transparentRsaPrivateKey.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s", transparentRsaPrivateKey.getKmipTag().getDescription(), spec));
        }

        List<KmipDataType> fields = transparentRsaPrivateKey.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupported()) {
                throw new UnsupportedEncodingException(String.format("%s in %s is not supported for KMIP spec %s",
                        field.getKmipTag().getDescription(), transparentRsaPrivateKey.getKmipTag().getDescription(), spec));
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(transparentRsaPrivateKey.getKmipTag());
        jsonGenerator.writeStringField("type", transparentRsaPrivateKey.getEncodingType().getDescription());
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeStartArray();
        for (KmipDataType fieldValue : fields) {
            if (fieldValue != null) {
                jsonGenerator.writeObject(fieldValue);
            }
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}