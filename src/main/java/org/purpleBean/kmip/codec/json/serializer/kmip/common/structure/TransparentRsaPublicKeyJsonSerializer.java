package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.TransparentRsaPublicKey;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class TransparentRsaPublicKeyJsonSerializer extends KmipDataTypeJsonSerializer<TransparentRsaPublicKey> {

    @Override
    public void serialize(TransparentRsaPublicKey transparentRsaPublicKey, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (transparentRsaPublicKey == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!transparentRsaPublicKey.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s", transparentRsaPublicKey.getKmipTag().getDescription(), spec));
        }

        List<KmipDataType> fields = transparentRsaPublicKey.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupported()) {
                throw new UnsupportedEncodingException(String.format("%s in %s is not supported for KMIP spec %s",
                        field.getKmipTag().getDescription(), transparentRsaPublicKey.getKmipTag().getDescription(), spec));
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(transparentRsaPublicKey.getKmipTag());
        jsonGenerator.writeStringField("type", transparentRsaPublicKey.getEncodingType().getDescription());
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