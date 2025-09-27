package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.StringUtils;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.AttributeIndex;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Attribute Domain Tests")
class AttributeTest extends AbstractKmipStructureSuite<Attribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<Attribute> type() {
        return Attribute.class;
    }

    @Override
    protected Attribute createDefault() {
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME).build();
        return Attribute.of(activationDate);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // Add assertions for components if desired
        assertThat(values.get(0)).isInstanceOf(AttributeName.class);
        assertThat(((AttributeName) values.get(0)).getValue()).isEqualTo(StringUtils.covertPascalToTitleCase("Activation Date"));
        if (values.size() == 2) {
            assertThat(values.get(1)).isInstanceOf(AttributeValue.class);
        }
        if (values.size() > 2) {
            assertThat(values.get(1)).isInstanceOf(AttributeIndex.class);
            assertThat(values.get(2)).isInstanceOf(AttributeValue.class);
        }
    }
}
