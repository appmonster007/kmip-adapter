package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvXmlDeserializer extends AbstractKmipXmlDeserializer<RandomIv, Boolean> {

    public RandomIvXmlDeserializer() {
        super(RandomIv.kmipTag, RandomIv.encodingType, Boolean.class, value -> RandomIv.builder().value(value).build());
    }
}