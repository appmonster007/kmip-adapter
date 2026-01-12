package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

public class OtpAlgorithmTtlvSerializer extends AbstractKmipTtlvSerializer<OtpAlgorithm, Integer> {

    public OtpAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}