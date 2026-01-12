package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveTtlvDeserializer extends AbstractKmipTtlvDeserializer<RecommendedCurve, Integer> {

    public RecommendedCurveTtlvDeserializer() {
        super(RecommendedCurve.kmipTag, RecommendedCurve.encodingType, Integer.class, value -> new RecommendedCurve(RecommendedCurve.fromValue(value)));
    }
}