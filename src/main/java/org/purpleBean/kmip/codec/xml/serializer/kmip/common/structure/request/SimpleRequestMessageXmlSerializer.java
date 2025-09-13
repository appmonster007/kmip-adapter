package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SimpleRequestMessageXmlSerializer extends JsonSerializer<SimpleRequestMessage> {

    @Override
    public void serialize(SimpleRequestMessage message, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!message.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("SimpleRequestMessage not supported for spec " + spec);
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = message.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(message);

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
