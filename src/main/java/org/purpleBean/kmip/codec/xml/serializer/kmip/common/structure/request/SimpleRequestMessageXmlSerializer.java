package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SimpleRequestMessageXmlSerializer extends JsonSerializer<SimpleRequestMessage> {

    @Override
    public void serialize(SimpleRequestMessage message, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!message.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("SimpleRequestMessage not supported for spec " + spec);
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        xmlGen.writeStartObject(message.getKmipTag().getDescription());

        List<KmipDataType> values = message.getValues();

        for (KmipDataType value : values) {
            if (value != null) {
                String fieldName = value.getKmipTag().getDescription();
                serializers.defaultSerializeField(fieldName, value, gen);
            }
        }

        xmlGen.writeEndObject();
    }
}
