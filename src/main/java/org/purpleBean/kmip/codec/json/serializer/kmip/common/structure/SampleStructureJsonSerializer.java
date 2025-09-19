package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SampleStructureJsonSerializer extends KmipDataTypeJsonSerializer<SampleStructure> {

    @Override
    public void serialize(SampleStructure sampleStructure, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (sampleStructure == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!sampleStructure.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s",
                            sampleStructure.getKmipTag().getDescription(), spec)
            );
        }

        List<KmipDataType> fields = sampleStructure.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(
                        String.format("%s in %s is not supported for KMIP spec %s",
                                field.getKmipTag().getDescription(),
                                sampleStructure.getKmipTag().getDescription(),
                                spec)
                );
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(sampleStructure.getKmipTag());
        jsonGenerator.writeStringField("type", sampleStructure.getEncodingType().getDescription());
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
