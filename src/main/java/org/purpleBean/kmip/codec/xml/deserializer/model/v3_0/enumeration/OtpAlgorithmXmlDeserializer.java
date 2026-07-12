package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3_0.enumeration.OtpAlgorithm;

import java.io.IOException;

public class OtpAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<OtpAlgorithm, OtpAlgorithm.OtpAlgorithmBuilder> {

    public OtpAlgorithmXmlDeserializer() {
        super(OtpAlgorithm.kmipTag, OtpAlgorithm.encodingType);
    }

    @Override
    protected OtpAlgorithm.OtpAlgorithmBuilder createBuilder() {
        return OtpAlgorithm.builder();
    }

    @Override
    protected void setValue(OtpAlgorithm.OtpAlgorithmBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(OtpAlgorithm.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected OtpAlgorithm build(OtpAlgorithm.OtpAlgorithmBuilder builder) {
        return builder.build();
    }
}