package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

public class OtpAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OtpAlgorithm, Integer> {

    public OtpAlgorithmTtlvSerializer() {
        super(OtpAlgorithm::getValue);
    }
}