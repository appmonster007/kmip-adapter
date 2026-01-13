package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RandomIv, Boolean> {

    public RandomIvXmlDeserializer() {
        super(RandomIv.kmipTag, RandomIv.encodingType, Boolean.class, value -> RandomIv.builder().value(value).build());
    }
}