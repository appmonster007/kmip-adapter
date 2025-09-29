package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SimpleRequestBatchItemJsonSerializer extends KmipDataTypeJsonSerializer<SimpleRequestBatchItem> {

    @Override
    public void serialize(SimpleRequestBatchItem batchItem, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();

        if (!batchItem.isSupported()) {
            throw new UnsupportedEncodingException();
        }

        List<KmipDataType> fields = batchItem.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupported()) {
                throw new UnsupportedEncodingException(
                        String.format("%s in %s is not supported for KMIP spec %s", field.getKmipTag().getDescription(), batchItem.getKmipTag().getDescription(), spec)
                );
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(batchItem.getKmipTag());
        jsonGenerator.writeStringField("type", batchItem.getEncodingType().getDescription());
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeStartArray();
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
