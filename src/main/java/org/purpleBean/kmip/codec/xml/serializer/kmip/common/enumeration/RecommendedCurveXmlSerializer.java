package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveXmlSerializer extends AbstractKmipXmlSerializer<RecommendedCurve, String> {

    public RecommendedCurveXmlSerializer() {
        super(RecommendedCurve::getDescription);
    }
}