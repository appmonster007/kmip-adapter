package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

public class CancellationResultXmlDeserializer extends AbstractKmipXmlDeserializer<CancellationResult, String> {

    public CancellationResultXmlDeserializer() {
        super(CancellationResult.kmipTag, CancellationResult.encodingType, String.class, value -> new CancellationResult(CancellationResult.fromName(value)));
    }
}