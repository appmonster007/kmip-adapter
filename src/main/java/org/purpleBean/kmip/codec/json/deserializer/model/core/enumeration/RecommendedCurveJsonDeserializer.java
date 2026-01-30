package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;

import java.io.IOException;

public class RecommendedCurveJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RecommendedCurve, RecommendedCurve.RecommendedCurveBuilder> {

    public RecommendedCurveJsonDeserializer() {
        super(RecommendedCurve.kmipTag, RecommendedCurve.encodingType);
    }

    @Override
    protected RecommendedCurve.RecommendedCurveBuilder createBuilder() {
        return RecommendedCurve.builder();
    }

    @Override
    protected void setValue(RecommendedCurve.RecommendedCurveBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(RecommendedCurve.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected RecommendedCurve build(RecommendedCurve.RecommendedCurveBuilder builder) {
        return builder.build();
    }
}
