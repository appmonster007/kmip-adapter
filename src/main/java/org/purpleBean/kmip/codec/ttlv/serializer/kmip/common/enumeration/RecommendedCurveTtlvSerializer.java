package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RecommendedCurve, Integer> {

    public RecommendedCurveTtlvSerializer() {
        super(RecommendedCurve::getValue);
    }
}