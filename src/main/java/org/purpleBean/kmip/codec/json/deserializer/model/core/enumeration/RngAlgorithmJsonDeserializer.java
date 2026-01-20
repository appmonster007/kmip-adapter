package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RngAlgorithm, String> {

    public RngAlgorithmJsonDeserializer() {
        super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType, String.class, value -> RngAlgorithm.fromName(value).inst());
    }
}