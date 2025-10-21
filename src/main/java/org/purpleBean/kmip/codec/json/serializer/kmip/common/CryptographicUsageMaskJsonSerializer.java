package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.List;

public class CryptographicUsageMaskJsonSerializer extends KmipDataTypeJsonSerializer<CryptographicUsageMask> {

    @Override
    public void serialize(CryptographicUsageMask cryptographicUsageMask, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (cryptographicUsageMask == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!cryptographicUsageMask.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", cryptographicUsageMask.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(cryptographicUsageMask.getKmipTag());
        gen.writeStringField("type", cryptographicUsageMask.getEncodingType().getDescription());
        gen.writeObjectField("value", cryptographicUsageMask.getValue());
        gen.writeEndObject();
    }
}
