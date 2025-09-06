package org.purpleBean.kmip.codec.json.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.ProtocolVersion;

import java.io.IOException;
import java.util.List;

public class ProtocolVersionJsonSerializer extends KmipDataTypeJsonSerializer<ProtocolVersion> {

    @Override
    public void serialize(ProtocolVersion protocolVersion, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<KmipDataType> values = protocolVersion.getValues();

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(protocolVersion.getKmipTag());
        jsonGenerator.writeStringField("type", protocolVersion.getEncodingType().getDescription());
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeStartArray();
        for (Object v : values) {
            jsonGenerator.writeObject(v);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
