package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SimpleRequestMessageJsonSerializer extends KmipDataTypeJsonSerializer<SimpleRequestMessage> {

    @Override
    public void serialize(SimpleRequestMessage message, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();

        if (!message.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        List<KmipDataType> fields = message.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(
                        String.format("%s in %s is not supported for KMIP spec %s", field.getKmipTag().getDescription(), message.getKmipTag().getDescription(), spec)
                );
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(message.getKmipTag());
        jsonGenerator.writeStringField("type", message.getEncodingType().getDescription());
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeStartArray();
        jsonGenerator.writeObject(message.getRequestHeader());
        for (Object item : message.getRequestBatchItems()) {
            if (item != null) {
                jsonGenerator.writeObject(item);
            }
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
