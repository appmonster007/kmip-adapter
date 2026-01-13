package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RecommendedCurve, Integer> {

    public RecommendedCurveTtlvDeserializer() {
        super(RecommendedCurve.kmipTag, RecommendedCurve.encodingType, Integer.class, value -> new RecommendedCurve(RecommendedCurve.fromValue(value)));
    }
}