package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultReason;

public class ResultReasonXmlDeserializer extends AbstractKmipXmlDeserializer<ResultReason, String> {

    public ResultReasonXmlDeserializer() {
        super(ResultReason.kmipTag, ResultReason.encodingType, String.class, value -> new ResultReason(ResultReason.fromName(value)));
    }
}