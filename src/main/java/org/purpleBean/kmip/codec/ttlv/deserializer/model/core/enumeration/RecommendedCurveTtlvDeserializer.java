package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;

public class RecommendedCurveTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RecommendedCurve, Integer> {

    public RecommendedCurveTtlvDeserializer() {
        super(RecommendedCurve.kmipTag, RecommendedCurve.encodingType, Integer.class, value -> new RecommendedCurve(RecommendedCurve.fromValue(value)));
    }
}