package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueDateTime> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueDateTimeBenchmarkSubject() throws Exception {
        AttributeValueDateTime attributeValueDateTime = AttributeValueDateTime.of(OffsetDateTime.now());
        initialize(attributeValueDateTime, AttributeValueDateTime.class);
    }

    @Override
    public String name() {
        return "AttributeValueDateTime";
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
