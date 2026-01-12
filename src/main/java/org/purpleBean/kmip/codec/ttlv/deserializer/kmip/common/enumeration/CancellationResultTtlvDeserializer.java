package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

public class CancellationResultTtlvDeserializer extends AbstractKmipTtlvDeserializer<CancellationResult, Integer> {

    public CancellationResultTtlvDeserializer() {
        super(CancellationResult.kmipTag, CancellationResult.encodingType, Integer.class, value -> new CancellationResult(CancellationResult.fromValue(value)));
    }
}