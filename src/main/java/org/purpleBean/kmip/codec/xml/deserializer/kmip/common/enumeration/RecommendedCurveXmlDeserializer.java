package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveXmlDeserializer extends AbstractKmipXmlDeserializer<RecommendedCurve, String> {

    public RecommendedCurveXmlDeserializer() {
        super(RecommendedCurve.kmipTag, RecommendedCurve.encodingType, String.class, value -> new RecommendedCurve(RecommendedCurve.fromName(value)));
    }
}