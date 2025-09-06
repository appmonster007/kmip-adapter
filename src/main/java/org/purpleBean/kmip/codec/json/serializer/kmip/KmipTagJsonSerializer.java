package org.purpleBean.kmip.codec.json.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipTag;

import java.io.IOException;

public class KmipTagJsonSerializer extends JsonSerializer<KmipTag> {

    @Override
    public void serialize(KmipTag kmipTag, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JsonStreamContext context = jsonGenerator.getOutputContext();
        boolean isRoot = (context.getParent() == null);

        if (isRoot) {
            jsonGenerator.writeStartObject();
        }
        if (kmipTag.isCustom()) {
            jsonGenerator.writeStringField("tag", kmipTag.getTagHexString());
            jsonGenerator.writeStringField("name", kmipTag.getDescription());
        } else {
            jsonGenerator.writeStringField("tag", kmipTag.getDescription());
        }
        if (isRoot) {
            jsonGenerator.writeEndObject();
        }
    }
}
