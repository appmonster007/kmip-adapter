package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.QString;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class QStringJsonSerializer extends KmipDataTypeJsonSerializer<QString> {

    @Override
    public void serialize(QString qString, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (qString == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!qString.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", qString.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(qString.getKmipTag());
        gen.writeStringField("type", qString.getEncodingType().getDescription());
        gen.writeObjectField("value", qString.getValue());
        gen.writeEndObject();
    }
}