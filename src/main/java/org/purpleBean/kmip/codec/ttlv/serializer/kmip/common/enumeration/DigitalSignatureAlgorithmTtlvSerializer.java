package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmTtlvSerializer extends AbstractKmipTtlvSerializer<DigitalSignatureAlgorithm, Integer> {

    public DigitalSignatureAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}