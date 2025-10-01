package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class AttributeValueBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue> {

    public AttributeValueBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        AttributeValue attributeValue = AttributeValue.of(fixed);
        initialize(attributeValue, AttributeValue.class);
    }

    @Override
    public String name() {
        return "AttributeValue";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
