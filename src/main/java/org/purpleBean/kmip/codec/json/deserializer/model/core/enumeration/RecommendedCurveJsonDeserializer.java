package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;

public class RecommendedCurveJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RecommendedCurve, String> {

    public RecommendedCurveJsonDeserializer() {
        super(RecommendedCurve.kmipTag, RecommendedCurve.encodingType, String.class, value -> new RecommendedCurve(RecommendedCurve.fromName(value)));
    }
}