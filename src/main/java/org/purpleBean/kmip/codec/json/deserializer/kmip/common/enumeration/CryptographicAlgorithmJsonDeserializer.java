package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmJsonDeserializer extends AbstractKmipJsonDeserializer<CryptographicAlgorithm, String> {

    public CryptographicAlgorithmJsonDeserializer() {
        super(CryptographicAlgorithm.kmipTag, CryptographicAlgorithm.encodingType, String.class, value -> new CryptographicAlgorithm(CryptographicAlgorithm.fromName(value)));
    }
}