package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CryptographicAlgorithm, String> {

    public CryptographicAlgorithmXmlDeserializer() {
        super(CryptographicAlgorithm.kmipTag, CryptographicAlgorithm.encodingType, String.class, value -> new CryptographicAlgorithm(CryptographicAlgorithm.fromName(value)));
    }
}