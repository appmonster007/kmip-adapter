package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RngAlgorithm, Integer> {

    public RngAlgorithmTtlvSerializer() {
        super(RngAlgorithm::getValue);
    }
}