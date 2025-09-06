package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.State;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class StateXmlSerializer extends JsonSerializer<State> {

    @Override
    public void serialize(State state, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!state.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        ToXmlGenerator xmlGen = (ToXmlGenerator) gen;
        xmlGen.writeStartObject(state.getKmipTag().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", state.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("value", state.getDescription());
        xmlGen.writeEndObject();
    }
}
