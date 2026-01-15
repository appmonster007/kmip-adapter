package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;

public class CancellationResultTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CancellationResult, Integer> {

    public CancellationResultTtlvDeserializer() {
        super(CancellationResult.kmipTag, CancellationResult.encodingType, Integer.class, value -> new CancellationResult(CancellationResult.fromValue(value)));
    }
}