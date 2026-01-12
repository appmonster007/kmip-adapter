package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveJsonSerializer extends AbstractKmipJsonSerializer<RecommendedCurve, String> {

    public RecommendedCurveJsonSerializer() {
        super(RecommendedCurve::getDescription);
    }
}