package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SimpleRequestHeaderJsonSerializer extends KmipDataTypeJsonSerializer<SimpleRequestHeader> {

    @Override
    public void serialize(SimpleRequestHeader header, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();

        if (!header.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        List<KmipDataType> fields = header.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(
                        String.format("%s in %s is not supported for KMIP spec %s", field.getKmipTag().getDescription(), header.getKmipTag().getDescription(), spec)
                );
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(header.getKmipTag());
        jsonGenerator.writeStringField("type", header.getEncodingType().getDescription());
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeStartArray();

        if (header.getProtocolVersion() != null) {
            jsonGenerator.writeObject(header.getProtocolVersion());
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
