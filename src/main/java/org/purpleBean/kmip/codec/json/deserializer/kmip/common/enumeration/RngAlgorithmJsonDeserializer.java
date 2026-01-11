package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

public class RngAlgorithmJsonDeserializer extends AbstractKmipJsonDeserializer<RngAlgorithm, String> {

    public RngAlgorithmJsonDeserializer() {
        super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType, String.class, value -> new RngAlgorithm(RngAlgorithm.fromName(value)));
    }
}