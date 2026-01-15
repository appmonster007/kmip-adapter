package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RngAlgorithm, String> {

    public RngAlgorithmJsonDeserializer() {
        super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType, String.class, value -> new RngAlgorithm(RngAlgorithm.fromName(value)));
    }
}