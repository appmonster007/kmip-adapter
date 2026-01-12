package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmJsonSerializer extends AbstractKmipJsonSerializer<CryptographicAlgorithm, String> {

    public CryptographicAlgorithmJsonSerializer() {
        super(CryptographicAlgorithm::getDescription);
    }
}